package learn.javaFx._14_tableView1;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    TreeView<String> tree;

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        Button button = new Button("Submit");



        Scene scene = new Scene(, 500, 400);
        window.setScene(scene);

        button.setOnAction(e -> {

        });

        window.show();
    }

    public ObservableList<Product> getProduct() {

    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
