package sleeper.parser;
import sleeper.exception.SleeperException;
import sleeper.task.Deadlines;
import sleeper.task.Events;
import sleeper.task.Task;


public class Parser {
    
    public static String parseCommandType(String userInput) {
        if (userInput.startsWith("todo")) {
            return "todo";
        } else if (userInput.startsWith("deadline")) {
            return "deadline";
        } else if (userInput.startsWith("event")) {
            return "event";
        } else if (userInput.equals("list")) {
            return "list";
        } else if (userInput.startsWith("mark ")) {
            return "mark";
        } else if (userInput.startsWith("unmark ")) {
            return "unmark";
        } else if (userInput.startsWith("delete")) {
            return "delete";
        } else if (userInput.equals("bye")) {
            return "bye";
        } else if (userInput.startsWith("find ")) {
            return "find";
        } else if (userInput.trim().isEmpty()) {
            return "empty";
        } else if (userInput.startsWith("clear")) {
            return "clear";
        } else {
            return "default";
        }
    }

    /**
     * Methods to parse ToDo command
     * @param userInput
     * @return
     * @throws SleeperException
     */
    public static String parseTodo(String userInput) throws SleeperException {
        assert userInput.startsWith("todo") : "Input should start with 'todo'";
        String rest = userInput.substring(5).trim();
        return rest;
    }
    
    /**
     * Methods to parse Deadline command
     * @param userInput
     * @return
     * @throws SleeperException
     */
    public static Task parseDeadline(String userInput) throws SleeperException {
        assert userInput.startsWith("deadline") : "Input should start with 'deadline'";
        String rest = userInput.substring(9).trim();
        return new Deadlines(rest);
    }
    
    /**
     * Methods to parse Event command
     * @param userInput
     * @return
     * @throws SleeperException
     */
    public static Task parseEvent(String userInput) throws SleeperException {
        assert userInput.startsWith("event") : "Input should start with 'event'";
        String rest = userInput.substring(6).trim();
        return new Events(rest);
    }

    /**
     * Methods to parse Mark command
     * @param userInput
     * @return
     */
    public static int parseMarkIndex(String userInput) {
        assert userInput.startsWith("mark ") : "Input should start with 'mark '";
        return Integer.parseInt(userInput.substring(5)) - 1;
    }

    /**
     * Methods to parse Unmark command
     * @param userInput
     * @return
     */
    public static int parseUnmarkIndex(String userInput) {
        assert userInput.startsWith("unmark ") : "Input should start with 'unmark '";
        return Integer.parseInt(userInput.substring(7)) - 1;
    }
    
    /**
     * Methods to parse Delete command
     * @param userInput
     * @return
     */
    public static int parseDeleteIndex(String userInput) {
        assert userInput.startsWith("delete ") : "Input should start with 'delete '";
        return Integer.parseInt(userInput.substring(7)) - 1;
    }

    /**
     * Methods to parse Find command
     * @param userInput
     * @return
     */
    public static String parseFindKeyword(String userInput) {
        assert userInput.startsWith("find ") : "Input should start with 'find '";
        return userInput.substring(5).trim();
    }
}