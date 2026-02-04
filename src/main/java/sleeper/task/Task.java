package sleeper.task;

/**
 * The Task class represents a task with a description and completion status.
 * 
 * It provides methods to mark the task as done or not done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon [X] or [ ] of the task.
     * 
     * @return the status icon as a string
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks the task as done.
     * 
     * @return void
     */
    public void markAsDone() {
        isDone = true;
    }
    
    /**
     * Marks the task as not done.
     * 
     * @return void
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     * 
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + description;
    }
}
