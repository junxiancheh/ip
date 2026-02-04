import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class for the Sleeper application.
 * It handles user input, manages tasks and handles the storage of tasks.
 * This is the entry point of the application.
 */
public class Sleeper {
    public static void main(String[] args) throws SleeperException, IOException {

        ArrayList<Task> items = new ArrayList<>();
        items = Storage.loadTasks();
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Sleeper\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equals("bye")) {
                break;
            }
            try {
                // Handle the "todo", "deadline", and "event" commands
                if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    String[] parts = userInput.trim().split("\\s+", 2);
                    String command = parts[0];
                    String rest = (parts.length == 2) ? parts[1] : "";
                    Task t = null;

                    // Creates a "todo" task
                    if (command.equals("todo")) {
                        if (rest.isEmpty()) {
                            throw new SleeperException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        t = new ToDos(rest);

                    // Creates a "deadline" task
                    } else if (command.equals("deadline")) {
                        if (rest.isEmpty()) {
                            throw new SleeperException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        t = new Deadlines(rest);

                    // Creates an "event" task
                    } else if (command.equals("event")) {
                        if (rest.isEmpty()) {
                            throw new SleeperException("OOPS!!! The description of an event cannot be empty.");
                        }
                        t = new Events(rest);
                    }

                    // Adds the created task to the list and saves it
                    items.add(t);
                    Storage.saveTasks(items);
                    System.out.println("    ____________________________________________________________\n"
                            + "    Got it. I've added this task: \n"
                            + "    " + t + "\n"
                            + "    Now you have " + items.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________");
                    continue;
                }

                // Handle the "list" command and display all tasks
                if (userInput.equals("list")) {
                    System.out.println("    ____________________________________________________________\n");
                    for (int i = 0; i < items.size(); i++) {
                        System.out.println("    " + (i + 1) + ": " + items.get(i) + "\n");
                    }
                    System.out.println("    ____________________________________________________________");
                    continue;
                }

                // Handle the "mark" command to mark a task as done
                if (userInput.startsWith("mark ")) {
                    int index = Integer.parseInt(userInput.substring(5)) - 1;
                    items.get(index).markAsDone();
                    Storage.saveTasks(items);

                    System.out.println("    ____________________________________________________________\n"
                            + "    Nice! I've marked this task as done: \n"
                            + "     " + items.get(index) + "\n"
                            + "    ____________________________________________________________");
                    continue;
                }

                // Handle the "unmark" command to mark a task as not done
                if (userInput.startsWith("unmark ")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    items.get(index).markAsNotDone();
                    Storage.saveTasks(items);

                    System.out.println("    ____________________________________________________________\n"
                            + "    OK, I've marked this task as not done yet: \n"
                            + "     " + items.get(index) + "\n"
                            + "    ____________________________________________________________");
                    continue;
                }

                // Handle the "delete" command to remove a task
                if (userInput.startsWith("delete")) {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    Task removedTask = items.remove(index);
                    Storage.saveTasks(items);
                    System.out.println("    ____________________________________________________________\n"
                            + "    Noted. I've removed this task: \n"
                            + "     " + removedTask + "\n"
                            + "    Now you have " + items.size() + " tasks in the list.\n"
                            + "    ____________________________________________________________");
                    continue;
                }

                items.add(new Task(userInput));
                Storage.saveTasks(items);

                System.out.println("    ____________________________________________________________\n"
                        + "    added: " + userInput + "\n"
                        + "    ____________________________________________________________");
            } catch (SleeperException e) {
                System.out.println("    ____________________________________________________________\n"
                        + "    " + e.getMessage() + "\n"
                        + "    ____________________________________________________________");
            }
        }

        // Print a goodbye message before exiting
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
        scanner.close();
    }

}
