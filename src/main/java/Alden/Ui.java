package Alden;

import java.util.ArrayList;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Ui {
    private TextArea outputArea; // For console output (if needed)
    private VBox dialogContainer; // For GUI output
    private boolean isGuiMode = false;

    public void setGuiMode(VBox container) {
        this.dialogContainer = container;
        this.isGuiMode = true;
    }

    public void setTextAreaMode(TextArea area) { // For console mode
        this.outputArea = area;
        this.isGuiMode = false;
    }

    private void appendToOutput(String message) {
        if (isGuiMode) {
            addToDialogContainer(new DialogBox(message, new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png")))); //Alden image
        } else {
            if (outputArea != null) { // Check if outputArea is initialized
                outputArea.appendText(message + "\n");
            } else {
                System.out.println(message); // Fallback to console if no TextArea
            }
        }
    }

    private void addToDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    public void showWelcome() {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Hello! I'm Alden");
        appendToOutput(" What can I do for you?");
        appendToOutput("____________________________________________________________");
    }

    public void showGoodbye() {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Bye. Hope to see you again soon!");
        appendToOutput("____________________________________________________________");
    }

    public void showError(String message) {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Error: " + message);
        appendToOutput("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int size) {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Got it. I've added this task:");
        appendToOutput("   " + task);
        appendToOutput(" Now you have " + size + " tasks in the list.");
        appendToOutput("____________________________________________________________");
    }

    public void showTaskMarkedAsDone(Task task) {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Nice! I've marked this task as done:");
        appendToOutput("   " + task);
        appendToOutput("____________________________________________________________");
    }

    public void showTaskUnmarked(Task task) {
        appendToOutput("____________________________________________________________");
        appendToOutput(" OK, I've marked this task as not done yet:");
        appendToOutput("   " + task);
        appendToOutput("____________________________________________________________");
    }

    public void showTaskRemoved(Task task, int size) {
        appendToOutput("____________________________________________________________");
        appendToOutput(" Noted. I've removed this task:");
        appendToOutput("   " + task);
        appendToOutput(" Now you have " + size + " tasks in the list.");
        appendToOutput("____________________________________________________________");
    }

    public void printTaskList(TaskList tasks) {
        appendToOutput("____________________________________________________________");
        if (tasks.isEmpty()) {
            appendToOutput(" Your task list is empty.");
        } else {
            appendToOutput(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        appendToOutput("____________________________________________________________");
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        appendToOutput("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            appendToOutput(" No matching tasks found.");
        } else {
            appendToOutput(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        appendToOutput("____________________________________________________________");
    }

    public void showExitMessage() {
        showGoodbye();
    }
}