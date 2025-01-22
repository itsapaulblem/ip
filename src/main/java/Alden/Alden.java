package Alden;
import java.util.Scanner;

public class Alden {
    private static final String FILE_PATH = "./data/Alden.txt";
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage(FILE_PATH);
    private static Ui ui = new Ui();

    public static void main(String[] args) {
        storage.load(tasks);

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        ui.showWelcome();

        while (isRunning) {
            try {
                String userInput = scanner.nextLine().trim();
                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                    ui.showGoodbye();
                } else {
                    Command command = Parser.parse(userInput);
                    command.execute(tasks, ui, storage);
                }
            } catch (AldenException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }
}
