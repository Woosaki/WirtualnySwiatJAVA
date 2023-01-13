package wirtualnyswiat.organizmy.zwierze;

import wirtualnyswiat.Organizm;
import wirtualnyswiat.Swiat;
import wirtualnyswiat.organizmy.Zwierze;

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
	    else if (organizm.rysowanie() == 'Z') {
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
    public char rysowanie() {
        return 'Z';
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
