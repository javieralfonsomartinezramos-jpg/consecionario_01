package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a catalog of motorbikes for a specific brand. The catalog contains
 * information about the brand and a list of motorbikes.
 *
 * @author Kamil Kotorc
 * @version 6.0
 */
@Entity
@Table(name = "catalogs")
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "brand", length = 50, nullable = false)
    @NotBlank
    private String brand;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "catalog_id")
    private List<Motorbike> motorbikeList = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String brand) {
        this.brand = brand;
    }

    public Catalog(String brand, List<Motorbike> motorbikeList) {
        this.brand = brand;
        this.motorbikeList = (motorbikeList != null) ? motorbikeList : new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Motorbike> getMotorbikeList() {
        return motorbikeList;
    }

    public void setMotorbikeList(List<Motorbike> motorbikeList) {
        this.motorbikeList = (motorbikeList != null) ? motorbikeList : new ArrayList<>();
    }

    public void removeMotorbike(Motorbike motorbike) {
        motorbikeList.remove(motorbike);
    }

    public void addMotorbike(Motorbike motorbike) {
        if (motorbike == null) {
            throw new IllegalArgumentException("Motorbike cannot be null");
        }
        motorbikeList.add(motorbike);
    }

    public void editMotorbike(Motorbike oldMotorbike, Motorbike newMotorbike) {
        if (newMotorbike == null) {
            throw new IllegalArgumentException("Motorbike cannot be null");
        }
        int index = motorbikeList.indexOf(oldMotorbike);
        if (index != -1) {
            motorbikeList.set(index, newMotorbike);
        } else {
            throw new IllegalArgumentException("Motorbike not found in catalog");
        }
    }

    public void clearCatalog() {
        motorbikeList.clear();
    }

    public boolean isCatalogEmpty() {
        return motorbikeList.isEmpty();
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", motorbikeList=" + motorbikeList +
                '}';
    }
}
