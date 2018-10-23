package learn.javaFx._14_tableView1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

public class Main extends Application {
    @Override
    public void start(Stage window)  {
        window.setTitle("JavaFx");

        TableColumn<Product, String> nameCol = new TableColumn<>("Name");
        TableColumn<Product, Double> widthCol = new TableColumn<>("Width");
        TableColumn<Product, Double> heightCol = new TableColumn<>("Height");
        TableColumn<Product, Double> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        widthCol.setCellValueFactory(new PropertyValueFactory<>("width"));
        heightCol.setCellValueFactory(new PropertyValueFactory<>("height"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        nameCol.setMinWidth(200);

        var table = new TableView<Product>();
        table.setItems(getProducts());

        table.getColumns().addAll(nameCol, widthCol, heightCol, priceCol);

        VBox vBox = new VBox();
        vBox.getChildren().add(table);

        Scene scene = new Scene(vBox, 500, 400);
        window.setScene(scene);

        window.show();
    }

    public ObservableList<Product> getProducts() {
        ObservableList<Product> products = FXCollections.observableArrayList();

        products.add(new Product("Laptop", 35, 22, 850));
        products.add(new Product("Bounce Box", 10, 10, 3));
        products.add(new Product("Corn", 0.3, 1, 1.1));
        products.add(new Product("iPhone", 8, 18, 4999));
        products.add(new Product("Car", 550, 160, 130000));
        products.add(new Product("Gold", 20, 12, 950000));

        return products;
    }

    public static void main(String[] args) {
        Main.launch(args);
    }

}
