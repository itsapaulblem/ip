public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Marked done or not done
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName().charAt(0) + "][" + getStatusIcon() + "] " + description;
    }
}
