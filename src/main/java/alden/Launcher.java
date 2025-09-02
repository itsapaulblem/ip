package alden;

/**
 * The `Launcher` class is a simple entry point for launching the Alden application.
 * It avoids potential issues with some JavaFX setups by using a separate class
 * with a `main` method that calls the `Main.main` method.
 */
public class Launcher {

    /**
     * Entry point for launching the Alden application.
     * Calls the `main` method of the `Main` class to start the program.
     *
     * @param args Command line arguments forwarded to the `Main.main`.
     */
    public static void main(String[] args) {
        Main.main(args);
    }
}
