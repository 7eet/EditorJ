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
        fontStage.setResizable(false);
        ChoiceBox<String> fonts = fontChoiceBox();
        ChoiceBox<Integer> sizes = sizeChoiceBox();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            String fontType = fonts.getSelectionModel().getSelectedItem() ;
            Integer size = sizes.getSelectionModel().getSelectedItem();
            textArea.setFont(Font.font(fontType != null ? fontType : "Ubuntu Mono", size != null ? size : 22));
            fontStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> fontStage.close());

        HBox vbox = new HBox(fonts,sizes);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        //BorderPane borderPane = new BorderPane();
       // borderPane.setCenter(vbox);
       // borderPane.setCenter(hbox);
        //borderPane.setBottom(showSelectedFont);
        VBox vertical = new VBox(vbox,hbox);
        vertical.setSpacing(30);
        vertical.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vertical,400,350);
        fontStage.setScene(scene);
        fontStage.initModality(Modality.APPLICATION_MODAL);
        fontStage.showAndWait();
    }

    private ChoiceBox<String> fontChoiceBox() {
        ChoiceBox<String> fonts = new ChoiceBox<>();
        fonts.getItems().addAll(string);
        return fonts;
    }

    private ChoiceBox<Integer> sizeChoiceBox() {
        ChoiceBox<Integer> size = new ChoiceBox<>();
        size.getItems().addAll(numbers);
        return size;
    }
}
