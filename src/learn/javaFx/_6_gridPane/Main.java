package learn.javaFx._6_gridPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.html.CSS;

public class Main extends Application {

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(15);

        var nameLabel = new Label("Username: ");
        var nameField = new Label("Password: ");
        var passLabel = new TextField("JavaFx");
        var passField = new PasswordField();
        var logIn = new Button("Log In");

        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);

        GridPane.setConstraints(passLabel, 0, 1);
        GridPane.setConstraints(passField, 1, 1);

        GridPane.setConstraints(logIn, 1, 2);

        grid.getChildren().addAll(nameField, passField, nameLabel, passLabel, logIn);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
