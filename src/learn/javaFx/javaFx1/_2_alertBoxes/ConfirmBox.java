package learn.javaFx.javaFx1._2_alertBoxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    static boolean answer = false;

    public static void display(String title, String mess) {
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);

        var label = new Label(mess);

        var noButton = new Button("NO");
        var yesButton = new Button("Yes");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        var layout = new VBox(10);
        layout.getChildren().addAll(label, noButton, yesButton);

        var scene = new Scene(layout, 250, 150);
        window.setScene(scene);

        window.showAndWait();
    }
}
