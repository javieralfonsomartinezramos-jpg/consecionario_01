package controller;

import model.Motorbike;

/**
 * This class is responsible for managing motorbike-related operations.
 * It provides methods for creating motorbike instances and validating their data.
 * 
 * @author Kamil Kotorc
 * @version 2.0
 */
public class MotorbikeManager {
    
    /**
     * Creates a new Motorbike object with the specified attributes.
     *
     * @param model        the model of the motorbike
     * @param price        the price of the motorbike
     * @param displacement the engine displacement of the motorbike
     * @param power        the power output of the motorbike
     * @return a new Motorbike object
     */
    public Motorbike createMotorbike(String model, double price, int displacement, int power) {
        return new Motorbike(model, price, displacement, power);
    }
    
    /**
     * Validates the data of a given Motorbike object.
     *
     * @param motorbike the Motorbike object to validate
     * @return true if the motorbike data is valid; false otherwise
     */
    public boolean validateData(Motorbike motorbike) {
        return motorbike.getModel() != null 
                && !motorbike.getModel().isEmpty() 
                && motorbike.getPrice() > 0 
                && motorbike.getDisplacement() > 0 
                && motorbike.getPower() > 0;
    }
}