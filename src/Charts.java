import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;



public class Charts {

//    List<Integer> neutrons = new ArrayList<Integer>();
//    List<Double> uran = new ArrayList<Double>();

    JFreeChart chart;
    JFreeChart chart2;
    JFreeChart chart3;

    long totalEnergy;

    double sampleLenght;

    Collisions c;

    Charts (){
        XYSeries series1 = new XYSeries("Neutrons number");
        XYSeries series2 = new XYSeries("Uran atoms number");
        XYSeries series3 = new XYSeries("Energy in MeV");

        c = new Collisions();

        totalEnergy = c.getTotalEnergy();

        int size = c.getListSize();

        int sizeEnergy = c.getEnergySize();
        for (int i = 0; i < size; i++) {
            series1.add(i, c.getNeutrons(i));
        }

        for (int i = 0; i < size; i++) {
            series2.add(i, c.getUran(i));
        }

        for (int i = 0; i < sizeEnergy; i++) {
            series3.add(i, c.getEnergy(i));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(series1);

        chart = ChartFactory.createXYAreaChart(
                "Neutrons chart",//Tytul
                "Time", // opisy osi
                "Neutrons",
                dataset, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // legenda
                true, // tooltips
                false
        );


        XYSeriesCollection dataset2 = new XYSeriesCollection();

        dataset2.addSeries(series2);

        chart2 = ChartFactory.createXYAreaChart(
                "Uran chart",//Tytul
                "Time", // opisy osi
                "No. of uran atoms",
                dataset2, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // legenda
                true, // tooltips
                false
        );


        XYSeriesCollection dataset3 = new XYSeriesCollection();

        dataset3.addSeries(series3);

        chart3 = ChartFactory.createXYAreaChart(
                "Energy chart",//Tytul
                "Time", // opisy osi
                "Energy, MeV",
                dataset3, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // legenda
                true, // tooltips
                false
        );
//        Dla osi logatytmicznej
//        XYPlot plot3 = chart3.getXYPlot();
//        LogarithmicAxis yAxis = new LogarithmicAxis("Energy, MeV");
//        plot3.setRangeAxis(yAxis);

    }

    ChartPanel getChart1(){
        return new ChartPanel(chart);
    }

    ChartPanel getChart2(){
        return new ChartPanel(chart2);
    }

    ChartPanel getChart3(){
        return new ChartPanel(chart3);
    }
    
    long getTotalEnergy(){
        return totalEnergy;
    }

    void setSampleLenght(double smpl){
        c.setSampleLenght(smpl);
    }
}
