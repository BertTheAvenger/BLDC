package Serial.TXCommands;

import Serial.TXCommand;

public class TXACK implements TXCommand
{
    private byte CommandByte = 1;

    @Override
    public byte getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {

        return new byte[]{CommandByte};
    }

    @Override
    public boolean requireAck() {
        return true;
    }

    @Override
    public String toReadableString() {
        return "ACK";
    }

    @Override
    public int getCommandLength() {
        return 1;
    }
}
