package view;

import java.util.Scanner;

/**
 *
 * @author Kamil Kotorc
 * @version 1.0
 * 
 */

public class Display {
    
    public static void menu(){ 
    
    Scanner in = new Scanner(System.in);
    
    String input = in.nextLine();
    
    System.out.println("Wybierz opje: ");
    System.out.println("1. Wyswietl katalogs");
    System.out.println("1. Dodaj motocykl do katalogu");
    System.out.println("3. Usun model z katalogu");
    System.out.println("4. Wyjscie");
    
        switch(input) {
            case "1":
                // kontroler
                break;
            case "2":
                // kontroler
                break;
            case "3":
                // kontroler
                break;
            case "4":
                // kontroler
                break;
            default:
                System.out.println("Niepoprawny wybor, sproboj ponownie");
        }

    }
    
}
