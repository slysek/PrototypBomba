import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatPropertiesLaf;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatSpacegrayIJTheme;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

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

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartPanel;


import java.util.Formatter;
import java.util.Properties;


public class Gui extends JFrame{

    // final - Wartości nie można zmienić w czasie działania programu
    final String Sciezka = "resources/";
    final String PolskiPlik = "Bundle_pl_PL.properties";
    final String AngielskiPlik = "Bundle_en_US.properties";

    Properties Angielski;
    Properties Polski;

    Properties Selected;   // Wartość obecnie używana

    JFrame frameProbki;

    JMenuBar menuBar;
    JTabbedPane panele;

    JMenu MenuMenu;
    JMenuItem StartMenu, EksMenu, ZmienMenu, daneMenu, PolMenu, AngMenu;

    JLabel labelDane, energiaDane, reakcjeDane;

    JPanel rightPanel, leftPanel, tabEnergy, tabNeutrons, tabUran;
    JLabel Symulacje;
    JButton Sym1, Sym2, Sym3;

    ChartPanel energy, neutrons, uran;

    JTextField sampleLenght;

    double smpl;
    double totalEnergy;

    JComboBox<String> Ksztalty, Substancje;

    Formatter formatter = new Formatter();


    public Gui() throws HeadlessException, UnsupportedLookAndFeelException {

        // Wczytanie plików Bundle_xx_XX.properties, dla każdego języka
        Polski = new Properties();
        try {
            Polski.load(new FileInputStream(Sciezka + PolskiPlik));
        } catch(IOException e) {
            System.out.println("Błąd przy wczytywaniu słownika: " + Sciezka + PolskiPlik);
        }

        Angielski = new Properties();
        try {
            Angielski.load(new FileInputStream(Sciezka + AngielskiPlik));
        } catch(IOException e) {
            System.out.println("Błąd przy wczytywaniu słownika: " + Sciezka + AngielskiPlik);
        }

        Selected = Polski;  // Domyślnie


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



        leftPanel.setLayout(new GridLayout(1,1));
        Symulacje = new JLabel(" Symulacje:");
        leftPanel.add(Symulacje);
        Sym1 = new JButton("Sym 1");
        Sym2 = new JButton("Sym 2");
        Sym3 = new JButton("Sym 3");
//        leftPanel.add(Sym1);
//        leftPanel.add(Sym2);
//        leftPanel.add(Sym3);


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

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Charts charts = new Charts(smpl);

                        JComponent energy = charts.getChart3();
                        JComponent neutrons = charts.getChart1();
                        JComponent uran = charts.getChart2();

                        double totalEnergy = (double) charts.getTotalEnergy();

                        SwingUtilities.invokeLater(new Runnable() {
                            @Override
                            public void run() {
                                JPanel tabEnergy = new JPanel(new BorderLayout());
                                tabEnergy.add(energy, BorderLayout.CENTER);

                                JPanel tabNeutrons = new JPanel(new BorderLayout());
                                tabNeutrons.add(neutrons, BorderLayout.CENTER);

                                JPanel tabUran = new JPanel(new BorderLayout());
                                tabUran.add(uran, BorderLayout.CENTER);

                                panele.addTab(Selected.getProperty("tab.energy"), tabEnergy);
                                panele.addTab(Selected.getProperty("tab.neutrons"), tabNeutrons);
                                panele.addTab(Selected.getProperty("tab.atoms"), tabUran);

                                energiaDane.setText("Wydzielona energia: ");
                                energiaDane.setText(energiaDane.getText() + String.format("%e", totalEnergy) + " MeV");

                                reakcjeDane.setText("Wydzielona reakcji: ");
                                reakcjeDane.setText(reakcjeDane.getText() + String.format("%e", (double) charts.getReakcje()) + " MeV");
                            }
                        });
                    }
                }).start();
            }
        });





        daneMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameProbki = new JFrame();
                frameProbki.setSize(400, 200);
                frameProbki.setLocationRelativeTo(null);
                frameProbki.setTitle(Selected.getProperty("label.title"));
                frameProbki.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frameProbki.setLayout(new BorderLayout());

                JPanel centerPanel = new JPanel();
                JPanel bottomPanel = new JPanel();
                JButton okButton = new JButton("OK");

                bottomPanel.add(okButton);

                okButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        smpl = Double.parseDouble(sampleLenght.getText());
                        frameProbki.dispose();
                    }
                });

                centerPanel.setLayout(new GridLayout(4, 2));

                centerPanel.add(new JLabel(Selected.getProperty(    "label.ksztalt")));

                String[] options = {
                        Selected.getProperty("shape.cube"),
                        Selected.getProperty("shape.sphere")
                };
                Ksztalty = new JComboBox<>(options);

                centerPanel.add(Ksztalty);

                sampleLenght = new JTextField();

                centerPanel.add(new JLabel(Selected.getProperty("label.dlugosc")));
                centerPanel.add(sampleLenght);

                String[] sub = {
                        Selected.getProperty("sub.uranium"),
                        Selected.getProperty("sub.plutonium")
                };
                Substancje = new JComboBox<>(sub);

                centerPanel.add(new JLabel(Selected.getProperty("label.izotop")));
                centerPanel.add(Substancje);


                frameProbki.add(centerPanel, BorderLayout.CENTER);
                frameProbki.add(bottomPanel, BorderLayout.SOUTH);

                frameProbki.setVisible(true);



            }
        });

        EksMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveChartWithDialog(energy);
                saveChartWithDialog(neutrons);
                saveChartWithDialog(uran);
            }
        });


        PolMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = Polski;
                setTitle("Bomba");
                // frameProbki.setTitle("Ustawienia probki");
                wczytajNazwy(Polski);
            }
        });

        AngMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Selected = Angielski;
                setTitle("Bomb");
                // frameProbki.setTitle("Sample configuration");
                wczytajNazwy(Angielski);
            }
        });

        wczytajNazwy(Selected);



    }


    private void wczytajNazwy(Properties properties) {
        MenuMenu.setText(properties.getProperty("menu.file"));
        StartMenu.setText(properties.getProperty("menu.start"));
        EksMenu.setText(properties.getProperty("menu.export"));
        daneMenu.setText(properties.getProperty("menu.enterData"));
        ZmienMenu.setText(properties.getProperty("menu.language"));
        PolMenu.setText(properties.getProperty("menu.polish"));
        AngMenu.setText(properties.getProperty("menu.english"));
        Symulacje.setText(properties.getProperty("label.simulations"));
        Sym1.setText(properties.getProperty("label.sim1"));
        Sym2.setText(properties.getProperty("label.sim2"));
        Sym3.setText(properties.getProperty("label.sim3"));
        labelDane.setText(properties.getProperty("label.data"));
        energiaDane.setText(properties.getProperty("label.energia"));
        reakcjeDane.setText(properties.getProperty("label.reakcje"));
        try {
            panele.setTitleAt(0, properties.getProperty("tab.energy"));
            panele.setTitleAt(1, properties.getProperty("tab.neutrons"));
            panele.setTitleAt(2, properties.getProperty("tab.atoms"));
        } catch (Exception e) {} // Jeżeli nie istnieje
    }


    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        Gui okno = new Gui();
        okno.setVisible(true);
    }


    public void saveChartWithDialog(ChartPanel chartPanel) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Zapisz wykres");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG Image", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            if (!filePath.toLowerCase().endsWith(".jpg") && !filePath.toLowerCase().endsWith(".jpeg")) {
                filePath += ".jpg";
            }

            try {
                JFreeChart chart = chartPanel.getChart();
                ChartUtilities.saveChartAsJPEG(new File(filePath), chart, chartPanel.getWidth(), chartPanel.getHeight());
                System.out.println("Wykres zapisany pomyślnie: " + filePath);
            } catch (IOException e) {
                System.out.println("Błąd podczas zapisywania wykresu: " + e.getMessage());
            }
        }
    }

}