package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Spacing implements MenuItemStrategy {
    private TextArea textArea;
    private boolean isDarkmode;
    private static final Integer[] letterSpacing = {5,10,15,20,25,30,35,40};
    private static final Integer[] lineSpacing = {5,10,15,20,25,30,35,40};
    public Spacing(TextArea text, boolean darkmode) {
        this.textArea = text;
        this.isDarkmode = darkmode;
    }
    @Override
    public void execute() {
        Stage fontStage = new Stage();
        fontStage.setTitle("Select Spacing");
        fontStage.setResizable(false);
        ChoiceBox<Integer> letter = letterSpacingChoiceBox();
        ChoiceBox<Integer> line = lineSpacingChoiceBox();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            Integer fontType = letter.getSelectionModel().getSelectedItem() ;
            Integer size = line.getSelectionModel().getSelectedItem();
           // textArea.setFont(Font.font(fontType != null ? fontType : "Ubuntu", size != null ? size : 22));
            logger.debug(textArea.getFont().toString());
            logger.debug("FontProperty class: Font color and size is " + fontType + " and " + size);
            fontStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            fontStage.close();
            logger.debug("FontProperty class: No changes");
        });
        Label letterLabel = new Label("Letter");
        Label lineLable = new Label("Line");
        HBox lable = new HBox(letterLabel, lineLable);
        lable.setSpacing(20);
        lable.setAlignment(Pos.CENTER);
        HBox vbox = new HBox(letter,line);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        VBox vertical = new VBox(lable,vbox,hbox);
        vertical.setSpacing(40);
        vertical.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vertical,500,400);
        if (isDarkmode) scene.getStylesheets().add("file:src/main/resources/darkView");
        else scene.getStylesheets().add("file:src/main/resources/simpleView");
        fontStage.setScene(scene);
        fontStage.initModality(Modality.APPLICATION_MODAL);
        fontStage.showAndWait();
    }

    private ChoiceBox<Integer> letterSpacingChoiceBox() {
        ChoiceBox<Integer> fonts = new ChoiceBox<>();
        fonts.getItems().addAll(letterSpacing);
        fonts.setValue(letterSpacing[3]);
        return fonts;
    }
    private ChoiceBox<Integer> lineSpacingChoiceBox() {
        ChoiceBox<Integer> fonts = new ChoiceBox<>();
        fonts.getItems().addAll(lineSpacing);
        fonts.setValue(lineSpacing[3]);
        return fonts;
    }
}
