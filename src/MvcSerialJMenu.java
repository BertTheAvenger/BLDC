import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;

class MvcSerialJMenu extends JMenu {
    private JMenu portMenu;
    private JMenu rateMenu;
    private JMenuItem serialConnectionJMenuItem;

    MvcSerialJMenu()
    {
        super("Serial"); //Title of menu
        construct();
    }

    private void construct() {
        //Serial Port menu and listener
        portMenu = new JMenu("Ports");
        portMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MvcController.refreshViewSerialPorts();


            }
            @Override
            public void menuDeselected(MenuEvent e) { }
            @Override
            public void menuCanceled(MenuEvent e) { }
        });
        add(portMenu);

        //Setup rate menu and listener
        rateMenu = new JMenu("Baudrate");
        rateMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                MvcController.refreshViewBaudRates();


            }
            @Override
            public void menuDeselected(MenuEvent e) { }
            @Override
            public void menuCanceled(MenuEvent e) { }
        });
        add(rateMenu);

        //Connect button.
        serialConnectionJMenuItem = new JMenuItem();
        serialConnectionJMenuItem.setAction(new AbstractAction("Connect") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MvcController.serialConnectActionPreformed();
            }
        });
        add(serialConnectionJMenuItem);

    }

    void updateSerialMenu(String[] ports, String selectedPort){
        portMenu.removeAll();
        for(String p : ports)
        {
            //System.out.println(p + "\t" + selectedPort);
            JRadioButtonMenuItem item = new JRadioButtonMenuItem();
            item.setAction(new AbstractAction(p) { //Add action listener
                @Override
                public void actionPerformed(ActionEvent e) {
                    MvcController.setSerialPort(p); //Call controller to set serial port
                }
            });

            if(p.equals(selectedPort)) {
                item.setSelected(true);
            }
            portMenu.add(item);
        }

    }

    void updateBaudRates(int[] rates, int selectedRate){
        rateMenu.removeAll();
        for(int r : rates)
        {
            //System.out.println(p + "\t" + selectedPort);
            JRadioButtonMenuItem item = new JRadioButtonMenuItem();
            item.setAction(new AbstractAction(Integer.toString(r)) { //Add action listener
                @Override
                public void actionPerformed(ActionEvent e) {
                    MvcController.setBaudRate(r); //Call controller to set serial port
                }
            });


            if(r == selectedRate) {
                item.setSelected(true);
            }
            rateMenu.add(item);
        }

    }

    //Serial Actions
    void setSerialActionConnect(){
        serialConnectionJMenuItem.setAction(new AbstractAction("Connect") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MvcController.serialConnectActionPreformed();
            }
        });
    }

    void setSerialActionDisconnect(){
        serialConnectionJMenuItem.setAction(new AbstractAction("Disconnect") {
            @Override
            public void actionPerformed(ActionEvent e) {
                MvcController.serialDisconnectActionPreformed();
            }
        });
    }




}
