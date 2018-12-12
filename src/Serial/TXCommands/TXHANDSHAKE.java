package Serial.TXCommands;

import Serial.TXCommand;

public class TXHANDSHAKE implements TXCommand
{
    private byte CommandByte = 1;

    @Override
    public int getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {

        return new byte[]{CommandByte};
    }

    @Override
    public String toReadableString() {
        return "HANDSHAKE";
    }

    @Override
    public int getLength() {
        return 1;
    }
}
