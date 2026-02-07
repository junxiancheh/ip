package sleeper.task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";  
        tasks.get(index).markAsDone();
    }

    public void unmarkTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds";
        tasks.get(index).markAsNotDone();
    }

    public void clearTasks() {
        tasks.clear();
    }
}
