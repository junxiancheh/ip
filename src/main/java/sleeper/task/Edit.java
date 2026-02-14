package sleeper.task;

import java.io.IOException;
import java.util.ArrayList;

import sleeper.exception.SleeperException;
import sleeper.parser.Parser;

public class Edit {
    private String input;

    public Edit(String input) {
        this.input = input;
    }

    public Task execute(ArrayList<Task> tasks) throws SleeperException {
        String[] parts = input.split(" ", 3);

        if (parts.length < 3) {
            throw new SleeperException(
                "The format of the command seems to be wrong. Try edit 1 todo read book");
        }

        // Extract the task index
        int editIndex = Integer.parseInt(parts[1]) - 1;

        if (editIndex < 0 || editIndex >= tasks.size()) {
            throw new SleeperException("Can't find task. Please provide a valid index");
        }

        // Extract the description after task index. Can be a command or non command.
        String inputDescription = parts[2];
        String newType = Parser.parseCommandType(inputDescription);

        Task newTask;
        // If user typed a command, replace the task.
        if (newType.equals("todo") || newType.equals("deadline") || newType.equals("event")) {
            newTask = createNewTaskFromType(newType, inputDescription);
        } else {
            // If no command is typed, just put in user's input in that index
            newTask = new Task(inputDescription);
        }
        tasks.set(editIndex, newTask);
        return newTask;
    }

    public Task createNewTaskFromType(String type, String description) throws SleeperException {
        switch (type) {
            case "todo":
                return new ToDos(Parser.parseTodo(description));
            case "deadline":
                return Parser.parseDeadline(description);
            case "event":
                return Parser.parseEvent(description);
            default:
                throw new SleeperException("Invalid task type.");
        }
    }
}
