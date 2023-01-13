package wirtualnyswiat.organizmy.roslina;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Roslina;

import java.util.Random;

public class Ciern extends Roslina {
    public Ciern(Swiat swiat) {
        super(swiat);
        this.sila = 2;
    }

    @Override
    public void akcja() {
        Random random = new Random();
        int czyRozmnozyc = random.nextInt(5);
        if (czyRozmnozyc == 0) {
            if (swiat.czyWolnePoleObok(this)) {
                rozmnazanie();
                System.out.println(nazwa() + " rozprzestrzenia sie na polu (" + x + ", " + y + ")\n");
            }
            else
                System.out.println(nazwa() + " nie mogl sie rozprzestrzenic na polu (" + x + ", " + y + "), gdyz nie bylo miejsca obok\n");
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (this.sila >= organizm.getSila()) {
            System.out.println(nazwa() + " pokonal " + organizm.nazwa() + " na polu (" + x + ", " + y + ")\n");
            swiat.usunOrganizm(organizm);
        }
        else
            super.kolizja(organizm);
    }

    @Override
    public char rysowanie() {
        return 'C';
    }

    @Override
    public String nazwa() {
        return "CIERN";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Ciern(swiat);
        rozmnoz(organizm);
    }
}
