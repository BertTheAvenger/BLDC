package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

import java.nio.ByteBuffer;

public class RXTOTALDATA implements RXCommand {
    //Form
    private byte commandByte;
    private int commandLength;
    public RXTOTALDATA()
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
        return null;
    }

    @Override
    public String toReadableString() {
        return null;
    } //CMDBYTE 5


}
