package com.labs.lab5.ELib.windows;

import com.labs.lab5.ELib.controllers.CreateBook;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowEditBook extends BaseWindow<CreateBook> {
    public WindowEditBook() throws IOException {
        super();
        setTitle("Edit The Book");
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

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(500);
        window.setMinHeight(680);
    }

    protected void initEvents() {
        super.initEvents();

        controller.getOnCancelListeners().add(() -> window.hide());
    }
}
