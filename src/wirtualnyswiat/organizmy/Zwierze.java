package wirtualnyswiat.organizmy;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.roslina.Ciern;
import wirtualnyswiat.organizmy.zwierze.Mysz;
import wirtualnyswiat.organizmy.zwierze.Zmija;

import java.util.Random;

public abstract class Zwierze extends Organizm {
    public Zwierze(Swiat swiat) {
        super(swiat);
    }

    @Override
    public void akcja() {
        int x;
        int y;
        Random random = new Random();
        do {
            x = getX();
            y = getY();

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
        } while (swiat.wspolrzednePozaMapa(x, y));
        if (swiat.czyZajeteMiejsce(x, y))
            kolizja(swiat.organizmNaPolu(x, y));
        else
            swiat.przesunOrganizm(this, x, y);
    }

    @Override
    public void kolizja(Organizm organizm) {
        int organizmX = organizm.getX();
        int organizmY = organizm.getY();
        //atakowanie ciernia przez slaby organizm
        if(organizm instanceof Ciern && this.sila <= organizm.getSila())
            organizm.kolizja(this);
            //atakowanie zwyklej rosliny
        else if (organizm.czyRoslina()) {
            organizm.kolizja(this);
            swiat.przesunOrganizm(this, organizmX, organizmY);
        }
        else {
            //jesli kolizja z tym samym gatunkiem
            if (this.getClass().equals(organizm.getClass())) {
                if (czyRozmnozyc(organizm))
                    rozmnazanie();
            }
            //atakowanie zmiji przez silniejszy organizm
            else if (organizm instanceof Zmija && sila >= organizm.getSila()) {
                swiat.dodajKomentarz("Atakujacy " + nazwa() + " pokonuje " + organizm.nazwa() + ", lecz ta go zatruwa "
                        + "i oba organizmy gina na polu (" + organizmX + ", " + organizmY + ")\n\n");
                swiat.usunOrganizm(organizm);
                swiat.usunOrganizm(this);
            }
            //atakowanie myszy przez zwierze nie bedace zmija z pustym polem obok
            else if (!(organizm instanceof Zmija) && organizm instanceof Mysz && swiat.czyWolnePoleObok(organizm)) {
                swiat.dodajKomentarz(organizm.nazwa() + " uciekla na sasiednie pole przed " + nazwa()
                        + " na polu (" + organizmX + ", " + organizmY + ")\n\n");
                swiat.przesunOrganizmLosowo(organizm);
                swiat.przesunOrganizm(this, organizmX, organizmY);
            }
            //zwykla walka organizmow
            else {
                swiat.dodajKomentarz("Doszlo do walki pomiedzy " + nazwa() + " a " + organizm.nazwa()
                        + " na polu (" + organizmX + ", " + organizmY + ")\n");
                if (this.sila >= organizm.getSila()) {

                    swiat.usunOrganizm(organizm);
                    swiat.przesunOrganizm(this, organizmX, organizmY);
                    swiat.dodajKomentarz("Atakujacy " + nazwa() + " okazal sie silniejszy\n\n");
                }
                else {
                    swiat.usunOrganizm(this);
                    swiat.dodajKomentarz("Broniacy " + organizm.nazwa() + " okazal sie silniejszy\n\n");
                }
            }
        }
   }

    public boolean czyRozmnozyc(Organizm organizm) {
        if (this.getWiek() < 3 || organizm.getWiek() < 3) {
            swiat.dodajKomentarz("Nie mozna rozmnozyc " + nazwa() + " na polu (" + x + ", " + y + "), gdyz ktorys organizm jest za mlody!\n\n");
            return false;
        }
        else if (!swiat.czyWolnePoleObok(this)) {
            swiat.dodajKomentarz(nazwa() + " nie moze sie rozmnozyc na polu (" + x + ", " + y + "), gdyz nie ma miejsca obok!\n\n");
            return false;
        }
        else {
            swiat.dodajKomentarz(nazwa() + " sie rozmnaza na polu (" + x + ", " + y + ")\n\n");
            return true;
        }
    }
}
