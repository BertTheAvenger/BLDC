package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

public class RXACK extends RXCommand {

    @Override
    public void parseBytes(byte[] commandBytes) {
    }

    public  RXEnums getCommandEnum() {
        return RXEnums.ACK;
    }

    @Override
    public String toReadableString() {
        return "ACK";
    }
}
