package alden;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages persistent storage for the Alden task management system.
 * Handles reading and writing tasks to/from a file system storage.
 * Provides functionality for loading existing tasks and saving task updates.
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath Path to the storage file
     * @throws IllegalArgumentException if filePath is null or empty
     */
    public Storage(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be null or empty");
        }
        this.filePath = filePath;
    }

    /**
     * Loads tasks from storage and adds them to the provided TaskList.
     * Creates a new storage file if one doesn't exist.
     *
     * @param tasks TaskList to populate with loaded tasks
     * @throws IllegalArgumentException if tasks is null
     */
    public void load(TaskList tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("TaskList cannot be null");
        }

        try {
            File file = ensureFileExists();
            if (file == null) {
                return; // New file was created, nothing to load
            }
            loadTasksFromFile(file, tasks);
        } catch (IOException e) {
            logError("Error loading tasks", e);
        }
    }

    /**
     * Ensures the storage file exists, creating it if necessary.
     *
     * @return The storage File object, or null if a new file was created
     * @throws IOException if file operations fail
     */
    private File ensureFileExists() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            createNewFile(file);
            return null;
        }
        return file;
    }

    /**
     * Creates a new storage file and required directories.
     *
     * @param file The File object representing the storage file
     * @throws IOException if file creation fails
     */
    private void createNewFile(File file) throws IOException {
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            boolean dirCreated = parentDir.mkdirs();
            if (!dirCreated) {
                throw new IOException("Failed to create directory: " + parentDir.getPath());
            }
        }
        boolean fileCreated = file.createNewFile();
        if (!fileCreated) {
            throw new IOException("Failed to create file: " + file.getPath());
        }
    }

    /**
     * Loads tasks from the storage file into the provided TaskList.
     *
     * @param file Source file to read tasks from
     * @param tasks TaskList to populate with loaded tasks
     * @throws IOException if file reading fails
     */
    private void loadTasksFromFile(File file, TaskList tasks) throws IOException {
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    processTaskLine(line, tasks);
                }
            }
        }
    }

    /**
     * Processes a single line from the storage file into a task.
     *
     * @param line Line from the storage file
     * @param tasks TaskList to add the processed task to
     */
    private void processTaskLine(String line, TaskList tasks) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 2) {
            logError("Invalid file format: insufficient parts in line", null);
            return;
        }

        try {
            Task task = createTask(parts);
            if (task != null) {
                if ("1".equals(parts[1])) {
                    task.markAsDone();
                }
                tasks.addTask(task);
            }
        } catch (AldenException e) {
            logError("Error creating task from file", e);
        }
    }

    /**
     * Creates a task object from parsed file data.
     *
     * @param parts Array of task data parts from file
     * @return Created Task object, or null if creation fails
     * @throws AldenException if task creation fails
     */
    private Task createTask(String[] parts) throws AldenException {
        String taskType = parts[0];
        return switch (taskType) {
            case "T" -> createTodoTask(parts);
            case "D" -> createDeadlineTask(parts);
            case "E" -> createEventTask(parts);
            default -> {
                logError("Unknown task type: " + taskType, null);
                yield null;
            }
        };
    }

    /**
     * Creates a Todo task from file data.
     *
     * @param parts Array of task data parts
     * @return Created Todo task
     * @throws AldenException if required data is missing
     */
    private Todo createTodoTask(String[] parts) throws AldenException {
        if (parts.length < 3) {
            throw new AldenException("Todo task must have description");
        }
        return new Todo(parts[2]);
    }

    /**
     * Creates a Deadline task from file data.
     *
     * @param parts Array of task data parts
     * @return Created Deadline task
     * @throws AldenException if required data is missing
     */
    private Deadline createDeadlineTask(String[] parts) throws AldenException {
        if (parts.length < 4) {
            throw new AldenException("Deadline task must have description and date");
        }
        return new Deadline(parts[2], parts[3]);
    }

    /**
     * Creates an Event task from file data.
     *
     * @param parts Array of task data parts
     * @return Created Event task
     * @throws AldenException if required data is missing
     */
    private Event createEventTask(String[] parts) throws AldenException {
        if (parts.length < 5) {
            throw new AldenException("Event task must have description and two dates");
        }
        return new Event(parts[2], parts[3], parts[4]);
    }

    /**
     * Saves the provided tasks to storage.
     *
     * @param tasks List of tasks to save
     * @throws IllegalArgumentException if tasks is null
     */
    public void save(ArrayList<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("Tasks list cannot be null");
        }

        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                if (task == null) {
                    logError("Null task in tasks list", null);
                    continue;
                }
                String fileFormat = task.toFileFormat();
                if (fileFormat == null || fileFormat.isEmpty()) {
                    logError("Invalid file format for task: " + task, null);
                    continue;
                }
                writer.write(fileFormat + System.lineSeparator());
            }
        } catch (IOException e) {
            logError("Error saving tasks", e);
        }
    }

    /**
     * Logs an error message with optional exception details.
     *
     * @param message Error message to log
     * @param e Exception that caused the error, or null if none
     */
    private void logError(String message, Exception e) {
        System.out.println(message + (e != null ? ": " + e.getMessage() : ""));
    }
}