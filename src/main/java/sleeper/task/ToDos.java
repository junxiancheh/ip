package sleeper.task;

public class ToDos extends Task {
    public ToDos(String description) {
        super("");
        this.description = description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
