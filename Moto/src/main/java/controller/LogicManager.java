package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.AppException;
import model.Catalog;
import model.Motorbike;
import model.MotorbikeType;
import static model.MotorbikeType.fromString;
import view.GUI;

/**
 * The LogicManager class is responsible for handling the business logic of the motorbike catalog application.
 * It acts as the controller in the MVC architecture, managing the interactions between the view (GUI) and the model (Catalog, Motorbike).
 * It handles events such as adding, removing, selecting motorbikes, and clearing the catalog.
 * 
 * @author Kamil Kotorc
 * @version 3.1
 */
public final class LogicManager {
    
    private final GUI gui;
    private final Catalog catalog;
    
    /**
     * Constructor that initializes the LogicManager with the GUI and Catalog objects.
     * It sets up listeners for various UI actions such as adding, removing, and selecting motorbikes.
     *
     * @param gui the GUI instance that represents the view of the application.
     * @param catalog the Catalog instance that holds the list of motorbikes.
     */
    public LogicManager(GUI gui, Catalog catalog) {
        this.gui = gui;
        this.catalog = catalog;
        
        this.handleClearAction();
        this.handleRemoveAction();
        this.handleAddAction();
        this.handleEditAction();
    }
    
    // METHODS
    
    public void updateMotorbikeTable(){
        List<String[]> motorbikeData = new ArrayList<>();
        
        for (Motorbike motorbike : catalog.getMotorbikeList()) {
            motorbikeData.add(new String[]{
                motorbike.model(),
                String.valueOf(motorbike.price())+" PLN",
                String.valueOf(motorbike.displacement())+" ccm",
                String.valueOf(motorbike.power())+ " kW",
                motorbike.type().toString()
            });
        }

        gui.updateMotoTable(motorbikeData);
    }
    
    public void attachValuesToComponents(){
        gui.setSelectedCatalog(catalog.getBrand());
        gui.setTypeBox(MotorbikeType.getFormattedValues());
    }

    public void clearMotorbikeTable() {
        if(!gui.showConfirmation("Are you sure to clear catalog?", "Confirmation")){
            return;
        }
        catalog.clearCatalog();
        this.updateMotorbikeTable();
        gui.showMessage("Catalog has been cleared");
    }
    
    public void removeMotorbikeFromTable() {
        int selectedIndex = gui.getSelectedTableRow();
        if (selectedIndex < 0) {
            gui.showWarning("No motorcycle selected!");
            return;
        }

        Motorbike motorbike = catalog.getMotorbikeList().get(selectedIndex);
        try {
            catalog.removeMotorbike(motorbike);
        } catch (AppException ex) {
        }
        
        updateMotorbikeTable();

        gui.showMessage("Motorbike has been removed");
    }
    
    public void addMotorbikeToTable() {
        Map<String, String> fieldValues = gui.getInputValues();
        
        String model = fieldValues.get("model");
        String price = fieldValues.get("price");
        String displacement = fieldValues.get("displacement");
        String power = fieldValues.get("power");
        String type = fieldValues.get("type");
        
        if (model.isEmpty()) {
            gui.showWarning("Model name cannot be empty.");
            return;
        } else if (price.isEmpty()) {
            gui.showWarning("Price cannot be empty.");
            return;
        } else if (displacement.isEmpty()) {
            gui.showWarning("Displacement cannot be empty.");
            return;
        } else if (power.isEmpty()) {
            gui.showWarning("Power cannot be empty.");
            return;
        } else if (type.isEmpty()) {
            gui.showWarning("Motorcycle type cannot be empty.");
            return;
        }
        
        try {
            String parsedModel = model;
            double parsedPrice = Math.abs(Double.parseDouble(price));
            int parsedDisplacement = Math.abs(Integer.parseInt(displacement));
            int parsedPower = Math.abs(Integer.parseInt(power));
            MotorbikeType parsedType = fromString(type);
            
            Motorbike motorbike = new Motorbike(parsedModel, parsedPrice, parsedDisplacement, parsedPower, parsedType);
            try {
                catalog.addMotorbike(motorbike);
            } catch (AppException ex) {
            }
            gui.clearInputFields();
            
            updateMotorbikeTable();
            
            gui.showMessage("Motorbike has been added");
        } catch (NumberFormatException e) {
            gui.showWarning("Please enter correct values");
        }
    }
    
    public void editMotorbikeFromTable() {
        int selectedIndex = gui.getSelectedTableRow();
        if (selectedIndex < 0) {
            gui.showWarning("No motorcycle selected!");
            return;
        }

        Motorbike motorbike = catalog.getMotorbikeList().get(selectedIndex);

        Map<String, String> editedValues = gui.showEditMessage(
                "Edit motorcycle", 
                motorbike.model(),
                motorbike.price(),
                motorbike.displacement(), 
                motorbike.power());

        if (editedValues == null) {
            return;
        }
        
        String model = editedValues.get("model");
        String price = editedValues.get("price");
        String displacement = editedValues.get("displacement");
        String power = editedValues.get("power");
        
        if (model.isEmpty()) {
            gui.showWarning("Model name cannot be empty.");
            return;
        } else if (price.isEmpty()) {
            gui.showWarning("Price cannot be empty.");
            return;
        } else if (displacement.isEmpty()) {
            gui.showWarning("Displacement cannot be empty.");
            return;
        } else if (power.isEmpty()) {
            gui.showWarning("Power cannot be empty.");
            return;
        }
        
        try {
            String parsedModel = model;
            double parsedPrice = Math.abs(Double.parseDouble(price));
            int parsedDisplacement = Math.abs(Integer.parseInt(displacement));
            int parsedPower = Math.abs(Integer.parseInt(power));
            
            Motorbike updatedMotorbike = new Motorbike(parsedModel, parsedPrice, parsedDisplacement, parsedPower, motorbike.type());
            try {
                catalog.editMotorbike(motorbike, updatedMotorbike);
            } catch (AppException ex) {
            }
            
            updateMotorbikeTable();  
            
            gui.showMessage("Motorbike has been edited");
        } catch (NumberFormatException e) {
            gui.showWarning("Please enter correct values");
        }
    }
    
    // ACTION HANDLERS
    
    public void handleClearAction(){
        gui.setClearButtonListener(e -> clearMotorbikeTable());
        gui.setClearMenuListener(e -> clearMotorbikeTable());
    }
    
    public void handleRemoveAction() {
        gui.setRemoveButtonListener(e -> removeMotorbikeFromTable());
        gui.setRemoveMenuListener(e -> removeMotorbikeFromTable());
    }
    
    public void handleAddAction() {
        gui.setAddButtonListener(e -> addMotorbikeToTable());
        gui.setAddMenuListener(e -> addMotorbikeToTable());
    } 
    
    public void handleEditAction() {
        gui.setEditButtonListener(e -> editMotorbikeFromTable());
        gui.setEditMenuListener(e -> editMotorbikeFromTable());
    } 
        
}
