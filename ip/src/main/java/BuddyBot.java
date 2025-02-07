import java.util.Scanner;

public class BuddyBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100]; // Array to store tasks
        int taskCount = 0; // Number of tasks stored

        // Print greeting
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm BuddyBot");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine(); // Read user input

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]); // Display stored tasks
                }
                System.out.println("____________________________________________________________");
            } else {
                tasks[taskCount] = input; // Store the task
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" added: " + input);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close(); // Close the scanner
    }
}
