import javax.swing.*;

public class GViewJMenuBar extends JMenuBar {
    private JMenuBar menuBar;
    private GSerialJMenu serialMenu;

    GViewJMenuBar()
    {
        super();
        construct();
    }
    private void construct() { //Construct JMenuBar components.
        serialMenu = new GSerialJMenu();
        add(serialMenu);

        setVisible(true);
    }

    private void linkActions() //Setup actions
    {
        //serialPortsMenu.
    }


    /*
    void serialAddSerialPortMenuListener(MenuListener l){
        serialPortsMenu.addMenuListener(l);
    }
    */
    public JMenuBar getMenuBar(){return this;}

    //Serial menu passthrough functions
    void updateSerialMenu(String[] ports, String selectedPort){serialMenu.updateSerialMenu(ports, selectedPort);}
    void updateBaudRates(int[] rates, int selectedRate){serialMenu.updateBaudRates(rates, selectedRate);}
    void setSerialActionConnect(){ serialMenu.setSerialActionConnect(); }
    void setSerialActionDisconnect(){ serialMenu.setSerialActionDisconnect(); }

}
