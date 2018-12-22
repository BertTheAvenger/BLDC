package Serial;

public abstract class TXCommand implements Command {//Interface for commands GOING TO Arduino.
    private byte commandByte;
    private int commandLength;
    private RXEnums ackCommand;

    public TXCommand()
    {
        this.commandByte = getCommandEnum().getCommandByte();
        this.commandLength = getCommandEnum().getCommandLength();
        this.ackCommand = getCommandEnum().getAckCommand();
    }

    public byte getCommandByte(){return commandByte;}

    public int getCommandLength(){return commandLength;}

    public RXEnums getAckCommand(){return ackCommand;}


    public abstract byte[] getByteArray();

    public abstract TXEnums getCommandEnum();


}