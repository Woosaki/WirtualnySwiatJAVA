package wirtualnyswiat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class GameFrame extends JFrame implements ActionListener {
    private Swiat swiat;
    private JMenuBar menu;
    private JMenu plikMenu;
    private JMenu infoMenu;
    private JMenuItem wczytajMenu;
    private JMenuItem zapiszMenu;
    private JMenuItem oMnieMenu;
    private JPanel mapaPanel;
    private JTextArea komentarz;
    private JScrollPane komentarzScroll;
    private JPanel legenda;
    private JButton nastepnaTura;
    private JButton[][] przycisk;

    GameFrame() {
        super("Wirtualny Swiat JAVA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        swiat = new Swiat();

        stworzMenu();

        mapaPanel = new JPanel();
        mapaPanel.setBackground(Color.darkGray);
        mapaPanel.setBounds(20,20,600,600);
        mapaPanel.setLayout(new GridLayout(20, 20));

        przycisk = new JButton[20][20];

        for(int i = 0; i < 20; i++) {
            for(int j = 0; j < 20; j++) {
                przycisk[i][j] = new JButton();
                mapaPanel.add(przycisk[i][j]);
            }
        }

        stworzKomentarze();

        stworzLegende();

        nastepnaTura = new JButton("Nastepna tura");
        nastepnaTura.setFocusable(false);
        nastepnaTura.setBounds(20, 630, 200, 50);
        nastepnaTura.addActionListener(this);

        this.zmienKolory();
        this.add(mapaPanel);
        this.add(nastepnaTura);
        this.setSize(1300, 760);
        this.setResizable(false);
        this.setVisible(true);
    }

    private void stworzMenu() {
        menu = new JMenuBar();
        plikMenu = new JMenu("Plik");

        wczytajMenu = new JMenuItem("Wczytaj");
        wczytajMenu.addActionListener(this);
        plikMenu.add(wczytajMenu);

        zapiszMenu = new JMenuItem("Zapisz");
        zapiszMenu.addActionListener(this);
        plikMenu.add(zapiszMenu);

        infoMenu = new JMenu("Info");
        oMnieMenu = new JMenuItem("O mnie");
        oMnieMenu.addActionListener(this);
        infoMenu.add(oMnieMenu);

        menu.add(plikMenu);
        menu.add(infoMenu);

        this.setJMenuBar(menu);
    }

    private void stworzKomentarze() {
        komentarz = new JTextArea("");
        komentarz.setBackground(Color.LIGHT_GRAY);
        komentarz.setFont(new Font("Tangerine", Font.BOLD, 13));
        komentarz.setForeground(new Color(27,27,27));
        komentarz.setEditable(false);

        komentarzScroll = new JScrollPane(komentarz);
        komentarzScroll.setBackground(Color.LIGHT_GRAY);
        komentarzScroll.setBounds(640,20,600,500);
        komentarzScroll.setFocusable(false);

        this.add(komentarzScroll);
    }

    private void stworzLegende() {
        legenda = new JPanel();
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

        ustawLegendaLabel(wilkLabel, new Color(128, 0, 0));
        ustawLegendaLabel(owcaLabel, new Color(61, 12, 2));
        ustawLegendaLabel(zmijaLabel, new Color(105, 53, 156));
        ustawLegendaLabel(leniwiecLabel, new Color(228, 155, 15));
        ustawLegendaLabel(myszLabel, new Color(28,169,201));
        ustawLegendaLabel(trawaLabel, new Color(27,131,40));
        ustawLegendaLabel(ciernLabel, new Color(21, 45, 101));
        ustawLegendaLabel(guaranaLabel, new Color(204,0,204));
        ustawLegendaLabel(nicLabel, Color.BLACK);

        this.add(legenda);
    }

    private void ustawLegendaLabel(JLabel label, Color color) {
        label.setForeground(color);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(color, 8));
        legenda.add(label);
    }

    public static void main(String[] args){
        new GameFrame();
    }

    public void dodajDoKomentarza(String str){
        komentarz.append(str);
    }

    public void wyczyscKomentarz() {
        komentarz.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nastepnaTura) {
            swiat.wykonajTure();
            komentarz.setText(swiat.getKomentarz());
            zmienKolory();
        } else if(e.getSource() == wczytajMenu) {
            System.out.println("Wczytano poprzedni stan gry");
        } else if(e.getSource() == zapiszMenu) {
            System.out.println("Zapisano stan gry");
        } else if (e.getSource() == oMnieMenu) {
            JOptionPane.showMessageDialog(null, "Kamil Szczukowski 186714", "Projekt stworzyl:", JOptionPane.PLAIN_MESSAGE);
        }
    }

    private void zmienKolory() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                Organizm organizm = swiat.organizmNaPolu(i, j);
                if (organizm == null)
                    przycisk[i][j].setBackground(Color.white);
                else
                    przycisk[i][j].setBackground(organizm.getColor());
            }
        }
    }
}

/*public class Main {
    public static void main(String[] args) throws IOException {
        int wybor;
        Swiat swiat = new Swiat();
        swiat.rysujSwiat();

        Scanner scanner = new Scanner(System.in);
        wybor = 1;

        while (wybor != 4) {
            wybor = scanner.nextInt();
            switch (wybor) {
                case 1 -> {
                    swiat.wykonajTure();
                    swiat.rysujSwiat();
                }
                case 2 -> {
                    //swiat.zapiszSwiat();
                    System.out.println("Zapisano obecny stan gry!\n");
                    swiat.rysujSwiat();
                }
                case 3 -> {
                    //swiat.wczytajSwiat();
                    System.out.println("Wczytano poprzedni stan gry!\n");
                    swiat.rysujSwiat();
                }
                default -> {
                    System.out.println("Nie ma takiej opcji w menu\n");
                    swiat.rysujSwiat();
                }
            }
        }
    }
}*/