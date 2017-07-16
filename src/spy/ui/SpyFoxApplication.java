package spy.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SpyFoxApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox layout = new VBox();
        HBox line = new HBox();
        layout.getChildren().add(line);

        Button inputFileButton = createInputFileButton(primaryStage);
        line.getChildren().add(inputFileButton);


        primaryStage.setTitle("SPY Fox");
        primaryStage.setScene(new Scene(layout, 600, 460));
        primaryStage.show();


    }

    private Button createInputFileButton(Stage stage) {

        InputFileChooserEventHandler fileChooserEventHandler = new InputFileChooserEventHandler(stage);

        Button inputFileButton = new Button();
        inputFileButton.setText("Alege fisier text!");
        inputFileButton.setOnAction(fileChooserEventHandler);

        return inputFileButton;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
