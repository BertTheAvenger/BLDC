package Serial.TXCommands;

import Serial.TXCommand;

import java.nio.ByteBuffer;

public class TXSETMODE implements TXCommand //Sends mode to enter to Arduino.
{
    private byte CommandByte = 4;
    private byte mode;
    //Mode 0 is off.
    //Mode 1 is default mode. Arduino uses hardcoded sin wave, controls are sent by this program. Status values need to
    // be requested.
    //Mode 2 is external drive mode.
    //Mode 3 is calibration mode. Client sends phase values, Arduino returns V, A, and Angle.

    public TXSETMODE(byte mode)
    {
        this.mode = mode;
    }

    @Override
    public byte getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {
        byte[] buffer = new byte[getLength()];
        buffer[0] = getCommandByte();
        buffer[1] = mode;
        return buffer;
    }

    @Override
    public boolean requireAck() {
        return true;
    }

    @Override
    public String toReadableString() {
        return "SETMODE: " + Byte.toUnsignedInt(mode);
    }

    @Override
    public int getLength() {
        return 2;
    }
}
