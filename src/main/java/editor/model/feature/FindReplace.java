package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FindReplace implements MenuItemStrategy {
    private TextArea textArea;
    private Stage stage;
    public FindReplace(TextArea textarea) {
        this.stage = new Stage();
        stage.setTitle("Find & Replace");
        this.textArea = textarea;
    }
    @Override
    public void execute() {
        VBox vbox = addTextFields();
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,400,150);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
    private void findText(String find) {
        if(find == null) return;
        int s = textArea.getText().indexOf(find);
        textArea.selectRange(s,s+find.length());
    }
    private VBox addTextFields() {
        TextField find = new TextField();
        find.setPromptText("Find");
        TextField replace = new TextField();
        replace.setPromptText("Replace");

        Button findButton = new Button("Find");
        findButton.setOnAction(e -> {
            findText(find.getText());
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            stage.close();
        });
        Button replaceButton = new Button("Replace");

        //HBox hBox = new HBox(find,replace,cancel);
        HBox findHBox = new HBox(find,findButton);
        HBox replaceHBox = new HBox(replace,replaceButton);
        return new VBox(findHBox,replaceHBox,cancelButton);
    }
}
