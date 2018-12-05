public class Main {
    SerialHandler serialHandler;
    MvcController mvcController;
    public static void main(String args[]){new Main();}
    private Main()
    {

        //Init GUI elements

        MvcController.passObjects(new MvcModel());
        MvcController.startInterface();
        //System.out.println(Arrays.toString(sh.getPorts()));
    }
}
