package Alden;

import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;

public class Ui {
    private VBox dialogContainer;
    private boolean isGuiMode = false;

    public void setGuiMode(VBox container) {
        this.dialogContainer = container;
        this.isGuiMode = true;
    }

    private void appendToOutput(String message, boolean isUser) {
        if (isGuiMode) {
            Image image = isUser ? new Image(this.getClass().getResourceAsStream("/images/UserLogo.jpg")) :
                    new Image(this.getClass().getResourceAsStream("/images/DukeLogo.png"));
            addToDialogContainer(new DialogBox(message, image, isUser));
        } else {
            System.out.println(message); // Console output (if needed)
        }
    }

    private void addToDialogContainer(DialogBox dialogBox) {
        dialogContainer.getChildren().add(dialogBox);
    }

    public void showWelcome() {
        appendToOutput(" Hello! I'm Alden", false);
        appendToOutput(" What can I do for you?", false);
    }

    public void showGoodbye() {
        appendToOutput(" Bye. Hope to see you again soon!", false);
    }

    public void showError(String message) {
        appendToOutput(" Error: " + message, false);
    }

    public void showTaskAdded(Task task, int size) {
        appendToOutput(" Got it. I've added this task:", false);
        appendToOutput("   " + task, false);
        appendToOutput(" Now you have " + size + " tasks in the list.", false);
    }

    public void showTaskMarkedAsDone(Task task) {
        appendToOutput(" Nice! I've marked this task as done:", false);
        appendToOutput("   " + task, false);
    }

    public void showTaskUnmarked(Task task) {
        appendToOutput(" OK, I've marked this task as not done yet:", false);
        appendToOutput("   " + task, false);
    }

    public void showTaskRemoved(Task task, int size) {
        appendToOutput(" Noted. I've removed this task:", false);
        appendToOutput("   " + task, false);
        appendToOutput(" Now you have " + size + " tasks in the list.", false);
    }

    public void printTaskList(TaskList tasks) {
        if (tasks.isEmpty()) {
            appendToOutput(" Your task list is empty.", false);
        } else {
            appendToOutput(" Here are the tasks in your list:", false);
            for (int i = 0; i < tasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + tasks.get(i), false);
            }
        }
    }

    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            appendToOutput(" No matching tasks found.", false);
        } else {
            appendToOutput(" Here are the matching tasks in your list:", false);
            for (int i = 0; i < matchingTasks.size(); i++) {
                appendToOutput(" " + (i + 1) + "." + matchingTasks.get(i), false);
            }
        }
    }

    public void showExitMessage() {
        showGoodbye();
    }
}