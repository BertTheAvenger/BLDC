import Serial.TXCommand;
import Serial.TXCommands.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

class GCPSerialTestPanel extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    private static HashMap<String, TXCommand> txBtns = new HashMap<>();
    static {
        txBtns.put("ACK", new TXACK());
        txBtns.put("SETMODE", new TXSETMODE((byte) 1));
        txBtns.put("ADD SHORTS", new TXADDSHORTS((short)1231,(short)25));
        txBtns.put("TOTAL DATA", new TXTOTALDATA());


    }
    GCPSerialTestPanel() //ControlPanel Motor.
    {
        super(new GridLayout());
        setBorder(new TitledBorder("Serial Commands"));

        for(Map.Entry<String, TXCommand> entry : txBtns.entrySet()) { //generate and add button objects
            add(new JButton(new AbstractAction(entry.getKey()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SerialHandler.sendSerialCommand(entry.getValue());
                }
            }));
        }

        SerialHandler.addSerialEventListener((event) -> { //Add events to disable/enable buttons.
            switch(event)
            {
                case SERIAL_DISCONNECTED:
                    disableButtons();
                    break;
                case SERIAL_CONNECTED:
                    enableButtons();
                    break;
            }
        });

        disableButtons();
    }

    private void enableButtons()
    {
        Component[] components = getComponents();
        for(Component c : components)
        {
            if(c.getClass() == JButton.class) //Is a button...
            {
                c.setEnabled(true);
            }
        }
    }

    private void disableButtons()
    {
        Component[] components = getComponents();
        for(Component c : components)
        {
            if(c.getClass() == JButton.class) //Is a button...
            {
                c.setEnabled(false);
            }
        }
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}