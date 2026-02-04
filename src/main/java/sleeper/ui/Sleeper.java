package sleeper.ui;
import sleeper.exception.SleeperException;
import sleeper.parser.Parser;
import sleeper.storage.Storage;
import sleeper.task.Task;
import sleeper.task.ToDos;
import java.io.IOException;
import java.util.ArrayList;

public class Sleeper {
    private Ui ui;
    private Storage storage;
    private ArrayList<Task> tasks;

    public Sleeper(String filePath) {
        ui = new Ui();
        storage = new Storage();
        try {
            tasks = storage.loadTasks();
        } catch (IOException e) {
            ui.showErrorMessage(filePath);
            tasks = new ArrayList<Task>();
        }
    }

    public void run() {
        ui.printWelcomeMessage();

        while (true) {
            String userInput = ui.readCommand();

            if (userInput.equals("bye")) {
                break;
            }
            try {
                handleCommand(userInput);
            } catch (IOException | SleeperException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
        ui.printGoodbyeMessage();
    }

    private void handleCommand(String input) throws SleeperException, IOException {
        String commandType = Parser.parseCommandType(input);
        switch (commandType) {
            case "todo":
                String todoDesc = Parser.parseTodo(input);
                Task todo = new ToDos(todoDesc);
                tasks.add(todo);
                storage.saveTasks(tasks);
                ui.showAddTaskMessage(todo, tasks);
                break;

            case "deadline":
                Task deadline = Parser.parseDeadline(input);
                tasks.add(deadline);
                storage.saveTasks(tasks);
                ui.showAddTaskMessage(deadline, tasks);
                break;

            case "event":
                Task event = Parser.parseEvent(input);
                tasks.add(event);
                storage.saveTasks(tasks);
                ui.showAddTaskMessage(event, tasks);
                break;
            
            case "list":
                ui.showTaskList(tasks);
                break;
            
            case "mark":
                int markIndex = Parser.parseMarkIndex(input);
                tasks.get(markIndex).markAsDone();
                storage.saveTasks(tasks);
                ui.showMarkTaskMessage(tasks.get(markIndex));
                break;

            case "unmark":
                int unmarkIndex = Parser.parseUnmarkIndex(input);
                tasks.get(unmarkIndex).markAsNotDone();
                storage.saveTasks(tasks);
                ui.showUnmarkTaskMessage(tasks.get(unmarkIndex));
                break;
        
            case "delete":
                int deleteIndex = Parser.parseDeleteIndex(input);
                Task removedTask = tasks.remove(deleteIndex);
                storage.saveTasks(tasks);
                ui.showDeleteTaskMessage(removedTask, tasks);
                break;

            default:
                tasks.add(new Task(input));
                storage.saveTasks(tasks);
                ui.showNormalMessage(input);
                break;
        }
    }

    public static void main(String[] args) throws SleeperException, IOException {
        new Sleeper("./bin/sleeper.txt").run();
    }
}