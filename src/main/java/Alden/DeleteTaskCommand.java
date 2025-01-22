package Alden;

public class DeleteTaskCommand extends Command {
    private String userInput;

    public DeleteTaskCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task removedTask = tasks.removeTask(taskNumber);
        ui.showTaskRemoved(removedTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
