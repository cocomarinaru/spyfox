package spy.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spy.input.InputFile;
import spy.input.InputFileReader;

import java.io.File;

/**
 * Created by cocos on 7/16/2017.
 */
public class InputFileChooserEventHandler implements EventHandler<ActionEvent> {

    private final FileChooser fileChooser;
    private final Stage stage;

    public InputFileChooserEventHandler(Stage stage) {
        fileChooser = new FileChooser();
        this.stage = stage;
        initFileChooser();
    }

    private void initFileChooser() {
        fileChooser.setTitle("Alege fisierul de intrare");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fisiere Text", "*.txt"));
    }

    @Override
    public void handle(ActionEvent event) {

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            readFile(file);
        }
    }

    private void readFile(File file) {
        InputFile inputFile = InputFileReader.readInputFile(file.getPath());

    }
}
