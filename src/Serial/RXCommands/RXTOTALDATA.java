package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

import java.nio.ByteBuffer;

public class RXTOTALDATA extends RXCommand {

    @Override
    public void parseBytes(byte[] commandBytes) {

    }

    public RXEnums getCommandEnum() {
        return null;
    }

    @Override
    public String toReadableString() {
        return "TOTAL DATA:";
    }


}
