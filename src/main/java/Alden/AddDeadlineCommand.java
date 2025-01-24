package Alden;

/**
 * A command that adds a deadline task to the task list.
 * This command parses the user input to extract the description and deadline date,
 * creates a new Deadline task, and adds it to the task list.
 */
public class AddDeadlineCommand extends Command {
    private String userInput; // The user input containing the description and deadline

    /**
     * Constructs an AddDeadlineCommand with the user input.
     *
     * @param userInput The input from the user that contains the task description and deadline.
     */
    public AddDeadlineCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Executes the command to add a deadline task to the task list.
     * The input is expected to contain a description followed by a /by clause with the deadline.
     * If the input is invalid (missing /by or description), an exception is thrown.
     *
     * @param tasks   The task list to which the new deadline task will be added.
     * @param ui      The user interface to show messages to the user.
     * @param storage The storage handler to save the updated task list.
     * @throws AldenException If the user input is malformed or doesn't contain a valid deadline.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        String[] parts = this.userInput.split("/by", 2); // Split the input by the "/by" keyword
        if (parts.length < 2) {
            // If there is no "/by" clause, throw an exception
            throw new AldenException("The deadline task must have a description and a /by clause.");
        } else {
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            Task newTask = new Deadline(description, by);
            tasks.addTask(newTask);
            ui.showTaskAdded(newTask, tasks.size());
            storage.save(tasks.getTasks());
        }
    }
}
