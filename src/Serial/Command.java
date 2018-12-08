package Serial;

public abstract class Command
{
    public abstract int getCommandByte();

    public abstract int[] getByteArray();

    public abstract String toString();
}