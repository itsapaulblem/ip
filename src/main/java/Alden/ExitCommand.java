package Alden;

/**
 * Represents the command to exit the application.
 * This command handles displaying the exit message when the user decides to terminate the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying the exit message.
     * This method does not modify the task list or storage,
     * as it is only responsible for handling the termination process.
     *
     * @param tasks The current task list (not used in this command).
     * @param ui The UI object used to display messages.
     * @param storage The storage object used to save tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExitMessage(); // Show exit message to the user
    }
}
