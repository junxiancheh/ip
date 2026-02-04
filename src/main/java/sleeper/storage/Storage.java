package sleeper.storage;
import sleeper.task.Task;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The Storage class handles saving and loading tasks to and from a file.
 */
public class Storage {
    public static final String FILE_PATH = "./bin/sleeper.txt";

    /**
     * Saves the list of tasks to a file.
     * 
     * This method writes each task's string representation to the specified file,
     * with each task on a new line.
     * 
     * @param tasks
     * @throws IOException
     */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            writer.write(task.toString() + "\n");
        }
        writer.close();
    }

    /**
     * Loads the list of tasks from a file.
     * 
     * This method reads each line from the specified file and loads the tasks.
     * If the file does not exist, it returns an empty list.
     * 
     * @return
     * @throws FileNotFoundException
     */
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