package learn.javaFx._19_sceneBuilder;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage window) throws Exception {
        window.setTitle("FXML 2");

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene scene = new Scene(root, 500, 300);
        window.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
