package learn.javaFx._10_onChange;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import learn.javaFx._2_alertBoxes.AlertBox;

public class Main extends Application {

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(15);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Apples", "Oranges", "Bananas");

        Label label = new Label("Select something");

        choiceBox.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> {
            label.setText("Your choice is " + newVal);
        });

        choiceBox.setValue("Apples");

        GridPane.setConstraints(choiceBox, 0, 0);
        GridPane.setConstraints(label, 0, 1);

        grid.getChildren().addAll(choiceBox, label);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);


        //AlertBox.display("Auth", choiceBox.getValue());

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
