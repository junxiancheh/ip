package sleeper.task;
import java.util.ArrayList;

public class Find {

    public Find() {
    }

    /**
     * Finds tasks that contain the given keyword in their description.
     * 
     * @param tasks The list of tasks to search through.
     * @param keyword The keyword to search for.
     * @return A list of tasks that contain the keyword.
     */
    public static ArrayList<Task> findTasks(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
