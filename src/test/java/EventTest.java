import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import sleeper.task.Event;
import sleeper.exception.SleeperException;

public class EventTest {
    @Test
    public void testEvent() throws SleeperException {
        String input = "event project meeting /from 12/9/2023 1400 /to 12/9/2023 1600";
        assertEquals("[E][ ] event project meeting (from:Sept 12 2023 to:Sept 12 2023)", new Event(input).toString());
    }

    @Test
    public void testEventWithInvalidDate() {
        try {
            assertEquals("[E][ ] event project meeting (from:Sept 12 2023 to:Sept 12 2023)", new Event("event project meeting /from 2023-09-12 14:00 /to 2023-09-12 16:00").toString());
        } catch (SleeperException e) {
            assertEquals("The date and time format is incorrect. Please use 'd/M/yyyy HHmm' format.", e.getMessage());
        }
    }
}
