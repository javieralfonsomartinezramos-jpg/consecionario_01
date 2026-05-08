package model;

/**
 * This class represents an application-specific exception.
 * It is used for signaling errors that occur during the execution of the application.
 * 
 * @author Kamil Kotorc
 * @version 2.0
 */
public class AppException extends Exception {

    /**
     * Default constructor that sets a default error message.
     */
    public AppException() {
        super("Unknown error");
    }

    /**
     * Constructor that allows for specifying a custom error message.
     *
     * @param message the error message that will be assigned to the exception.
     */
    public AppException(String message) {
        super(message);
    }
    
}
