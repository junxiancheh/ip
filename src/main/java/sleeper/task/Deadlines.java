package sleeper.task;
import sleeper.exception.SleeperException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task with a description and a deadline time. 
 * 
 * Extends the Task class.
 */
public class Deadlines extends Task {
    private LocalDateTime endTime;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    
    /**
     * Constructor for Deadline tasks. 
     * 
     * This constructor takes a description string which includes the task description
     * and the deadline time separated by " /by". It parses the deadline time into a
     * LocalDateTime object.
     * 
     * @param description The description of the deadline task including the deadline time.
     * @throws SleeperException if the date and time format is incorrect.
     * @throws DateTimeParseException if the date and time format is incorrect.
     * @returns Deadlines object
     */
    public Deadlines(String description) throws SleeperException {
        super("");

        String[] parts = description.split(" /by");
        this.description = parts[0].trim();
       
        try {
            this.endTime = LocalDateTime.parse(parts[1].trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new SleeperException("The date and time format is incorrect. Please use 'd/M/yyyy HH:mm' format.");
        }
    }

    /**
     * Returns the string representation of the Deadline task.
     * 
     * @return A string representing the Deadline task in the format "[D] Task description (by: d/M/yyyy HH:mm)".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.format(OUTPUT_FORMAT) + ")";
    }
}
