package learn.javaFx.javaFx1.alertBoxes;

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
        primaryStage.setTitle("Alert Boxes");

        button = new Button("Open alert box");
        button.setOnAction(e -> {
            AlertBox.display("Alert Box", "Confirm you choice");
            ConfirmBox.display("Confirm Box", "Are you sure you want to sent it?");

            if (ConfirmBox.answer) {
                AlertBox.display("Alert Box", "You answered Yes");
            } else {
                AlertBox.display("Alert Box", "You answered No");
            }
        });

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

}
