package model;

import javax.swing.JOptionPane;

/**
 * This class represents an application-specific exception.
 * It is used for signaling errors that occur during the execution of the application.
 * 
 * @author Kamil Kotorc
 * @version 3.0
 */
public class AppException extends Exception {

    /**
     * Constructor that allows for specifying a custom error message.
     *
     * @param message the error message that will be assigned to the exception.
     */
    public AppException(String message) {
        JOptionPane.showMessageDialog(null, message, "Error has occured", JOptionPane.ERROR_MESSAGE);
    }
    
}
