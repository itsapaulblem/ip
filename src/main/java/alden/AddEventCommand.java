package alden;

/**
 * A command that adds an event task to the task list.
 * This command parses the user input to extract the description, start time, and end time,
 * creates a new Event task, and adds it to the task list.
 */
public class AddEventCommand extends Command {
    private final String userInput; // The user input containing the description, start time, and end time

    /**
     * Constructs an AddEventCommand with the user input.
     *
     * @param userInput The input from the user that contains the task description, start time, and end time.
     */
    public AddEventCommand(String userInput) {
        assert userInput != null : "User input cannot be null";
        assert userInput.startsWith("event") : "Command must start with event";
        this.userInput = userInput;
    }

    /**
     * Executes the command to add an event task to the task list.
     * The input is expected to contain a description followed
     * by /from and /to clauses for the event's start and end times.
     * If the input is invalid (missing /from, /to, or description), an exception is thrown.
     *
     * @param tasks   The task list to which the new event task will be added.
     * @param ui      The user interface to show messages to the user.
     * @param storage The storage handler to save the updated task list.
     * @throws AldenException If the user input is malformed or doesn't contain a valid event with description,
     *     /from, and /to.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";

        // Split the user input by the "/from" and "/to" markers to extract description, start, and end times
        String[] parts = userInput.split("/from|/to", 3);

        // Get the event task from the split parts of the input
        Task newTask = getTask(parts);

        // Add the event task to the task list
        tasks.addTask(newTask);

        // Display a message confirming the task was added
        ui.showTaskAdded(newTask, tasks.size());

        // Save the updated task list to storage
        storage.save(tasks.getTasks());
    }

    /**
     * Extracts the task details from the user input and creates a new Event task.
     *
     * @param parts The parts of the input, split by /from and /to.
     * @return The newly created Event task.
     * @throws AldenException If the parts are missing required information (description, /from, /to).
     */
    private static Task getTask(String[] parts) throws AldenException {
        // Ensure that there are at least three parts: description, from time, and to time
        if (parts.length < 3) {
            throw new AldenException(
                    "The event task must have a description, /from clause, and /to clause.");
        }

        // Extract description (everything before /from), from time (between /from and /to), and to time (after /to)
        String description = parts[0].substring(6).trim(); // Assuming the description starts after "event"
        if (description.isEmpty()) {
            throw new AldenException("Event description cannot be empty");
        }

        String from = parts[1].trim();
        String to = parts[2].trim();

        if (from.isEmpty() || to.isEmpty()) {
            throw new AldenException("Event times cannot be empty");
        }

        // Create and return the new Event task with the extracted details
        return new Event(description, from, to);
    }
}
