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
   final static private String FXML_URL = "../views/createBook.fxml";

    public WindowAddBook() throws IOException {
        super();
    }

    public void setTitle(String title) {
        controller.setTitle(title);
        window.setTitle(title);
    }

    protected void load() throws IOException {
        super.load();

        window.initModality(Modality.WINDOW_MODAL);
    }

    protected void initEvents() {
        super.initEvents();

        controller.getOnCancelListeners().add(() -> window.hide());
    }
}
