import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Graph extends JPanel
{
    private ArrayList<XYSeries> lineData;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private Font axisFont;
    private Font titleFont;

    public Graph()
    {
        axisFont = new Font("Dialog", Font.PLAIN, 12);
        titleFont = new Font("Dialog", Font.PLAIN, 15);

        setLayout(new GridLayout());
        setMinimumSize(new Dimension(0,0));
        setPreferredSize(new Dimension(0,0));
        final XYSeries series = new XYSeries("Random Data");
        series.add(1.0, 500.2);
        series.add(5.0, 694.1);
        series.add(4.0, 100.0);
        series.add(12.5, 734.4);
        series.add(17.3, 453.2);
        series.add(21.2, 500.2);
        series.add(21.9, null);
        series.add(25.6, 734.4);
        series.add(30.0, 453.2);
        final XYSeriesCollection data = new XYSeriesCollection(series);
        chart = ChartFactory.createXYLineChart(
                "XY Series Demo",
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.getXYPlot().getDomainAxis().setLabelFont(axisFont);
        chart.getXYPlot().getRangeAxis().setLabelFont(axisFont);
        chart.getTitle().setFont(titleFont);

        chartPanel = new ChartPanel(chart);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        add(chartPanel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}




