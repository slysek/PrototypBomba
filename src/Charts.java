import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class Charts {

//    List<Integer> neutrons = new ArrayList<Integer>();
//    List<Double> uran = new ArrayList<Double>();

    Charts (){
        XYSeries series1 = new XYSeries("Neutrons number");
        XYSeries series2 = new XYSeries("Uran atoms number");
        XYSeries series3 = new XYSeries("Energy in MeV");

        Collisions c = new Collisions(15);

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

        JFreeChart chart = ChartFactory.createXYAreaChart(
                "Neutrons chart",//Tytul
                "Time", // opisy osi
                "Neutrons",
                dataset, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // legenda
                true, // tooltips
                false
        );

        ChartFrame frame = new ChartFrame("Neutrons chart", chart);
        frame.setVisible(true);
        frame.setSize(500,500);

        XYSeriesCollection dataset2 = new XYSeriesCollection();

        dataset2.addSeries(series2);

        JFreeChart chart2 = ChartFactory.createXYAreaChart(
                "Uran chart",//Tytul
                "Time", // opisy osi
                "No. of uran atoms",
                dataset2, // Dane
                PlotOrientation.VERTICAL, // Orjentacja wykresu /HORIZONTAL
                true, // legenda
                true, // tooltips
                false
        );

        ChartFrame frame2 = new ChartFrame("Uran chart", chart2);
        frame2.setVisible(true);
        frame2.setSize(500,500);

        XYSeriesCollection dataset3 = new XYSeriesCollection();

        dataset3.addSeries(series3);

        JFreeChart chart3 = ChartFactory.createXYAreaChart(
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

        ChartFrame frame3 = new ChartFrame("Energy chart", chart3);
        frame3.setVisible(true);
        frame3.setSize(500,500);
    }

    public static void main(String[] args) {
        Charts charts = new Charts();
    }
}
