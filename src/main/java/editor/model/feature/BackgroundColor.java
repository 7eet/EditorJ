package editor.model.feature;

import editor.model.MenuItemStrategy;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextArea;

public class BackgroundColor implements MenuItemStrategy {
    private MenuBar menuBar;
    private TextArea textArea;
    public BackgroundColor(MenuBar menuBar, TextArea textArea) {
        this.menuBar = menuBar;
        this.textArea = textArea;
    }

    @Override
    public void execute() {
    }
}
