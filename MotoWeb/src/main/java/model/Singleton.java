package model;

import static model.MotorbikeType.CRUISER;
import static model.MotorbikeType.ENDURO;
import static model.MotorbikeType.SPORT;
import static model.MotorbikeType.STANDARD;

/**
 * Singleton pattern implementation for the Catalog object.
 * 
 * The singleton is initialized with a set of predefined motorbikes.
 * It provides a static method to get the single instance of the Catalog.
 * 
 * @author Kamil Kotorc
 * @version 5.0
 */
public class Singleton {
   
    private static Catalog instance;
    
    /**
     * Private constructor to prevent instantiation.
     */
    private Singleton() {        
    }
    
    /**
     * Returns the unique instance of the Catalog.
     *
     * @return the singleton instance of Catalog
     */
    public static Catalog getInstance() {

        if(instance == null) {
            instance = new Catalog("Singleton");          
            instance.addMotorbike( new Motorbike("Chart", 30000.00, 750, 21, SPORT));
            instance.addMotorbike( new Motorbike("Kadet", 19400.00, 125, 7 , STANDARD));
            instance.addMotorbike( new Motorbike("Pony", 11999.99, 50, 3, ENDURO));
            instance.addMotorbike( new Motorbike("Simson", 26600.00, 125, 12, CRUISER));
            instance.addMotorbike( new Motorbike("Ogar", 15555.50, 50, 5, STANDARD));
        }
        return instance;
    }   
}
