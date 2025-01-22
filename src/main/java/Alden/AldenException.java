package Alden;

/**
 * Custom exception class for handling specific errors in the Alden application.
 * This exception is thrown when there are errors related to task management or command execution.
 */
public class AldenException extends Exception {

    /**
     * Constructs a new AldenException with the specified detail message.
     *
     * @param message The detail message that explains the cause of the exception.
     */
    public AldenException(String message) {
        super(message);
    }
}
