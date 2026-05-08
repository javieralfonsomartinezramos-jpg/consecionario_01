package model;

import java.util.Arrays;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Enum representing the different types of motorbikes.
 * Each constant corresponds to a specific category of motorbike.
 * 
 * @author Kamil Kotorc
 * @version 3.1
 */
public enum MotorbikeType {
    
    STANDARD, SPORT, CRUISER, TOURING, ENDURO;
    
    /**
     * Converts the enum constant to a capitalized string format.
     *
     * @return a formatted string representing the motorbike type
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
    
    /**
     * Converts a string to the corresponding MotorbikeType enum value.
     *
     * @param value the string value to convert
     * @return the corresponding MotorbikeType enum
     */
    public static MotorbikeType fromString(String value) {
        return MotorbikeType.valueOf(value.toUpperCase());
    }
    
     /**
     * Returns a vector of formatted motorbike type names, capitalized for display purposes.
     *
     * @return a Vector of formatted motorbike type names
     */
    public static Vector<String> getFormattedValues() {
        return Arrays.stream(MotorbikeType.values())
                .map(type -> {                           
                    String name = type.name();
                    return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                })
                .collect(Collectors.toCollection(Vector::new));
    }

}
