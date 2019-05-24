package com;

import dao.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.time.LocalDate;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        //launch(args);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

        User user = new User();

        user.setFirstName("Dmitry");
        user.setLastName("Gashko");
        user.setEmail("Email");
        user.setPasswordSalt("PassSalt_DG");
        user.setPasswordKey("PassKey_DG");
        user.setUsername("DmitryGashko");
        user.setRegisterDate(LocalDate.now());

        System.out.println(user);
        userDao.add(user);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("Bootstrap.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Memory.pro");
        setIcon("/img/logo.png");

        //primaryStage.setMaximized(true);
        primaryStage.setMinWidth(100);
        primaryStage.setMinHeight(100);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setIcon(String path) {
        try {
            Image icon = new Image(getClass().getResource(path).toString());
            stage.getIcons().add(icon);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

}
