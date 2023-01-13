package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

import java.awt.*;

public class Mysz extends Zwierze {
    public Mysz(Swiat swiat) {
        super(swiat);
        this.sila = 1;
        this.inicjatywa = 6;
    }

    @Override
    public Color getColor() {
        return new Color(28,169,201);
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
