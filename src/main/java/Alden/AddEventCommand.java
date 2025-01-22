package Alden;

public class AddEventCommand extends Command {
    private String userInput;

    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        String[] parts = userInput.split("/from|/to", 3);
        if (parts.length < 3) {
            throw new AldenException("The event task must have a description, /from clause, and /to clause.");
        }
        String description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task newTask = new Event(description, from, to);
        tasks.addTask(newTask);
        ui.showTaskAdded(newTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
