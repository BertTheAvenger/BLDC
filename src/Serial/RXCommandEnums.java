package Serial;

import Serial.RXCommands.RXACK;
import Serial.RXCommands.RXADDSHORTS;
import Serial.RXCommands.RXNUMBERTEST;
import Serial.RXCommands.RXTOTALDATA;

public enum RXCommandEnums {
    ACK, ADDSHORTS, NUMBERTEST, TOTALDATA;

    public static RXCommandEnums fromInt(int i)
    {
        switch (i)
        {
            case 1: return ACK;
            case 2 : return NUMBERTEST;
            case 3 : return ADDSHORTS;
            case 5: return TOTALDATA;
            default: return null;
        }
    }

    public static RXCommandEnums fromByte(byte i)
    {
        return (fromInt(Byte.toUnsignedInt(i)));
    }

    public static RXCommand commandFromEnum(RXCommandEnums e)
    {
        switch (e)
        {
            case ACK: return new RXACK();
            case NUMBERTEST: return new RXNUMBERTEST();
            case TOTALDATA: return new RXTOTALDATA();
            case ADDSHORTS: return new RXADDSHORTS();
            default: return null;
        }
    }

    public static RXCommand commandFromByte(byte b)
    {
        return commandFromEnum(fromByte(b));
    }
}
