import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    protected LocalDate by; // Use LocalDate for date storage

    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Use YYYY-MM-DD.");
            this.by = null;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                (by != null ? " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" : "");
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + (by != null ? by.toString() : "N/A");
    }
}
