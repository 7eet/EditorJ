package editor.main;

import editor.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private MenuBar menuBar;
    private TextArea textArea;
    public static void main(String... args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller(primaryStage);
        primaryStage.setTitle("EditorJ");
        menuBar = controller.intiMenuBar();
        // menuBar.setStyle("-fx-background-color: #ebebeb;");

        textArea = controller.addTextArea();
        textArea.setStyle("text-area-background: blue;");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane,600,700);

        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> controller.alertBox());
        primaryStage.show();
    }
}
