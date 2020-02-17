package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BackgroundColor implements MenuItemStrategy {
    private MenuBar menuBar;
    private TextArea textArea;
    private String[] colors = {"darkgreen black","black yellow","darkgray black"};
    public BackgroundColor(MenuBar menuBar, TextArea textArea) {
        this.menuBar = menuBar;
        this.textArea = textArea;
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
            colorStage.close();
        });
        Button cancel = new Button("Cancel");
        cancel.setOnAction(e -> colorStage.close());
        HBox hbox = new HBox(apply,cancel);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(20);

        VBox vertical = new VBox(background,hbox);
        vertical.setSpacing(30);
        vertical.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vertical,400,350);
        colorStage.setScene(scene);
        colorStage.initModality(Modality.APPLICATION_MODAL);
        colorStage.showAndWait();
    }

    private ChoiceBox<String> backgroundCheckBox() {
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(colors);
        return choiceBox;
    }


}
