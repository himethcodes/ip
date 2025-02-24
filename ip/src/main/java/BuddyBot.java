import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BuddyBot {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "data/duke.txt";

    public BuddyBot() {
        this.tasks = new ArrayList<>();
        loadTasksFromFile();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        printLine();
        System.out.println("     Hello! I'm BuddyBot");
        System.out.println("     What can I do for you?");
        printLine();

        while (true) {
            String command = scanner.nextLine();

            try {
                if (command.equals("bye")) {
                    printLine();
                    System.out.println("Bye! Hope to see you again soon!");
                    printLine();
                    break;
                } else if (command.equals("list")) {
                    printLine();
                    listTasks();
                    printLine();
                } else if (command.startsWith("todo ")) {
                    printLine();
                    addTodo(command.substring(5));
                    printLine();
                } else if (command.startsWith("deadline ")) {
                    printLine();
                    String[] parts = command.substring(9).split(" /by ");
                    if (parts.length < 2) throw new BuddyException("Oops! A deadline must have a description and a '/by' date.");
                    addDeadline(parts[0], parts[1]);
                    printLine();
                } else if (command.startsWith("event ")) {
                    printLine();
                    String[] parts = command.substring(6).split(" /from | /to ");
                    if (parts.length < 3) throw new BuddyException("Oops! An event must have a description, a '/from' time, and a '/to' time.");
                    addEvent(parts[0], parts[1], parts[2]);
                    printLine();
                } else if (command.startsWith("mark ")) {
                    printLine();
                    int index = Integer.parseInt(command.substring(5)) - 1;
                    markTask(index);
                    printLine();
                } else if (command.startsWith("unmark ")) {
                    printLine();
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    unmarkTask(index);
                    printLine();
                } else if (command.startsWith("delete ")) {
                    printLine();
                    int index = Integer.parseInt(command.substring(7)) - 1;
                    deleteTask(index);
                    printLine();
                } else {
                    throw new BuddyException("Sorry, I don't understand that command.");
                }
            } catch (BuddyException e) {
                printLine();
                System.out.println("OOPS!!! " + e.getMessage());
                printLine();
            } catch (NumberFormatException e) {
                printLine();
                System.out.println("OOPS!!! Please enter a valid task number.");
                printLine();
            } catch (IndexOutOfBoundsException e) {
                printLine();
                System.out.println("OOPS!!! Task number does not exist.");
                printLine();
            }
        }

        scanner.close();
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private void addTodo(String description) throws BuddyException {
        if (description.isBlank()) {
            throw new BuddyException("The description of a todo cannot be empty.");
        }
        Task task = new Todo(description);
        tasks.add(task);
        saveTasksToFile();
        printTaskAdded(task);
    }

    private void addDeadline(String description, String by) throws BuddyException {
        if (description.isBlank()) {
            throw new BuddyException("The description of a deadline cannot be empty.");
        }
        Task task = new Deadline(description, by);
        tasks.add(task);
        saveTasksToFile();
        printTaskAdded(task);
    }

    private void addEvent(String description, String from, String to) throws BuddyException {
        if (description.isBlank()) {
            throw new BuddyException("The description of an event cannot be empty.");
        }
        Task task = new Event(description, from, to);
        tasks.add(task);
        saveTasksToFile();
        printTaskAdded(task);
    }

    private void markTask(int index) {
        tasks.get(index).markAsDone();
        saveTasksToFile();
        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
    }

    private void unmarkTask(int index) {
        tasks.get(index).unmarkAsDone();
        saveTasksToFile();
        System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
    }

    private void deleteTask(int index) {
        Task removedTask = tasks.remove(index);
        saveTasksToFile();
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    private void loadTasksFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    tasks.add(Task.parse(line));
                } catch (BuddyException e) {
                    System.out.println("Skipping invalid task entry: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
    }


    private void printTaskAdded(Task task) {
        System.out.println("Got it! I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        new BuddyBot().run();
    }
}
