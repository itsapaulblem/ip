package alden;

/**
 * An abstract class representing a command in the Alden application.
 * This class defines the structure for all commands that can be executed in the system.
 * Subclasses must implement the {@link #execute(TaskList, Ui, Storage)} method to define
 * the specific behavior of each command.
 */
public abstract class Command {

    /**
     * Executes the command, performing an action based on the user's input.
     * Subclasses must override this method to provide specific functionality for each command.
     *
     * @param tasks   The task list to interact with when executing the command.
     * @param ui      The user interface for displaying results or errors to the user.
     * @param storage The storage handler used to save or retrieve the task list.
     * @throws AldenException If an error occurs during the execution of the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException;
}
