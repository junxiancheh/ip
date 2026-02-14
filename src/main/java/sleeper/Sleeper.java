package sleeper;

import sleeper.exception.SleeperException;
import sleeper.parser.Parser;
import sleeper.storage.Storage;
import sleeper.task.Task;
import sleeper.task.ToDos;
import sleeper.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Sleeper {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks;

    public Sleeper() {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            ui.showErrorMessage("Unable to load saved tasks. Starting a new task list instead.");
            tasks = new ArrayList<>();
        }
    }

    /**
     * Gets the welcome message when the application starts.
     * 
     * @return The welcome message as a String.
     */
    public String printWelcomeMessage() {
        return ui.printWelcomeMessage();
    }

    /**
     * Handles non-command responses. This does not save the response into the list.
     * 
     * @param input The user's input string.
     * @return The chatbot's response string.
     */
    public String getResponse(String input) {
        try {
            String commandType = Parser.parseCommandType(input);
            if (commandType.equals("empty")) {
                throw new SleeperException("The description of a command cannot be empty.");
            }
            if (commandType.equals("bye")) {
                return ui.printGoodbyeMessage();
            }
            return handleCommand(input, commandType);
        } catch (SleeperException e) {
            return ui.showErrorMessage(e.getMessage());
        } catch (IOException e) {
            return "Error saving tasks to storage: " + e.getMessage();
        }
    }

    /**
     * Handles command responses. This saves responses into the list.
     * 
     * @param input
     * @param commandType
     * @return String
     * @throws SleeperException
     * @throws IOException
     */
    private String handleCommand(String input, String commandType) throws SleeperException, IOException {
        switch (commandType) {
            case "todo":
                return executeAddTodo(input);
            case "deadline":
                return executeAddDeadline(input);
            case "event":
                return executeAddEvent(input);
            case "list":
                return executeList();
            case "mark":
                return executeMark(input);
            case "unmark":
                return executeUnmark(input);
            case "delete":
                return executeDelete(input);
            case "find":
                return executeFind(input);
            case "clear":
                return executeClear(input);
            case "edit":
                return executeEdit(input);
    }

    public String executeAddTodo(String input) throws SleeperException, IOException {
        String todoDesc = Parser.parseTodo(input);
        Task todo = new ToDos(todoDesc);
        tasks.add(todo);
        storage.saveTasks(tasks);
        return ui.showAddTaskMessage(todo, tasks);
    }

    public String executeAddDeadline(String input) throws SleeperException, IOException {
        Task deadline = Parser.parseDeadline(input);
        tasks.add(deadline);
        storage.saveTasks(tasks);
        return ui.showAddTaskMessage(deadline, tasks);
    }

    public String executeAddEvent(String input) throws SleeperException, IOException {
        Task event = Parser.parseEvent(input);
        tasks.add(event);
        storage.saveTasks(tasks);
        return ui.showAddTaskMessage(event, tasks);
    }

    public String executeList() {
        return ui.showTaskList(tasks);
    }

    public String executeMark(String input) throws SleeperException, IOException {
        int markIndex = Parser.parseMarkIndex(input);
        tasks.get(markIndex).markAsDone();
        storage.saveTasks(tasks);
        return ui.showMarkTaskMessage(tasks.get(markIndex));
    }

    public String executeUnmark(String input) throws SleeperException, IOException {
        int unmarkIndex = Parser.parseUnmarkIndex(input);
        tasks.get(unmarkIndex).markAsNotDone();
        storage.saveTasks(tasks);
        return ui.showUnmarkTaskMessage(tasks.get(unmarkIndex));
    }

    public String executeDelete(String input) throws SleeperException, IOException {
        int deleteIndex = Parser.parseDeleteIndex(input);
        Task removedTask = tasks.remove(deleteIndex);
        storage.saveTasks(tasks);
        return ui.showDeleteTaskMessage(removedTask, tasks);
    }

    public String executeFind(String input) throws SleeperException, IOException {
        String keyword = input.substring(5).trim();
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return ui.showFoundTasks(foundTasks, keyword);
    }

    public String executeClear(String input) throws SleeperException, IOException {
        tasks.clear();
        storage.saveTasks(tasks);
        return ui.showClearListMessage();
    }

    public String executeDefault(String input) throws SleeperException, IOException {
        Task defaultTask = new Task(input);
        tasks.add(defaultTask);
        storage.saveTasks(tasks);
        return ui.showNormalMessage(input);
    }
    
    public String executeEdit(String input) throws SleeperException, IOException {
        String[] parts = input.split(" ", 3);

            if (parts.length < 3) {
                throw new SleeperException(
                    "The format of the command seems to be wrong. Try edit 1 todo read book");
                }

                // Extract the task index
            int editIndex = Integer.parseInt(parts[1]) - 1;

            if (editIndex < 0 || editIndex >= tasks.size()) {
                throw new SleeperException("Can't find task. Please provide a valid");
                }

                // Extract the description after task index. Can be a command or non command.
            String inputDescription = parts[2];
            String newType = Parser.parseCommandType(inputDescription);

            Task taskToEdit = tasks.get(editIndex);

            // If user typed a command, replace the task.
            if (newType.equals("todo") || newType.equals("deadline") || newType.equals("event")) {
                    
                Task newTask;
                    
                switch (newType) {
                    case "todo":
                        newTask = new ToDos(Parser.parseTodo(inputDescription));
                        break;
                    case "deadline":
                        newTask = Parser.parseDeadline(inputDescription);
                        break;
                    case "event":
                        newTask = Parser.parseEvent(inputDescription);
                        break;
                    default:
                        throw new SleeperException("Invalid task type.");
                 }
                 tasks.set(editIndex, newTask);
                 storage.saveTasks(tasks);

                 } else {
                    // If no command is typed, just put in user's input in that index
                    taskToEdit.setDescription(inputDescription);
			}

            storage.saveTasks(tasks);
            return ui.showEditTaskMessage(taskToEdit, tasks);

            default:
                return executeDefault(input);
        }


}