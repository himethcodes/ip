import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Use YYYY-MM-DD.");
            this.from = null;
            this.to = null;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                (from != null && to != null ? " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                        " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" : "");
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
