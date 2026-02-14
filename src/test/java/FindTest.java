import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import sleeper.task.Find;
import java.util.ArrayList;
import sleeper.task.Task;

public class FindTest {
    @Test
    public void testFind_TasksPresent_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("read book"));
        tasks.add(new Task("return book"));
        tasks.add(new Task("return food"));

        ArrayList<Task> foundTasks = Find.findTasks(tasks, "book");
        assertEquals(2, foundTasks.size());
    }

    @Test 
    public void testFind_NoTasks_success() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("read book"));
        tasks.add(new Task("return book"));
        tasks.add(new Task("return food"));

        ArrayList<Task> foundTasks = Find.findTasks(tasks, "exercise");
        assertEquals(0, foundTasks.size());
    }
}
