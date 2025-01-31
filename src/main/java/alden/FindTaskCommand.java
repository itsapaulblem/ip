package alden;

import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 * This command searches for tasks whose descriptions contain the keyword and displays them.
 */
public class FindTaskCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindTaskCommand with the given user input.
     * Extracts the keyword from the full command.
     *
     * @param fullCommand The full command input by the user.
     */
    public FindTaskCommand(String fullCommand) {
        this.keyword = fullCommand.substring(4).trim();
    }

    /**
     * Executes the find task command by searching for tasks that contain the keyword.
     * The matching tasks are then displayed to the user.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The user interface to interact with the user.
     * @param storage The storage system (not used in this command).
     * @throws AldenException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}
