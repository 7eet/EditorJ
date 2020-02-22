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
            "Courier New","Georgia", "Ubuntu"
    };
    private static final Double[] numbers = {18.0,20.0,22.0,24.0,26.0,28.0,29.0,30.0};
    private boolean isDarkmode;

    public FontProperty(TextArea textArea,boolean darmode) {
        this.textArea = textArea;
        this.isDarkmode = darmode;
    }

    @Override
    public void execute() {
        Stage fontStage = new Stage();
        fontStage.setTitle("Select Font");
        fontStage.setResizable(false);
        ChoiceBox<String> fonts = fontChoiceBox();
        ChoiceBox<Double> sizes = sizeChoiceBox();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            String fontType = fonts.getSelectionModel().getSelectedItem() ;
            Double size = sizes.getSelectionModel().getSelectedItem();
            textArea.setFont(Font.font(fontType != null ? fontType : "Ubuntu", size != null ? size : 22));
            logger.debug(textArea.getFont().toString());
            logger.debug("FontProperty class: Font color and size is " + fontType + " and " + size);
            fontStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            fontStage.close();
            logger.debug("FontProperty class: No changes");
        });
        Label nameLabel = new Label("Font Name");
        Label sizeLabel = new Label("Size");
        HBox label = new HBox(nameLabel, sizeLabel);
        label.setSpacing(20);
        label.setAlignment(Pos.CENTER);
        HBox vbox = new HBox(fonts,sizes);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);
        VBox vertical = new VBox(label,vbox,hbox);
        vertical.setSpacing(40);
        vertical.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vertical,500,400);
        if (isDarkmode) scene.getStylesheets().add("file:src/main/resources/darkView");
        else scene.getStylesheets().add("file:src/main/resources/simpleView");
        fontStage.setScene(scene);
        fontStage.initModality(Modality.APPLICATION_MODAL);
        fontStage.showAndWait();
    }

    private ChoiceBox<String> fontChoiceBox() {
        ChoiceBox<String> fonts = new ChoiceBox<>();
        fonts.getItems().addAll(string);
        fonts.setValue(textArea.getFont().getName());
        logger.debug("Font name : " + textArea.getFont().getName());
        logger.debug("Font style : " + textArea.getFont().getStyle());
        logger.debug("Font family: " + textArea.getFont().getFamily());
        return fonts;
    }

    private ChoiceBox<Double> sizeChoiceBox() {
        ChoiceBox<Double> size = new ChoiceBox<>();
        size.getItems().addAll(numbers);
        size.setValue(textArea.getFont().getSize());
        logger.debug("font size: " + textArea.getFont().getSize());
        return size;
    }
}
