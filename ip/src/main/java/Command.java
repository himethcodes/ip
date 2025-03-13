public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    public boolean isExit() {
        return false;
    }
}

class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks(ui);
    }
}

class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.showMessage("Got it! I've added this task:\n  " + task);
    }
}

class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task removedTask = tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
    }
}

class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task markedTask = tasks.markTask(index);
        storage.save(tasks.getTasks());
        ui.showMessage("Nice! I've marked this task as done:\n  " + markedTask);
    }
}

// NEW: UnmarkCommand
class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException {
        Task unmarkedTask = tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
        ui.showMessage("OK, I've marked this task as not done yet:\n  " + unmarkedTask);
    }
}