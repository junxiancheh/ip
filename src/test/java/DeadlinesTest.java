import sleeper.task.Deadlines;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import sleeper.exception.SleeperException;

public class DeadlinesTest {
    @Test
    public void testDeadline() throws SleeperException {
        String input = "deadline return book /by 2/12/2019 1800";
        assertEquals("[D][ ] deadline return book (by: 2/12/2019 1800)", new Deadlines(input).toString());
    }

    @Test
    public void testDeadlineWithInvalidDate() {
        try {
            assertEquals("[D][ ] deadline return book (by: 2/12/2019 1800)", new Deadlines("deadline return book /by 2019-12-02 18:00").toString());
        } catch (SleeperException e) {
            assertEquals("The date and time format is incorrect. Please use 'd/M/yyyy HHmm' format.", e.getMessage());
        }
    }
}
