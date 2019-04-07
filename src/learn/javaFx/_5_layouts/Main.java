package learn.javaFx._5_layouts;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    Button button;

    public static void main(String[] args) {
            Main.launch(args);
    }

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        HBox menu = new HBox();
        menu.getChildren().addAll(
                new Button("File"),
                new Button("Edit"),
                new Button("View"),
                new Button("Navigate"),
                new Button("Code"),
                new Button("Analyze"),
                new Button("Build"),
                new Button("Help")
        );

        VBox projects = new VBox();
        projects.getChildren().addAll(
                new Button("Project1"),
                new Button("Project2"),
                new Button("Project3"),
                new Button("Project4"),
                new Button("Project5")
        );

        BorderPane wrapper = new BorderPane();
        wrapper.setTop(menu);
        wrapper.setLeft(projects);

        Scene scene = new Scene(wrapper, 300, 250);
        window.setScene(scene);

        window.show();
    }

}
