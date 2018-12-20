package Serial.TXCommands;

import Serial.TXCommand;

public class TXREQUESTALLDATA implements TXCommand
{
    private byte CommandByte = 5;

    @Override
    public int getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {

        return new byte[]{CommandByte};
    }

    @Override
    public boolean requireAck() {
        return false;
    }

    @Override
    public String toReadableString() {
        return "REQUEST ALL DATA";
    }

    @Override
    public int getLength() {
        return 1;
    }
}
