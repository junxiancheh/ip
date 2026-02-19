import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import sleeper.exception.SleeperException;
import sleeper.task.Edit;
import sleeper.task.ToDos;
import sleeper.task.Task;

public class EditTest {
    @Test
    public void testExecuteEdit_simpleStringUpdate_success() throws SleeperException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("task test"));
        Edit editCommand = new Edit("edit 1 buy milk");
        assertEquals("[ ] buy milk", editCommand.execute(tasks).toString());
    }

    @Test
    public void testExecuteEdit_invalidIndex_throwsException() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("task test"));
        Edit editCommand = new Edit("edit 10 buy milk");
        try {
            editCommand.execute(tasks);
        } catch (SleeperException e) {
            assertEquals("Can't find task. Please provide a valid index", e.getMessage());
        }
    }

    @Test
    public void testExecuteEdit_wrongFormat_throwsException() {
        ArrayList<Task> tasks = new ArrayList<>();
        Edit editCommand = new Edit("edit 1");
        try {
            editCommand.execute(tasks);
        } catch (SleeperException e) {
            assertEquals("The format of the command seems to be wrong. Try edit 1 todo read book", e.getMessage());
        }
    }
}
