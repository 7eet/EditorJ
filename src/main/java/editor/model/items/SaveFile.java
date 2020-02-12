package editor.model.items;

import editor.model.MenuItemStrategy;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class SaveFile implements MenuItemStrategy {
    private TextArea textArea;
    private Stage stage;
    private FileChooser fileChooser;
    public SaveFile(TextArea textArea, Stage stage) {
        this.textArea = textArea;
        this.stage = stage;
        logger.debug("Save Class instantiated");
    }
    @Override
    public void execute() {
        fileChooser = addExtensionsAndReturnFileChooser();
        File file = fileChooser.showSaveDialog(stage);
        saveIt(textArea.getText(),file);
    }
    // retrieve text from textare and append to file.
    private void saveIt(String text, File file) {
        String[] line = text.split("\n");
        System.out.println("the size of string array is " + line.length );
        if (file == null) {
            logger.debug("Null return because Closed the save stage");
            return;
        }
        try {
            logger.info("saving file...");
            PrintStream stream = new PrintStream(file);
            stream.write(text.getBytes());
        } catch(IOException io) {
            logger.error(io.getMessage());
        }
        logger.info("saved");
    }
}
