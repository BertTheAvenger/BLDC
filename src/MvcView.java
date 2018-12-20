import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MvcView {
    private static final int startingHeight = 400;
    private static final int startingWidth = 600;

    private static GViewJMenuBar menuBar;
    private static GMainPanel mainPanel;

    private
    //Serial menuBar components
    ButtonGroup serialPortsButtonGroup;

    public static void constructGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Disable "focused" borders on all ui elements
        UIManager.put("Button.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("ToggleButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("CheckBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("TabbedPane.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("RadioButton.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("Slider.focus", new ColorUIResource(new Color(0, 0, 0, 0)));
        UIManager.put("ToggleButton.light", new ColorUIResource(new Color(255, 0, 0, 1)));


        // figure out combobox
        UIManager.put("ComboBox.focus", new ColorUIResource(new Color(0, 0, 0, 0)));

        JFrame frame = new JFrame();
        //frame.setJMenuBar(constructJMB());

        menuBar = new GViewJMenuBar();
        frame.setJMenuBar(menuBar);

        mainPanel = new GMainPanel();
        mainPanel.setPreferredSize(new Dimension(500,500));
        frame.setContentPane(mainPanel);
        frame.setMinimumSize(new Dimension(800,600));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Exit program on window close.

        frame.setVisible(true);
    }

    //Menu Bar passthrough functions
    static void updateSerialMenu(String[] ports, String selectedPort){menuBar.updateSerialMenu(ports, selectedPort);}
    static void updateBaudRates(int[] rates, int selectedRate){menuBar.updateBaudRates(rates, selectedRate);}
    static void setSerialActionConnect(){ menuBar.setSerialActionConnect(); mainPanel.setSerialStatus(0);}
    static void setSerialActionDisconnect(){ menuBar.setSerialActionDisconnect(); mainPanel.setSerialStatus(1); }

}
