import javax.swing.*;
import java.awt.event.*;

public class MvcAction extends AbstractAction
{
    private MvcEnum action;
    private Object[] params;
    MvcAction(String name, MvcEnum action, Object... params)
    {
        super(name);
        this.params = params;
        this.action = action;
    }

    public void actionPerformed(ActionEvent e)
    {
        //JOptionPane.showMessageDialog(null, "Would have done the 'Cut' action.");
        switch(action)
        {
            case REFRESH: break;
            case SETPORT:
                MvcController.setSerialPort(e.getActionCommand()); //Set port to button text.

                break;
        }
    }



}
