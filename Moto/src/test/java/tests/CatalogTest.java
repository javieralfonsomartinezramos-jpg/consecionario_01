package tests;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.Catalog;
import model.Motorbike;
import static model.MotorbikeType.STANDARD;
import model.AppException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Tests for the Catalog class. Covers functionality related to motorbike management in the catalog.
 * 
 * @author Kamil Kotorc
 * @version 4.0
 */
public class CatalogTest {

    private Catalog catalog;
    
    @BeforeEach
    public void setUp() {
        catalog = new Catalog("TestCatalog");
    }
    
    // testing class for motorbikeList being null
    
    /**
     * Tests that the motorbike list in a catalog is never null, even if initialized or explicitly set to null.
     */
    @Test
    public void testNullArrayList() {
        List<Motorbike> emptyList = new ArrayList<>();
        
        Catalog testCatalog = new Catalog("test",null);
        assertFalse(testCatalog.getMotorbikeList() == null, "Catalog list should NOT be null");
        
        testCatalog.setMotorbikeList(null);
        assertFalse(testCatalog.getMotorbikeList() == null, "Catalog list should NOT be null");
        
        assertEquals(testCatalog.getMotorbikeList(), emptyList, "Catalog list should be empty ArrayList");
    }
    
    // testing removeMotorbike
    
    /**
     * Tests the successful removal of a motorbike from the catalog.
     */
    @Test
    public void testResultRemoveMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        try {
            catalog.removeMotorbike(motorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");
    }
    
    /**
     * Tests that removing a motorbike not listed in the catalog throws an exception.
     */
    @Test
    public void testExceptionRemoveMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
                
        try {
            catalog.removeMotorbike(motorbike);  
            fail("An exception should be thrown");
        } catch (AppException e) {   
        }
    }
    
    /**
     * Parameterized test for removing motorbikes.
     *
     * @param motorbike      the motorbike to be removed
     * @param shouldExist    whether the motorbike exists in the catalog before removal
     */
    @ParameterizedTest
    @MethodSource("motorbikesToRemove")
    void testRemoveMotorbike(Motorbike motorbike, boolean shouldExist) {
        if (shouldExist) {
            try {
                catalog.addMotorbike(motorbike);
            } catch (AppException ex) {
                fail("Motorbike should be added successfully");
            }
        }

        try {
            catalog.removeMotorbike(motorbike);
            if (!shouldExist) {
                fail("Exception should have been thrown for non-existent motorbike");
            }
        } catch (AppException e) {
            if (shouldExist) {
                fail("Exception should NOT have been thrown for existing motorbike");
            }
        }
    }

    static Stream<Arguments> motorbikesToRemove() {
        Motorbike existing = new Motorbike("Existing", 1.11, 2, 3, STANDARD);
        Motorbike nonExistent = new Motorbike("NonExistent", 2.22, 4, 6, STANDARD);
        return Stream.of(
            Arguments.of(existing, true),
            Arguments.of(nonExistent, false)
        );
    }
    
    // testing addMotorbike
    
    /**
     * Tests the successful addition of a motorbike to the catalog.
     */
    @Test
    public void testResultAddMotorbike() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
                
        assertEquals(1, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should increase by 1");
    }
    
    /**
     * Parameterized test for adding motorbikes.
     *
     * @param motorbike the motorbike to be added
     */
    @ParameterizedTest
    @MethodSource("motorbikeStream")
    public void testExceptionAddMotorbike(Motorbike motorbike) {
        if(motorbike == null){
            try {
                catalog.addMotorbike(motorbike);
                fail("An exception should be thrown for null");
            } catch (AppException ex) {
            }
        } else {
            try {
                catalog.addMotorbike(motorbike);
                catalog.addMotorbike(motorbike);
                fail("An exception should be thrown for duplicate");
            } catch (AppException ex) {
            }
        }
    }
    
    static Stream<Motorbike> motorbikeStream() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        return Stream.of(motorbike,null);
    }
    
    // testing editMotorbike
    
    /**
     * Tests the successful editing of a motorbike in the catalog.
     */
    @Test
    public void testResultEditMotorbike() {
        Motorbike oldMotorbike = new Motorbike("TestMotorbike1",1.11,2,3,STANDARD);
        Motorbike newMotorbike = new Motorbike("TestMotorbike2",2.22,4,6,STANDARD);
        
        try {
            catalog.addMotorbike(oldMotorbike);
            catalog.editMotorbike(oldMotorbike,newMotorbike);
        } catch (AppException ex) {
            fail("An exception should NOT be thrown");
        }
        
        assertFalse(catalog.getMotorbikeList().contains(oldMotorbike), "Catalog should NOT contain oldMotorbike");        
        assertTrue(catalog.getMotorbikeList().contains(newMotorbike), "Catalog should contain newMotorbike");
    }
    
    /**
     * Tests that editing a motorbike not listed in the catalog or replacing it with null throws an exception.
     */
    @Test
    public void testExceptionEditMotorbike() {
        Motorbike nullMotorbike = null;
        Motorbike validMotorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        try {
            catalog.editMotorbike(validMotorbike,validMotorbike);
            fail("An exception should be thrown for editing motorbike NOT listed in catalog");
        } catch (AppException ex) {
        }
        
        try {
            catalog.addMotorbike(validMotorbike);
            catalog.editMotorbike(validMotorbike, nullMotorbike);
            fail("An exception should be thrown for new motorbike being null");
        } catch (AppException ex) {
        }   
    }
    
    /**
     * Parameterized test for editing motorbikes.
     *
     * @param oldMotorbike        the motorbike to be replaced
     * @param newMotorbike        the new motorbike
     * @param shouldThrowException whether an exception is expected
     */
    @ParameterizedTest
    @MethodSource("motorbikesToEdit")
    void testEditMotorbikeSimple(Motorbike oldMotorbike, Motorbike newMotorbike, boolean shouldThrowException) {
        if (oldMotorbike != null && !shouldThrowException) {
            try {
                catalog.addMotorbike(oldMotorbike);
            } catch (AppException e) {
                fail("Setup failed: could not add oldMotorbike");
            }
        }

        try {
            catalog.editMotorbike(oldMotorbike, newMotorbike);
            if (shouldThrowException) {
                fail("Exception was expected but not thrown");
            } else {
                assertFalse(catalog.getMotorbikeList().contains(oldMotorbike), "Old motorbike should be replaced");
                assertTrue(catalog.getMotorbikeList().contains(newMotorbike), "New motorbike should be in the catalog");
            }
        } catch (AppException e) {
            if (!shouldThrowException) {
                fail("Unexpected exception: " + e.getMessage());
            }
        }
    }

    static Stream<Arguments> motorbikesToEdit() {
        Motorbike existingMotorbike = new Motorbike("ExistingMotorbike", 1.11, 2, 3, STANDARD);
        Motorbike newMotorbike = new Motorbike("NewMotorbike", 2.22, 4, 6, STANDARD);
        Motorbike nonExistingMotorbike = new Motorbike("NonExistingMotorbike", 3.33, 6, 9, STANDARD);

        return Stream.of(
            Arguments.of(existingMotorbike, newMotorbike, false),
                
            Arguments.of(nonExistingMotorbike, newMotorbike, true),

            Arguments.of(existingMotorbike, null, true)
        );
    }
    
    // testing clearCatalog
    
     /**
     * Tests that clearing the catalog results in an empty motorbike list.
     */
    @Test
    public void testClearCatalog() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        catalog.clearCatalog();
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");
        
        catalog.clearCatalog();
        
        assertEquals(0, catalog.getMotorbikeList().size(), 0.01, "Motorbike list size should be equal to 0");     
    }
    
    // testing isCatalogEmpty
    
     /**
     * Tests the isCatalogEmpty method, ensuring it returns true for an empty catalog and false otherwise.
     */
    @Test
    public void testIsCatalogEmpty() {
        Motorbike motorbike = new Motorbike("TestMotorbike",1.11,2,3,STANDARD);
        
        assertTrue(catalog.isCatalogEmpty(), "Method should return true");
        
        try {
            catalog.addMotorbike(motorbike);
        } catch (AppException ex) {
        }
        
        assertFalse(catalog.isCatalogEmpty(), "Method should return false");
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
    
}