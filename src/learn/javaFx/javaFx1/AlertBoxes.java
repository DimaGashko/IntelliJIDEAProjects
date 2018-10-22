package learn.javaFx.javaFx1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AlertBoxes extends Application {
    Button button;

    public static void main(String[] args) {
        Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("Alert Boxes");

        button = new Button("Button");
        button.setOnAction(e -> System.out.println("Hi"));

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
