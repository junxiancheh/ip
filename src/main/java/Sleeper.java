import java.util.*;
import java.io.*;

public class Sleeper {
    public static void main(String[] args) {

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
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

}
