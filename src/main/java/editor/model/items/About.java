package editor.model.items;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class About implements MenuItemStrategy {
    private String about = "EditorJ is a simple text editor.\n" +
            "It is written in Java language.\n" +
            "Developed by 7eet.\n" +
            "Tools i used to build this application: \n" +
            "\t1. Intellij\n" +
            "\t2. Gradle\n" +
            "\t3. Git\n" +
            "\t4. Log4j\n" +
            "\t5. JUnit\n" +
            "Want source code or to contribute: \n" +
            "\thttps://github.com/7eet/EditorJ\n" +
            "Thank you :)\n";
    @Override
    public void execute() {
        Stage aboutStage = new Stage();
        aboutStage.setTitle("About EditorJ");
        Label label = new Label(about);
        label.setLineSpacing(2.0);
        label.setFont(Font.font("San serif",22));
        Button button = new Button("Feedback");
        button.setFont(Font.font("San serif",22));
        VBox vbox = new VBox(label,button);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setSpacing(30.0);
        Scene scene = new Scene(vbox,600,500);
        aboutStage.setScene(scene);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
    }
}
