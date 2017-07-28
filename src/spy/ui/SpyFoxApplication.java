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


    @Override
    public void start(Stage primaryStage) throws Exception {


        foxProService = FoxProService.getInstance();

        VBox layout = new VBox();

        HBox startDateHbox = createDatePicker(UiTexts.START_DATE);
        HBox endDateHbox = createDatePicker(UiTexts.END_DATE);

        HBox chooseFolderHBox = new HBox();

        Button chooseDirectoryButton = createChooseDirectoryButton(primaryStage);
        chooseFolderHBox.getChildren().add(chooseDirectoryButton);

        layout.getChildren().addAll(startDateHbox, endDateHbox, chooseFolderHBox);

        primaryStage.setTitle(UiTexts.PRIMARY_STAGE_TITLE);
        primaryStage.setScene(new Scene(layout, 600, 460));
        primaryStage.show();


    }

    private HBox createDatePicker(String labelText) {
        HBox dateHBox = new HBox();
        Label label = new Label(labelText);
        DatePicker datePicker = new DatePicker();
        dateHBox.getChildren().addAll(label, datePicker);
        return dateHBox;
    }

    private Button createChooseDirectoryButton(Stage stage) {

        EventHandler<ActionEvent> eventHandler = new DirectoryChooserEventHandler(stage);

        Button chooseDirectoryButton = new Button();
        chooseDirectoryButton.setText(UiTexts.DIRECTORY_CHOOSER_TITLE);
        chooseDirectoryButton.setOnAction(eventHandler);

        return chooseDirectoryButton;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
