import sleeper.task.Deadlines;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import sleeper.exception.SleeperException;

public class DeadlinesTest {
    @Test
    public void testDeadline_validInput_success() throws SleeperException {
        String input = "return book /by 2/12/2019 1800";
        assertEquals("[D][ ] return book (by: 2/12/2019 18:00)", new Deadlines(input).toString());
    }

    @Test
    public void testDeadline_invalidFormat_exceptionThrown() {
        try {
            assertEquals("[D][ ] deadline return book (by: 2/12/2019 1800)",
                    new Deadlines("deadline return book /by 2019-12-02 18:00").toString());
        } catch (SleeperException e) {
            assertEquals("The date and time format is incorrect. Please use '/by dd/MM/yyyy HHmm' format.", e.getMessage());
        }
    }

    @Test
    public void testDeadline_nonExistentDate_exceptionThrown() {
        try {
            new Deadlines("return book /by 30/2/2024 1800");
        } catch (SleeperException e) {
            assertEquals("That date doesn't exist on the calendar.", e.getMessage());
        }
    }

    @Test
    public void testDeadline_missingByKeyword_exceptionThrown() {
        try {
            new Deadlines("return book 2/12/2019 1800");
        } catch (SleeperException e) {
            assertEquals("Something went wrong! You might be using the wrong format! Please use '/by dd/MM/yyyy HHmm' format!", e.getMessage());
        }
    }

    @Test
    public void testDeadline_emptyDescription_exceptionThrown() {
        try {
            new Deadlines(" /by 2/12/2019 1800");
        } catch (SleeperException e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }
}
