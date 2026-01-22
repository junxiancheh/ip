public class Deadlines extends Task {
    private String endTime;

    public Deadlines(String description) {
        super("");
        String[] parts = description.split(" /by");
        this.description = parts[0];
        this.endTime = parts[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + endTime + ")";
    }
}
