package Alden;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load(TaskList tasks) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
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

                if (task != null && isDone) {
                    task.markAsDone();
                }

                if (task != null) {
                    tasks.addTask(task);
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }
}
