package com.labs.lab5.ELib;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ELib extends Application {
    private final static String defTitle = "ELib - the world of books";
    private final static int minWidth = 600;
    private final static int minHeight = 400;

    @Override
    public void start(Stage mainWindow) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("views/index.fxml"));
        Parent root = loader.load();
        mainWindow.setTitle(defTitle);
        Image icon = new Image(getClass().getResource("img/icon.png").toString());
        mainWindow.getIcons().add(icon);

        Scene scene = new Scene(root);
        mainWindow.setScene(scene);

        mainWindow.setMinWidth(minWidth);
        mainWindow.setMinHeight(minHeight);

        mainWindow.setMaximized(true);

        mainWindow.show();

        WindowAddBook addBook = new WindowAddBook();

        mainWindow.initModality(Modality.WINDOW_MODAL);
    }

    public static void main(String[] args) {
        /*
        LocalDate d1 = LocalDate.of(1000,1,1);
        LocalDate d2 = LocalDate.of(1001, 1, 1);
        LocalDate d3 = LocalDate.of(1002, 1, 1);

        Book book = new Book("JavaScript", "Jonathan", "Apple", d2, 200, 301);
        BookFilters filters = new BookFilters();

        filters.setNameFilter("JavaScript");
        filters.setAuthorFilter("th");
        filters.setPublisherFilter("Apple");
        filters.setPriceFromFilter(null);
        filters.setPriceToFilter(200d);
        filters.setDateFromFilter(d1);
        filters.setDateToFilter(d3);

        System.out.println(filters.check(book));
        */

        launch(args);
    }
}
