import Serial.RXCommand;

public class MotorDriver {
    private int controlMode = 0;

    public static void init()
    {
        SerialHandler.addListener(new SerialEventListener() {
            @Override
            public void serialEvent(RXCommand event) {
                MotorDriver.serialEvent(event);
            }
        });



    }

    private static void update()
    {

    }

    private static void serialEvent(RXCommand command)
    {



    }
}
