package learn.javaFx._4_closeTheApp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import learn.javaFx._2_alertBoxes.ConfirmBox;

public class Main extends Application {
    Stage window;
    Button button;

    public static void main(String[] args) {
        Main.launch(args);
    }

    @Override
    public void start(Stage primaryStage)  {
        window = primaryStage;
        window.setTitle("My first a native JavaFx Application");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        button = new Button("Close Program");
        button.setOnAction(e -> closeProgram());

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 300, 250);

        window.setScene(scene);
        window.show();
    }

    private void closeProgram() {
        ConfirmBox.display("Confirm close the program", "Are you sure you want to close program?");
        if (ConfirmBox.answer) {
            System.out.println("OK. Your data is saved! See you later :)");
            window.close();
        } else {
            System.out.println("Hi. We are glad you stay here!");
        }
    }

}
