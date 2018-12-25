package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXEnums;

import java.nio.ByteBuffer;

public class RXTOTALDATA extends RXCommand {
    //{cmd, high long, long, long, low long, high int, low int, high int, low int } <cmd, Encoder raw, amperage raw, voltage raw>

    private int encoderPos;
    private int amperageRaw;
    private int voltageRaw;

    @Override
    public void parseBytes(byte[] commandBytes) {
        this.encoderPos = ByteBuffer.wrap(commandBytes, 1, 4).getInt();
        this.amperageRaw = ByteBuffer.wrap(commandBytes, 5, 2).getShort();
        this.voltageRaw = ByteBuffer.wrap(commandBytes, 7, 2).getShort();

    }

    public RXEnums getCommandEnum() {
        return RXEnums.TOTALDATA;
    }

    @Override
    public String toReadableString() {
        return "TOTAL DATA: <encoder:" + encoderPos + ", amperageRaw:" + amperageRaw + ", voltageRaw:" + voltageRaw + ">";
    }


}
