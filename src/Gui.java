import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Formatter;


public class Gui extends JFrame{
    JFrame frameProbki;

    JMenuBar menuBar;
    JTabbedPane panele;

    JMenu MenuMenu;
    JMenuItem StartMenu, EksMenu, ZmienMenu, daneMenu, PolMenu, AngMenu;

    JLabel labelDane, energiaDane, reakcjeDane;

    JPanel rightPanel, leftPanel, tabEnergy, tabNeutrons, tabUran;

    ChartPanel energy, neutrons, uran;

    JTextField sampleLenght;

    JComboBox<String> Ksztalty, Substancje;

    Formatter formatter = new Formatter();


    public Gui() throws HeadlessException, UnsupportedLookAndFeelException {



        FlatSpacegrayIJTheme.setup();


        setTitle("Bomba");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        menuBar = new JMenuBar();
        panele = new JTabbedPane();
        rightPanel = new JPanel();
        rightPanel.setPreferredSize(new Dimension(250, 700));
        leftPanel = new JPanel();
        add(BorderLayout.WEST, leftPanel);
        add(BorderLayout.EAST, rightPanel);
        add(BorderLayout.CENTER, panele);

        MenuMenu = new JMenu("Menu");
        menuBar.add(MenuMenu);
        StartMenu = new JMenuItem("Start");
        MenuMenu.add(StartMenu);
        EksMenu = new JMenuItem("Eksportowanie wyników");
        MenuMenu.add(EksMenu);
        daneMenu = new JMenuItem("Wprowadz dane");
        MenuMenu.add(daneMenu);
        ZmienMenu = new JMenu("Zmień język");
        MenuMenu.add(ZmienMenu);
        PolMenu = new JMenuItem("Polski");
        ZmienMenu.add(PolMenu);
        AngMenu = new JMenuItem("Angielski");
        ZmienMenu.add(AngMenu);
        this.setJMenuBar(menuBar);



        leftPanel.setLayout(new GridLayout(6,1));
        leftPanel.add(new JLabel(" Symulacje:"));
        leftPanel.add(new JButton("Sym 1"));
        leftPanel.add(new JButton("Sym 2"));
        leftPanel.add(new JButton("Sym 3"));


        rightPanel.setLayout(new GridLayout(6,1));
        labelDane = new JLabel("Dane");
        labelDane.setHorizontalAlignment(SwingConstants.CENTER);
        rightPanel.add(labelDane);
        energiaDane = new JLabel("Wydzielona energia: ");
        reakcjeDane = new JLabel("Ilości reakcji lancuchowych: ");
        rightPanel.add(energiaDane);
        rightPanel.add(reakcjeDane);

        StartMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Charts charts = new Charts();

                if(!sampleLenght.getText().equals("Podaj dlugosc w mm")){
                    charts.setSampleLenght(Double.parseDouble(sampleLenght.getText()));
                }

                energy = charts.getChart3();
                neutrons = charts.getChart1();
                uran = charts.getChart2();

                tabEnergy = new JPanel(new BorderLayout());
                tabEnergy.add(energy, BorderLayout.CENTER);

                tabNeutrons = new JPanel(new BorderLayout());
                tabNeutrons.add(neutrons, BorderLayout.CENTER);

                tabUran = new JPanel(new BorderLayout());
                tabUran.add(uran, BorderLayout.CENTER);

                panele.addTab("Energia", tabEnergy);
                panele.addTab("Ilosc neutronow", tabNeutrons);
                panele.addTab("Ilosc atomow uranu", tabUran);

                energiaDane.setText("Wydzielona energia: " + formatter.format("%e", (double)  charts.getTotalEnergy()) + "MeV");

            }
        });



        daneMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameProbki = new JFrame();
                frameProbki.setSize(400, 200);
                frameProbki.setLocationRelativeTo(null);
                frameProbki.setTitle("Ustawienia probki");
                frameProbki.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameProbki.setLayout(new GridLayout(4, 2));

                frameProbki.add(new JLabel("    Ksztalt probki:"));

                String[] options = {"Szescian", "Kula"};
                Ksztalty = new JComboBox<>(options);

                frameProbki.add(Ksztalty);

                sampleLenght = new JTextField("Podaj dlugosc w mm");

                frameProbki.add(new JLabel("    Dlugosc boku/promien:"));
                frameProbki.add(sampleLenght);

                String[] sub = {"Uran 235", "Pluton 239"};
                Substancje = new JComboBox<>(sub);

                frameProbki.add(new JLabel("    Izotop:"));
                frameProbki.add(Substancje);


                frameProbki.setVisible(true);



            }
        });


    }


    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Gui okno = new Gui();
        okno.setVisible(true);
    }



}