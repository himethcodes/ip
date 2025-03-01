public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public abstract String toSaveFormat();

    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}
