import Serial.RXCommand;
import Serial.RXEnums;
import Serial.TXCommand;
import com.fazecast.jSerialComm.*;

import java.util.*;

class SerialHandler {
    private static final int[] baudrates = {9600, 115200};
    private static SerialPort port;
    private static boolean serialStatus = false;
    private static ArrayList<TXCommand> sendBuffer;

    private static RXCommand incomingCommand;

    private static boolean recievingPacket = false;
    private static byte[] packet;
    private static int packetIndex = 0;
    private static int packetLength = 0;

    private static RXEnums ackCommand;
    private static Thread txTimeoutWatchdog;

    private static Runnable rxTimeoutWatchdog;

    private static List<SerialEventListener> listeners = new ArrayList<>();

    static String[] getPorts()
    {
        return Arrays.stream(SerialPort.getCommPorts()).map(SerialPort::getSystemPortName).toArray(String[]::new); //Get ports objects, that arr to Stream, each then converted to string by getSystemPortName, then back to string arr.
    }

    static int[] getAvailableBaudrates(){return baudrates;}

    static boolean openSerialConnection(String portName, int baudRate)
    {
        port = SerialPort.getCommPort(portName);
        port.setBaudRate(baudRate);
        if(port.openPort()) {
            serialStatus = true;
            sendBuffer = new ArrayList<>();
            port.addDataListener(new SerialPortDataListener() {
                @Override
                public int getListeningEvents() { return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; }
                @Override
                public void serialEvent(SerialPortEvent event)
                {
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) return;
                    byte[] newData = new byte[port.bytesAvailable()];
                    port.readBytes(newData, newData.length);
                    for(byte b : newData) {
                        //System.out.println("read byte:" + b);
                        buildPacket(b);
                    }
                }
            });



            return true;
        }
        else
        {
            return false;
        }
    }

    static void closeSerialConnection()
    {
        if(serialStatus)
        {
            serialStatus = false;
            port.removeDataListener();
            port.closePort();
        }
    }

    private static void buildPacket(byte b)
    {
        //System.out.println("Read byte: " + inputByte);
        //System.out.println(b);
        if(!recievingPacket && b == 0) //Not recieving packet & new packet command recieved
        {
            recievingPacket = true;
            packetIndex = 0;
            packetLength = 0;

        }
        else if(recievingPacket && packetIndex == 0) //Catch second overall byte, first byte which is recorded, which is command byte.
        {
            incomingCommand = RXEnums.byteToCommand(b);
            if(incomingCommand != null) //Make sure command was found.
            {
                packetLength = incomingCommand.getCommandLength(); //Get corrosponding command object length.
                packet = new byte[packetLength]; //Init packet array with this length

                packet[packetIndex] = b; //Record first byte.
                packetIndex++;

                if(packetLength == 1) //First byte is also last, check.
                {

                    recievingPacket = false;
                    recieveSerialCommand(packet); //Parse commands,
                }
            }
            else //Command for byte not found:
            {
                System.out.println("PACKET RECEIVE ERROR - COMMAND NOT FOUND!");
                recievingPacket = false; //Cancel packet.
            }

        }
        else if(recievingPacket && packetIndex < packetLength - 1) //If recieving and in range, just keep adding the bytes.
        {
            packet[packetIndex] = b; //Record first byte.
            packetIndex++;
        }
        else if(recievingPacket)//Recieving last byte, Packet is finished, analyze.
        {
            packet[packetIndex] = b; //Record first byte.
            recievingPacket = false;
            recieveSerialCommand(packet); //Parse commands,
        }
        //Otherwise, not part of packet. Just let it go.

    }

    private static void recieveSerialCommand(byte[] packet)
    {
        incomingCommand.parseBytes(packet);
        System.out.println("RECIEVED: " + incomingCommand.toReadableString());
        eventTriggered(incomingCommand);

    }

    static void sendSerialCommand(TXCommand command){
        if(serialStatus)
        {
            sendBuffer.add(command); //Add command to buffer to send
            attemptNextSend(); //Attempt to send. If waiting on ACK might be delayed
        }
    }

    private static void attemptNextSend()
    {
        if(serialStatus && port.isOpen())
        {
            if(ackCommand == null && sendBuffer.size() > 0) //If ACK command is nonexistant(Not waiting for one) and buffer is not empty, send.
            {
                TXCommand command = sendBuffer.get(0); //Get oldest command in buffer
                sendBuffer.remove(0); //Remove it, as it will be sent.

                ackCommand = command.getAckCommand(); //get required ACK command
                if(ackCommand != null && command.getAckTimeout() != 0) //If ACK command exists and timeout exists, start a timeout watchdog.
                {
                    txTimeoutWatchdog = new Thread() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(command.getAckTimeout());
                                txCommandTimedOut();
                            }
                            catch (Exception ignore){} //Ignore interrupted thread.
                        }
                    };
                    txTimeoutWatchdog.start();
                }

                System.out.println("SENDING: " + command.toReadableString());
                byte[] outBuffer = new byte[command.getCommandLength() + 1];

                outBuffer[0] = 0;
                System.arraycopy(command.getByteArray(), 0, outBuffer, 1, command.getCommandLength()); //Append command to end of buffer
                //System.out.println(Arrays.toString(outBuffer));

                port.writeBytes(outBuffer, outBuffer.length);
            }

        }
        else
        {
            MvcController.serialDisconnectActionPreformed();
        }
    }

    private static void txCommandTimedOut()
    {
        System.out.println("TX TIMEOUT!");
        ackCommand = null;
        txTimeoutWatchdog = null;
        attemptNextSend();
    }


    static void addListener(SerialEventListener listener)
    {
        listeners.add(listener);
    }

    private static void eventTriggered(RXCommand command)
    {
        if(command.getCommandEnum() == ackCommand) { //Ack received
            //System.out.println("ACK RECEIVED!");
            txTimeoutWatchdog.interrupt();
            txTimeoutWatchdog = null;
            ackCommand = null; //Not waiting anymore
            attemptNextSend(); //Try to send next in buffer.
        }
        for(SerialEventListener l : listeners)
        {
            l.serialEvent(command);
        }
    }

}

