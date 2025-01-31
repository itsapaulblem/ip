package alden;

/**
 * Represents a Todo task. A Todo is a simple task that has a description and a completion status.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the Todo task.
     */
    public Todo(String description) {

        super(description);
    }

    /**
     * Returns a string representation of the Todo task.
     * The format is: "[T][X] description" where "X" is the task's status icon.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {

        return "[T][" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns the Todo task in a format suitable for saving to a file.
     * The format is: "T | status | description".
     *
     * @return A string representing the Todo task in a file-compatible format.
     */
    @Override
    public String toFileFormat() {

        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
