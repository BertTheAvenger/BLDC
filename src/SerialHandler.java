import Serial.RXCommand;
import Serial.RXCommandEnums;
import Serial.RXCommands.RXACK;
import Serial.RXCommands.RXADDSHORTS;
import Serial.RXCommands.RXNUMBERTEST;
import Serial.TXCommand;
import com.fazecast.jSerialComm.*;

import java.util.*;

class SerialHandler {
    private static final int[] baudrates = {9600, 115200};
    private static SerialPort port;
    private static boolean serialStatus = false;

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static Map<RXCommandEnums, RXCommand> rxCommandRegister;
    static { //Populate RXCommand register.
        rxCommandRegister = new HashMap<>();
        rxCommandRegister.put(RXCommandEnums.ACK, new RXACK());
        rxCommandRegister.put(RXCommandEnums.ADDSHORTS, new RXADDSHORTS());
        rxCommandRegister.put(RXCommandEnums.NUMBERTEST, new RXNUMBERTEST());
    }


    private static boolean recievingPacket = false;
    private static byte[] packet;
    private static int packetIndex = 0;
    private static int packetLength = 0;

    private static boolean waitingOnAck = false;

    private static List<SerialEventListener> listeners = new ArrayList<>();

    static String[] getPorts()
    {
        return Arrays.stream(SerialPort.getCommPorts()).map(SerialPort::getSystemPortName).toArray(String[]::new); //Get ports objects, that arr to Stream, each then converted to string by getSystemPortName, then back to string arr.
    }

    static int[] getAvailableBaudrates(){return baudrates;}

    static boolean openSerialConnection(String portName, int baudRate)
    {
        port = SerialPort.getCommPort(portName);
        if(port.openPort()) {
            serialStatus = true;
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

    static void buildPacket(byte inputByte)
    {
        //System.out.println("Read byte: " + inputByte);
        int b = Byte.toUnsignedInt(inputByte); //Allows math 0-255

        //System.out.println(b);
        if(!recievingPacket && b == 0) //Not recieving packet & new packet command recieved
        {
            recievingPacket = true;
            packetIndex = 0;
            packetLength = 0;

        }
        else if(recievingPacket && packetIndex == 0) //Catch second overall byte, first byte which is recorded, which is command byte.
        {
            packetLength = rxCommandRegister.get(RXCommandEnums.fromInt(b)).getLength(); //Get corrosponding command object length.
            packet = new byte[packetLength]; //Init packet array with this length

            packet[packetIndex] = inputByte; //Record first byte.
            packetIndex++;

            if(packetLength == 1) //First byte is also last, check.
            {

                recievingPacket = false;
                recieveSerialCommand(packet); //Parse commands,
            }
        }
        else if(recievingPacket && packetIndex < packetLength - 1) //If recieving and in range, just keep adding the bytes.
        {
            packet[packetIndex] = inputByte; //Record first byte.
            packetIndex++;
        }
        else if(recievingPacket)//Recieving last byte, Packet is finished, analyze.
        {
            packet[packetIndex] = inputByte; //Record first byte.
            recievingPacket = false;
            recieveSerialCommand(packet); //Parse commands,
        }
        //Otherwise, not part of packet. Just let it go.

    }

    static void recieveSerialCommand(byte[] packet)
    {
        RXCommand command = rxCommandRegister.get(RXCommandEnums.fromByte(packet[0])); //Get relevant command.
        command.setBytes(packet);
        System.out.println("RECIEVED: " + command.toReadableString());
        eventTriggered(command);

    }

    static void sendSerialCommand(TXCommand command){
        if(serialStatus && port.isOpen())
        {
            System.out.println("SENDING: " + command.toReadableString());
            byte[] outBuffer = new byte[command.getLength() + 1];

            outBuffer[0] = 0;
            System.arraycopy(command.getByteArray(), 0, outBuffer, 1, command.getLength()); //Append command to end of buffer

            port.writeBytes(outBuffer, outBuffer.length);
        }
        else
        {
            MvcController.serialDisconnectActionPreformed();
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void addListener(SerialEventListener listener)
    {
        listeners.add(listener);
    }

    private static void eventTriggered(RXCommand command)
    {
        for(SerialEventListener l : listeners)
        {
            l.serialEvent(command);
        }
    }

}

