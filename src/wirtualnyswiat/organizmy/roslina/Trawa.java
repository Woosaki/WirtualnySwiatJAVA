package wirtualnyswiat.organizmy.roslina;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Roslina;

import java.awt.*;

public class Trawa extends Roslina {
    public Trawa(Swiat swiat) {
        super(swiat);
    }

    @Override
    public Color getColor() {
        return new Color(27,131,40);
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
