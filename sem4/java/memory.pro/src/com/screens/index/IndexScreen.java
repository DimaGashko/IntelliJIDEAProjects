package com.screens.index;

import dao.UserDao;
import javafx.fxml.Initializable;
import lib.Auth.Auth;
import lib.Screen.Screen;
import schemas.User;

import java.net.URL;
import java.util.ResourceBundle;

public class IndexScreen extends Screen implements Initializable {
    UserDao userDao;
    User user;
    Auth auth;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("IndexScreen!!");

        userDao = new UserDao(em);
        auth = new Auth(userDao);

    }

}
