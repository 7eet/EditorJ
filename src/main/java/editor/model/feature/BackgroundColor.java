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

public class BackgroundColor implements MenuItemStrategy {
    private MenuBar menuBar;
    private boolean isDarkmode;
    private TextArea textArea;
    private String[] colors = {"Darkgreen Black","Black Yellow","DarkGray Black","White Black"};
    public BackgroundColor(MenuBar menuBar, TextArea textArea, boolean darkmode) {
        this.menuBar = menuBar;
        this.textArea = textArea;
        this.isDarkmode = darkmode;
    }

    @Override
    public void execute() {
        Stage colorStage = new Stage();
        colorStage.setTitle("Select Background Color");
        colorStage.setResizable(false);
        ChoiceBox<String> background = backgroundCheckBox();
        Button apply = new Button("Apply");
        apply.setOnAction(e -> {
            String[] backgroundAndTextColor = background.getSelectionModel().getSelectedItem().split(" ");
            textArea.setStyle("-fx-control-inner-background: " + backgroundAndTextColor[0] + "; -fx-text-fill: " + backgroundAndTextColor[1] + ";");
            logger.debug("BackgroundColor class: background and text color is " + backgroundAndTextColor[0] +" and " + backgroundAndTextColor[1]);
            colorStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> {
            logger.debug("BackgroundColor class: No changes");
            colorStage.close();
        });

        Label backgroundLabel = new Label("Background");
        Label fontLabel = new Label("Font");
        HBox label = new HBox(backgroundLabel, fontLabel);
        label.setSpacing(20);
        label.setAlignment(Pos.CENTER);

        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vertical = new VBox(label,background,hbox);
        vertical.setSpacing(40);
        vertical.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vertical,400,350);
        if (isDarkmode) scene.getStylesheets().add("file:src/main/resources/darkView");
        else scene.getStylesheets().add("file:src/main/resources/simpleView");
        colorStage.setScene(scene);
        colorStage.initModality(Modality.APPLICATION_MODAL);
        colorStage.showAndWait();
    }

    private ChoiceBox<String> backgroundCheckBox() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(colors);
        choiceBox.setValue(colors[2]);
        return choiceBox;
    }


}
