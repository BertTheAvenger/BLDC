package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

public class RXERROR extends RXCommand {


    @Override
    public byte getCommandByte() {
        return 0;
    }

    @Override
    public int getCommandLength() {
        return 0;
    }

    @Override
    public void parseBytes(byte[] commandBytes) {
    }

    public  RXEnums getCommandEnum() {
        return RXEnums.ERROR;
    }

    @Override
    public String toReadableString() {
        return "ERROR: ";
    }
}
