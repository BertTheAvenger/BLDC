package Serial.RXCommands;

import Serial.RXCommand;

public class RXHANDSHAKE implements RXCommand {
    @Override
    public String toReadableString() {
        return "HANDSHAKE";
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public void setBytes(byte[] commandBytes) {

    }
}
