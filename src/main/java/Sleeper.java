import java.util.*;
import java.io.*;

public class Sleeper {
    public static void main(String[] args) {
        ArrayList<String> items = new ArrayList<>();
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Sleeper\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                for (int i = 0; i < items.size(); i++) {
                    System.out.println("    " + (i + 1) + ": " + items.get(i) + "\n");
                }
                System.out.println("    ____________________________________________________________");
            }

            items.add(userInput);

            System.out.println("    ____________________________________________________________\n"
                    + "    added: " + userInput + "\n"
                    + "    ____________________________________________________________");
            userInput = scanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

}
