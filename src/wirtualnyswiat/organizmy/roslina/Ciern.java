package wirtualnyswiat.organizmy.roslina;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Roslina;

import java.awt.*;
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
                swiat.dodajKomentarz(nazwa() + " rozprzestrzenia sie na polu (" + x + ", " + y + ")\n\n");
            }
            else
                swiat.dodajKomentarz(nazwa() + " nie mogl sie rozprzestrzenic na polu (" + x + ", " + y + "), gdyz nie bylo miejsca obok\n\n");
        }
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (this.sila >= organizm.getSila()) {
            swiat.dodajKomentarz(nazwa() + " pokonal " + organizm.nazwa() + " na polu (" + x + ", " + y + ")\n\n");
            swiat.usunOrganizm(organizm);
        }
        else
            super.kolizja(organizm);
    }

    @Override
    public Color getColor() {
        return new Color(21, 45, 101);
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
