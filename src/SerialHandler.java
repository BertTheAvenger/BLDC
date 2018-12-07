import com.fazecast.jSerialComm.*;

import java.util.Arrays;

class SerialHandler {
    private static final int[] baudrates = {9600, 115200};
    private static SerialPort port;
    private static boolean serialStatus = false;
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

    static void sendSerialPacket(){}
}
