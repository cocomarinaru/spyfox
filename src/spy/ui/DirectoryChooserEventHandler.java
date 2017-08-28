package spy.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.DirectoryChooser;
import spy.db.FoxProService;
import spy.logic.FileGenerator;

import java.io.File;
import java.time.LocalDate;

/**
 * Created by cocos on 7/18/2017.
 */
public class DirectoryChooserEventHandler implements EventHandler<ActionEvent> {

    private final DirectoryChooser directoryChooser;
    private final SpyFoxApplication application;
    private FoxProService service = FoxProService.getInstance();
    private FileGenerator fileGenerator = new FileGenerator();

    public DirectoryChooserEventHandler(SpyFoxApplication application) {
        directoryChooser = new DirectoryChooser();
        this.application = application;
        configureDirectoryChooser(directoryChooser);
    }

    private void configureDirectoryChooser(DirectoryChooser directoryChooser) {
        directoryChooser.setTitle(UiTexts.DIRECTORY_CHOOSER_TITLE);
        directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    @Override
    public void handle(ActionEvent event) {

        File directory = directoryChooser.showDialog(application.getPrimaryStage());
        if (directory != null) {
            String outputDirectory = directory.getAbsolutePath();
            LocalDate startDate = application.getStartDatePicker().getValue();
            LocalDate stopDate = application.getEndDatePicker().getValue();


            fileGenerator.generate(outputDirectory, startDate, stopDate);
        }
    }
}
