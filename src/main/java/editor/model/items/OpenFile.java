package editor.model.items;

import editor.model.MenuItemStrategy;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Stream;

public class OpenFile implements MenuItemStrategy {
    private TextArea textArea;
    private Stage stage;
    private FileChooser fileChooser;
    public OpenFile(TextArea textArea, Stage stage) {
        this.textArea = textArea;
        this.stage = stage;
    }
    @Override
    public void execute() {
        fileChooser = addExtensionsAndReturnFileChooser();
        File file = fileChooser.showOpenDialog(stage);
        setText(file);
    }
    // retrieve text from file and add to textarea
    private void setText(File file) {
        try {
            logger.debug("OpenFile class: adding text to textarea.");
            Stream<String> lines = Files.lines(file.toPath());
            lines.forEach(e -> {
                textArea.appendText(e + "\n");
            });
            logger.debug("OpenFile class: added.");
        } catch(IOException io) {
            logger.error("OpenFile Class: " + io.getMessage());
        }
    }
}
