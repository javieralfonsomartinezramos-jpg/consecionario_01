package model;

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 1.0
 */
public class Motorbike {
    
    private String model; // The model name of the motorbike
    private double price; // The price of the motorbike
    private int displacement; // The engine displacement of the motorbike in cc
    private int power; // The power of the motorbike in horsepower

    /**
     * Constructs a Motorbike with the specified attributes.
     *
     * @param model         the model name of the motorbike
     * @param price         the price of the motorbike
     * @param displacement  the engine displacement of the motorbike in cc
     * @param power         the power of the motorbike in horsepower
     */
    public Motorbike(String model, double price, int displacement, int power) {
        this.model = model;
        this.price = price;
        this.displacement = displacement;
        this.power = power;
    }

    /**
     * Returns the model name of the motorbike.
     *
     * @return the model name of the motorbike
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model name of the motorbike.
     *
     * @param model the new model name to set for the motorbike
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Returns the price of the motorbike.
     *
     * @return the price of the motorbike
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the motorbike.
     *
     * @param price the new price to set for the motorbike
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the engine displacement of the motorbike.
     *
     * @return the engine displacement of the motorbike in cc
     */
    public int getDisplacement() {
        return displacement;
    }

    /**
     * Sets the engine displacement of the motorbike.
     *
     * @param displacement the new engine displacement to set for the motorbike in cc
     */
    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    /**
     * Returns the power of the motorbike.
     *
     * @return the power of the motorbike in horsepower
     */
    public int getPower() {
        return power;
    }

    /**
     * Sets the power of the motorbike.
     *
     * @param power the new power to set for the motorbike in horsepower
     */
    public void setPower(int power) {
        this.power = power;
    }
}
