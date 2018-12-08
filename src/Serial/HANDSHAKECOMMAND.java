package Serial;

public class HANDSHAKECOMMAND extends Command
{
    private int CommandByte = 1;

    @Override
    public int getCommandByte() {
        return CommandByte;
    }

    @Override
    public int[] getByteArray() {

        return new int[]{CommandByte};
    }

    @Override
    public String toString() {
        return "HANDSHAKE";
    }
}
