import java.util.ArrayList;

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
        return task;  // Return the marked task
    }


    public Task unmarkTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task index out of bounds.");
        }
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;  // Return the unmarked task
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
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
