package learn.javaFx._15_menus;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import learn.javaFx._14_tableView1.Product;

public class Main extends Application {
    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        //File
        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(
                new MenuItem("New Project"),
                new MenuItem("Open"),
                new MenuItem("Settings"),
                new MenuItem("Save")
        );

        //Edit
        Menu editMenu = new Menu("Edit");
        editMenu.getItems().addAll(
                new MenuItem("Undo"),
                new MenuItem("Redo"),
                new MenuItem("Cut"),
                new MenuItem("Paste")
        );

        //Run
        Menu runMenu = new Menu("Run");
        runMenu.getItems().addAll(
                new MenuItem("Run"),
                new MenuItem("Debug"),
                new MenuItem("Profile")
        );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, runMenu);

        var bp = new BorderPane();
        bp.setTop(menuBar);

        Scene scene = new Scene(bp, 500, 350, Color.RED);
        window.setScene(scene);

        window.show();
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
