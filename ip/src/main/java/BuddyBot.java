import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BuddyBot {
    private final ArrayList<Task> tasks;
    private static final String FILE_PATH = "./data/duke.txt";

    public BuddyBot() {
        this.tasks = new ArrayList<>();
        loadTasks(); // Load tasks when the bot starts
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm BuddyBot\nWhat can I do for you?");

        while (true) {
            String command = scanner.nextLine();

            if (command.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("todo ")) {
                addTodo(command.substring(5));
            } else if (command.startsWith("deadline ")) {
                String[] parts = command.substring(9).split(" /by ");
                addDeadline(parts[0], parts[1]);
            } else if (command.startsWith("event ")) {
                String[] parts = command.substring(6).split(" /from | /to ");
                addEvent(parts[0], parts[1], parts[2]);
            } else if (command.startsWith("mark ")) {
                markTask(Integer.parseInt(command.substring(5)) - 1);
            } else if (command.startsWith("unmark ")) {
                unmarkTask(Integer.parseInt(command.substring(7)) - 1);
            } else if (command.startsWith("delete ")) {
                deleteTask(Integer.parseInt(command.substring(7)) - 1);
            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
        }

        scanner.close();
    }

    private void listTasks() {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        printTaskAdded(task);
        saveTasks();
    }

    private void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAdded(task);
        saveTasks();
    }

    private void addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAdded(task);
        saveTasks();
    }

    private void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.remove(index);
            System.out.println("Noted. I've removed this task:\n  " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            saveTasks();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void printTaskAdded(Task task) {
        System.out.println("Got it! I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" \\| ");
                switch (parts[0]) {
                    case "T":
                        tasks.add(new Todo(parts[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(parts[2], parts[3]));
                        break;
                    case "E":
                        tasks.add(new Event(parts[2], parts[3], parts[4]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks.");
        }
    }

    public static void main(String[] args) {
        new BuddyBot().run();
    }
}
