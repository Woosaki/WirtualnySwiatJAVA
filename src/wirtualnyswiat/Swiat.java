package wirtualnyswiat;

import wirtualnyswiat.organizmy.roslina.*;
import wirtualnyswiat.organizmy.zwierze.*;

import java.util.ArrayList;
import java.util.Random;

public class Swiat {

    public Swiat(){
        this.tura = 0;
        this.iloscNowychOrganizmow = 0;
        this.kolejnosc = new ArrayList<>();
        this.N = 20;
        this.mapa = new Organizm[N][N];

        for (int i = -1; i < N / 3; i++) {
            Organizm organizm = new Wilk(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 4; i++) {
            Organizm organizm = new Owca(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 5; i++) {
            Organizm organizm = new Leniwiec(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 4; i++) {
            Organizm organizm = new Zmija(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 5; i++) {
            Organizm organizm = new Mysz(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 5; i++) {
            Organizm organizm = new Trawa(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 6; i++) {
            Organizm organizm = new Guarana(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
        for (int i = -1; i < N / 10; i++) {
            Organizm organizm = new Ciern(this);
            dodajLosowoOrganizmNaMape(organizm);
        }
    }

    public int getTura(){
        return tura;
    }
    public int getRozmiar() {
        return N;
    }

    public void ustawTura(int tura) {
        this.tura = tura;
    }

    public void ustawRozmiar(int N) {
        this.N = N;
    }

    public void zwiekszIloscNowychOrganizmow(){
        iloscNowychOrganizmow++;
    }

    public void dodajDoKolejki(Organizm organizm){
        kolejnosc.add(organizm);
    }

    private void sortOrganizmow(){
        kolejnosc.sort((Organizm organizm1, Organizm organizm2) -> {
            if (organizm1.inicjatywa != organizm2.getInicjatywa()) {
                if (organizm1.inicjatywa > organizm2.getInicjatywa())
                    return -1;
                else
                    return 1;
            }
            else {
                if (organizm1.wiek > organizm2.getWiek())
                    return -1;
                else
                    return 1;
            }
        });
    }

    public void dodajKomentarz(String str) {
        komentarz += str;
    }

    public String getKomentarz() {
        return komentarz;
    }

    public void wykonajTure() {
        komentarz = "\t\t\tTura: "+ tura + "\n\n\n";
        sortOrganizmow();
        for (int i = 0; i < kolejnosc.size()- iloscNowychOrganizmow; i++) {
            kolejnosc.get(i).akcja();
            kolejnosc.get(i).zwiekszWiek();
        }
        iloscNowychOrganizmow = 0;
        tura++;
    }

    public void wyczyscMape() {
        for (int j = 0; j < N; j++) {
            for (int i = 0; i < N; i++) {
                mapa[i][j] = null;
            }
        }
        kolejnosc.clear();
    }

    private void dodajLosowoOrganizmNaMape(Organizm organizm) {
        Random random = new Random();
        int nowyX = random.nextInt(N);
        int nowyY = random.nextInt(N);
        while (czyZajeteMiejsce(nowyX, nowyY)) {
            nowyX = random.nextInt(N);
            nowyY = random.nextInt(N);
        }
        mapa[nowyX][nowyY] = organizm;
        organizm.ustawXY(nowyX, nowyY);
        kolejnosc.add(organizm);
    }

    public void dodajOrganizmNaMape(Organizm organizm, int x, int y) {
        mapa[x][y] = organizm;
        organizm.ustawXY(x, y);
        kolejnosc.add(organizm);
    }

    public void przesunOrganizm(Organizm organizm, int x, int y) {
        mapa[organizm.getX()][organizm.getY()] = null;
        organizm.ustawXY(x, y);
        mapa[x][y] = organizm;
    }

    public void przesunOrganizmLosowo(Organizm organizm) {
        int x;
        int y;
        Random random = new Random();
        do {
            x = organizm.getX();
            y = organizm.getY();

            switch (random.nextInt(8)) {
                case 0 -> {
                    x--;
                    y--;
                }
                case 1 -> y--;
                case 2 -> {
                    x++;
                    y--;
                }
                case 3 -> x++;
                case 4 -> {
                    x++;
                    y++;
                }
                case 5 -> y++;
                case 6 -> {
                    x--;
                    y++;
                }
                case 7 -> x--;
            }
        } while (wspolrzednePozaMapa(x, y) || czyZajeteMiejsce(x, y));
        organizm.ustawXY(x, y);
        mapa[x][y] = organizm;
    }

    public boolean czyZajeteMiejsce(int x, int y) {
        if(wspolrzednePozaMapa(x, y))
            return true;
        return mapa[x][y] != null;
    }

    public Organizm organizmNaPolu(int x, int y) {
        return mapa[x][y];
    }

    public void usunOrganizm(Organizm organizm) {
        kolejnosc.remove(organizm);
        mapa[organizm.getX()][organizm.getY()] = null;
    }

    public boolean wspolrzednePozaMapa(int x, int y) {
        return (x < 0 || y < 0 || x > N - 1 || y > N - 1);
    }

    public boolean czyWolnePoleObok(Organizm organizm) {
        for (int i = 0; i < 8; i++) {
            int x = organizm.getX();
            int y = organizm.getY();
            switch (i) {
                case 0 -> {
                    x--;
                    y--;
                }
                case 1 -> y--;
                case 2 -> {
                    x++;
                    y--;
                }
                case 3 -> x++;
                case 4 -> {
                    x++;
                    y++;
                }
                case 5 -> y++;
                case 6 -> {
                    x--;
                    y++;
                }
                case 7 -> x--;
            }
            if (!czyZajeteMiejsce(x, y) && !wspolrzednePozaMapa(x, y))
                return true;
        }
        return false;
    }

    private int N;
    private String komentarz;
    private final Organizm[][] mapa;
    private final ArrayList<Organizm> kolejnosc;
    private int tura;
    private int iloscNowychOrganizmow;
}
