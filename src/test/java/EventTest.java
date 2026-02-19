import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import sleeper.task.Event;
import sleeper.exception.SleeperException;

public class EventTest {
    @Test
    public void testEvent_validInput_success() throws SleeperException {
        String input = "project meeting /from 12/9/2023 1400 /to 12/9/2023 1600";
        assertEquals("[E][ ] project meeting (from: 12/9/2023 14:00 to: 12/9/2023 16:00)", new Event(input).toString());
    }

    @Test
    public void testEvent_invalidFormat_throwsSleeperException() {
        try {
            new Event("project meeting /from 2023-09-12 14:00 /to 2023-09-12 16:00");
        } catch (SleeperException e) {
            assertEquals("The date and time format is incorrect. Please use 'dd/MM/yyyy HHmm' format.", e.getMessage());
        }
    }

    @Test
    public void testEvent_nonExistentDate_throwsSleeperException() {
        try {
            new Event("project meeting /from 30/2/2024 2200 /to 30/2/2024 2300");
        } catch (SleeperException e) {
            assertEquals("That date doesn't exist on the calendar.", e.getMessage());
        }
    }

    @Test
    public void testEvent_endTimeBeforeStartTime_throwsSleeperException() {
        try {
            new Event("project meeting /from 12/9/2023 1600 /to 12/9/2023 1400");
        } catch (SleeperException e) {
            assertEquals("The end time must be after the start time!", e.getMessage());
        }
    }

    @Test
    public void testEvent_missingToKeyword_throwsSleeperException() {
        try {
            new Event("project meeting /from 12/9/2023 1400");
        } catch (SleeperException e) {
            assertEquals(
                    "The event seems to be incorrectly formatted. Please use the format: description /from 'dd/MM/yyyy HHmm' /to 'dd/MM/yyyy HHmm'",
                    e.getMessage());
        }
    }

    @Test
    public void testEvent_missingFromKeyword_throwsSleeperException() {
        try {
            new Event("project meeting /to 12/9/2023 1600");
        } catch (SleeperException e) {
            assertEquals(
                    "The event seems to be incorrectly formatted. Please use the format: description /from 'dd/MM/yyyy HHmm' /to 'dd/MM/yyyy HHmm'",
                    e.getMessage());
        }
    }

}
