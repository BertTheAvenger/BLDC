package Serial.TXCommands;

import Serial.TXCommand;

import java.nio.ByteBuffer;

//TXCommand 2, 2 bytes.
//1st byte is phase # (1-3), second is byte representing PWM power to apply.
public class TXSETPHASEDUTY implements TXCommand
{
    private byte CommandByte = 2;
    private byte phase; //0-2 for each of the 3 phases.
    private byte pwm; //0 - 255 for PWM.

    public TXSETPHASEDUTY(byte phase, byte pwm)
    {
        this.pwm = pwm;
        this.phase = phase;
    }

    @Override
    public int getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {

        byte[] buffer = new byte[getLength()];
        buffer[0] = (byte)getCommandByte();
        buffer[1] = phase;
        buffer[2] = pwm;
        //System.out.println(Arrays.toString(buffer));
        return buffer;
    }

    @Override
    public boolean requireAck() {
        return false;
    }

    @Override
    public String toReadableString() {
        return "PHASE " + phase + " TO " + pwm;
    }

    @Override
    public int getLength() {
        return 3;
    }
}