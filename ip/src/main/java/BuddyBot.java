import java.util.ArrayList;

/**
 * A chatbot program that tracks different types of tasks.
 */


public class BuddyBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;


    public BuddyBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BuddyException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (BuddyException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new BuddyBot("data/duke.txt").run();
    }
}
