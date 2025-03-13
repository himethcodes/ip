public class Parser {
    public static Command parse(String userInput) throws BuddyException {
        String[] words = userInput.trim().split(" ", 2);  // Trim input before splitting
        String command = words[0];

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(words[1].trim()) - 1);  // Trim before parsing
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(words[1].trim()) - 1);  // Trim before parsing
            case "delete":
                return new DeleteCommand(Integer.parseInt(words[1].trim()) - 1);  // Trim before parsing
            case "todo":
                return new AddCommand(new Todo(words[1].trim()));
            case "deadline":
                String[] deadlineParts = words[1].trim().split(" /by ");
                if (deadlineParts.length < 2) throw new BuddyException("Invalid deadline format.");
                return new AddCommand(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
            case "event":
                String[] eventParts = words[1].trim().split(" /from | /to ");
                if (eventParts.length < 3) throw new BuddyException("Invalid event format.");
                return new AddCommand(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
            case "find":
                if (words.length < 2) {
                    throw new BuddyException("Please provide a keyword to search for.");
                }
                return new FindCommand(words[1].trim());
            default:
                throw new BuddyException("Unknown command.");
        }
    }
}
