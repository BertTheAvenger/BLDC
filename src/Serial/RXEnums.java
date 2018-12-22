package Serial;

import Serial.RXCommands.RXACK;
import Serial.RXCommands.RXADDSHORTS;
import Serial.RXCommands.RXERROR;
import Serial.RXCommands.RXTOTALDATA;

import java.util.concurrent.ExecutionException;

public enum RXEnums { //Defines relationships between Enum, Actual class, command lengths and command bytes in one place for simplicity.
    ACK(1, RXACK.class, 1),
    //{cmd, byte} <cmd>
    //Acknowledgement of completed command. Certain TX commands will wait for this before proceeding.

    ERROR(2, RXERROR.class, 2),
    //{cmd, byte} <cmd, error byte>
    //An error on the HW side.

    ADDSHORTS(3, RXADDSHORTS.class, 5),
    //{cmd, high long, long, long, low long} <cmd, result>
    //Result of TXADDSHORTS command.

    TOTALDATA(4, RXTOTALDATA.class, 9);
    //{cmd, high long, long, long, low long, high int, low int, high int, low int } <cmd, Encoder raw, amperage raw, voltage raw>
    //Returns various data about state of HW.


    private byte commandByte;
    private Class commandClass;
    private int commandLength;

    RXEnums(int commandByte, Class commandClass, int commandLength) {
        this.commandByte = (byte)commandByte;
        this.commandClass = commandClass;
        this.commandLength = commandLength;
    }

    public RXCommand getCommand() {
        try {
            return (RXCommand) commandClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public byte getCommandByte() {
        return commandByte;
    }

    public int getCommandLength(){
        return commandLength;
    }

    public String getStr(){return "KSENFGIK";}

    public static RXEnums byteToEnum(byte cmdByte)
    {
        RXEnums[] enums = RXEnums.values();
        for(RXEnums e : enums)
        {
            if(e.getCommandByte() == cmdByte){return e;}
        }
        return null;
    }

    public static RXCommand byteToCommand(byte cmdByte)
    {
        RXEnums[] enums = RXEnums.values();
        for(int i = 0; i < enums.length; i++)
        {
            if(enums[i].getCommandByte() == cmdByte)
            {
                System.out.println(enums[i].getCommand().toReadableString());
                return enums[i].getCommand();
            }
        }
        return null;
    }

}
