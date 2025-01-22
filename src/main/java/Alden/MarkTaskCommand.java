package Alden;

public class MarkTaskCommand extends Command {
    private String userInput;
    private boolean isDone;

    public MarkTaskCommand(String userInput, boolean isDone) {
        this.userInput = userInput;
        this.isDone = isDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task task = tasks.get(taskNumber);
        if (isDone) {
            task.markAsDone();
            ui.showTaskMarkedAsDone(task);
        } else {
            task.unmarkAsDone();
            ui.showTaskUnmarked(task);
        }
        storage.save(tasks.getTasks());
    }
}
