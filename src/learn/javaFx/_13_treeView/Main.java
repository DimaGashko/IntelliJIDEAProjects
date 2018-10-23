package learn.javaFx._13_treeView;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import learn.javaFx._2_alertBoxes.AlertBox;

public class Main extends Application {

    TreeView<String> tree;

    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        var grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(15);

        TreeItem<String> root, apples, langs;

        root = new TreeItem<>();
        root.setExpanded(false);

        apples = makeBranch("Item1", root);
        makeBranch("Apple 1", apples);
        makeBranch("Apple 2", apples);
        makeBranch("Apple 3", apples);
        makeBranch("Apple 4", apples);
        makeBranch("Apple 5", apples);
        makeBranch("Apple 6", apples);

        langs = makeBranch("Languages", root);
        makeBranch("JavaScript", langs);
        makeBranch("TypeScript", langs);
        makeBranch("Java", langs);
        makeBranch("C++", langs);
        makeBranch("Jade", langs);
        makeBranch("SASS", langs);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);

        Button button = new Button("Submit");

        GridPane.setConstraints(tree, 0, 0);
        GridPane.setConstraints(button, 1, 0);

        grid.getChildren().addAll(tree, button);

        Scene scene = new Scene(grid, 500, 400);
        window.setScene(scene);

        button.setOnAction(e -> {

        });

        window.show();
    }

    public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);

        return item;
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
