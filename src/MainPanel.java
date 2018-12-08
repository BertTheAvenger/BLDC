import Serial.Command;
import Serial.HANDSHAKECOMMAND;
import Serial.SETPHASECOMMAND;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class MainPanel extends JPanel implements GuiComponent{
    private JPanel graphPanel;
    private JPanel controlPanel;
    MainPanel()
    {
        super();
        setLayout(new GridBagLayout());
        setBackground(new Color(255,127,127));

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.anchor = GridBagConstraints.CENTER;


        graphPanel = new GuiGraphHolder();
        c.gridx = 1;
        c.gridy = 0;
        c.weightx = .5;
        c.weighty = 1;
        add(graphPanel, c);

        controlPanel = new JPanel(new GridBagLayout());
        c.gridx = 0;
        add(controlPanel, c);

        controlPanel.add(new JButton(new AbstractAction("Send Handshake") {
            @Override
            public void actionPerformed(ActionEvent e) {
                SerialHandler.addCommandToBuffer(new HANDSHAKECOMMAND());
                SerialHandler.addCommandToBuffer(new SETPHASECOMMAND(1,126));
                SerialHandler.sendSerialPacket();
            }
        }));

    }

    @Override
    public GridBagConstraints getConstraints() {

        return null;
    }
}
