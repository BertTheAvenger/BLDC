package Serial;

public interface TXCommand extends Command {//Interface for commands GOING TO Arduino.
    int getCommandByte();

    byte[] getByteArray();

    boolean requireAck();


}