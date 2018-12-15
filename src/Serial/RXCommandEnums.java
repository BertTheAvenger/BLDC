package Serial;

public enum RXCommandEnums {
    ACK, ADDSHORTS, NUMBERTEST;

    public static RXCommandEnums fromInt(int i)
    {
        switch (i)
        {
            case 1: return ACK;
            case 2 : return NUMBERTEST;
            case 3 : return ADDSHORTS;
            default: return null;
        }
    }

    public static RXCommandEnums fromByte(byte i)
    {
        switch (Byte.toUnsignedInt(i))
        {
            case 1: return ACK;
            case 2 : return NUMBERTEST;
            case 3 : return ADDSHORTS;
            default: return null;
        }
    }

}
