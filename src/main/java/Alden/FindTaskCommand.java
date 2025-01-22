package Alden;

import java.util.ArrayList;

/**
 * Command that searches for tasks by a keyword in the task description.
 */
public class FindTaskCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the specified user input.
     *
     * @param userInput The user input containing the "find" command and the search keyword.
     */
    public FindTaskCommand(String userInput) {
        this.keyword = userInput.substring(5).trim(); // Extract the keyword after the "find" command.
    }

    /**
     * Executes the find command, searching for tasks that contain the keyword in their description.
     *
     * @param tasks   The task list to search through.
     * @param ui      The UI to display the results.
     * @param storage The storage to save the tasks.
     * @throws AldenException If no tasks match the keyword.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Search for tasks that contain the keyword in the description.
        for (Task task : tasks.getTasks()) {
            if (task.toString().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        // If no tasks match, throw an exception.
        if (matchingTasks.isEmpty()) {
            throw new AldenException("No tasks found matching: " + keyword);
        }

        // Show matching tasks.
        ui.showMatchingTasks(matchingTasks);
    }
}
