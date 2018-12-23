package Serial;

public abstract class TXCommand implements Command {//Interface for commands GOING TO Arduino.
    private byte commandByte;
    private int commandLength;
    private RXEnums ackCommand;
    private int ackTimeout;

    public TXCommand()
    {
        this.commandByte = getCommandEnum().getCommandByte();
        this.commandLength = getCommandEnum().getCommandLength();
        this.ackCommand = getCommandEnum().getAckCommand();
        this.ackTimeout = getCommandEnum().getAckTimeout();
    }

    public byte getCommandByte(){return commandByte;}

    public int getCommandLength(){return commandLength;}

    public int getAckTimeout() { return ackTimeout; }

    public RXEnums getAckCommand(){return ackCommand;}


    public abstract byte[] getByteArray();

    public abstract TXEnums getCommandEnum();


}