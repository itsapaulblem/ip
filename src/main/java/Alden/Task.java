package Alden;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty or null.");
        }
        this.description = description.trim(); // Trim whitespace
        this.isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Marked done or not done
    }

    public String getDescription(){
        return this.description;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description; // Consistent with getStatusIcon
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return isDone == task.isDone && description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + (isDone ? 1 : 0);
        return result;
    }
}