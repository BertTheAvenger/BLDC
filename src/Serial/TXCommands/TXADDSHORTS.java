package Serial.TXCommands;

import Serial.RXCommand;
import Serial.TXCommand;
import Serial.TXEnums;

import java.nio.ByteBuffer;

public class TXADDSHORTS extends TXCommand //Takes two shorts, adds them ON THE ARDUINO, and returns an int.
{
    private short a;
    private short b;

    public TXADDSHORTS(short a, short b)
    {
        super();
        this.a = a;
        this.b = b;
    }

    @Override
    public byte[] getByteArray() {
        byte[] buffer = new byte[getCommandLength()];
        buffer[0] = getCommandByte();

        byte[] aOut = ByteBuffer.allocate(2).putShort(a).array(); //Create array for first int.
        byte[] bOut = ByteBuffer.allocate(2).putShort(b).array(); //Create array for second int.

        System.arraycopy(aOut, 0, buffer, 1, aOut.length);
        System.arraycopy(bOut, 0, buffer, aOut.length + 1, bOut.length);
        //System.out.println(Arrays.toString(buffer));
        return buffer;
    }

    @Override
    public TXEnums getCommandEnum() {
        return TXEnums.ADDSHORTS;
    }

    @Override
    public String toReadableString() {
        return "ADDSHORTS: " + a + " " + b;
    }
}
