package wirtualnyswiat;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int wybor;
        Swiat swiat = new Swiat();
        swiat.rysujSwiat();

        Scanner scanner = new Scanner(System.in);
        wybor = 1;

        while (wybor != 4) {
            wybor = scanner.nextInt();
            switch (wybor) {
                case 1 -> {
                    swiat.wykonajTure();
                    swiat.rysujSwiat();
                }
                case 2 -> {
                    //swiat.zapiszSwiat();
                    System.out.println("Zapisano obecny stan gry!\n");
                    swiat.rysujSwiat();
                }
                case 3 -> {
                    //swiat.wczytajSwiat();
                    System.out.println("Wczytano poprzedni stan gry!\n");
                    swiat.rysujSwiat();
                }
                default -> {
                    System.out.println("Nie ma takiej opcji w menu\n");
                    swiat.rysujSwiat();
                }
            }
        }
    }
}