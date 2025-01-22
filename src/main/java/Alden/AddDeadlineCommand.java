package Alden;

public class AddDeadlineCommand extends Command {
    private String userInput;

    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        String[] parts = userInput.split("/by", 2);
        if (parts.length < 2) {
            throw new AldenException("The deadline task must have a description and a /by clause.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();
        Task newTask = new Deadline(description, by);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
