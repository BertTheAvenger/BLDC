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
            return true;
        }
        else
        {
            return false;
        }
    }
}
