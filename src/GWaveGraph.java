import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;

class GWaveGraph extends JPanel
{
    private XYSeries Aphase;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private XYSeriesCollection collection;
    private Font axisFont;
    private Font titleFont;

    public GWaveGraph()
    {
        axisFont = new Font("Dialog", Font.PLAIN, 12);
        titleFont = new Font("Dialog", Font.PLAIN, 15);


        setLayout(new GridLayout());
        setMinimumSize(new Dimension(0,0));
        setPreferredSize(new Dimension(0,0));
        Aphase = new XYSeries("Phase A");

        collection = new XYSeriesCollection();
        collection.addSeries(Aphase);
        chart = ChartFactory.createXYLineChart(
                "Drive Wave",
                "Angle",
                "PWM",
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

        ValueMarker val = new ValueMarker(25);
        val.setPaint(Color.red);

        chart.getXYPlot().addDomainMarker(val);
        //Set domain and range
        //chart.getXYPlot().getDomainAxis().setAutoRange(false);
        //chart.getXYPlot().getDomainAxis().setRange(new Range(0,2400));
        //chart.getXYPlot().getRangeAxis().setAutoRange(false);
        //chart.getXYPlot().getRangeAxis().setRange(new Range(0,360));

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

    public void setSinWave(int[] sinWave)
    {
        collection.removeSeries(Aphase);

        Aphase = new XYSeries("Phase A");
        for(int i = 0; i < sinWave.length; i++)
        {
            Aphase.add(i, sinWave[i]);
        }
        collection.addSeries(Aphase);
        chart.getXYPlot().setDataset(chart.getXYPlot().getDataset());


    }
}




