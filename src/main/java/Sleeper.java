import java.util.*;
import java.io.*;

public class Sleeper {
    public static void main(String[] args) throws SleeperException {

        ArrayList<Task> items = new ArrayList<>();

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
            if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                String[] parts = userInput.trim().split("\\s+", 2); 
                String command = parts[0];
                String rest = (parts.length == 2) ? parts[1] : "";  
                Task t = null;
                if (command.equals("todo")) {
                    if (rest.isEmpty()) {
                        throw new SleeperException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    t = new ToDos(rest);
                } else if (command.equals("deadline")) {
                    if (rest.isEmpty()) {
                        throw new SleeperException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    t = new Deadlines(rest);
                } else if (command.equals("event")) {
                    if (rest.isEmpty()) {
                        throw new SleeperException("OOPS!!! The description of an event cannot be empty.");
                    }
                    t = new Events(rest);
                }
                items.add(t);
                System.out.println("    ____________________________________________________________\n"
                        + "    Got it. I've added this task: \n"
                        + "    " + t + "\n"
                        + "    Now you have " + items.size() + " tasks in the list.\n"
                        + "    ____________________________________________________________");
                continue;
            }

            if (userInput.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + items.get(i) + "\n");
                }
                System.out.println("    ____________________________________________________________");
                continue;
            }

            if (userInput.startsWith("mark ")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                items.get(index).markAsDone();

                System.out.println("    ____________________________________________________________\n"
                        + "    Nice! I've marked this task as done: \n"
                        + "     " + items.get(index) + "\n"
                        + "    ____________________________________________________________");
                        continue;
            }

            if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                items.get(index).markAsNotDone();

                System.out.println("    ____________________________________________________________\n"
                        + "    OK, I've marked this task as not done yet: \n"
                        + "     " + items.get(index) + "\n"
                        + "    ____________________________________________________________");
                        continue;
            }

            items.add(new Task(userInput)); 

            System.out.println("    ____________________________________________________________\n"
                    + "    added: " + userInput + "\n"
                    + "    ____________________________________________________________");
        } catch (SleeperException e) {
            System.out.println("    ____________________________________________________________\n"
                    + "    " + e.getMessage() + "\n"
                    + "    ____________________________________________________________");
        } 
    }

        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

}
