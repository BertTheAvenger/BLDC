package Serial;

public abstract class RXCommand implements Command { //Interface for commands incoming from Arduino

    private byte commandByte;
    private int commandLength;

    public RXCommand()
    {
        this.commandLength = getCommandEnum().getCommandLength();
        this.commandByte = getCommandEnum().getCommandByte();
    }

    public byte getCommandByte(){return commandByte;}

    public int getCommandLength(){return commandLength;}


    public abstract void parseBytes(byte[] commandBytes);

    public abstract RXEnums getCommandEnum();
}
