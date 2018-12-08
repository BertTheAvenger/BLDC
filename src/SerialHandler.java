import Serial.Command;
import com.fazecast.jSerialComm.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SerialHandler {
    private static final int[] baudrates = {9600, 115200};
    private static SerialPort port;
    private static boolean serialStatus = false;
    private static ArrayList<Command> sendBuffer = new ArrayList<>();
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
                    System.out.println("Read " + numRead + " bytes.");
                }
            });

            return true;
        }
        else
        {
            return false;
        }
    }
    static void addCommandToBuffer(Command command)
    {
        //System.out.println(command);
        sendBuffer.add(command);
    }

    static void sendSerialPacket(){
        ArrayList<Integer> intBuffer = new ArrayList<>();
        intBuffer.add(0);

        for(Command c : sendBuffer)
        {
            System.out.print(c.toString() + "\t");
            for(int i : c.getByteArray())
            {
                intBuffer.add(i);
            }
        }
        System.out.println();
        intBuffer.add(1, intBuffer.size() - 1); //Send how many elements are after delimiter then size.
        System.out.println(intBuffer);
        sendBuffer = new ArrayList<>(); //Clear send buffer.
    }
}
