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
    private static final Pos pos = Pos.CENTER;
    private boolean isDarkmode;
    public FindReplace(TextArea textarea, boolean darkmode) {
        this.stage = new Stage();
        stage.setTitle("Find & Replace");
        this.textArea = textarea;
        this.isDarkmode = darkmode;
    }
    @Override
    public void execute() {
        VBox vbox = addTextFields();
        vbox.setSpacing(25);
        vbox.setAlignment(pos);
        Scene scene = new Scene(vbox,550,250);
        if (isDarkmode) scene.getStylesheets().add("file:src/main/resources/darkView");
        else scene.getStylesheets().add("file:src/main/resources/simpleView");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
    private int findText(String find) {
        if(find == null) return 0;
        int s = textArea.getText().indexOf(find);
        return s;
    }
    private VBox addTextFields() {
        TextField find = new TextField();
        find.setPromptText("Find");
        TextField replace = new TextField();
        replace.setPromptText("Replace");

        Button findButton = new Button("Find");
        findButton.setOnAction(e -> {
            int index = findText(find.getText());
            if (index < 0) return;
            textArea.selectRange(index,index+find.getLength());
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            stage.close();
        });
        Button replaceButton = new Button("Replace");
        replaceButton.setOnAction(e -> {
            int index = findText(find.getText());
            if(index < 0 || replace.getLength() == 0) return;
            textArea.replaceText(index,index+find.getLength(),replace.getText());
        });

        //HBox hBox = new HBox(find,replace,cancel);
        HBox findHBox = new HBox(find,findButton);
        findHBox.setAlignment(pos);
        findHBox.setSpacing(25);
        HBox replaceHBox = new HBox(replace,replaceButton);
        replaceHBox.setAlignment(pos);
        replaceHBox.setSpacing(25);
        return new VBox(findHBox,replaceHBox,cancelButton);
    }
}
