package sleeper.parser;
import sleeper.exception.SleeperException;
import sleeper.task.Deadlines;
import sleeper.task.Events;
import sleeper.task.Task;


/**
 * Parses user input commands.
 * 
 * It identifies the type of command by the user's input 
 * and extracts the relevant details for each command type.
 */
public class Parser {
    
    /**
     * Parse each command type and return the command type as a string
     * 
     * This is used to identify which command the user has inputted and 
     * classify it accordingly.
     * 
     * @param userInput
     * @return String representing the command type
     */
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
     * Method to parse ToDo command
     * 
     * This method will extract the description of the ToDo task
     * from the user input string.
     * 
     * @param userInput
     * @return String description
     * @throws SleeperException
     */
    public static String parseTodo(String userInput) throws SleeperException {
        assert userInput.startsWith("todo") : "Input should start with 'todo'";
        String rest = userInput.substring(5).trim();
        return rest;
    }
    
    /**
     * Method to parse Deadline command
     * 
     * This method will extract the description and deadline of the Deadline task
     * from the user input string.
     * 
     * @param userInput
     * @return Task representing the deadline
     * @throws SleeperException
     */
    public static Task parseDeadline(String userInput) throws SleeperException {
        assert userInput.startsWith("deadline") : "Input should start with 'deadline'";
        String rest = userInput.substring(9).trim();
        return new Deadlines(rest);
    }
    
    /**
     * Method to parse Event command
     * 
     * This method will extract the description and event time of the Event task
     * from the user input string.
     * 
     * @param userInput
     * @return Task event
     * @throws SleeperException
     */
    public static Task parseEvent(String userInput) throws SleeperException {
        assert userInput.startsWith("event") : "Input should start with 'event'";
        String rest = userInput.substring(6).trim();
        return new Events(rest);
    }

    /**
     * Method to parse Mark command
     * 
     * This method will return the index of the task to be marked as done.
     * 
     * @param userInput
     * @return int index
     */
    public static int parseMarkIndex(String userInput) {
        assert userInput.startsWith("mark ") : "Input should start with 'mark '";
        return Integer.parseInt(userInput.substring(5)) - 1;
    }

    /**
     * Method to parse Unmark command
     * 
     * This method will return the index of the task to be marked as not done.
     * 
     * @param userInput
     * @return int index
     */
    public static int parseUnmarkIndex(String userInput) {
        assert userInput.startsWith("unmark ") : "Input should start with 'unmark '";
        return Integer.parseInt(userInput.substring(7)) - 1;
    }
    
    /**
     * Method to parse Delete command
     * 
     * This method will return the index of the task to be deleted.
     * 
     * @param userInput
     * @return int index
     */
    public static int parseDeleteIndex(String userInput) {
        assert userInput.startsWith("delete ") : "Input should start with 'delete '";
        return Integer.parseInt(userInput.substring(7)) - 1;
    }

    /**
     * Method to parse Find command
     * 
     * This method will return the keyword to search for in the task list.
     * 
     * @param userInput
     * @return String keyword
     */
    public static String parseFindKeyword(String userInput) {
        assert userInput.startsWith("find ") : "Input should start with 'find '";
        return userInput.substring(5).trim();
    }
}