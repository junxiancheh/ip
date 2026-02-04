package sleeper.parser;

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
        } else {
            return "default";
        }
    }

    // Methods to parse commands
    public static String parseTodo(String userInput) throws SleeperException {
        String rest = userInput.substring(5).trim();
        return rest;
    }
    
    // Methods to parse commands
    public static Task parseDeadline(String userInput) throws SleeperException {
        String rest = userInput.substring(9).trim();
        return new Deadlines(rest);
    }
    
    public static Task parseEvent(String userInput) throws SleeperException {
        String rest = userInput.substring(6).trim();
        return new Events(rest);
    }
    public static int parseMarkIndex(String userInput) {
        return Integer.parseInt(userInput.substring(5)) - 1;
    }
    
    public static int parseUnmarkIndex(String userInput) {
        return Integer.parseInt(userInput.substring(7)) - 1;
    }
    
    public static int parseDeleteIndex(String userInput) {
        return Integer.parseInt(userInput.substring(7)) - 1;
    }
}