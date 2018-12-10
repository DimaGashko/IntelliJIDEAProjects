package com.labs.lab5.ELib.controllers;

import com.labs.lab3.part1.library.Book;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateBook implements Initializable {
    private Stage window;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //initWindow();
    }

    public void setTitle(String title) {

    }

    private Book createNewWindow() {
        return new Book();
    }


}
