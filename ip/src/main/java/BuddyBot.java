import java.util.Scanner;

public class BuddyBot {
    private static final int MAX_TASKS = 100;
    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (true) {
            String command = scanner.nextLine().trim();

            if (command.equals("bye")) {
                printExitMessage();
                break;
            } else if (command.equals("list")) {
                printTaskList();
            } else if (command.startsWith("mark ")) {
                markTaskAsDone(command);
            } else if (command.startsWith("unmark ")) {
                unmarkTask(command);
            } else {
                addTask(command);
            }
        }

        scanner.close();
    }

    private static void printGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    private static void printExitMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    private static void addTask(String taskDescription) {
        if (taskCount < MAX_TASKS) {
            tasks[taskCount] = new Task(taskDescription);
            taskCount++;
            System.out.println("____________________________________________________________");
            System.out.println(" added: " + taskDescription);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Task list is full!");
        }
    }

    private static void printTaskList() {
        System.out.println("____________________________________________________________");
        if (taskCount == 0) {
            System.out.println(" No tasks in the list!");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < taskCount; i++) {
                System.out.println(" " + (i + 1) + ". " + tasks[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }

    private static void markTaskAsDone(String command) {
        int taskIndex = getTaskIndex(command, "mark ");
        if (taskIndex != -1) {
            tasks[taskIndex].markAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        }
    }

    private static void unmarkTask(String command) {
        int taskIndex = getTaskIndex(command, "unmark ");
        if (taskIndex != -1) {
            tasks[taskIndex].unmarkAsDone();
            System.out.println("____________________________________________________________");
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks[taskIndex]);
            System.out.println("____________________________________________________________");
        }
    }

    private static int getTaskIndex(String command, String prefix) {
        try {
            int index = Integer.parseInt(command.substring(prefix.length())) - 1;
            if (index >= 0 && index < taskCount) {
                return index;
            } else {
                System.out.println("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
        return -1;
    }
}
