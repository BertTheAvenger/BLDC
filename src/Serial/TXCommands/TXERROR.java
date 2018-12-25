package Serial.TXCommands;

import Serial.TXCommand;
import Serial.TXEnums;

public class TXERROR extends TXCommand
{
    @Override
    public byte[] getByteArray() {

        return new byte[]{getCommandByte()};
    }

    @Override
    public TXEnums getCommandEnum() {
        return TXEnums.ERROR;
    }

    @Override
    public String toReadableString() {
        return "ERROR";
    }
}
