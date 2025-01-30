package Alden;

/**
 * Represents the command to delete a task from the task list.
 * This class extends the {@link Command} class and provides functionality
 * to remove a task based on the task number provided by the user.
 */
public class DeleteTaskCommand extends Command {
    private final String userInput;

    /**
     * Constructs a DeleteTaskCommand with the given user input.
     *
     * @param userInput The user input containing the task number to be deleted.
     */
    public DeleteTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the delete task command by removing the specified task from the task list.
     * It parses the task number from the user input and checks for validity before removing
     * the task from the list. If the task number is invalid, an exception is thrown.
     * After removal, the updated task list is saved.
     *
     * @param tasks The list of tasks where the task will be deleted.
     * @param ui The user interface to display messages to the user.
     * @param storage The storage system to save the updated task list.
     * @throws AldenException If the task number is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1; // Parse task number from user input
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task removedTask = tasks.removeTask(taskNumber); // Remove the task from the list
        ui.showTaskRemoved(removedTask, tasks.size()); // Display task removal message
        storage.save(tasks.getTasks()); // Save the updated task list
    }
}
