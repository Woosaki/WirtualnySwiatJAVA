package wirtualnyswiat.organizmy;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;

import java.util.Random;

public abstract class Roslina extends Organizm {
    public Roslina(Swiat swiat) {
        super(swiat);
    }

    @Override
    public void akcja() {
        Random random = new Random();
        int czyRozmnozyc = random.nextInt(10);
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
        swiat.dodajKomentarz(organizm.nazwa() + " zjadl " + nazwa() + " na polu (" + x + ", " + y + ")\n\n");
        swiat.usunOrganizm(this);
    }
}
