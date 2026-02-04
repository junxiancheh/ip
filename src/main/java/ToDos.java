/**
 * Represents a todo task.
 * 
 * Extends the Task class.
 */
public class ToDos extends Task {
    public ToDos(String description) {
        super("");
        this.description = description;
    }
    
    /**
     * Returns the string representation of the ToDo task.
     * 
     * @return A string representing the ToDo task in the format "[T] Task description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
