package editor.controller;
import editor.model.MenuItemStrategy;
import editor.model.feature.FindReplace;
import editor.model.feature.FontProperty;
import editor.model.feature.BackgroundColor;
import editor.model.items.About;
import editor.model.items.Help;
import editor.model.items.OpenFile;
import editor.model.items.SaveFile;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Controller {
    private Stage stage;
    private MenuBar menuBar;
    private TextArea textArea;
    private SeparatorMenuItem separator;
    private MenuItemStrategy itemStrategy;
    private static final Logger logger = LogManager.getLogger(Controller.class);

    public Controller(Stage stage) {
        this.stage = stage;
    }

    // initialize menu bar with menu items and return menubar
    public MenuBar intiMenuBar() {
        menuBar = new MenuBar();
        separator = new SeparatorMenuItem();
        logger.debug("Controller Class: initializing menubar and its item.");
        initFileMenu();
        initSetting();
        initFont();
        initBackground();
        initHelp();
        initAbout();
        logger.debug("Contoller Class: initialized.");
        return menuBar;
    }

    // initialize textarea with font and size and return textarea
    public TextArea addTextArea() {
        textArea = new TextArea();
        textArea.setFont(Font.font("Ubuntu Mono",22));
        return textArea;
    }

    // alertbox when user exits application
    public void alertBox() {
        Stage alert = new Stage();
        alert.setTitle("Alert");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setResizable(false);
        Label label = new Label("Do you want to save the file?");
        Button yes = new Button("Yes");
        yes.setOnAction(e -> {
            itemStrategy = new SaveFile(textArea,stage);
            itemStrategy.execute();
        });

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
        newFile.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        MenuItem openFile = new MenuItem("Open File");
        openFile.setAccelerator(KeyCombination.keyCombination("Ctrl+O"));
        openFile.setOnAction(e -> {
            itemStrategy = new OpenFile(textArea,stage);
            itemStrategy.execute();
        });
        MenuItem saveFile = new MenuItem("Save");
        saveFile.setAccelerator(KeyCombination.keyCombination("Ctrl+S"));
        saveFile.setOnAction( e -> {
            itemStrategy = new SaveFile(textArea,stage);
            itemStrategy.execute();
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
        exit.setOnAction(e -> alertBox());
        file.getItems().addAll(newFile, openFile, saveFile, separator, exit);
        menuBar.getMenus().add(file);
    }

    // adding menu Setting
    private void initSetting() {
        Menu setting = new Menu("Setting");
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioMenuItem darkMode = new RadioMenuItem("Dark Mode");
       // darkMode.setOnAction(e -> textArea.setStyle("-fx-control-inner-background:gray"));
        MenuItem fullscreen = new MenuItem("Full Screen");
        fullscreen.setAccelerator(KeyCombination.keyCombination("Ctrl+F"));
        fullscreen.setOnAction(e -> stage.setFullScreen(true));

        setting.getItems().addAll(darkMode, fullscreen);
        menuBar.getMenus().add(setting);
    }

    private void initFont() {
        Menu font = new Menu("Font");
        MenuItem change_font = new MenuItem("Change Font");
        change_font.setOnAction(e -> {
            new FontProperty(textArea).execute();
        });
        MenuItem spacing = new MenuItem("Spacing");
        MenuItem findReplace = new MenuItem("Find & Replace");
        findReplace.setOnAction(e -> {
            FindReplace fR = new FindReplace(textArea);
            fR.execute();
        });
        font.getItems().addAll(change_font,spacing,separator,findReplace);
        menuBar.getMenus().add(font);
    }

    private void initBackground() {
        Menu background = new Menu("Background");
        MenuItem background_color = new MenuItem("Background Color");
        background_color.setOnAction(e -> {
            new BackgroundColor(menuBar,textArea).execute();
        });
        MenuItem backgroundImage = new MenuItem("Background Image");
        background.getItems().addAll(background_color,backgroundImage);
        menuBar.getMenus().add(background);
    }

    // adding Help menu
    private void initHelp() {
        Menu help = new Menu("Help");
        MenuItem subHelp = new MenuItem("Help");
        subHelp.setOnAction(e -> {
            Help helpClass = new Help();
            helpClass.execute();
        });
        subHelp.setAccelerator(KeyCombination.keyCombination("Ctrl+K"));
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
