package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.scene.control.TextArea;

public class BackImage implements MenuItemStrategy {
    private TextArea textArea;
    public BackImage(TextArea text) {
        this.textArea = text;
    }
    @Override
    public void execute() {

    }
}
