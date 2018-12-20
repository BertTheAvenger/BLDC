package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXCommandEnums;

public class RXACK implements RXCommand {
    @Override
    public String toReadableString() {
        return "ACK";
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public void setBytes(byte[] commandBytes) {

    }

    @Override
    public RXCommandEnums getCommand() {
        return RXCommandEnums.ACK;
    }
}
