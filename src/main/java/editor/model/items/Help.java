package editor.model.items;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Help implements MenuItemStrategy {
    private String help = "This editor is same as notepad.";
    private boolean darkMode;
    public Help(boolean darkmode) {
        this.darkMode = darkmode;
    }
    @Override
    public void execute() {
        logger.debug("Help Class: displaying");
        Stage aboutStage = new Stage();
        aboutStage.setTitle("Help");
        Label label = new Label(help);
        label.setLineSpacing(2.0);
        //label.setFont(Font.font("San serif",22));
        VBox vbox = new VBox(label);
        vbox.setAlignment(Pos.CENTER_LEFT);
        Scene scene = new Scene(vbox,600,500);
        if (darkMode) scene.getStylesheets().add("file:src/main/resources/darkView");
        else scene.getStylesheets().add("file:src/main/resources/simpleView");
        aboutStage.setScene(scene);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
        logger.debug("Help Class: closed");
    }
}
