package alden;

import java.util.Scanner;

/**
 *  Represents the core task manager application that handles task storage and user commands.
 *  Provides functionality for adding, listing, marking and managing various types of tasks.
 *
 */
public class Alden {
    private static final String FILE_PATH = "./data/Alden.txt";
    private static final TaskList tasks = new TaskList(); // The list of tasks
    private static final Storage storage = new Storage(FILE_PATH); // The storage handler for tasks
    private static final Ui ui = new Ui(); // The user interface to interact with the user

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
                String userInput = scanner.nextLine().trim();

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
        scanner.close();
    }
}
