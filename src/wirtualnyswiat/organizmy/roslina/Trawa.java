package wirtualnyswiat.organizmy.roslina;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Roslina;
import wirtualnyswiat.organizmy.zwierze.Wilk;

public class Trawa extends Roslina {
    public Trawa(Swiat swiat) {
        super(swiat);
    }

    @Override
    public char rysowanie() {
        return 'T';
    }

    @Override
    public String nazwa() {
        return "TRAWA";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Trawa(swiat);
        rozmnoz(organizm);
    }
}
