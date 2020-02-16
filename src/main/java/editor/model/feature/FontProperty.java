package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Arrays;

public class FontProperty implements MenuItemStrategy {
    private TextArea textArea;
    private static final Label showSelectedFont = new Label("The quick brown fox jumps over the lazy dog") ;
    private static final String[] string = {"Alegreya",
            "Times New Roman","Arial",
            "Verdana","Helvetica",
            "Courier New","Georgia"
    };
    private static final Integer[] numbers = {18,20,22,24,26,28,29,30};

    public FontProperty(TextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void execute() {
        Stage fontStage = new Stage();
        fontStage.setTitle("Select Font");
        ChoiceBox<String> fonts = fontCheckBox();
        ChoiceBox<Integer> sizes = sizeCheckBox();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            textArea.setFont(Font.font(fonts.getSelectionModel().getSelectedItem(),sizes.getSelectionModel().getSelectedItem()));
            fontStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> fontStage.close());

        VBox vbox = new VBox(fonts,sizes);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hbox);
        borderPane.setCenter(vbox);
        //borderPane.setBottom(showSelectedFont);
        Scene scene = new Scene(borderPane,400,350);
        fontStage.setScene(scene);
        fontStage.initModality(Modality.APPLICATION_MODAL);
        fontStage.showAndWait();
    }

    private ChoiceBox<String> fontCheckBox() {
        ChoiceBox<String> fonts = new ChoiceBox<>();
        fonts.getItems().addAll(string);
        return fonts;
    }

    private ChoiceBox<Integer> sizeCheckBox() {
        ChoiceBox<Integer> size = new ChoiceBox<>();
        size.getItems().addAll(numbers);
        return size;
    }
}
