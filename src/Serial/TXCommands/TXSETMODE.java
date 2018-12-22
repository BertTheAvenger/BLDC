package Serial.TXCommands;

import Serial.RXCommand;
import Serial.TXCommand;
import Serial.TXEnums;

public class TXSETMODE extends TXCommand //Sends mode to enter to Arduino.
{
    private byte mode;
    //Mode 0 is off.
    //Mode 1 is default mode. Arduino uses hardcoded sin wave, controls are sent by this program. Status values need to
    // be requested.
    //Mode 2 is external drive mode.
    //Mode 3 is calibration mode. Client sends phase values, Arduino returns V, A, and Angle.

    public TXSETMODE(byte mode)
    {
        super();
        this.mode = mode;
    }

    @Override
    public byte[] getByteArray() {
        byte[] buffer = new byte[getCommandLength()];
        buffer[0] = getCommandByte();
        buffer[1] = mode;
        return buffer;
    }

    @Override
    public TXEnums getCommandEnum() {
        return TXEnums.SETMODE;
    }

    @Override
    public String toReadableString() {
        return "SETMODE: " + Byte.toUnsignedInt(mode);
    }

}
