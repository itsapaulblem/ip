package Alden;

import java.io.*;
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

        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file specified in the file path and adds them to the provided TaskList.
     * If the file does not exist, a new file will be created.
     *
     * @param tasks The TaskList object to which tasks will be added after loading.
     */
    public void load(TaskList tasks) {
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
                if (line.isEmpty()) continue;

                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task = null;

                // Create task based on task type in the file
                switch (taskType) {
                    case "T":
                        if (parts.length >= 3) {
                            task = new Todo(parts[2]);
                        }
                        break;
                    case "D":
                        if (parts.length >= 4) {
                            task = new Deadline(parts[2], parts[3]);
                        }
                        break;
                    case "E":
                        if (parts.length >= 5) {
                            task = new Event(parts[2], parts[3], parts[4]);
                        }
                        break;
                }

                // Mark task as done if indicated in the file
                if (task != null && isDone) {
                    task.markAsDone();
                }

                // Add task to TaskList if it was successfully created
                if (task != null) {
                    tasks.addTask(task);
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
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator()); // Write each task's file format to the file
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
