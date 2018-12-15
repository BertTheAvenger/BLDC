import javax.swing.*;
import java.awt.event.ActionEvent;

abstract class ThumbnailAction extends AbstractAction {

    /**
     *The icon if the full image we want to display.
     */
    private Icon displayPhoto;
    public ThumbnailAction(Icon photo, Icon thumb, String desc){
        displayPhoto = photo;

        // The short description becomes the tooltip of a button.
        putValue(SHORT_DESCRIPTION, desc);

        // The LARGE_ICON_KEY is actually the key for setting the
        // icon when an Action is applied to a button.
        putValue(LARGE_ICON_KEY, thumb);
    }
    abstract public void actionPerformed(ActionEvent e);
}