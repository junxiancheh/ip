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
     * 
     * @return The welcome message as a String
     */
    public String printWelcomeMessage() {
        return "Ready to run the floor! What's the play for today?\n" 
                + "Here's a list of commands you can use:\n"
                + "1. todo <description> - Adds a todo task\n"
                + "2. event <description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm> - Adds an event task\n"
                + "3. deadline <description> /by <d/M/yyyy HHmm> - Adds a deadline task\n"
                + "4. mark <task number> - Marks a task as done\n"
                + "5. unmark <task number> - Marks a task as not done\n"
                + "6. delete <task number> - Deletes a task\n"
                + "7. find <keyword> - Finds tasks containing the keyword\n"
                + "8. list - Lists all tasks\n"
                + "9. edit <task number> <description>- Edit an existing task\n"
                + "10. bye - Exits the application\n";
    }

    /**
     * Prints the goodbye message when the application ends
     * 
     * @return The goodbye message as a String
     */
    public String printGoodbyeMessage() {
        return "Bye. Hope to see you again soon! 😴";
    }
;
    /**
     * Reads a command from the user 
     * 
     * @return The command input by the user as a String
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Displays a message when a task is added
     * 
     * @param t The task that was added
     * @param items The current list of tasks
     * @return Add task message as a String
     */
    public String showAddTaskMessage(Task t, ArrayList<Task> items) {
        return "Splash. Added that to the list. \n"
                + t + "\n"
                + "Now you have " + items.size() + " tasks in the list.\n"
                + "Night night. 😴";
    }
                   

    /**
     * Displays the list of tasks to the user
     * 
     * @param items The current list of tasks
     * @return The task list as a String
     */
    public String showTaskList(ArrayList<Task> items) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            sb.append("    " + (i + 1) + ": " + items.get(i) + "\n");
        }
        sb.append("Stay locked in.");
        return sb.toString();
    }

    /**
     * Displays a message when a task is marked as done
     * 
     * @param t The task that was marked as done
     * @return Mark task message as a String
     */
    public String showMarkTaskMessage(Task t) {
        return "Splash! 🏀 I've marked this task as done: \n"
                + "  " + t + "\n"
                + "Night night!";
    }

    /**
     * Displays a message when a task is unmarked
     * 
     * @param t The task that was unmarked
     * @return Unmark task message as a String
     */
    public String showUnmarkTaskMessage(Task t) {
        return "Timeout! I've marked this task as not done yet: \n"
                + t;
    }

    /**
     * Displays a message when a task is deleted
     * 
     * @param t The task that was deleted
     * @param items The current list of tasks
     * @return Delete task message as a String
     */
    public String showDeleteTaskMessage(Task t, ArrayList<Task> items) {
        return "Ball game. 🏀 I've retired this task: \n"
                + t + "\n"
                + "Now you have " + items.size() + " tasks in the list.";
    }

    /**
     * Displays an error message to the user
     * 
     * @param message The error message to be displayed
     * @return Error message as a String
     */
    public String showErrorMessage(String message) {
        return "Airball! 💨" + message;
    }

    /**
     * Displays a normal message to the user
     * 
     * @param message The message to be displayed
     * @return Normal message as a String
     */
    public String showNormalMessage(String message) {
        return "Locking in your message! I added: " + message + "\n";
    }


    /**
     * Displays the tasks that match the search keyword
     * 
     * @param tasks
     * @param keyword
     * @return Found tasks message as a String
     */
    public String showFoundTasks(ArrayList<Task> tasks, String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        int count = 1;
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                sb.append("    " + count + ": " + task + "\n");
                count++;
            }
        }
        return sb.toString();
    }

    public String showClearListMessage() {
        return "All tasks have been cleared from your list. 🧹 \n"
                + "Time to rest!";
    }

    public String showEditTaskMessage(Task newTask, ArrayList<Task> items) {
        return "Great adjustments! I've edited and added this task: \n"
                + newTask + "\n"
                + "Now you have " + items.size() + " tasks in the list.\n";
    }

    public void close() {
        scanner.close();
    }
}
