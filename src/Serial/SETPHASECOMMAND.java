package Serial;

public class SETPHASECOMMAND extends Command
{
    private int CommandByte = 2;
    private int phase; //0-2 for each of the 3 phases.
    private int pwm; //0 - 255 for PWM.

    public SETPHASECOMMAND(int phase, int pwm)
    {
        this.pwm = pwm;
        this.phase = phase;
    }

    @Override
    public int getCommandByte() {
        return CommandByte;
    }

    @Override
    public int[] getByteArray() {

        return new int[]{CommandByte, phase, pwm};
    }

    @Override
    public String toString() {
        return "PHASE " + phase + " TO " + pwm;
    }
}