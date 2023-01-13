package wirtualnyswiat.organizmy;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;

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
        if(organizm.rysowanie() =='C' && this.sila <= organizm.getSila())
            organizm.kolizja(this);
            //atakowanie zwyklej rosliny
        else if (organizm.czyRoslina()) {
            organizm.kolizja(this);
            swiat.przesunOrganizm(this, organizmX, organizmY);
        }
        else {
            //jesli kolizja z tym samym gatunkiem
            if (this.rysowanie() == organizm.rysowanie()) {
                if (czyRozmnozyc(organizm))
                    rozmnazanie();
            }
            //atakowanie zmiji przez silniejszy organizm
            else if (organizm.rysowanie() == 'Z' && sila >= organizm.getSila()) {
                System.out.println("Atakujacy " + nazwa() + " pokonuje " + organizm.nazwa() + ", lecz ta go zatruwa "
                        + "i oba organizmy gina na polu (" + organizmX + ", " + organizmY + ")\n");
                swiat.usunOrganizm(organizm);
                swiat.usunOrganizm(this);
            }
            //atakowanie myszy przez zwierze nie bedace zmija z pustym polem obok
            else if (this.rysowanie() != 'Z' && organizm.rysowanie() == 'M' && swiat.czyWolnePoleObok(organizm)) {
                System.out.println(organizm.nazwa() + " uciekla na sasiednie pole przed " + nazwa()
                        + " na polu (" + organizmX + ", " + organizmY + ")\n");
                swiat.przesunOrganizmLosowo(organizm);
                swiat.przesunOrganizm(this, organizmX, organizmY);
            }
            //zwykla walka organizmow
            else {
                System.out.println("Doszlo do walki pomiedzy " + nazwa() + " a " + organizm.nazwa()
                        + " na polu (" + organizmX + ", " + organizmY + ")");
                if (this.sila >= organizm.getSila()) {

                    swiat.usunOrganizm(organizm);
                    swiat.przesunOrganizm(this, organizmX, organizmY);
                    System.out.println("Atakujacy " + nazwa() + " okazal sie silniejszy\n");
                }
                else {
                    swiat.usunOrganizm(this);
                    System.out.println("Broniacy " + organizm.nazwa() + " okazal sie silniejszy\n");
                }
            }
        }
   }

    public boolean czyRozmnozyc(Organizm organizm) {
        if (this.getWiek() < 3 || organizm.getWiek() < 3) {
            System.out.println("Nie mozna rozmnozyc " + nazwa() + " na polu (" + x + ", " + y + "), gdyz ktorys organizm jest za mlody!\n");
            return false;
        }
        else if (!swiat.czyWolnePoleObok(this)) {
            System.out.println(nazwa() + " nie moze sie rozmnozyc na polu (" + x + ", " + y + "), gdyz nie ma miejsca obok!\n");
            return false;
        }
        else {
            System.out.println(nazwa() + " sie rozmnaza na polu (" + x + ", " + y + ")\n");
            return true;
        }
    }
}
