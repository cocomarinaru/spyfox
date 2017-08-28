package spy.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import spy.db.FoxProService;

public class SpyFoxApplication extends Application {


    private FoxProService foxProService;

    private DatePicker startDatePicker;

    private DatePicker endDatePicker;

    private Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {

        foxProService = FoxProService.getInstance();
        this.primaryStage = primaryStage;

        createLayout(primaryStage);

    }

    private void createLayout(Stage primaryStage) {
        VBox layout = new VBox();

        this.startDatePicker = new DatePicker();
        this.endDatePicker = new DatePicker();

        HBox startDateHbox = wrapDatePicker(UiTexts.START_DATE, startDatePicker);
        HBox endDateHbox = wrapDatePicker(UiTexts.END_DATE, endDatePicker);

        HBox chooseFolderHBox = new HBox();

        Button chooseDirectoryButton = createChooseDirectoryButton(this);
        chooseFolderHBox.getChildren().add(chooseDirectoryButton);

        layout.getChildren().addAll(startDateHbox, endDateHbox, chooseFolderHBox);

        primaryStage.setTitle(UiTexts.PRIMARY_STAGE_TITLE);
        primaryStage.setScene(new Scene(layout, 600, 460));
        primaryStage.show();
    }

    private HBox wrapDatePicker(String labelText, DatePicker datePicker) {
        HBox dateHBox = new HBox();
        Label label = new Label(labelText);
        dateHBox.getChildren().addAll(label, datePicker);
        return dateHBox;
    }

    private Button createChooseDirectoryButton(SpyFoxApplication application) {

        EventHandler<ActionEvent> eventHandler = new DirectoryChooserEventHandler(application);

        Button chooseDirectoryButton = new Button();
        chooseDirectoryButton.setText(UiTexts.DIRECTORY_CHOOSER_TITLE);
        chooseDirectoryButton.setOnAction(eventHandler);

        return chooseDirectoryButton;
    }


    public static void main(String[] args) {
        launch(args);
    }


    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public DatePicker getEndDatePicker() {
        return endDatePicker;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
