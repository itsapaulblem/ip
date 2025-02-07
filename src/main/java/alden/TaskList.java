package alden;

import java.util.ArrayList;

/**
 * Represents a list of tasks. This class provides methods to add, remove, and retrieve tasks.
 * It also provides methods to get the size of the list and check if it is empty.
 */
public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>(); // List of tasks

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null task";
        int sizeBefore = tasks.size();
        tasks.add(task);
        assert tasks.size() == sizeBefore + 1 : "Task list size should increase by 1";
        assert tasks.get(tasks.size() - 1).equals(task) : "Added task must be in list";
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     */
    public Task removeTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index must be within bounds";
        Task taskToRemove = tasks.get(index);
        Task removed = tasks.remove(index);
        assert removed.equals(taskToRemove) : "Removed task should match requested task";
        assert tasks.size() >= 0 : "Task list size cannot be negative";
        // Check that the removed task is no longer in the list
        for (Task t : tasks) {
            assert !t.equals(removed) : "Removed task should not be in list";
        }
        return removed;
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index must be within bounds";
        return tasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        assert tasks.size() >= 0 : "Task list size cannot be negative";
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the list contains no tasks, otherwise false.
     */
    public boolean isEmpty() {

        return tasks.isEmpty();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of tasks that match the given keyword.
     */
    public ArrayList<Task> findTasks(String keyword) {
        assert keyword != null && !keyword.trim().isEmpty() : "Search keyword cannot be null or empty";
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String lowerCaseKeyword = keyword.toLowerCase(); // Convert keyword to lowercase for case-insensitive search

        for (Task task : tasks) {
            if (task.getDescription() != null) {
                String lowerCaseDescription = task.getDescription().toLowerCase(); // Convert description to lowercase
                if (lowerCaseDescription.contains(lowerCaseKeyword)) {
                    matchingTasks.add(task);
                }
            }
        }
        return matchingTasks; // Return the list of matching tasks
    }
}
