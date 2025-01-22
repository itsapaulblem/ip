package Alden;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public ArrayList<Task> findTasks(String keyword) {
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
