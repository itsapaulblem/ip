public class AddTodoCommand extends Command {
    private final String userInput;

    public AddTodoCommand(String userInput) {
        this.userInput = userInput;
    }

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
