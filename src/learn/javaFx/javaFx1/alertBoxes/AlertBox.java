package learn.javaFx.javaFx1.alertBoxes;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String title, String mess) {
        Stage window = new Stage();
        window.setTitle(title);

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        var label = new Label(mess);
        var closeButton = new Button(mess);
        closeButton.setOnAction(e -> window.close());

        var layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);

        var scene = new Scene(layout, 500, 500);
        window.setScene(scene);

        window.showAndWait();
    }
}
