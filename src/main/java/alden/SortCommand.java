package alden;
import java.util.ArrayList;
import java.util.Comparator;
/**
 * A command that sorts the tasks in the task list.
 * Supports sorting chronologically for deadline and event tasks.
 */
public class SortCommand extends Command {
    /**
     * Constructs a SortCommand with the specified sort type.
     *
     * @param userInput The user input containing the sort command.
     * @throws AldenException If the sort command is invalid.
     */
    public SortCommand(String userInput) throws AldenException {
        // Remove "sort" from the input and trim
        String sortType = userInput.substring(4).trim().toLowerCase();
        // Validate sort type
        if (!sortType.equals("date") && !sortType.equals("chronological")) {
            throw new AldenException("Invalid sort type. Use 'sort date' or 'sort chronological'.");
        }
    }
    /**
     * Executes the sort command on the task list.
     *
     * @param tasks   The task list to be sorted.
     * @param ui      The user interface to show messages.
     * @param storage The storage handler to save the sorted list.
     * @throws AldenException If sorting fails.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        // Create a list to hold sortable tasks
        ArrayList<Task> sortableTasks = new ArrayList<>(tasks.getTasks());
        // Create a comparator for chronological sorting
        Comparator<Task> chronologicalComparator = (task1, task2) -> {
            // Only sort Deadline and Event tasks
            if (task1 instanceof Deadline && task2 instanceof Deadline) {
                return ((Deadline) task1).getDateTime().compareTo(((Deadline) task2).getDateTime());
            } else if (task1 instanceof Event && task2 instanceof Event) {
                return ((Event) task1).getStartDateTime().compareTo(((Event) task2).getStartDateTime());
            }
            // If tasks are different types or not sortable, maintain original order
            return 0;
        };
        sortableTasks.sort(chronologicalComparator);
        // Clear and repopulate the task list
        tasks.getTasks().clear();
        for (Task task : sortableTasks) {
            tasks.addTask(task);
        }
        storage.save(tasks.getTasks());
        // Show the sorted list to the user
        ui.printTaskList(tasks);
    }
}