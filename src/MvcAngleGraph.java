import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class MvcAngleGraph extends JPanel
{
    private XYSeries data;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private Font axisFont;
    private Font titleFont;

    public MvcAngleGraph()
    {
        axisFont = new Font("Dialog", Font.PLAIN, 12);
        titleFont = new Font("Dialog", Font.PLAIN, 15);


        setLayout(new GridLayout());
        setMinimumSize(new Dimension(0,0));
        setPreferredSize(new Dimension(0,0));
        data = new XYSeries("Actual Angle");
        data.add(1,2);
        data.add(500,100);
        XYSeriesCollection collection = new XYSeriesCollection(data);
        chart = ChartFactory.createXYLineChart(
                "Motor Angles : Commanded vs. Actual",
                "Commanded Angle",
                "Actual Angle",
                collection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        //Set fonts
        chart.getXYPlot().getDomainAxis().setLabelFont(axisFont);
        chart.getXYPlot().getRangeAxis().setLabelFont(axisFont);
        chart.getTitle().setFont(titleFont);

        //Set domain and range
        chart.getXYPlot().getDomainAxis().setAutoRange(false);
        chart.getXYPlot().getDomainAxis().setRange(new Range(0,2400));
        chart.getXYPlot().getRangeAxis().setAutoRange(false);
        chart.getXYPlot().getRangeAxis().setRange(new Range(0,360));



        chartPanel = new ChartPanel(chart);
        chartPanel.setMaximumDrawHeight(5000);
        chartPanel.setMaximumDrawWidth(5000);
        chartPanel.setMinimumDrawWidth(10);
        chartPanel.setMinimumDrawHeight(10);
        chartPanel.setDomainZoomable(false);
        chartPanel.setRangeZoomable(false);
        //chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        add(chartPanel);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}




