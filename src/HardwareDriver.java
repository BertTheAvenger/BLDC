import Serial.RXCommand;
import Serial.TXCommands.*;

public class HardwareDriver {
    private int controlMode = 0;

    public static void init()
    {
        SerialHandler.addListener(new SerialEventListener() { //Bind event handler.
            @Override
            public void serialEvent(RXCommand event) {
                HardwareDriver.serialEvent(event);
            }
        });



    }

    private static void serialEvent(RXCommand command)
    {
        switch(command.getCommand()){
            case ACK:
                break;
            case ADDSHORTS:
                break;
            case TOTALDATA:
                break;
            case NUMBERTEST:
                break;
        }
    }

    static void startCalibration()
    {
        System.out.println("Calibration Starting!");
        //SerialHandler.sendSerialCommand(new TXACK());
        SerialHandler.sendSerialCommand(new TXSETMODE((byte)1));
        //SerialHandler.sendSerialCommand(new TXTOTALDATA());
        SerialHandler.sendSerialCommand(new TXSETPHASEDUTY((byte)1, (byte)100));
        SerialHandler.sendSerialCommand(new TXSETPHASEDUTY((byte)2, (byte)100));
        SerialHandler.sendSerialCommand(new TXSETPHASEDUTY((byte)3, (byte)0));

    }
}
