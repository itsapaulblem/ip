package Alden;

/**
 * The `Launcher` class is a simple entry point for launching the Alden application.
 * It avoids potential issues with some JavaFX setups by using a separate class
 * with a `main` method that calls the `Main.main` method.
 */
public class Launcher {

    /**
     * The main method of the `Launcher` class.  It simply calls the `main` method
     * of the `Main` class to start the Alden application.
     *
     * @param args Command line arguments passed to the application.
     *             These arguments are forwarded to the `Main.main` method.
     */
    public static void main(String[] args) {
        Main.main(args);
    }
}
