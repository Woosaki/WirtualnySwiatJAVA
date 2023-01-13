package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

public class Mysz extends Zwierze {
    public Mysz(Swiat swiat) {
        super(swiat);
        this.sila = 1;
        this.inicjatywa = 6;
    }

    @Override
    public char rysowanie() {
        return 'M';
    }

    @Override
    public String nazwa() {
        return "MYSZ";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Mysz(swiat);
        rozmnoz(organizm);
    }
}
