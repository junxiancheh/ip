package sleeper.ui;
import sleeper.task.Task;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Ui class handles all interactions with the user.
 * 
 * It is responsible for displaying messages and reading user input.
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message when the application starts
     */
    public void printWelcomeMessage() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Sleeper\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Prints the goodbye message when the application ends
     * 
     */
    public void printGoodbyeMessage() {
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Reads a command from the user 
     * @return The command input by the user as a String
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays an error message to the user
     * @param message
     */
    public void showError(String message) {
        System.out.println("    ____________________________________________________________\n"
                + "    " + message + "\n"
                + "    ___________________________________________________________\n");
    }

    /**
     * Displays a message when a task is added
     * @param t The task that was added
     * @param items The current list of tasks
     */
    public void showAddTaskMessage(Task t, ArrayList<Task> items) {
        System.out.println("    ____________________________________________________________\n"
                + "    Got it. I've added this task: \n"
                + "    " + t + "\n"
                + "    Now you have " + items.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Displays the list of tasks to the user
     * @param items The current list of tasks
     */
    public void showTaskList(ArrayList<Task> items) {
        System.out.println("    ____________________________________________________________\n");
        for (int i = 0; i < items.size(); i++) {
            System.out.println("    " + (i + 1) + ": " + items.get(i) + "\n");
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Displays a message when a task is marked as done
     * @param t The task that was marked as done
     */
    public void showMarkTaskMessage(Task t) {
        System.out.println("    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done: \n"
                + "    " + t + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Displays a message when a task is unmarked as not done
     * @param t The task that was unmarked as not done
     */
    public void showUnmarkTaskMessage(Task t) {
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet: \n"
                + "    " + t + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Displays a message when a task is deleted
     * @param t The task that was deleted
     * @param items The current list of tasks
     */
    public void showDeleteTaskMessage(Task t, ArrayList<Task> items) {
        System.out.println("    ____________________________________________________________\n"
                + "    Noted. I've removed this task: \n"
                + "    " + t + "\n"
                + "    Now you have " + items.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Displays an error message to the user
     * @param message The error message to be displayed
     */
    public void showErrorMessage(String message) {
        System.out.println("    ____________________________________________________________\n"
                + "    " + message + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Displays a normal message to the user
     * @param message The message to be displayed
     */
    public void showNormalMessage(String message) {
        System.out.println("    ____________________________________________________________\n"
                + "    added: " + message + "\n"
                + "    ____________________________________________________________\n");
    }

    
    public void showFoundTasks(ArrayList<Task> tasks, String keyword) {
        System.out.println("    ____________________________________________________________\n"
                + "    Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                System.out.println("    " + count + ": " + task + "\n");
                count++;
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public void close() {
        scanner.close();
    }
}
