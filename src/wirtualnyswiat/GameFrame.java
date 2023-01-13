package wirtualnyswiat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame implements ActionListener {
    private Swiat swiat;
    private JPanel mapaPanel;
    private JPanel komentarze;
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

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                przycisk[i][j] = new JButton();
                mapaPanel.add(przycisk[i][j]);
            }
        }

        komentarze = new JPanel();
        komentarze.setBackground(Color.lightGray);
        komentarze.setBounds(640,20,600,600);

        nastepnaTura = new JButton("Nastepna tura");
        nastepnaTura.setFocusable(false);
        nastepnaTura.setBounds(20, 630, 200, 50);
        nastepnaTura.addActionListener(this);

        this.add(mapaPanel);
        this.add(komentarze);
        this.add(nastepnaTura);
        this.setSize(1280, 720);
        this.setResizable(false);
        this.setVisible(true);
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
                    przycisk[i][j].setText("");
                else
                    przycisk[i][j].setText(Character.toString(organizm.rysowanie()));
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