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

public class Gui extends JFrame{
    JFrame frameProbki;

    JMenuBar menuBar;
    JTabbedPane panele;

    JMenu MenuMenu;
    JMenuItem StartMenu, EksMenu, ZmienMenu, daneMenu, PolMenu, AngMenu;

    JLabel labelDane, energiaDane, reakcjeDane;

    JPanel rightPanel, leftPanel;

    JComboBox<String> Ksztalty, Substancje;
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
        rightPanel.setPreferredSize(new Dimension(200, 700));
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

        panele.addTab("Energia", new JPanel());
        panele.addTab("Reakcje lancuchowe", new JPanel());
        panele.addTab("Ilosc zderzen", new JPanel());

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

                frameProbki.add(new JLabel("    Dlugosc boku/promien:"));
                frameProbki.add(new JTextField());

                frameProbki.add(new JLabel("    Waga:"));
                frameProbki.add(new JTextField());

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