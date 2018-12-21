package Serial.TXCommands;

import Serial.TXCommand;

import java.nio.ByteBuffer;

public class TXADDSHORTS implements TXCommand //Takes two shorts, adds them ON THE ARDUINO, and returns an int.
{
    private byte CommandByte = 3;
    private short a;
    private short b;

    public TXADDSHORTS(short a, short b)
    {
        this.a = a;
        this.b = b;
    }

    @Override
    public byte getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {
        byte[] buffer = new byte[getLength()];
        buffer[0] = getCommandByte();

        byte[] aOut = ByteBuffer.allocate(2).putShort(a).array(); //Create array for our int.
        //System.out.println(Arrays.toString(aOut));
        byte[] bOut = ByteBuffer.allocate(2).putShort(b).array(); //Create array for our int.
        //System.out.println(Arrays.toString(bOut));
        System.arraycopy(aOut, 0, buffer, 1, aOut.length);
        System.arraycopy(bOut, 0, buffer, aOut.length + 1, bOut.length);
        //System.out.println(Arrays.toString(buffer));
        return buffer;
    }

    @Override
    public boolean requireAck() {
        return false;
    }

    @Override
    public String toReadableString() {
        return "ADDSHORTS: " + a + " " + b;
    }

    @Override
    public int getLength() {
        return 5;
    }
}
