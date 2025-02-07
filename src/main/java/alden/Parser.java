
package alden;

/**
 * The Parser class is responsible for interpreting the user's input command
 * and returning the corresponding {@link Command} object.
 * It parses the command string and generates the appropriate command instance
 * based on the command type.
 */
public class Parser {

    /**
     * Parses the given command string and returns the corresponding command object.
     *
     * @param fullCommand The full user input command as a string.
     * @return The {@link Command} object corresponding to the parsed command.
     * @throws AldenException If the command is invalid or unrecognized.
     */
    public static Command parse(String fullCommand) throws AldenException {
        if (fullCommand.startsWith("todo")) {
            return new AddTodoCommand(fullCommand); // Create a new AddTodoCommand
        } else if (fullCommand.startsWith("deadline")) {
            return new AddDeadlineCommand(fullCommand); // Create a new AddDeadlineCommand
        } else if (fullCommand.startsWith("event")) {
            return new AddEventCommand(fullCommand); // Create a new AddEventCommand
        } else if (fullCommand.startsWith("mark")) {
            return new MarkTaskCommand(fullCommand, true); // Create a new MarkTaskCommand to mark as done
        } else if (fullCommand.startsWith("unmarked")) {
            return new MarkTaskCommand(fullCommand, false); // Create a new MarkTaskCommand to unmark as done
        } else if (fullCommand.startsWith("delete")) {
            return new DeleteTaskCommand(fullCommand); // Create a new DeleteTaskCommand
        } else if (fullCommand.equalsIgnoreCase("list")) {
            return new ListTasksCommand(); // Create a new ListTasksCommand
        } else if (fullCommand.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (fullCommand.startsWith("find")) {
            return new FindTaskCommand(fullCommand);
        } else if (fullCommand.isEmpty()) {
            throw new AldenException("Keyword cannot be empty for find command.");
        } else if (fullCommand.startsWith("sort")) {
            return new SortCommand(fullCommand);
        } else {
            throw new AldenException("Invalid command: " + fullCommand); // Throw exception if the command is invalid
        }
    }
}
