package wirtualnyswiat;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private Swiat swiat;
    private JPanel mapaPanel;
    private JPanel komentarzPanel;
    private JLabel komentarzLabel;
    private JPanel legenda;
    private JButton nastepnaTura;
    private JButton[][] przycisk;

    GameFrame() {
        super("Wirtualny Swiat JAVA");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);

        swiat = new Swiat();

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

        komentarzPanel = new JPanel();
        komentarzPanel.setBackground(Color.LIGHT_GRAY);
        komentarzPanel.setBounds(640,20,600,500);
        komentarzLabel = new JLabel();
        komentarzPanel.add(komentarzLabel);

        stworzLegende();

        nastepnaTura = new JButton("Nastepna tura");
        nastepnaTura.setFocusable(false);
        nastepnaTura.setBounds(20, 630, 200, 50);
        nastepnaTura.addActionListener(this);

        this.zmienKolory();
        this.add(mapaPanel);
        this.add(komentarzPanel);
        this.add(nastepnaTura);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setVisible(true);
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

        ustawKolorLabel(wilkLabel, new Color(128, 0, 0));
        ustawKolorLabel(owcaLabel, new Color(61, 12, 2));
        ustawKolorLabel(zmijaLabel, new Color(105, 53, 156));
        ustawKolorLabel(leniwiecLabel, new Color(228, 155, 15));
        ustawKolorLabel(myszLabel, new Color(28,169,201));
        ustawKolorLabel(trawaLabel, new Color(27,131,40));
        ustawKolorLabel(ciernLabel, new Color(21, 45, 101));
        ustawKolorLabel(guaranaLabel, new Color(204,0,204));
        ustawKolorLabel(nicLabel, Color.BLACK);

        legenda.add(wilkLabel);
        legenda.add(owcaLabel);
        legenda.add(zmijaLabel);
        legenda.add(leniwiecLabel);
        legenda.add(myszLabel);
        legenda.add(trawaLabel);
        legenda.add(ciernLabel);
        legenda.add(guaranaLabel);
        legenda.add(nicLabel);

        this.add(legenda);
    }

    private void ustawKolorLabel(JLabel label, Color color) {
        label.setForeground(color);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(color, 3));
    }

    public static void main(String[] args){
        new GameFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == nastepnaTura) {
            swiat.wykonajTure();
            zmienKolory();
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