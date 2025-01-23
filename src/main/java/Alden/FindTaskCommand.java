package Alden;

import java.util.ArrayList;

public class FindTaskCommand extends Command {
    private final String keyword;

    public FindTaskCommand(String fullCommand) {
        this.keyword = fullCommand.substring(4).trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AldenException {
        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        ui.showMatchingTasks(matchingTasks);
    }
}

