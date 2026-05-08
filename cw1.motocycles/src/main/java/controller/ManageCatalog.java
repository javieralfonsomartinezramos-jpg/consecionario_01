package controller;

import model.Catalog;
import model.Motorbike;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import model.AppException;

/**
 * This class is responsible for managing the motorcycle catalog.
 * It provides methods for adding, removing, updating, and searching motorbikes within a catalog.
 * 
 * @author Kamil Kotorc
 * @version 1.1
 */
public class ManageCatalog {
    
    /**
     * Clears all motorbikes from the specified catalog.
     *
     * @param catalog the catalog to be cleared
     * @throws AppException if the catalog is empty
     */
    public void clearCatalog(Catalog catalog) throws AppException {
        if (catalog.getMotorbikes().isEmpty()) {
            throw new AppException("Cannot clear catalog because it is empty.");
        }
        catalog.getMotorbikes().clear();
        System.out.println("Catalog " + catalog.getBrand() + " cleared.");
    }
    
    /**
     * Checks if the specified catalog is empty.
     *
     * @param catalog the catalog to check
     * @return true if the catalog is empty; false otherwise
     */
    public boolean isCatalogEmpty(Catalog catalog) {
        return catalog.getMotorbikes().isEmpty();
    }
    
    /**
     * Checks if a specific motorbike model is present in the catalog.
     *
     * @param catalog   the catalog to search in
     * @param modelName the model name to search for
     * @return true if the model is found; false otherwise
     */
    public boolean isModelInCatalog(Catalog catalog, String modelName) {
        for (Motorbike motorbike : catalog.getMotorbikes()) {
            if (motorbike.getModel().equalsIgnoreCase(modelName)) {
                return true;
            }
        }
        return false; 
    }

    /**
     * Finds all motorbikes in the catalog whose model names contain the specified name.
     *
     * @param catalog the catalog to search in
     * @param name    the name to search for in motorbike models
     * @return a list of motorbikes matching the specified name
     */
    public List<Motorbike> findMotorbikesByName(Catalog catalog, String name) {
        List<Motorbike> result = new ArrayList<>();
        for (Motorbike motorbike : catalog.getMotorbikes()) { 
            if (motorbike.getModel().toLowerCase().contains(name.toLowerCase())) {
                result.add(motorbike);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No motorcycles found with the given name: " + name);
            return Collections.emptyList();
        } else {
            return result;
        }
    }

    /**
     * Finds all motorbikes in the catalog that match the specified parameters.
     *
     * @param catalog         the catalog to search in
     * @param maxPrice        the maximum price of the motorbikes
     * @param maxDisplacement the maximum displacement of the motorbikes
     * @param minPower        the minimum power of the motorbikes
     * @return a list of motorbikes matching the specified parameters
     */
    public List<Motorbike> findMotorbikesByParameters(Catalog catalog, double maxPrice, int maxDisplacement, int minPower) {
        List<Motorbike> result = new ArrayList<>();
        for (Motorbike motorbike : catalog.getMotorbikes()) {
            boolean matches = true;
            if (maxPrice > 0 && motorbike.getPrice() > maxPrice) {
                matches = false;
            }
            if (maxDisplacement > 0 && motorbike.getDisplacement() > maxDisplacement) {
                matches = false;
            }
            if (minPower > 0 && motorbike.getPower() < minPower) {
                matches = false;
            }
            if (matches) {
                result.add(motorbike);
            }
        }
        if (result.isEmpty()) {
            System.out.println("No motorcycles found with the given parameters.");
            return Collections.emptyList();
        } else {
            return result;
        }
    }
    
    /**
     * Retrieves a motorbike from the catalog by its model name.
     *
     * @param catalog   the catalog to search in
     * @param modelName the model name of the motorbike to retrieve
     * @return the motorbike if found; null otherwise
     */
    public Motorbike getMotorbikeByModel(Catalog catalog, String modelName) {
        for (Motorbike motorbike : catalog.getMotorbikes()) {
            if (motorbike.getModel().equalsIgnoreCase(modelName)) {
                return motorbike;
            }
        }
        return null; 
    }

    /**
     * Updates the specifications of a motorbike in the catalog.
     *
     * @param catalog      the catalog containing the motorbike
     * @param motorbike    the motorbike to update
     * @param displacement the new displacement value
     * @param power        the new power value
     */
    public void updateMotorbikeSpecification(Catalog catalog, Motorbike motorbike, int displacement, int power) {
        if (catalog.getMotorbikes().contains(motorbike)) { 
            motorbike.setDisplacement(displacement);
            motorbike.setPower(power);
            System.out.println("Motorcycle specifications updated.");
        } else {
            System.out.println("Motorcycle not found in catalog.");
        }
    }
    
    /**
     * Updates the price of a motorbike in the catalog.
     *
     * @param catalog the catalog containing the motorbike
     * @param motorbike the motorbike whose price will be updated
     * @param price the new price value
     */
    public void updateMotorbikePrice(Catalog catalog, Motorbike motorbike, double price) {
        if (catalog.getMotorbikes().contains(motorbike)) { 
            motorbike.setPrice(price);  
            System.out.println("Motorcycle price updated.");
        } else {
            System.out.println("Motorcycle not found in catalog.");
        }
    }
    
    /**
     * Adds a new motorbike to the catalog.
     *
     * @param catalog   the catalog to which the motorbike will be added
     * @param motorbike the motorbike to add
     */
    public void AddMotorbike(Catalog catalog, Motorbike motorbike) {
        if (catalog.getMotorbikes().contains(motorbike)) {
            System.out.println("This motorcycle is already in the catalog.");
        } else {
            catalog.getMotorbikes().add(motorbike);
            System.out.println("Motorcycle added successfully.");
        }
    }
    
    /**
     * Removes a motorbike from the catalog.
     *
     * @param catalog   the catalog from which the motorbike will be removed
     * @param motorbike the motorbike to remove
     * @throws AppException if the motorbike is not found in the catalog
     */
    public void RemoveMotorbike(Catalog catalog, Motorbike motorbike) throws AppException {
        if (!catalog.getMotorbikes().contains(motorbike)) {
            throw new AppException("Motorcycle not found in catalog.");
        }
        catalog.getMotorbikes().remove(motorbike);
        System.out.println("Motorcycle removed successfully.");
    }

    /**
     * Counts the total number of motorbikes in the specified catalog.
     *
     * @param catalog the catalog to count motorbikes in
     * @return the number of motorbikes in the catalog
     */
    public int countMotorbikes(Catalog catalog) {
        return catalog.getMotorbikes().size();
    }
}
