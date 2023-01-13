package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat) {
        super(swiat);
        this.sila = 9;
        this.inicjatywa = 5;
    }

    @Override
    public char rysowanie() {
        return 'W';
    }

    @Override
    public String nazwa() {
        return "WILK";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Wilk(swiat);
        rozmnoz(organizm);
    }
}
