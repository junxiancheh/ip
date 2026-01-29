import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Storage {
    public static final String FILE_PATH = "./bin/sleeper.txt";

    // Method to save tasks to a file
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            writer.write(task.toString() + "\n");
        }
        writer.close();
    }

    // Method to load tasks from a file
    public static ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return tasks;
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.startsWith("[ ]") || line.startsWith("[X]")) {
                int firstBracket = line.indexOf("]");
                if (firstBracket != -1) {
                    String taskDetails = line.substring(firstBracket + 1).trim();
                    line = taskDetails;
                }
                tasks.add(new Task(line));
            }
        }
        scanner.close();
        return tasks;
    }
}