package Serial;

import Serial.TXCommands.*;

public enum TXEnums { //Defines relationships between Enum, Actual class, command lengths and command bytes in one place for simplicity.
    ACK(1, TXACK.class, 1,RXEnums.ACK, 100),
    //{cmd} <cmd>
    //Sends acknowledgement. Some TX classes might wait for ACK to proceeed.

    ERROR(2, TXERROR.class, 2, null, 1000),
    //{cmd, byte} <cmd, error byte>
    //An error on the client side.

    ADDSHORTS(3, TXADDSHORTS.class, 5, RXEnums.ADDSHORTS, 1000),
    //{cmd, high int, low int, high int, low int} <cmd, short 1, short 2>
    //Sends 2 shorts for Arduino to add.s

    TOTALDATA(4, TXTOTALDATA.class, 1,RXEnums.TOTALDATA, 1000),
    //{cmd} <cmd>
    //Sends request for all data.

    SETMODE(5, TXTOTALDATA.class, 2, RXEnums.ACK, 1000),
    //{cmd} <cmd>
    //Returns various data about state of HW.

    SETALLPHASEDUTIES(6, TXTOTALDATA.class, 1, RXEnums.ACK, 1000),
    //{cmd, byte, byte, byte} <cmd, phase1 pwm, phase2 pwm, phase3 pwm>
    //Returns various data about state of HW.

    SETPHASEDUTY(7, TXTOTALDATA.class, 1, RXEnums.ACK, 1000);
    //{cmd, byte, byte} <cmd, phase, pwm>
    //Returns various data about state of HW.

    private byte commandByte;
    private Class<?> commandClass;
    private int commandLength;
    private RXEnums ackCommand;
    private int ackTimeout;

    TXEnums(int commandByte, Class commandClass, int commandLength, RXEnums ackCommand, int ackTimeout) {
        this.commandByte = (byte)commandByte; //Command byte associated with command class.
        this.commandClass = commandClass; //Reference to class that handles this command.
        this.commandLength = commandLength; //Length of command, including cmdbyte.
        this.ackCommand = ackCommand; //(Optional) Specifies command to wait for before proceeding with next transmission.
        this.ackTimeout = ackTimeout; //Timeout for ACK command.
    }

    public TXCommand getCommand() {
        try {
            return (TXCommand) commandClass.getDeclaredConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public RXEnums getAckCommand(){
        return this.ackCommand;
    }

    public byte getCommandByte() {
        return commandByte;
    }

    public int getCommandLength(){
        return commandLength;
    }

    public int getAckTimeout() { return ackTimeout; }
}
