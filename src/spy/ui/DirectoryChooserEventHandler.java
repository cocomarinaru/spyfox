package spy.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by cocos on 7/18/2017.
 */
public class DirectoryChooserEventHandler implements EventHandler<ActionEvent> {

    private final DirectoryChooser directoryChooser;
    private final Stage stage;

    public DirectoryChooserEventHandler(Stage stage) {
        directoryChooser = new DirectoryChooser();
        this.stage = stage;
        configureDirectoryChooser(directoryChooser);
    }

    private void configureDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle(UiTexts.DIRECTORY_CHOOSER_TITLE);
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    @Override
    public void handle(ActionEvent event) {

        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            System.out.println(directory.getAbsolutePath());
        }
    }
}
