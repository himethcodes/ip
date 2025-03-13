import java.util.ArrayList;

/**
 * Represents a list of tasks that the user manages.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task number does not exist.");
        }
        return tasks.remove(index);
    }

    public Task markTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task index out of bounds.");
        }
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    public Task unmarkTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task index out of bounds.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    public void listTasks(Ui ui) {
        if (tasks.isEmpty()) {
            ui.showMessage("Your task list is empty.");
        } else {
            ui.showMessage("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Searches for tasks that contain the given keyword and displays them.
     * @param keyword The keyword to search for.
     * @param ui The UI component for displaying messages.
     */
    public void findTask(String keyword, Ui ui) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found.");
        } else {
            ui.showMessage("Here are the matching tasks:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + matchingTasks.get(i));
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
