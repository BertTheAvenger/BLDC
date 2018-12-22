package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

import java.nio.ByteBuffer;

public class RXADDSHORTS extends RXCommand {
    private int result; //Cast from int
    private byte commandByte;
    private int commandLength;

    public RXADDSHORTS()
    {
        this.commandLength = getCommandEnum().getCommandLength();
        this.commandByte = getCommandEnum().getCommandByte();
    }

    @Override
    public byte getCommandByte() { return this.commandByte; }

    @Override
    public int getCommandLength() { return this.commandLength; }

    public static RXEnums getCommandEnum() {
        return RXEnums.ADDSHORTS;
    }

    public void parseBytes(byte[] commandBytes)
    {
       //1 - 2 int, 3 - 6 long, 7 - 10 double.
        this.result = ByteBuffer.wrap(commandBytes, 1, 4).getInt();
    }

    @Override
    public String toReadableString() {
        return "ADDINT - int: " + result;
    }

    public int getResult(){return result;}
}
