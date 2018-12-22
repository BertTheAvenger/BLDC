package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

public class RXACK implements RXCommand {

    private byte commandByte;
    private int commandLength;
    public RXACK()
    {
        System.out.println(RXEnums.ACK.getStr());
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
        return RXEnums.ACK;
    }

    @Override
    public String toReadableString() {
        return "ACK";
    }
}
