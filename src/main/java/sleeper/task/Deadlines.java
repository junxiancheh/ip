package sleeper.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadlines extends Task {
    private LocalDateTime endTime;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Deadlines(String description) throws SleeperException {
        super("");

        String[] parts = description.split(" /by");
        this.description = parts[0].trim();
       
        try {
            this.endTime = LocalDateTime.parse(parts[1].trim(), INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new SleeperException("The date and time format is incorrect. Please use 'd/M/yyyy HHmm' format.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.format(OUTPUT_FORMAT) + ")";
    }
}
