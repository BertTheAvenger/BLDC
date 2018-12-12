import Serial.RXCommands.RXNUMBERTEST;
import Serial.TXCommands.TXADDSHORTS;

public class Main {
    public static void main(String args[]){
        /*
        byte[] packet = {1,2,3,3,0,1,1,2,3,4,0,7,3,0,0,0,100,10,33};
        for(byte b : packet)
        {
            SerialHandler.buildPacket(b);
        }
        TXADDSHORTS test = new TXADDSHORTS((short)10,(short)20);
        test.getByteArray();


        */
        new Main();



    }
    private Main()
    {

        //Init GUI elements


        MvcController.passObjects(new MvcModel());
        MvcController.startInterface();
        //System.out.println(Arrays.toString(sh.getPorts()));
    }
}
