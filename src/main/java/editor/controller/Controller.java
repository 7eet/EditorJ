package editor.controller;
import editor.model.MenuItemStrategy;
import editor.model.items.About;
import editor.model.items.OpenFile;
import editor.model.items.SaveFile;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;
    private MenuBar menuBar;
    private TextArea textArea;
    private SeparatorMenuItem separator;
    private MenuItemStrategy itemStrategy;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public MenuBar intiMenuBar() {
        menuBar = new MenuBar();
        separator = new SeparatorMenuItem();
        initFileMenu();
        initSetting();
        initHelp();
        initAbout();
        return menuBar;
    }
    public TextArea addTextArea() {
        textArea = new TextArea();
        textArea.setFont(Font.font("Helvetica Neue",22));
        return textArea;
    }

    public void alertBox() {
        Stage alert = new Stage();
        alert.setTitle("Alert");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setResizable(false);
        Label label = new Label("Do you want to save the file?");
        Button yes = new Button("Yes");

        Button no = new Button("No");
        no.setOnAction(e -> {
            alert.close();
            stage.close();
        });

        HBox hbox = new HBox(yes,no);
        hbox.setSpacing(20.0);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(label,hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(20.0);

        Scene scene = new Scene(vbox,250,100);
        alert.setScene(scene);
        alert.showAndWait();
    }

    // adding menu and its item to File menu
    private void initFileMenu() {
        Menu file = new Menu("File");
        MenuItem newFile = new MenuItem("New File");
        MenuItem openFile = new MenuItem("Open File");
        openFile.setOnAction(e -> {
            itemStrategy = new OpenFile(textArea,stage);
            itemStrategy.execute();
        });
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setOnAction( e -> {
            itemStrategy = new SaveFile(textArea,stage);
            itemStrategy.execute();
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> alertBox());
        file.getItems().addAll(newFile, openFile, saveFile, separator, exit);
        menuBar.getMenus().add(file);
    }

    // adding menu Setting
    private void initSetting() {
        Menu setting = new Menu("Setting");
        RadioMenuItem darkMode = new RadioMenuItem("Dark Mode");
//        darkMode.selectedProperty().addListener(new ChangeListener<Boolean>() {
//            @Override
//            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
//                dark = new DarkMode(textArea,menuBar);
//                if (isNowSelected) {
//                    dark.darkModeOn();
//                } else {
//                    dark.darkModeOff();
//                }
//            }
//        });

        MenuItem fullscreen = new MenuItem("Full Screen");
        fullscreen.setOnAction(e -> stage.setFullScreen(true));
        MenuItem fullSetting = new MenuItem("Full setting");
        setting.getItems().addAll(darkMode, fullscreen, separator, fullSetting);
        menuBar.getMenus().add(setting);
    }

    // adding Help menu
    private void initHelp() {
        Menu help = new Menu("Help");
        MenuItem subHelp = new MenuItem("Help");
        help.getItems().addAll(subHelp);
        menuBar.getMenus().add(help);
    }

    // adding About menu
    private void initAbout() {
        Menu about = new Menu("About");
        MenuItem subAbout = new MenuItem("About Application");
        subAbout.setOnAction(e -> new About().execute());
        about.getItems().addAll(subAbout);
        menuBar.getMenus().add(about);
    }

}
