package model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 4.0
 */
@Entity
@Table(name = "motorbikes")
public class Motorbike implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "model", length = 50, nullable = false)
    @NotBlank
    private String model;

    @Column(name = "price", nullable = false)
    @Min(0)
    private double price;

    @Column(name = "displacement", nullable = false)
    @Min(0)
    private int displacement;

    @Column(name = "power", nullable = false)
    @Min(0)
    private int power;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private MotorbikeType type;

    public Motorbike() {
    }

    public Motorbike(String model, double price, int displacement, int power, MotorbikeType type) {
        this.model = model;
        this.price = price;
        this.displacement = displacement;
        this.power = power;
        this.type = type;
    }

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String model() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double price() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int displacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int power() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public MotorbikeType type() {
        return type;
    }

    public void setType(MotorbikeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Motorbike{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", displacement=" + displacement +
                ", power=" + power +
                ", type=" + type +
                '}';
    }
}

/**
 * Represents a motorbike with specific attributes such as model, price, displacement, and power.
 * This class provides methods to access and modify these attributes.
 * 
 * @author Kamil Kotorc
 * @version 4.0

public record Motorbike(String model, double price, int displacement, int power, MotorbikeType type) {

    @Override
    public String toString() {
        return model + " " + price + " " + displacement + " " + power + " " + type;
    }
    
}
*/