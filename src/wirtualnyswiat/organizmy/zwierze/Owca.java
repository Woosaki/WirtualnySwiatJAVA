package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

public class Owca extends Zwierze {
    public Owca(Swiat swiat) {
        super(swiat);
        this.sila = 4;
        this.inicjatywa = 4;
    }

    @Override
    public char rysowanie() {
        return 'O';
    }

    @Override
    public String nazwa() {
        return "OWCA";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Owca(swiat);
        rozmnoz(organizm);
    }
}
