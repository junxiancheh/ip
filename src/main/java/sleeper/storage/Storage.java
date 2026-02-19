package sleeper.storage;

import sleeper.task.Task;
import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * The Storage class handles saving and loading tasks to and from a file.
 * 
 * @note Javadoc phrasing and resource management structure refined with the
 *       assistance of AI.
 */
public class Storage {
    public static final String FILE_PATH = "./sleeper.txt";

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
        createNewFile();
        assert tasks != null : "Tasks list cannot be null";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(tasks); // This saves the entire ArrayList at once!
        }
    }

    /**
     * Loads the list of tasks from a file.
     * 
     * This method reads each line from the specified file and loads the tasks.
     * If the file does not exist, it returns an empty list.
     * 
     * @return ArrayList<Task> containing the loaded tasks
     * @throws FileNotFoundException if the file is not found
     * @return An ArrayList of tasks. Returns an empty list if the file is missing.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Task>) obj;
            } else {
                throw new ClassNotFoundException("Data in file is not of correct type");
            }
        }
    }

    /**
     * Checks if a line from the file is a valid task line.
     * 
     * A valid task line starts with either "[ ]" for incomplete tasks or "[X]" for
     * completed tasks.
     * 
     * @param line the line to check
     * @return true if the line is a valid task line, false otherwise
     */
    public static boolean isValidTaskLine(String line) {
        return line.contains("[ ]") || line.contains("[X]");
    }

    public static void createNewFile() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();

        try {
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.err.println("Could not initialize storage file: " + e.getMessage());
        }
    }
}