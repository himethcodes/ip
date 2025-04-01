/**
 * Abstract class for all commands in Buddybot.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks The task list to operate on.
     * @param ui The UI to display messages.
     * @param storage The storage to save tasks.
     * @throws BuddyException If there's an error executing the command.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BuddyException;

    /**
     * Checks if this command is an exit command.
     *
     * @return True if this is an exit command, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
