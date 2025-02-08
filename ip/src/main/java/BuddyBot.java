import java.util.ArrayList;
import java.util.Scanner;

public class BuddyBot {
    private final ArrayList<Task> tasks;

    public BuddyBot() {
        this.tasks = new ArrayList<>();
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
    }

    private void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        printTaskAdded(task);
    }

    private void addEvent(String description, String from, String to) {
        Task task = new Event(description, from, to);
        tasks.add(task);
        printTaskAdded(task);
    }

    private void markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsDone();
            System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).unmarkAsDone();
            System.out.println("OK, I've marked this task as not done yet:\n  " + tasks.get(index));
        } else {
            System.out.println("Invalid task number.");
        }
    }

    private void printTaskAdded(Task task) {
        System.out.println("Got it! I've added this task:\n  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    public static void main(String[] args) {
        new BuddyBot().run();
    }
}
