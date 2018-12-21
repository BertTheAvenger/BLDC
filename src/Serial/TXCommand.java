package Serial;

public interface TXCommand extends Command {//Interface for commands GOING TO Arduino.
    byte getCommandByte();

    byte[] getByteArray();

    boolean requireAck();


}