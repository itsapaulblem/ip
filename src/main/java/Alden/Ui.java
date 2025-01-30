package Alden;

import java.util.ArrayList;


/**
 * Represents the user interface that interacts with the user by displaying messages.
 */
public class Ui {

    /**
     * Displays a welcome message when the program starts.
     */
    public void showWelcome() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alden");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a goodbye message when the user exits the program.
     */
    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(" Error: " + message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task The task that was added.
     * @param size The new size of the task list after the task is added.
     */
    public void showTaskAdded(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void showTaskMarkedAsDone(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is unmarked (marked as not done).
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println(" OK, I've marked this task as not done yet:");
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when a task is removed from the task list.
     *
     * @param task The task that was removed.
     * @param size The new size of the task list after the task is removed.
     */
    public void showTaskRemoved(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The task list to be displayed.
     */
    public void printTaskList(TaskList tasks) {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays a message when tasks matching a search query are found.
     *
     * @param matchingTasks The list of tasks that match the search query.
     */
    public void showMatchingTasks(ArrayList<Task> matchingTasks) {
        System.out.println("____________________________________________________________");
        if (matchingTasks.isEmpty()) {
            System.out.println(" No matching tasks found.");
        } else {
            System.out.println(" Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an exit message when the program ends.
     */
    public void showExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
