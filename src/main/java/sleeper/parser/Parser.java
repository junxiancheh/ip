package sleeper.parser;

import java.io.IOException;
import java.util.ArrayList;

import sleeper.exception.SleeperException;
import sleeper.task.Deadlines;
import sleeper.task.Event;
import sleeper.task.Task;
import sleeper.task.ToDos;

/**
 * Parse user input commands.
 * 
 * It identifies the type of command by the user's input
 * and extracts the relevant details for each command type.
 * 
 * @note Javadoc phrasing and documentation structure in this class
 *       were refined with the assistance of AI (Gemini).
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
        } else if (userInput.startsWith("edit")) {
            return "edit";
        } else {
            return "default";
        }
    }

    /**
     * Parse the ToDo command from the user input.
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
     * Parse the Deadline command from user input.
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
     * Parse the Event command from user input.
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
        if (!userInput.contains("/from") || !userInput.contains("/to")) {
            throw new SleeperException("You're missing either the '/from' or '/to' !");
        }
        String rest = userInput.substring(6).trim();
        return new Event(rest);
    }

    /**
     * Parse the Mark command from user input.
     * 
     * This method will return the index of the task to be marked as done.
     * 
     * @param userInput
     * @return int index
     * @exception SleeperException
     */
    public static int parseMarkIndex(String userInput, int listSize) throws SleeperException {
        assert userInput.startsWith("mark ") : "Input should start with 'mark '";
        try {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            if (index < 0 || index >= listSize) {
                throw new SleeperException("I can't find that task. You only have " + listSize + " tasks!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new SleeperException("That's not a valid number.");
        }
    }

    /**
     * Parse the Unmark command from user input.
     * 
     * This method will return the index of the task to be marked as not done.
     * 
     * @param userInput
     * @return int index
     * @exception SleeperException
     */
    public static int parseUnmarkIndex(String userInput, int listSize) throws SleeperException {
        assert userInput.startsWith("unmark ") : "Input should start with 'unmark '";
        try {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            if (index < 0 || index >= listSize) {
                throw new SleeperException("I can't find that task. You only have " + listSize + " tasks!");
            }

            return index;
        } catch (NumberFormatException e) {
            throw new SleeperException("That's not a valid number.");
        }
    }

    /**
     * Parse the Event command from user input.
     * 
     * This method will return the index of the task to be deleted.
     * 
     * @param userInput
     * @return int index
     * @exception SleeperException
     */
    public static int parseDeleteIndex(String userInput) throws SleeperException {
        assert userInput.startsWith("delete ") : "Input should start with 'delete '";
        try {
            return Integer.parseInt(userInput.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new SleeperException("That's not a valid number.");
        }
    }

    /**
     * Parse the Find command from user input.
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