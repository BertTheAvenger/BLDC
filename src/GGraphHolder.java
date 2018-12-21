import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class GGraphHolder extends JPanel {

    GAngleGraph angleGraph;
    GWaveGraph waveGraph;
    GGraphHolder()
    {
        super(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10), new TitledBorder(new LineBorder(Color.GRAY, 1), "Graphs")));
        GridBagConstraints c = new GridBagConstraints();

        angleGraph = new GAngleGraph();
        //angleGraph.setBorder(new EmptyBorder(10,10,10,10));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = .33;
        add(angleGraph, c);

        c.gridy = 1;
        waveGraph = new GWaveGraph();
        add(waveGraph, c);

        c.gridy = 2;
        add(new JPanel(), c);

    }

    void setSinWave(int[] sinArr){waveGraph.setSinWave(sinArr);}

}
