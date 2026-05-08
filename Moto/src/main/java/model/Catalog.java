package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of motorbikes for a specific brand. The catalog contains
 * information about the brand and a list of motorbikes.
 *
 * @author Kamil Kotorc
 * @version 4.1
 */
public class Catalog {

    /**
     * The brand name of the motorbikes in the catalog.
     */
    private String brand;

    /**
     * A list of motorbikes associated with the brand in this catalog.
     */
    private List<Motorbike> motorbikeList = new ArrayList<>();

    /**
     * Constructs a Catalog with the specified brand name.
     *
     * @param brand the name of the brand for this catalog
     */
    public Catalog(String brand) {
        this.brand = brand;
    }

    /**
     * Constructs a Catalog with the specified brand name.
     *
     * @param brand the name of the brand for this catalog
     * @param motorbikeList the list of motorbikes, if list is null it creates new ArrayList
     */
    public Catalog(String brand, List<Motorbike> motorbikeList) {
        this.brand = brand;
        this.motorbikeList = (motorbikeList != null) ? motorbikeList : new ArrayList<>();
    }

    /**
     * Returns the brand of the catalog.
     *
     * @return the brand name of the catalog
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand name of the catalog.
     *
     * @param brand the new brand name to set for the catalog
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Returns the list of motorbikes in the catalog.
     *
     * @return a list of Motorbike objects in the catalog
     */
    public List<Motorbike> getMotorbikeList() {
        return motorbikeList;
    }

    /**
     * Sets the list of motorbikes for the catalog.
     *
     * @param motorbikes a list of Motorbike objects to set in the catalog, if list is null it creates new ArrayList
     */
    public void setMotorbikeList(List<Motorbike> motorbikes) {
        this.motorbikeList = (motorbikeList != null) ? motorbikeList : new ArrayList<>();
    }

    // METHODS
    
    /**
     * Removes a motorbike from the catalog.
     *
     * @param motorbike the motorbike to remove
     * @throws AppException if there is no motorbike in catalog
     */
    public void removeMotorbike(Motorbike motorbike) throws AppException {
        if (!motorbikeList.remove(motorbike)) {
            throw new AppException("No motorbike listed in catalog");
        }
    }

    /**
     * Adds a new motorbike to the catalog.
     *
     * @param motorbike the motorbike to add to the list
     * @throws AppException if motorbike already exist in catalog
     */
    public void addMotorbike(Motorbike motorbike) throws AppException {
        if (motorbike == null) {
            throw new AppException("Motorbike cannot be null");
        }
        if (motorbikeList.contains(motorbike)) {
            throw new AppException("Motorbike already exists");
        }
        motorbikeList.add(motorbike);
    }

    /**
     * Edits the motorbike in the catalog.
     *
     * @param oldMotorbike the motorbike to edit
     * @param newMotorbike the motorbike with new parameters
     * @throws AppException if there is no motorbike in catalog
     */
    public void editMotorbike(Motorbike oldMotorbike, Motorbike newMotorbike) throws AppException {
        if (newMotorbike == null) {
            throw new AppException("Motorbike cannot be null");
        }
        int index = motorbikeList.indexOf(oldMotorbike);
        if (index == -1) {
            throw new AppException("No motorbike listed in catalog");
        }
        motorbikeList.set(index, newMotorbike);
    }

    /**
     * Clears all motorbikes in motorbike list
     */
    public void clearCatalog() {
        motorbikeList.clear();
    }

    /**
     * Checks if the specified catalog is empty.
     *
     * @return true if the catalog is empty; false otherwise
     */
    public boolean isCatalogEmpty() {
        return motorbikeList.isEmpty();
    }

}
