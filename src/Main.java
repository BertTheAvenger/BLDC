public class Main {
    private static int mode = 0;
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

        MvcController.passObjects(new MvcModel());
        MvcController.startInterface();
    }

}
