package wirtualnyswiat;

import wirtualnyswiat.organizmy.roslina.Ciern;
import wirtualnyswiat.organizmy.roslina.Guarana;
import wirtualnyswiat.organizmy.roslina.Trawa;
import wirtualnyswiat.organizmy.zwierze.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class GameFrame extends JFrame implements ActionListener {
    private final Swiat swiat;
    private final int rozmiarSwiata;
    private JMenuItem wczytajMenu;
    private JMenuItem zapiszMenu;
    private JMenuItem oMnieMenu;
    private JTextArea komentarz;
    private JButton nastepnaTura;
    private JButton[][] przycisk;

    GameFrame() {
        super("Wirtualny Swiat JAVA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setIconImage(new ImageIcon("logoWirtualnySwiat.png").getImage());

        swiat = new Swiat();
        rozmiarSwiata = swiat.getRozmiar();

        stworzMenu();
        stworzGlownyPanel();
        stworzKomentarze();
        stworzLegende();
        stworzNastepnaTura();

        this.zmienKolory();
        this.setSize(1300, 760);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void stworzMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu plikMenu = new JMenu("Plik");

        wczytajMenu = new JMenuItem("Wczytaj");
        wczytajMenu.setIcon(new ImageIcon("wczytajGre.png"));
        wczytajMenu.addActionListener(this);
        plikMenu.add(wczytajMenu);

        zapiszMenu = new JMenuItem("Zapisz");
        zapiszMenu.setIcon(new ImageIcon("zapiszGre.png"));
        zapiszMenu.addActionListener(this);
        plikMenu.add(zapiszMenu);

        JMenu infoMenu = new JMenu("Info");
        oMnieMenu = new JMenuItem("O mnie");
        oMnieMenu.setIcon(new ImageIcon("oMnie.png"));
        oMnieMenu.addActionListener(this);
        infoMenu.add(oMnieMenu);

        menu.add(plikMenu);
        menu.add(infoMenu);

        this.setJMenuBar(menu);
    }

    private void stworzGlownyPanel() {
        JPanel panelX = new JPanel();
        panelX.setBounds(20,0,600,20);
        panelX.setLayout(new GridLayout(1, rozmiarSwiata));

        JPanel panelY = new JPanel();
        panelY.setBounds(0,20,20,600);
        panelY.setLayout(new GridLayout(rozmiarSwiata, 1));

        JPanel mapaPanel = new JPanel();
        mapaPanel.setBackground(Color.darkGray);
        mapaPanel.setBounds(20,20,600,600);
        mapaPanel.setLayout(new GridLayout(rozmiarSwiata, rozmiarSwiata));

        JLabel[] indexX = new JLabel[rozmiarSwiata];
        JLabel[] indexY = new JLabel[rozmiarSwiata];
        przycisk = new JButton[rozmiarSwiata][rozmiarSwiata];

        for(int i = 0; i < rozmiarSwiata; i++) {
            indexX[i] = new JLabel(String.valueOf(i));
            indexX[i].setVerticalAlignment(JLabel.CENTER);
            indexX[i].setHorizontalAlignment(JLabel.CENTER);
            panelX.add(indexX[i]);

            indexY[i] = new JLabel(String.valueOf(i));
            indexY[i].setVerticalAlignment(JLabel.CENTER);
            indexY[i].setHorizontalAlignment(JLabel.CENTER);
            panelY.add(indexY[i]);

            for(int j = 0; j < rozmiarSwiata; j++) {
                przycisk[i][j] = new JButton();
                przycisk[i][j].addActionListener(this);
                mapaPanel.add(przycisk[i][j]);
            }
        }
        this.add(panelX);
        this.add(panelY);
        this.add(mapaPanel);
    }

    private void stworzKomentarze() {
        komentarz = new JTextArea("");
        komentarz.setBackground(Color.LIGHT_GRAY);
        komentarz.setFont(new Font("Tangerine", Font.BOLD, 13));
        komentarz.setForeground(new Color(27,27,27));
        komentarz.setEditable(false);

        JScrollPane komentarzScroll = new JScrollPane(komentarz);
        komentarzScroll.setBackground(Color.LIGHT_GRAY);
        komentarzScroll.setBounds(640,20,600,500);
        komentarzScroll.setFocusable(false);
        komentarzScroll.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 3));

        this.add(komentarzScroll);
    }

    private void stworzLegende() {
        JPanel legenda = new JPanel();
        legenda.setBackground(Color.GRAY);
        legenda.setBounds(640,520,600,100);
        legenda.setLayout(new GridLayout(3, 3));

        JLabel wilkLabel = new JLabel("Czerwony - Wilk");
        JLabel owcaLabel = new JLabel("Brazowy - Owca");
        JLabel zmijaLabel = new JLabel("Fioletowy - Zmija");
        JLabel leniwiecLabel = new JLabel("Pomaranczowy - Leniwiec");
        JLabel myszLabel = new JLabel("Blekitny - Mysz");
        JLabel trawaLabel = new JLabel("Zielony - Trawa");
        JLabel ciernLabel = new JLabel("Granatowy - Ciern");
        JLabel guaranaLabel = new JLabel("Magenta - Guarana");
        JLabel nicLabel = new JLabel();

        ustawLegendaLabel(legenda, wilkLabel, new Color(128, 0, 0));
        ustawLegendaLabel(legenda, owcaLabel, new Color(61, 12, 2));
        ustawLegendaLabel(legenda, zmijaLabel, new Color(105, 53, 156));
        ustawLegendaLabel(legenda, leniwiecLabel, new Color(228, 155, 15));
        ustawLegendaLabel(legenda, myszLabel, new Color(28,169,201));
        ustawLegendaLabel(legenda, trawaLabel, new Color(27,131,40));
        ustawLegendaLabel(legenda, ciernLabel, new Color(21, 45, 101));
        ustawLegendaLabel(legenda, guaranaLabel, new Color(204,0,204));
        ustawLegendaLabel(legenda, nicLabel, Color.BLACK);

        this.add(legenda);
    }

    private void ustawLegendaLabel(JPanel legenda, JLabel label, Color color) {
        label.setForeground(color);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(color, 8));
        legenda.add(label);
    }

    private void stworzNastepnaTura() {
        nastepnaTura = new JButton("Nastepna tura");
        nastepnaTura.setFocusable(false);
        nastepnaTura.setBounds(20, 630, 200, 50);
        nastepnaTura.addActionListener(this);

        this.add(nastepnaTura);
    }

    public static void main(String[] args){
        new GameFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nastepnaTura) {
            swiat.wykonajTure();
            komentarz.setText(swiat.getKomentarz());
            zmienKolory();
        } else if(e.getSource() == wczytajMenu) {
            wczytajGre();
            komentarz.setText("\n\n\t\t         Wczytano poprzedni stan gry");
        } else if(e.getSource() == zapiszMenu) {
            zapiszGre();
            komentarz.setText("\n\n\t\t                   Zapisano stan gry");
        } else if (e.getSource() == oMnieMenu) {
            JOptionPane.showMessageDialog(null, "Kamil Szczukowski 186714", "Projekt stworzyl:", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void zmienKolory() {
        for (int i = 0; i < rozmiarSwiata; i++) {
            for (int j = 0; j < rozmiarSwiata; j++) {
                Organizm organizm = swiat.organizmNaPolu(i, j);
                if (organizm == null)
                    przycisk[i][j].setBackground(Color.white);
                else
                    przycisk[i][j].setBackground(organizm.getColor());
            }
        }
    }

    private void zapiszGre() {
        StringBuilder str = new StringBuilder();
        str.append(swiat.getRozmiar()).append("\n").append(swiat.getTura()).append("\n");
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Organizm organizm = swiat.organizmNaPolu(i, j);
                if (organizm != null)
                    str.append(organizm.toString()).append("\n");
            }
        }
        try {
            FileWriter zapisz = new FileWriter("swiat.txt");
            zapisz.write(str.toString());
            zapisz.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void wczytajGre() {
        swiat.wyczyscMape();
        try {
            List<String> linie = Files.readAllLines(Path.of("swiat.txt"), StandardCharsets.UTF_8);
            for (int i = 0; i < linie.size(); i++) {
                String str = linie.get(i);
                if (i == 0) {
                    //rozmiarSwiata = Integer.parseInt(str);
                    //swiat.ustawRozmiar(Integer.parseInt(str));
                } else if (i == 1) {
                    swiat.ustawTura(Integer.parseInt(str));
                }
                else {
                    List<String> atrybuty = Arrays.asList(str.split(" "));
                    String nazwa = atrybuty.get(0);
                    int sila = Integer.parseInt(atrybuty.get(1));
                    int inicjatywa = Integer.parseInt(atrybuty.get(2));
                    int wiek = Integer.parseInt(atrybuty.get(3));
                    int x = Integer.parseInt(atrybuty.get(4));
                    int y = Integer.parseInt(atrybuty.get(5));

                    Organizm organizm = null;
                    switch (nazwa) {
                        case "WILK" -> organizm = new Wilk(swiat);
                        case "OWCA" -> organizm = new Owca(swiat);
                        case "ZMIJA" -> organizm = new Zmija(swiat);
                        case "LENIWIEC" -> organizm = new Leniwiec(swiat);
                        case "MYSZ" -> organizm = new Mysz(swiat);
                        case "TRAWA" -> organizm = new Trawa(swiat);
                        case "GUARANA" -> organizm = new Guarana(swiat);
                        case "CIERN" -> organizm = new Ciern(swiat);
                    }
                    assert organizm != null;
                    organizm.ustawSila(sila);
                    organizm.ustawInicjatywa(inicjatywa);
                    organizm.ustawWiek(wiek);

                    swiat.dodajOrganizmNaMape(organizm, x, y);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        zmienKolory();
    }
}