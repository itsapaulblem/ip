import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Alden {
    private static final String FILE_PATH = "./data/Alden.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Alden");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (isRunning) {
            try {
                String userInput = scanner.nextLine().trim();
                if (userInput.equalsIgnoreCase("bye")) {
                    isRunning = false;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                } else if (userInput.equalsIgnoreCase("list")) {
                    printTaskList();
                } else if (userInput.startsWith("todo")) {
                    addTodoTask(userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadlineTask(userInput);
                } else if (userInput.startsWith("event")) {
                    addEventTask(userInput);
                } else if (userInput.startsWith("mark")) {
                    markTask(userInput, true);
                } else if (userInput.startsWith("unmark")) {
                    markTask(userInput, false);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    throw new AldenException("Invalid input");
                }
                saveTasksToFile();
            } catch (AldenException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Error: " + e.getMessage());
                System.out.println("____________________________________________________________");
            } catch (Exception e) {
                System.out.println("____________________________________________________________");
                System.out.println(" An unexpected error occurred: " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isDone = parts[1].equals("1");
                Task task;
                switch (taskType) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new IOException("Corrupted data format");
                }
                if (isDone) task.markAsDone();
                tasks.add(task);
            }
            fileScanner.close();
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Error loading tasks: " + e.getMessage());
            System.out.println(" Starting with an empty task list.");
            System.out.println("____________________________________________________________");
        }
    }

    private static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println(" Error saving tasks: " + e.getMessage());
            System.out.println("____________________________________________________________");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (tasks.isEmpty()) {
            System.out.println(" Your task list is empty.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void addTodoTask(String userInput) throws AldenException {
        if (userInput.length() <= 5) {
            throw new AldenException("The description of a todo cannot be empty.");
        }
        String description = userInput.substring(5).trim();
        Task newTask = new Todo(description);
        tasks.add(newTask);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void addDeadlineTask(String userInput) throws AldenException {
        String[] parts = userInput.split("/by", 2);
        if (parts.length < 2) {
            throw new AldenException("The deadline task must have a description and a /by clause.");
        }
        String description = parts[0].substring(9).trim();
        String by = parts[1].trim();
        Task newTask = new Deadline(description, by);
        tasks.add(newTask);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void addEventTask(String userInput) throws AldenException {
        String[] parts = userInput.split("/from|/to", 3);
        if (parts.length < 3) {
            throw new AldenException("The event task must have a description, /from clause, and /to clause.");
        }
        String description = parts[0].substring(6).trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        Task newTask = new Event(description, from, to);
        tasks.add(newTask);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void markTask(String userInput, boolean isDone) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task task = tasks.get(taskNumber);
        if (isDone) {
            task.markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            task.unmarkAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
        System.out.println("____________________________________________________________");
    }

    private static void deleteTask(String userInput) throws AldenException {
        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new AldenException("Invalid task number.");
        }
        Task removedTask = tasks.remove(taskNumber);
        System.out.println("____________________________________________________________");
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + removedTask);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}