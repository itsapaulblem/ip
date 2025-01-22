package Alden;

/**
 * Represents the command to list all tasks in the task list.
 * This command handles displaying the current tasks to the user.
 */
public class ListTasksCommand extends Command {

    /**
     * Executes the list tasks command by displaying all tasks in the task list.
     * This method invokes the UI to print all tasks, regardless of their completion status.
     *
     * @param tasks The current task list to be displayed.
     * @param ui The UI object used to display the tasks.
     * @param storage The storage object used to save tasks (not modified in this command).
     * @throws AldenException If an error occurs during the task listing process.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        ui.printTaskList(tasks); // Display the current task list
    }
}
