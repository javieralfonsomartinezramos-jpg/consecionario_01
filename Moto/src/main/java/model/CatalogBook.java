package model;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

/**
 * Represents a catalog of motorbikes for a specific brand.
 * The catalog contains information about the brand and a list of motorbikes.
 * 
 * @author Kamil Kotorc
 * @version 3.1
 */

//Lombok version B
@AllArgsConstructor
@Data  //replaces @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode. 
public class CatalogBook {  
    
    
    private String brand;
    private List<Motorbike> motorbikeList = new ArrayList<>();
    
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
