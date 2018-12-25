package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

public class RXERROR extends RXCommand {

    private byte errorByte;

    @Override
    public void parseBytes(byte[] commandBytes) { errorByte = commandBytes[1]; }

    public  RXEnums getCommandEnum() { return RXEnums.ERROR; }

    @Override
    public String toReadableString() {
        return "ERROR: " + errorByte;
    }

}
