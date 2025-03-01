public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toSaveFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
