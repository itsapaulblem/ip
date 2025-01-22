package Alden;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
