package com;

import dao.UserDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import schemas.User;
import schemas.Word;
import schemas.WordsResult;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        //launch(args);

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

/*

        User user = new User();

        user.setFirstName("yyy4y");
        user.setLastName("y4yy");
        user.setEmail("yy4y");
        user.setPasswordSalt("sghjhjkhkjhjklhjkhlklkjhjkl");
        user.setPasswordKey("asf");
        user.setUsername("aaa");
        user.setRegisterDate(LocalDate.now());

        userDao.add(user);

*/
        User dg = userDao.getUserByUsername("aaa").orElseThrow(null);

       WordsResult wordsResult = new WordsResult();
        wordsResult.setDate(LocalDate.now());
        wordsResult.setGrade(256);
        wordsResult.setUser(dg);

        em.getTransaction().begin();
        em.persist(wordsResult);
        em.getTransaction().commit();


        System.out.println(wordsResult);
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
