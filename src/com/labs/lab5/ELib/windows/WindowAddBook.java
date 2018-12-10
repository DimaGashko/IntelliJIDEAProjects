package com.labs.lab5.ELib.windows;

import com.labs.lab3.part1.library.Book;
import com.labs.lab5.ELib.controllers.CreateBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowAddBook extends BaseWindow<CreateBook> {
    public WindowAddBook() throws IOException {
        super();
    }

    public void setTitle(String title) {
        controller.setTitle(title);
        window.setTitle(title);
    }

    protected void load() throws IOException {
        var loader = new FXMLLoader(getClass().getResource("../views/createBook.fxml"));

        Parent root = loader.load();
        controller = loader.getController();

        Scene scene = new Scene(root);

        window = new Stage();

        Image icon = new Image(getClass().getResource("../img/icon.png").toString());
        window.getIcons().add(icon);

        window.setScene(scene);

        window.setMinWidth(500);
        window.setMinHeight(680);
        window.initModality(Modality.WINDOW_MODAL);
    }

    protected void initEvents() {
        super.initEvents();

        controller.getOnCancelListeners().add(() -> window.hide());
    }
}
