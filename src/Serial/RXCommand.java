package Serial;

public interface RXCommand extends Command { //Interface for commands incoming from Arduino
    void setBytes(byte[] commandBytes);
}
