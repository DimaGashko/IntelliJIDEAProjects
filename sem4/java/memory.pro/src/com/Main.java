package com;

import com.services.AuthService.AuthService;
import com.services.AuthService.AuthServiceException;
import com.services.TrainingService.WordsTrainingResult;
import com.services.TrainingService.WordsTrainingService;
import dao.UserDao;
import dao.WordDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import schemas.User;
import schemas.WordsResultData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        //launch(args);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

        AuthService authService = new AuthService(new UserDao(em));
/*
        User user = new User();

        user.setFirstName("Dmitry");
        user.setLastName("Gashko");
        user.setUsername("DmitryGashko");
        user.setEmail("dimagashko@gmail.com");
        user.setPassword("qqqqqqqqww");

        try {
            authService.signup(user);
        } catch (AuthServiceException e) {

        }*/

        User u = userDao.loadByUsername("DmitryGashko").orElseThrow(null);
        WordsTrainingService wordsTrainingService = new WordsTrainingService(u, em);

        wordsTrainingService.setUp(25);
        var words = wordsTrainingService.start();

        var res = words.stream().map((word) -> new WordsTrainingResult(word, word.length()))
                .collect(Collectors.toCollection(ArrayList::new));

        res.get(0).setValue("wrong1");
        res.get(2).setValue("wrong3");
        res.get(4).setValue("wrong5");
        res.get(6).setValue("wrong7");

        wordsTrainingService.finish(res);

        /*WordDao wordDao = new WordDao(em);
        var words = wordDao.getRandomWords(10);

        WordsResultData wordsResultData = new WordsResultData();
        wordsResultData.setWord(words.get(2));
        wordsResultData.setAnswer("asdf");
        wordsResultData.setTime(25);

        em.getTransaction().begin();
        em.persist(wordsResultData);
        em.getTransaction().commit();

        System.out.println("Done");*/
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
