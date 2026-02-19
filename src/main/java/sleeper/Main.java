package sleeper;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Sleeper using FXML.
 */
public class Main extends Application {

    private Sleeper sleeper = new Sleeper();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Sleeper Chatbot");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setOnCloseRequest(event -> {
                Platform.exit();
                System.exit(0);
            });
            fxmlLoader.<MainWindow>getController().setSleeper(sleeper); // inject the Sleeper instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
