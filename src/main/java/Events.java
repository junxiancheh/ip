public class Events extends Task {
    private String startTime;
    private String endTime;

    public Events(String description) {
        super("");
        String[] parts = description.split(" /from", 2);
        this.description = parts[0];
        String[] remaining = parts[1].split(" /to", 2);
        this.startTime = remaining[0];
        this.endTime = remaining[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + startTime + " to:" + endTime + ")";
    }
}
