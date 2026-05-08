package tests;

import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import model.MotorbikeType;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;



/**
 * Tests for the MotorbikeType enum class. Covers functionality related to motorbike types methods.
 * 
 * @author Kamil Kotorc
 * @version 4.0
 */
public class MotorbikeTypeTest {
    
    /**
     * Tests the toString method of MotorbikeType.
     * @param input the MotorbikeType instance
     * @param expected the expected string representation
     */
    @ParameterizedTest
    @MethodSource("toStringData")
    public void testToString(MotorbikeType input, String expected) {
        assertEquals(expected, input.toString(), input + ".toString() should return " + expected);
    }

    static Stream<Arguments> toStringData() {
        return Stream.of(
                Arguments.of(MotorbikeType.STANDARD, "Standard"),
                Arguments.of(MotorbikeType.SPORT, "Sport"),
                Arguments.of(MotorbikeType.CRUISER, "Cruiser"),
                Arguments.of(MotorbikeType.TOURING, "Touring"),
                Arguments.of(MotorbikeType.ENDURO, "Enduro")
        );
    }
    
     /**
     * Tests the fromString method with valid inputs.
     * @param input the string input
     * @param expected the expected MotorbikeType
     */
    @ParameterizedTest
    @MethodSource("fromStringData")
    public void testFromString(String input, MotorbikeType expected) {
        assertEquals(expected, MotorbikeType.fromString(input), "fromString should return " + expected + " for input " + input);
    }
    
    static Stream<Arguments> fromStringData() {
        return Stream.of(
                Arguments.of("STANDARD", MotorbikeType.STANDARD),
                Arguments.of("sport", MotorbikeType.SPORT),
                Arguments.of("CrUiSeR", MotorbikeType.CRUISER),
                Arguments.of("tOuring", MotorbikeType.TOURING),
                Arguments.of("Enduro", MotorbikeType.ENDURO)
        );
    }
    
     /**
     * Tests the getFormattedValues method, verifies the correct formatting of enum values.
     */
    @Test
    public void testGetFormattedValues() {
        Vector<String> expectedValues = new Vector<>(List.of("Standard", "Sport", "Cruiser", "Touring", "Enduro"));
        Vector<String> actualValues = MotorbikeType.getFormattedValues();
        MotorbikeType[] defaultValues = MotorbikeType.values();
        
        assertEquals(expectedValues, actualValues, "getFormattedValues should return correctly formatted MotorbikeType values");
        assertEquals(actualValues.size(), defaultValues.length, "getFormattedValues should return same number of elements as values()");
    }
    
}  
//    @BeforeAll
//    public static void setUpClass() {
//    }
//    
//    @AfterAll
//    public static void tearDownClass() {
//    }
//    
//    @BeforeEach
//    public void setUp() {
//    }
//    
//    @AfterEach
//    public void tearDown() {
//    }