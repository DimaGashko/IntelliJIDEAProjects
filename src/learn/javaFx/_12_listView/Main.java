package learn.javaFx._12_listView;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        ListView view = new ListView<>();
        view.getItems().addAll(
                "Apples",
                "Oranges",
                "Bananas"
        );

        view.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button button = new Button("Submit");
        view.setEditable(true);

        GridPane.setConstraints(view, 0, 0);
        GridPane.setConstraints(button, 0, 1);

        grid.getChildren().addAll(view, button);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);

        button.setOnAction(e -> {
            var mess = "";
            ObservableList<String> items;
            items = view.getSelectionModel().getSelectedItems();

            for (String item: items) {
                mess += item + "\n";
            }

            AlertBox.display("ListView", mess);
        });

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
