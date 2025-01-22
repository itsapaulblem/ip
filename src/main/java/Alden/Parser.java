package Alden;
public class Parser {
    public static Command parse(String fullCommand) throws AldenException {
        if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand);
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand);
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand);
        } else if (fullCommand.startsWith("mark")) {
            return new MarkTaskCommand(fullCommand, true);
        } else if (fullCommand.startsWith("unmarked")) {
            return new MarkTaskCommand(fullCommand, false);
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteTaskCommand(fullCommand);
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListTasksCommand();
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("find")) {
            return new FindTaskCommand(fullCommand);
        } else if (fullCommand.isEmpty()) {
            throw new AldenException("Keyword cannot be empty for find command.");
        } else {
            throw new AldenException("Invalid command: " + fullCommand);
        }
    }
}
