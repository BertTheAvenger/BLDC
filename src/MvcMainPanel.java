import javax.swing.*;
import java.awt.*;


public class MvcMainPanel extends JPanel implements GuiComponent{
    private JPanel graphPanel;
    private MvcControlPanel controlPanel;
    MvcMainPanel()
    {
        super();
        setLayout(new BorderLayout());
        setBackground(new Color(255,127,127));

        graphPanel = new MvcGraphHolder();
        add(graphPanel, BorderLayout.CENTER);

        controlPanel = new MvcControlPanel();
        add(controlPanel, BorderLayout.LINE_START);


        /*
        controlPanel.add(new JButton(new AbstractAction("Send Handshake") {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerialHandler.sendSerialCommand(new TXADDSHORTS((short)5209, (short) 2000));
            }
        }));
        */
    }

    @Override
    public GridBagConstraints getConstraints() {

        return null;
    }
    public void setSerialStatus(int serialStatus){controlPanel.setSerialStatus(serialStatus);}
}
