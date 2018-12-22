package Serial;

public enum ErrorEnums {
    TIMEOUT((byte)1);

    private byte cmdByte;
    ErrorEnums(byte numVal)
    {
        this.cmdByte = numVal;
    }

    public byte getCmdByte() {
        return cmdByte;
    }
}
