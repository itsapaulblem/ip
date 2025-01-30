package Alden;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Scanner;

/**
 * The main entry point of the Alden task management program.
 * This class is responsible for initializing the task list, loading data from storage,
 * and managing the user interaction loop.
 */
public class Alden {
    private static final String FILE_PATH = "./data/Alden.txt";
    private static TaskList tasks = new TaskList(); // The list of tasks
    private static Storage storage = new Storage(FILE_PATH); // The storage handler for tasks
    private static Ui ui = new Ui(); // The user interface to interact with the user

    /**
     * The main method that starts the Alden program.
     * It initializes the program, loads the tasks from storage, and starts the user input loop.
     * The program continues running until the user types "bye".
     *
     * @param args Command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // Load tasks from the storage file
        storage.load(tasks);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        // Display the welcome message
        ui.showWelcome();

        // Main input loop
        while (isRunning) {
            try {
                // Read user input
                String userInput = scanner.nextLine().trim();

                // Check if the user wants to exit
                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                    ui.showGoodbye();
                } else {
                    // Parse the user input into a command and execute it
                    Command command = Parser.parse(userInput);
                    command.execute(tasks, ui, storage);
                }
            } catch (AldenException e) {
                // Display error message if there is an exception
                ui.showError(e.getMessage());
            }
        }

        // Close the scanner to free resources
        scanner.close();
    }
}
