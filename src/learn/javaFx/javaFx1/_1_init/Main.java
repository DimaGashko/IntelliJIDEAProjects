package learn.javaFx.javaFx1._1_init;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    Button button;

    public static void main(String[] args) {
            Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        primaryStage.setTitle("My first a native JavaFx Application");

        button = new Button("Button");
        button.setOnAction(e -> System.out.println("Hi"));

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
