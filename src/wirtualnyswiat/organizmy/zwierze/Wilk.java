package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

import java.awt.*;

public class Wilk extends Zwierze {
    public Wilk(Swiat swiat) {
        super(swiat);
        this.sila = 9;
        this.inicjatywa = 5;
    }

    @Override
    public Color getColor() {
        return new Color(128, 0, 0);
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
