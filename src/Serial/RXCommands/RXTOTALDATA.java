package Serial.RXCommands;

import Serial.RXCommand;
import Serial.RXCommandEnums;

import java.nio.ByteBuffer;

public class RXTOTALDATA implements RXCommand { //CMDBYTE 5
    //Form
    //{3, high int, int, int, low int} //Big-endian.
    private int result; //Cast from int
    public RXTOTALDATA()
    {
       result = 0;
    }

    public RXTOTALDATA(byte[] commandBytes)
    {
        result = 0;
        setBytes(commandBytes);
    }

    @Override
    public void setBytes(byte[] commandBytes) { parseBytes(commandBytes);}

    @Override
    public RXCommandEnums getCommand() {
        return RXCommandEnums.ADDSHORTS;
    }

    public void parseBytes(byte[] commandBytes)
    {
       //1 - 2 int, 3 - 6 long, 7 - 10 double.
        this.result = ByteBuffer.wrap(commandBytes, 1, 4).getInt();
    }

    @Override
    public String toReadableString() {
        return "TOTAL DATA: " + result;
    }

    @Override
    public int getLength() {
        return 5;
    }

    public int getResult(){return result;}
}
