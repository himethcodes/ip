/**
 * Represents a task that can be managed by the task list.
 * A task has a description and a completion status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the specified description.
     * By default, the task is not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * "[X]" for done tasks, "[ ]" for not done tasks.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The formatted task details.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    /**
     * Returns a formatted string for saving the task to a file.
     * The format is: "1 | description" for done tasks, "0 | description" for not done tasks.
     *
     * @return The save format string.
     */
    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Parses a task from a formatted string.
     *
     * @param line The formatted task string from storage.
     * @return The parsed Task object.
     * @throws BuddyException If the format is incorrect.
     */
    public static Task parse(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 2) {
            throw new BuddyException("Invalid task format in file.");
        }

        boolean isDone = parts[0].equals("1");
        String description = parts[1];

        Task task;
        if (line.startsWith("T")) {
            task = new Todo(description);
        } else if (line.startsWith("D")) {
            if (parts.length < 3) {
                throw new BuddyException("Invalid deadline format.");
            }
            task = new Deadline(description, parts[2]);
        } else if (line.startsWith("E")) {
            if (parts.length < 4) {
                throw new BuddyException("Invalid event format.");
            }
            task = new Event(description, parts[2], parts[3]);
        } else {
            throw new BuddyException("Unknown task type in file.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }
}
