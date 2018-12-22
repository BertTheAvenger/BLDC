package Serial;

public interface RXCommand extends Command { //Interface for commands incoming from Arduino
    byte getCommandByte();
    void parseBytes(byte[] commandBytes);
    RXEnums getCommandEnum();
}
