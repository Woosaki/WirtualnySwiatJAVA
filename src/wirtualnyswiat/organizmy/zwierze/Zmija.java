package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

import java.awt.*;

public class Zmija extends Zwierze {
    public Zmija(Swiat swiat) {
        super(swiat);
        this.sila = 2;
        this.inicjatywa = 3;
    }

    @Override
    public void kolizja(Organizm organizm) {
        if (organizm.czyRoslina())
            super.kolizja(organizm);
	    else if (organizm instanceof Zmija) {
            if (czyRozmnozyc(organizm))
                rozmnazanie();
            else if (this.sila <= organizm.getSila()) {
                System.out.println(nazwa() + " zatruwa " + organizm.nazwa() + " i oba organizmy gina na polu ("
                        + organizm.getX() + ", " + organizm.getY() + ")\n");
                swiat.usunOrganizm(organizm);
                swiat.usunOrganizm(this);
            } else
                super.kolizja(organizm);
        }
    }

    @Override
    public Color getColor() {
        return new Color(105, 53, 156);
    }

    @Override
    public String nazwa() {
        return "ZMIJA";
    }

    @Override
    public void rozmnazanie() {
        Organizm organizm = new Zmija(swiat);
        rozmnoz(organizm);
    }
}
