package Serial.TXCommands;

import Serial.TXCommand;
import Serial.TXEnums;

public class TXACK extends TXCommand
{
    @Override
    public byte[] getByteArray() {

        return new byte[]{getCommandByte()};
    }

    @Override
    public TXEnums getCommandEnum() {
        return TXEnums.ACK;
    }

    @Override
    public String toReadableString() {
        return "ACK";
    }
}
