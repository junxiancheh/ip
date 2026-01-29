import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Events(String description) throws SleeperException {
        super("");
        String[] parts = description.split(" /from", 2);
        this.description = parts[0].trim();
        String[] remaining = parts[1].split(" /to", 2);

        try {
            LocalDateTime fromDateTime = LocalDateTime.parse(remaining[0].trim(), INPUT_FORMAT);
            this.startTime = fromDateTime.toLocalDate();
            LocalDateTime toDateTime = LocalDateTime.parse(remaining[1].trim(), INPUT_FORMAT);
            this.endTime = toDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            throw new SleeperException("The date and time format is incorrect. Please use 'd/M/yyyy HHmm' format.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + startTime.format(OUTPUT_FORMAT) + " to:" + endTime.format(OUTPUT_FORMAT) + ")";
    }
}
