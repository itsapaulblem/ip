package alden;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

/**
 * The `Ui` class handles the user interface interactions for the Alden application.
 * It manages displaying messages to the user, both in GUI and (optionally) console modes.
 */
public class Ui {
    private VBox dialogContainer;
    private boolean isGuiMode = false;
    private final StringBuilder currentBotResponse = new StringBuilder(); // Accumulate bot's response

    /**
     * Sets the UI to GUI mode, using the provided VBox as the dialog container.
     *
     * @param container The VBox to use as the dialog container for GUI mode.
     */
    public void setGuiMode(VBox container) {
        this.dialogContainer = container;
        this.isGuiMode = true;
    }

    /**
     * Appends a message to the output, either to the GUI dialog container or the console.
     *
     * @param message The message to display.
     * @param isUser  True if the message is from the user, false if it's from Alden.
     */
    private void appendToOutput(String message, boolean isUser) {
        if (isGuiMode) {
            if (isUser) {
                Image image = new Image(this.getClass().getResourceAsStream("/images/UserLogo.jpg"));
                addToDialogContainer(new DialogBox(message, image, true));
            } else {
                currentBotResponse.append(message).append("\n"); // Add to buffer
            }
        } else {
            System.out.println(message); // Console output
        }
    }

    /**
     * Adds a DialogBox to the GUI dialog container.
     *
     * @param dialogBox The DialogBox to add.
     */
    private void addToDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    /**
     * Displays the accumulated bot response in the GUI dialog container.
     */
    private void flushBotResponse() {
        if (currentBotResponse.length() > 0) {
            Image image = new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png"));
            addToDialogContainer(new DialogBox(currentBotResponse.toString(), image, false));
            currentBotResponse.setLength(0); // Clear buffer
        }
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        appendToOutput(" Hello! I'm Alden", false);
        appendToOutput(" What can I do for you?", false);
        flushBotResponse(); // Display welcome message
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        appendToOutput(" Bye. Hope to see you again soon!", false);
        flushBotResponse();
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        appendToOutput(" Error: " + message, false);
        flushBotResponse();
    }

    /**
     * Displays the task added message.
     *
     * @param task The added task.
     * @param size The current number of tasks in the list.
     */
    public void showTaskAdded(Task task, int size) {
        appendToOutput(" Got it. I've added this task:", false);
        appendToOutput("   " + task, false);
        appendToOutput(" Now you have " + size + " tasks in the list.", false);
        flushBotResponse();
    }

    /**
     * Displays the task marked as done message.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        appendToOutput(" Nice! I've marked this task as done:", false);
        appendToOutput("   " + task, false);
        flushBotResponse();
    }

    /**
     * Displays the task marked as not done message.
     *
     * @param task The task that was marked as not done.
     */
    public void showTaskUnmarked(Task task) {
        appendToOutput(" OK, I've marked this task as not done yet:", false);
        appendToOutput("   " + task, false);
        flushBotResponse();
    }

    /**
     * Displays the task removed message.
     *
     * @param task The removed task.
     * @param size The current number of tasks in the list.
     */
    public void showTaskRemoved(Task task, int size) {
        appendToOutput(" Noted. I've removed this task:", false);
        appendToOutput("   " + task, false);
        appendToOutput(" Now you have " + size + " tasks in the list.", false);
        flushBotResponse();
    }

    /**
     * Displays the task list.
     *
     * @param tasks The list of tasks to display.
     */
    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            appendToOutput(" Your task list is empty.", false);
        } else {
            appendToOutput(" Here are the tasks in your list:", false);
            for (int i = 0; i < tasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + tasks.get(i), false);
            }
        }
        flushBotResponse();
    }

    /**
     * Displays the matching tasks.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            appendToOutput(" No matching tasks found.", false);
        } else {
            appendToOutput(" Here are the matching tasks in your list:", false);
            for (int i = 0; i < matchingTasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + matchingTasks.get(i), false);
            }
        }
        flushBotResponse();
    }

    /**
     * Displays the exit message (same as goodbye).
     */
    public void showExitMessage() {
        showGoodbye();
    }
}