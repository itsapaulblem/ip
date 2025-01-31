package alden;

/**
 * A command that adds a Todo task to the task list.
 * This command processes the user input to extract the description of the Todo task,
 * creates a new Todo task, and adds it to the task list.
 */
public class AddTodoCommand extends Command {
    private final String userInput;

    /**
     * Constructs an AddTodoCommand with the user input.
     *
     * @param userInput The input from the user that contains the task description.
     */
    public AddTodoCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Executes the command to add a Todo task to the task list.
     * The input is expected to contain a description for the Todo task.
     * If the description is empty or too short, an exception is thrown.
     *
     * @param tasks   The task list to which the new Todo task will be added.
     * @param ui      The user interface to show messages to the user.
     * @param storage The storage handler to save the updated task list.
     * @throws AldenException If the user input is empty or malformed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        if (userInput.length() <= 5) {
            throw new AldenException("The description of a todo cannot be empty.");
        }

        String description = userInput.substring(5).trim();
        Task newTask = new Todo(description);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
