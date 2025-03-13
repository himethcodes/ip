public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }

    public String toSaveFormat() {
        return (isDone ? "1" : "0") + " | " + description;
    }

    public static Task parse(String line) throws BuddyException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) throw new BuddyException("Invalid task format in file.");

        boolean isDone = parts[1].equals("1");  // Corrected index for isDone
        String description = parts[2];  // Corrected index for description

        Task task;
        if (line.startsWith("T")) {
            task = new Todo(description);
        } else if (line.startsWith("D")) {
            if (parts.length < 4) throw new BuddyException("Invalid deadline format.");
            task = new Deadline(description, parts[3]);  // Corrected index for deadline date
        } else if (line.startsWith("E")) {
            if (parts.length < 5) throw new BuddyException("Invalid event format.");
            task = new Event(description, parts[3], parts[4]);  // Corrected indices for event start & end
        } else {
            throw new BuddyException("Unknown task type in file.");
        }

        if (isDone) {
            task.markAsDone();
        }

        return task;
    }


}

