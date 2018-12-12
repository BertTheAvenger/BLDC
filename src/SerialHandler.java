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
    private static ArrayList<TXCommand> sendBuffer = new ArrayList<>();


    private static boolean recievingPacket = false;
    private static int packetIndex = 0;
    private static int packetLength = 0;

    private static ArrayList<RXCommand> incomingCommands = new ArrayList<>();

    private static ArrayList<Byte> incomingPacket = new ArrayList<>();

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
                    if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
                        return;
                    byte[] newData = new byte[port.bytesAvailable()];

                    int numRead = port.readBytes(newData, newData.length);
                    for(byte b : newData)
                    {
                        buildPacket(b);
                    }
                    //System.out.println("Read " + numRead + " bytes.");
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
            incomingPacket.add(inputByte);
        }
        else if(recievingPacket && incomingPacket.size() == 1) //Catch second byte, which is message length.
        {
            packetLength = b;
            incomingPacket.add(inputByte);
        }
        else if(recievingPacket && incomingPacket.size() < packetLength-1) //If recieving and in range, just keep adding the bytes.
        {
            incomingPacket.add(inputByte);
        }
        else if(recievingPacket)//Packet is finished, analyze.
        {
            incomingPacket.add(inputByte);
            recievingPacket = false;
            parseCommands(); //Parse commands,
            incomingPacket = new ArrayList<>(); //Reset arrList for next packet.
        }
        //Otherwise, not part of packet. Just let it go.
    }

    static void parseCommands()
    {
        Byte[] byteArr = new Byte[incomingPacket.size()];
        byteArr = incomingPacket.toArray(byteArr); //Convert complete incoming Arraylist of bytes into actual array.
        incomingCommands = new ArrayList<>(); //Reset commands arrlist before parsing byte array.
        System.out.println("BYTES IN:" + incomingPacket);
        for(int i = 2; i < byteArr.length; i = i) //Start at byte 2, skip initiator byte and length byte in packet.
        {
            switch (Byte.toUnsignedInt(byteArr[i])) //Command switch
            //Increment i by cmd length - 1 (exclude command byte) of parsed commands to only read command bytes
            // in switch statement.
            {
                case 1 : //Handshake command
                    RXHANDSHAKE cmd1 = new RXHANDSHAKE(); //New
                    incomingCommands.add(cmd1);
                    i += cmd1.getLength();
                    break;
                case 3 : //ADDSHORTS command
                    RXADDSHORTS cmd3 = new RXADDSHORTS();
                    cmd3.setBytes(new byte[]{byteArr[i], byteArr[i+1], byteArr[i+2], byteArr[i+3], byteArr[i+4]}); //5 bytes
                    incomingCommands.add(cmd3);
                    SerialActions.RXADDSHORTS(cmd3);
                    i += cmd3.getLength();
                    break;
            }
        }
        for(RXCommand c : incomingCommands)
        {
            System.out.println("RECIEVED COMMAND:" + c.toReadableString());
        }
    }

    static void addCommandToBuffer(TXCommand TXCommand)
    {
        //System.out.println(TXCommand);
        sendBuffer.add(TXCommand);
    }

    static void sendSerialPacket(){
        ArrayList<Byte> byteBuffer = new ArrayList<>();
        byteBuffer.add((byte)0);

        System.out.println("SENDING:");
        for(TXCommand c : sendBuffer) //Go through each command...
        {
            System.out.print(c.toReadableString() + "\t");
            for(byte i : c.getByteArray()) //And add bytes to the arraylist.
            {
                byteBuffer.add(i);
            }
        }
        System.out.println();
        byteBuffer.add(1, (byte)(byteBuffer.size() + 1)); //Send how many elements are after delimiter then size.
        byte[] bytesToWrite = new byte[byteBuffer.size()];
        for(int i = 0; i < byteBuffer.size(); i++)
        {
            bytesToWrite[i] = byteBuffer.get(i);
        }
        System.out.println("BYTES OUT:" + Arrays.toString(bytesToWrite));
        port.writeBytes(bytesToWrite, bytesToWrite.length);


        sendBuffer = new ArrayList<>(); //Clear send buffer.
    }
}
