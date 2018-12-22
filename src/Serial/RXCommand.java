package Serial;

public abstract class RXCommand implements Command { //Interface for commands incoming from Arduino
    public abstract byte getCommandByte();
    public abstract int getCommandLength();
    public abstract void parseBytes(byte[] commandBytes);
    public static RXEnums getCommandEnum(){return RXEnums.ACK;}
}
