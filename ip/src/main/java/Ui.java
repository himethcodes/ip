import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm BuddyBot");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye! Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
