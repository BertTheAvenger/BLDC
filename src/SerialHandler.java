import Serial.RXCommand;
import Serial.RXCommands.RXADDSHORTS;
import Serial.RXCommands.RXHANDSHAKE;
import Serial.TXCommand;
import com.fazecast.jSerialComm.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

class SerialHandler {
    private static final int[] baudrates = {9600, 115200};
    private static SerialPort port;
    private static boolean serialStatus = false;

    private static final RXCommand[] rxCommandRegister = {null, new RXHANDSHAKE(), null ,new RXADDSHORTS()}; //Register of RX commands. Command byte is index.


    private static boolean recievingPacket = false;
    private static byte[] packet;
    private static int packetIndex = 0;
    private static int packetLength = 0;

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
            packetLength = rxCommandRegister[b].getLength(); //Get corrosponding command object length.
            packet = new byte[packetLength]; //Init packet array with this length

            packet[packetIndex] = inputByte; //Record first byte.
            packetIndex++;

            if(packetLength == 1) //First byte is also last, check.
            {

                recievingPacket = false;
                parseCommand(packet); //Parse commands,
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
            parseCommand(packet); //Parse commands,
        }
        //Otherwise, not part of packet. Just let it go.

    }

    static void parseCommand(byte[] packet)
    {
        RXCommand command = rxCommandRegister[packet[0]]; //Get relevant command.
        command.setBytes(packet);
        System.out.println("RECIEVED: " + command.toReadableString());
        //System.out.println("BYTES RECIEVED: " + Arrays.toString(packet));

    }

    static void sendSerialCommand(TXCommand command){
        System.out.println("SENDING: " + command.toReadableString());
        byte[] outBuffer = new byte[command.getLength() + 1];

        outBuffer[0] = 0;
        System.arraycopy(command.getByteArray(), 0, outBuffer, 1, command.getLength()); //Append command to end of buffer

        //System.out.println("BYTES SENT:" + Arrays.toString(outBuffer));
        port.writeBytes(outBuffer, outBuffer.length);

    }
}
