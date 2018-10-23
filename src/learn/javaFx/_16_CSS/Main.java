package learn.javaFx._16_CSS;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import learn.javaFx._2_alertBoxes.AlertBox;

public class Main extends Application {

    @Override
    public void start(Stage window) {
        window.setTitle("JavaFx");

        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(15);

        var nameLabel = new Label("Username: ");
        var passLabel = new Label("Password: ");
        var nameField = new TextField("JavaFx");
        var passField = new PasswordField();
        var logIn = new Button("Log In");
        var license = new CheckBox("License: ");

        passField.setPromptText("Password");

        GridPane.setConstraints(nameLabel, 0, 0);
        GridPane.setConstraints(nameField, 1, 0);

        GridPane.setConstraints(passLabel, 0, 1);
        GridPane.setConstraints(passField, 1, 1);

        GridPane.setConstraints(license, 1, 2);
        GridPane.setConstraints(logIn, 1, 3);

        grid.getChildren().addAll(nameField, passField, nameLabel, passLabel, license, logIn);

        Scene scene = new Scene(grid, 500, 400);
        //scene.getStylesheets().add("./Main.sass");
        scene.getStylesheets().add(getClass().getResource("Main.css").toExternalForm());

        window.setScene(scene);

        logIn.setOnAction(e -> {
            var login = nameField.getText();
            var pass = passField.getText();
            var mess = "";

            if (!license.isSelected()) {
                mess = "You have to agree the license";
            } else if (!login.matches("[\\w]{2,20}")) {
                mess = "Incorrect a Username";
            } else if (pass.length() < 5) {
                mess = "The password is too short";
            } else {
                mess = "Username: " + login + "\nPassword: " + pass;
            }

            AlertBox.display("Auth", mess);
        });

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
