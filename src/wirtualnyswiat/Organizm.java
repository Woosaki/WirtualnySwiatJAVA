package wirtualnyswiat;

public abstract class Organizm {
    public abstract void akcja();
    public abstract void kolizja(Organizm organizm);
    public abstract char rysowanie();
    public abstract String nazwa();
    public abstract void rozmnazanie();

    public Organizm(Swiat swiat){
        this.swiat = swiat;
        this.sila = 0;
        this.inicjatywa = 0;
        this.x = 0;
        this.y = 0;
        this.wiek = 0;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getInicjatywa() { return inicjatywa; }
    public int getSila() { return sila; }
    public int getWiek() { return wiek; }

    public void ustawXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void ustawSila(int sila) {
        this.sila = sila;
    }

    public void ustawInicjatywa(int inicjatywa) {
        this.inicjatywa = inicjatywa;
    }

    public void ustawWiek(int wiek) {
        this.wiek = wiek;
    }

    public void zwiekszWiek() {
        this.wiek++;
    }

    public boolean czyRoslina() {
        return this.rysowanie() == 'T' || this.rysowanie() == 'C' || this.rysowanie() == 'G';
    }

    public void rozmnoz(Organizm organizm) {
        organizm.ustawXY(x, y);
        swiat.przesunOrganizmLosowo(organizm);
        swiat.dodajDoKolejki(organizm);
        swiat.zwiekszIloscNowychOrganizmow();
    }

    protected int sila;
    protected int inicjatywa;
    protected int x;
    protected int y;
    protected int wiek;
    protected Swiat swiat;
}
