import Serial.RXCommand;
import Serial.TXCommand;
import Serial.TXCommands.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Executable;
import java.util.HashMap;
import java.util.Map;

class GCPSerialTest extends JPanel //GUI Motor Control Panel. Used by GControlPanel
{
    private static HashMap<String, TXCommand> txBtns = new HashMap<>();
    static {
        txBtns.put("ACK", new TXACK());
        txBtns.put("SETMODE", new TXSETMODE((byte) 1));
        txBtns.put("ADD SHORTS", new TXADDSHORTS((short)1231,(short)25));
        txBtns.put("TOTAL DATA", new TXTOTALDATA());


    }
    public GCPSerialTest() //ControlPanel Motor.
    {
        super(new GridLayout());
        for(Map.Entry<String, TXCommand> entry : txBtns.entrySet()) {
            add(new JButton(new AbstractAction(entry.getKey()) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SerialHandler.sendSerialCommand(entry.getValue());
                }
            }));
        }
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(super.getMaximumSize().width, 100);
    }

}