package alden;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class is responsible for saving and loading tasks from a file.
 * It handles reading and writing task data to the file specified by the file path.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath The path to the file where tasks will be saved or loaded.
     */
    public Storage(String filePath) {
        assert filePath != null && !filePath.trim().isEmpty() : "File path cannot be null or empty";
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path and adds them to the provided TaskList.
     * If the file does not exist, a new file will be created.
     *
     * @param tasks The TaskList object to which tasks will be added after loading.
     */
    public void load(TaskList tasks) {
        assert tasks != null : "TaskList cannot be null";
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create directories if they do not exist
                file.createNewFile(); // Create a new file if it doesn't exist
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ");
                assert parts.length >= 2 : "File format must have at least type and status";

                String taskType = parts[0];
                boolean isDone = "1".equals(parts[1]);
                Task task = null;

                try {
                    switch (taskType) {
                        case "T":
                            assert parts.length >= 3 : "Todo task must have description";
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            assert parts.length >= 4 : "Deadline task must have description and date";
                            task = new Deadline(parts[2], parts[3]);
                            break;
                        case "E":
                            assert parts.length >= 5 : "Event task must have description and two dates";
                            task = new Event(parts[2], parts[3], parts[4]);
                            break;
                        default:
                            System.out.println("Unknown task type in file: " + taskType);
                            break;
                    }

                    if (task != null) {
                        if (isDone) {
                            task.markAsDone();
                        }
                        tasks.addTask(task);
                    }
                } catch (AldenException e) {
                    System.out.println("Error creating task from file: " + e.getMessage());
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Saves the tasks in the provided TaskList to the file specified by the file path.
     *
     * @param tasks The list of tasks to save to the file.
     */
    public void save(ArrayList<Task> tasks) {
        assert tasks != null : "Tasks list cannot be null";
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                assert task != null : "Individual task cannot be null";
                String fileFormat = task.toFileFormat();
                assert fileFormat != null && !fileFormat.isEmpty() : "Task file format cannot be null or empty";
                writer.write(fileFormat + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
