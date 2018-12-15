package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXCommandEnums;

import java.nio.ByteBuffer;

public class RXNUMBERTEST implements RXCommand { //CMDBYTE 2
    //Form
    //{2, high int, low int, high long, long, long, low long, high double, double, double, low double} //Big-endian.
    private int i; //Cast from short
    private long l; //Cast from int
    private double d; //Cast from a float
    public RXNUMBERTEST()
    {
        i = 0;
        l = 0;
        d = 0;

    }
    @Override
    public void setBytes(byte[] commandBytes) { parseBytes(commandBytes);}

    @Override
    public RXCommandEnums getCommand() {
        return null;
    }

    public void parseBytes(byte[] commandBytes)
    {
       //1 - 2 int, 3 - 6 long, 7 - 10 double.
        this.i = (int) ByteBuffer.wrap(commandBytes, 1, 2).getShort();
        this.l = (long) ByteBuffer.wrap(commandBytes, 3, 4).getInt();
        this.d = (double) ByteBuffer.wrap(commandBytes, 7, 4).getFloat();
    }

    @Override
    public String toReadableString() {
        return "NUMBERTEST - int:" + i + "\tlong:" + l + "\tdouble:" + d;
    }

    @Override
    public int getLength() {
        return 11;
    }
}
