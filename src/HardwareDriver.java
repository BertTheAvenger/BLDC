import Serial.RXCommand;

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
}
