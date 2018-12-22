package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

public class RXERROR implements RXCommand {

    private byte commandByte;

    private int commandLength;

    public RXERROR()
    {
        this.commandLength = getCommandEnum().getCommandLength();
        this.commandByte = getCommandEnum().getCommandByte();
    }

    @Override
    public byte getCommandByte() { return this.commandByte; }

    @Override
    public int getCommandLength() { return this.commandLength; }

    @Override
    public void parseBytes(byte[] commandBytes) {
    }

    @Override
    public RXEnums getCommandEnum() {
        return RXEnums.ERROR;
    }

    @Override
    public String toReadableString() {
        return "ERROR: ";
    }
}
