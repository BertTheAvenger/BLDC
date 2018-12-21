package Serial.TXCommands;

import Serial.TXCommand;

import java.util.Arrays;

//TXCommand 4 bytes.
//1st byte is phase # (1-3), second is byte representing PWM power to apply.
public class TXSETALLPHASEDUTY implements TXCommand
{
    private byte CommandByte = 6;
    private byte[] pwms; //0 - 255 for PWM.

    public TXSETALLPHASEDUTY(byte pwm1, byte pwm2, byte pwm3)
    {
        this.pwms = new byte[3]; //3 phases
        this.pwms[0] = pwm1;
        this.pwms[1] = pwm2;
        this.pwms[2] = pwm3;

    }

    @Override
    public byte getCommandByte() {
        return CommandByte;
    }

    @Override
    public byte[] getByteArray() {

        byte[] buffer = new byte[getLength()];
        buffer[0] = getCommandByte();
        buffer[1] = pwms[0];
        buffer[2] = pwms[1];
        buffer[3] = pwms[2];
        //System.out.println(Arrays.toString(buffer));
        return buffer;
    }

    @Override
    public boolean requireAck() {
        return false;
    }

    @Override
    public String toReadableString() {
        return "PHASES TO: " + Arrays.toString(pwms);
    }

    @Override
    public int getLength() {
        return 4;
    }
}