package sleeper.task;
import sleeper.exception.SleeperException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a description, start time, and end time.
 * 
 * Extends the Task class.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor for Event tasks.
     *
     * This constructor takes a description string which includes the task description
     * and the start and end time separated by " /from" and " /to". It parses the start
     * and end time into LocalDateTime objects.
     *
     * @param description The description of the event task including the start and end time.
     * @throws SleeperException if the date and time format is incorrect.
     * @throws DateTimeParseException if the date and time format is incorrect.
     * @returns Events object
     */
    public Event(String description) throws SleeperException {
        super("");
        
        // Split 1: Separates the Description (Task Name) from the Time Information.
        // Example Input: "project meeting /from 2/12/2019 1800 /to 2/12/2019 2000"
        // firstSplit[0] -> "project meeting"
        // firstSplit[1] -> " 2/12/2019 1800 /to 2/12/2019 2000"
        String[] firstSplit = description.split(" /from", 2);
        this.description = firstSplit[0].trim();
       
        // Split 2: Takes the Time Information (firstSplit[1]) and separates Start from End.
        // Input: " 2/12/2019 1800 /to 2/12/2019 2000"
        // secondSplit[0] -> " 2/12/2019 1800" (Start Time)
        // secondSplit[1] -> " 2/12/2019 2000" (End Time)
        String[] secondSplit = firstSplit[1].split(" /to", 2);

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(secondSplit[0].trim(), INPUT_FORMAT);
            this.startTime = fromDateTime.toLocalDate();
            LocalDateTime toDateTime = LocalDateTime.parse(secondSplit[1].trim(), INPUT_FORMAT);
            this.endTime = toDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            throw new SleeperException("The date and time format is incorrect. Please use 'd/M/yyyy HHmm' format.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SleeperException("The event seems to be incorrectly formatted. Please use the format: description /from 'd/M/yyyy HHmm' /to 'd/M/yyyy HHmm'");
        }
    }

    /**
     * Returns the string representation of the Event task.
     *
     * @return A string representing the Event task in the format "[E] Task description (from: MMM dd yyyy to: MMM dd yyyy)".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + startTime.format(OUTPUT_FORMAT) + " to:" + endTime.format(OUTPUT_FORMAT) + ")";
    }
}
