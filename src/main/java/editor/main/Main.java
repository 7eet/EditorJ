package editor.main;

import editor.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main extends Application {
    private MenuBar menuBar;
    private TextArea textArea;
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String... args) {
        logger.debug("Main: started");
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Controller controller = new Controller(primaryStage);
        primaryStage.setTitle("Editor");

        menuBar = controller.intiMenuBar();
        logger.debug("menubar initialized");
        textArea = controller.addTextArea();
        logger.debug("textbar initialized");
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(textArea);
        Scene scene = new Scene(borderPane,600,700);
        primaryStage.getIcons().add(new Image("file:src/main/resources/logo.png"));
        scene.getStylesheets().add("file:src/main/resources/Main.css");
        primaryStage.setScene(scene);

        primaryStage.setOnCloseRequest(e -> controller.alertBox());
        primaryStage.show();
        logger.debug("Main: closed");
    }
}
