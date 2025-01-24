package Alden;

/**
 * Represents the command to mark a task as done or not done.
 * This command handles updating the task's completion status based on user input.
 */
public class MarkTaskCommand extends Command {
    private final String userInput;
    private final boolean isDone;

    /**
     * Constructs a MarkTaskCommand instance.
     *
     * @param userInput The user input that contains the task number to mark.
     * @param isDone A boolean indicating whether to mark the task as done (true) or not done (false).
     */
    public MarkTaskCommand(String userInput, boolean isDone) {
        this.userInput = userInput;
        this.isDone = isDone;
    }

    /**
     * Executes the command to mark a task as done or not done.
     * The method updates the task's status based on the provided user input.
     *
     * @param tasks The task list to update.
     * @param ui The UI object used to display the task status change.
     * @param storage The storage object used to save the updated task list.
     * @throws AldenException If the task number is invalid or if any other error occurs.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task task = tasks.get(taskNumber);
        if (isDone) {
            task.markAsDone();
            ui.showTaskMarkedAsDone(task);
        } else {
            task.unmarkAsDone();
            ui.showTaskUnmarked(task);
        }
        storage.save(tasks.getTasks()); // Save the updated task list to storage
    }
}
