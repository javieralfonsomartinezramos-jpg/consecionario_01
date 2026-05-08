package view;

import java.util.List;
import java.util.Scanner;
import model.Catalog;
import model.Motorbike;

/**
 * This class is responsible for displaying information to the user
 * and handling user input in the Motorcycle Catalog Application.
 * It provides methods for showing menus, messages, and details about motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 1.0
 */
public class Console {
    
    /**
     * Displays the welcome instructions for the application.
     */
    public void showInstructions() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("Welcome to the Motorcycle Catalog Application!");
        System.out.println("You can view and manage chosen motorcycles.");
        System.out.println("Please follow the prompts to navigate through the application.");
        System.out.println("--------------------------------------------------------------");
    }
    
    /**
     * Displays the main menu for catalog options.
     */
    public void showCatalogMenu() {
        System.out.println("1. Display Catalog");
        System.out.println("2. Select Motorbike");
        System.out.println("3. Find Motorcycle by Name");
        System.out.println("4. Filter Motorcycles by parameters");
        System.out.println("5. Add Motorcycle");
        System.out.println("6. Clear Catalog");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }
    
    /**
     * Displays the menu for motorbike-specific options.
     */
    public void showMotorbikeMenu() {
        System.out.println("1. View details");
        System.out.println("2. Remove Motorbike");
        System.out.println("3. Update parameters");
        System.out.println("4. Change price");
        System.out.println("5. Go back to catalog");
        System.out.print("Choose an option: ");
    }
    
    /**
     * Displays a success message to the user.
     *
     * @param message the success message to display
     */
    public void showSuccessMessage(String message) {
        System.out.println("Success: " + message);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
    
    /**
     * Displays the details of a specific motorbike.
     *
     * @param motorbike the motorbike whose details are to be displayed
     */
    public void displayMotorbike(Motorbike motorbike) {
        System.out.println("Model: " + motorbike.getModel());
        System.out.println("Price: " + motorbike.getPrice() + " pln");
        System.out.println("Displacement: " + motorbike.getDisplacement() + " cm3");
        System.out.println("Power: " + motorbike.getPower() + " kW");
    }

    /**
     * Gets user input to create a new Motorbike object.
     *
     * @return a Motorbike object created from user input
     */
    public Motorbike getMotorbikeInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter displacement: ");
        int displacement = scanner.nextInt();
        System.out.print("Enter power: ");
        int power = scanner.nextInt();
        return new Motorbike(model, price, displacement, power);
    }
    
    /**
     * Displays the catalog information, including the brand and list of motorbikes.
     *
     * @param catalog the catalog to display
    
    public void displayCatalog(Catalog catalog) {
        System.out.println("Catalog: " + catalog.getBrand());
        if (catalog.getMotorbikes().isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            for (Motorbike motorbike : catalog.getMotorbikes()) {
                System.out.println("- " + motorbike.getModel()); 
            }
        }
    }
    */
    
    /**
     * Displays a list of motorbikes.
     *
     * @param motorbikes the list of motorbikes to display
     */
    public void displayList(List<Motorbike> motorbikes) {
        for (Motorbike motorbike : motorbikes) {
            System.out.println("- " + motorbike.getModel()); 
        }
    }
}
