package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

public class Leniwiec extends Zwierze {
    public Leniwiec(Swiat swiat) {
        super(swiat);
        this.sila = 2;
        this.inicjatywa = 1;
    }

    @Override
    public void akcja() {
        if(wiek % 2 == 0)
            super.akcja();
    }

    @Override
    public char rysowanie() {
        return 'L';
    }

    @Override
    public String nazwa() {
        return "LENIWIEC";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Leniwiec(swiat);
        rozmnoz(organizm);
    }
}
