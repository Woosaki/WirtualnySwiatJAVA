package wirtualnyswiat.organizmy.roslina;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Roslina;
import wirtualnyswiat.organizmy.zwierze.Wilk;

import java.awt.*;

public class Guarana extends Roslina {
    public Guarana(Swiat swiat) {
        super(swiat);
    }

    @Override
    public void kolizja(Organizm organizm) {
        super.kolizja(organizm);
        System.out.println("GUARANA sprawila ze " + organizm.nazwa() + " zwiekszyl sile o 3!\n");
        organizm.ustawSila(organizm.getSila() + 3);
    }

    @Override
    public Color getColor() {
        return new Color(204,0,204);
    }

    @Override
    public String nazwa() {
        return "GUARANA";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Guarana(swiat);
        rozmnoz(organizm);
    }
}
