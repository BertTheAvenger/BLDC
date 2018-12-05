import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.util.ArrayList;

public class MvcView {
    private static final int startingHeight = 400;
    private static final int startingWidth = 600;

    private static MvcViewJMenuBar menuBar;
    private static MainPanel mainPanel;

    private
    //Serial menuBar components
    ButtonGroup serialPortsButtonGroup;

    public static void constructGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        //frame.setJMenuBar(constructJMB());

        menuBar = new MvcViewJMenuBar();
        frame.setJMenuBar(menuBar);

        mainPanel = new MainPanel();
        mainPanel.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(mainPanel);

        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Exit program on window close.

        frame.setVisible(true);
    }

    //Menu Bar passthrough functions
    static void updateSerialMenu(String[] ports, String selectedPort){menuBar.updateSerialMenu(ports, selectedPort);}
    static void updateBaudRates(int[] rates, int selectedRate){menuBar.updateBaudRates(rates, selectedRate);}
    static void setSerialActionConnect(){ menuBar.setSerialActionConnect(); }
    static void setSerialActionDisconnect(){ menuBar.setSerialActionDisconnect(); }

}
