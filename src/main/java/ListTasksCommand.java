public class ListTasksCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        ui.printTaskList(tasks);
    }
}
