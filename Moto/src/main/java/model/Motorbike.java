package model;

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 4.0
 */
public record Motorbike(String model, double price, int displacement, int power, MotorbikeType type) {

    @Override
    public String toString() {
        return model + " " + price + " " + displacement + " " + power + " " + type;
    }
    
}
