package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of motorbikes for a specific brand.
 * The catalog contains information about the brand and a list of motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 1.0
 */
public class Catalog {  
    
    private String brand; // The brand of the motorbikes in the catalog
    private List<Motorbike> Motorbikes = new ArrayList<>(); // List of motorbikes in the catalog

    /**
     * Constructs a Catalog with the specified brand name.
     *
     * @param brand the name of the brand for this catalog
     */
    public Catalog(String brand) {
        this.brand = brand;
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
    public List<Motorbike> getMotorbikes() {
        return Motorbikes;
    }

    /**
     * Sets the list of motorbikes for the catalog.
     *
     * @param Motorbikes a list of Motorbike objects to set in the catalog
     */
    public void setMotorbikes(List<Motorbike> Motorbikes) {
        this.Motorbikes = Motorbikes;
    }
    
}