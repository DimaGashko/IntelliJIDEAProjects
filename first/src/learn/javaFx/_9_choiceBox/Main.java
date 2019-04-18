package learn.javaFx._9_choiceBox;

import javafx.application.Application;
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

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Apples", "Oranges", "Bananas");
        choiceBox.setValue("Applesgit");

        Button button = new Button("I know your Choice");

        GridPane.setConstraints(choiceBox, 0, 0);
        GridPane.setConstraints(button, 0, 1);

        grid.getChildren().addAll(choiceBox, button);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);

        button.setOnAction(e -> {
            AlertBox.display("Auth", choiceBox.getValue());
        });

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
