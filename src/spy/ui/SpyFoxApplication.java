package spy.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import spy.db.FoxProService;

public class SpyFoxApplication extends Application {


    private FoxProService foxProService;


    @Override
    public void start(Stage primaryStage) throws Exception {


        foxProService = FoxProService.getInstance();

        VBox layout = new VBox();
        HBox line = new HBox();
        layout.getChildren().add(line);

        Button inputFileButton = createInputFileButton(primaryStage);
        line.getChildren().add(inputFileButton);


        primaryStage.setTitle(UiTexts.PRIMARY_STAGE_TITLE);
        primaryStage.setScene(new Scene(layout, 600, 460));
        primaryStage.show();


    }

    private Button createInputFileButton(Stage stage) {

        EventHandler<ActionEvent> eventHandler = new DirectoryChooserEventHandler(stage);

        Button inputFileButton = new Button();
        inputFileButton.setText(UiTexts.DIRECTORY_CHOOSER_TITLE);
        inputFileButton.setOnAction(eventHandler);

        return inputFileButton;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
