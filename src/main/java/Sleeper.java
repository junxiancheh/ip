import java.util.*;
import java.io.*;

public class Sleeper {
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm Sleeper\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println("    ____________________________________________________________\n"
                    + "    " + userInput + "\n"
                    + "    ____________________________________________________________");
            userInput = scanner.nextLine();
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

}
