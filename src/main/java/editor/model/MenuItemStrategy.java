package editor.model;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface MenuItemStrategy {
    // logger for all implementing classes
    Logger logger = LogManager.getLogger(MenuItemStrategy.class);

    public void execute();

    // this method return filechooser with added some extension filters
    default FileChooser addExtensionsAndReturnFileChooser() {
        FileChooser fileChooser =  new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text files","*.txt"),
                new FileChooser.ExtensionFilter("CSS Files","*.css"),
                new FileChooser.ExtensionFilter("Html Files","*.htm"),
                new FileChooser.ExtensionFilter("Java Files","*.java"));
        return fileChooser;
    }
}
