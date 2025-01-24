package Alden;

/**
 * Represents a task with a description and a completion status.
 * This is the base class for different types of tasks (e.g., Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description; // Description of the task
    protected boolean isDone; // Status of whether the task is completed

    /**
     * Constructs a Task object with the given description.
     * The task is initially not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty or null.");
        }
        this.description = description.trim(); // Trim whitespace
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {

        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkAsDone() {

        isDone = false;
    }

    /**
     * Returns a string representation of the task's completion status.
     *
     * @return "X" if the task is done, " " if the task is not done.
     */
    public String getStatusIcon() {

        return (isDone ? "X" : " "); // Marked done or not done
    }

    /**
     * Returns the task's data in a format suitable for saving to a file.
     * This method should be implemented by subclasses to return their specific file format.
     *
     * @return A string representation of the task in file format.
     */

    public String getDescription(){

        return this.description;
    }

    public abstract String toFileFormat();

    /**
     * Returns a string representation of the task in a human-readable format.
     * This includes the task's completion status and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
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