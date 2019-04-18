package learn.javaFx._11_comboBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(15);

        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll(
                "Apples",
                "Oranges",
                "Bananas"
        );

        CheckBox editableCheckBox = new CheckBox("Editable ComboBox");

        combo.setPromptText("What is your favorite fruit");

        Label label = new Label("Select something");

        /*combo.getSelectionModel().selectedItemProperty().addListener((v, oldVal, newVal) -> {
            label.setText("Your choice is " + newVal);
        });*/

        combo.setEditable(true);

        combo.setEditable(editableCheckBox.isSelected());

        GridPane.setConstraints(combo, 0, 0);
        GridPane.setConstraints(editableCheckBox, 1, 0);
        GridPane.setConstraints(label, 0, 1);

        grid.getChildren().addAll(combo, label, editableCheckBox);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);

        combo.setOnAction(e -> {
            label.setText("Your choice is " + combo.getValue());
        });

        editableCheckBox.setOnAction(e -> {
            combo.setEditable(editableCheckBox.isSelected());
        });

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
