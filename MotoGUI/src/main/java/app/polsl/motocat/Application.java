package app.polsl.motocat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.AppException;
import model.Catalog;
import model.Motorbike;
import view.Console;
import controller.CatalogManager;
import controller.MotorbikeManager;
import view.GUI;


/**
 * The main class of the Motorcycle Catalog Application.
 *
 * It initializes the application.
 * Loads a motorcycle catalog. 
 * Manages user interactions.
 * Allows manipulation of the catalog data.
 *
 * This class contains the main method, which serves as the entry point of the application.
 *
 * @version 1.1
 * @author Kamil Kotorc
 *
 * Changelog:
 * 1.1 
 * - Added support for catalog name from command-line arguments (args).
 * 1.0 
 * - Initial version.
 */
public class Application {

     /**
     * The main method that runs the application.
     *
     * It creates an instance of the motorcycle catalog. 
     * The catalog name can be provided as the first command-line argument (args[0]).
     * If no argument is given, the application will prompt the user to input a catalog name.
     * This method also adds sample motorbikes to the catalog and then allows the user to interact with the catalog.
     * The user can view, search, add, update, and delete motorbikes, as well as clear the entire catalog or exit.
     *
     * @param args Command line parameters, the first argument (args[0]) should be the catalog name.
     * If missing, the application prompts the user for the catalog name.
     * @throws AppException
     */
    public static void main(String[] args) throws AppException {
        
        Console console = new Console();
        
        // Check if catalog name is provided as a command-line argument
        // If not, prompt the user to enter a catalog name
        String loadedCatalogName;
        if (args.length > 0) {
            loadedCatalogName = args[0];
        } else {
            console.showErrorMessage("Catalog name not provided as an argument.");
            System.out.print("Please enter the catalog name: ");
            Scanner scanner = new Scanner(System.in);
            loadedCatalogName = scanner.nextLine();
        }
        
        // Initialize catalog with given name
        Catalog loadedCatalog = new Catalog(loadedCatalogName);
        
        // Adding sample motorbikes to the catalog
        List<Motorbike> loadedMotorbikes = new ArrayList<>();
        Motorbike chart = new Motorbike("Chart", 30000.00, 750, 21);
        loadedMotorbikes.add(chart);
        Motorbike kadet = new Motorbike("Kadet", 19500.00, 125, 7);
        loadedMotorbikes.add(kadet);
        Motorbike pony = new Motorbike("Pony", 15999.99, 50, 3);
        loadedMotorbikes.add(pony);

        // Setting the loaded motorbikes to the catalog
        loadedCatalog.setMotorbikeList(loadedMotorbikes);
        
        // Initialize controllers
        CatalogManager catalogManager = new CatalogManager();
        MotorbikeManager motorbikeManager = new MotorbikeManager();
        
        console.showInstructions();
        Scanner in = new Scanner(System.in);
       
        boolean appRunner = true;
        
        // Main application loop
        while(appRunner){
            
            boolean selectedMotorbikeRunner = false;
            Motorbike selectedMotorbike = null;
            
            console.showCatalogMenu();
            
            String input = in.nextLine();

            switch(input) {
                case "1" -> {
                    System.out.println(catalogManager.countMotorbikes(loadedCatalog) + " results");
                    //console.displayCatalog(loadedCatalog);
                }
                case "2" -> {
                    System.out.print("Type model: ");
                    String inputModel = in.nextLine();
                    if(catalogManager.isModelInCatalog(loadedCatalog, inputModel)){
                        selectedMotorbikeRunner = true;
                        selectedMotorbike = catalogManager.getMotorbikeByModel(loadedCatalog, inputModel);
                        console.showSuccessMessage("Selected " + selectedMotorbike.getModel());
                    } else{
                        console.showErrorMessage("No given model in catalog");
                    }
                }
                case "3" -> {
                    System.out.print("Search by name: ");
                    String searchName = in.nextLine();
                    List<Motorbike> nameResult = catalogManager.findMotorbikesByName(loadedCatalog, searchName);
                    System.out.println(nameResult.size() + " results");
                    console.displayList(nameResult);
                }
                case "4" -> {
                    try {
                        System.out.print("Maximum price: ");
                        double maxPrice = Double.parseDouble(in.nextLine());
                        System.out.print("Maximum displacement: ");
                        int maxDisplacement = Integer.parseInt(in.nextLine());
                        System.out.print("Minimum power: ");
                        int minPower = Integer.parseInt(in.nextLine());

                        List<Motorbike> parametersResult = catalogManager.findMotorbikesByParameters(loadedCatalog, maxPrice, maxDisplacement, minPower);
                        System.out.println(parametersResult.size() + " results");
                        console.displayList(parametersResult);
                    } catch (NumberFormatException e) {
                        console.showErrorMessage("Invalid numeric value entered");
                    }
                }
                case "5" -> {
                    try {
                        Motorbike newMotorbike = console.getMotorbikeInput();
                        if (motorbikeManager.validateData(newMotorbike)) {
                            catalogManager.AddMotorbike(loadedCatalog, newMotorbike);
                        } else {
                            console.showErrorMessage("Invalid data entered");
                        }
                    } catch (Exception e) {
                        console.showErrorMessage("An error occurred while adding motorbike");
                    }
                }
                case "6" -> catalogManager.clearCatalog(loadedCatalog);
                case "7" -> appRunner = false;
                default -> System.out.println("Incorrect input, try again");
            }
             
            while(selectedMotorbikeRunner && selectedMotorbike != null){
                
                console.showMotorbikeMenu();
                
                input = in.nextLine();
                
                switch(input) {
                    case "1" -> console.displayMotorbike(selectedMotorbike);
                    case "2" -> {
                        catalogManager.RemoveMotorbike(loadedCatalog, selectedMotorbike);
                        selectedMotorbikeRunner = false;
                    }
                    case "3" -> {
                        try {
                            System.out.print("Enter new displacement: ");
                            int newDisplacement = Integer.parseInt(in.nextLine());

                            System.out.print("Enter new power: ");
                            int newPower = Integer.parseInt(in.nextLine());

                            catalogManager.updateMotorbikeSpecification(loadedCatalog, selectedMotorbike, newDisplacement, newPower);
                        } catch (NumberFormatException e) {
                            console.showErrorMessage("Invalid specification entered");
                        }
                    }
                    case "4" -> {
                        try {
                            System.out.print("Enter new price: ");
                            double newPrice = Double.parseDouble(in.nextLine());

                            catalogManager.updateMotorbikePrice(loadedCatalog, selectedMotorbike, newPrice);
                        } catch (NumberFormatException e) {
                            console.showErrorMessage("Invalid price entered");
                        }
                    }
                    case "5" -> selectedMotorbikeRunner = false;
                    default -> System.out.println("Incorrect input, try again");
                }
            }
        }
        System.out.println("Application is closing");
    }
    // End of application
}
