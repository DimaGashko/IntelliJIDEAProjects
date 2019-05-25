package com;

import com.services.AuthService.AuthService;
import com.services.AuthService.AuthServiceException;
import com.services.TrainingService.NumberTrainingService;
import com.services.TrainingService.TrainingResult;
import com.services.TrainingService.WordsTrainingService;
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        //launch(args);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        UserDao userDao = new UserDao(em);

        AuthService authService = new AuthService(new UserDao(em));

       /* User user = new User();

        user.setFirstName("Jon");
        user.setLastName("Kotlin");
        user.setUsername("Kotlin");
        user.setEmail("kotlin@gmail.com");
        user.setPassword("kotlin");

        try {
            authService.signup(user);
        } catch (AuthServiceException e) {

        }
*/
        User u = userDao.loadByUsername("Kotlin").orElseThrow(null);
        WordsTrainingService wordsTrainingService = new WordsTrainingService(u, em);

        wordsTrainingService.setUp(500);
        var words = wordsTrainingService.start();

        var res = words.stream().map((word) -> new TrainingResult(word, word.length()))
                .collect(Collectors.toCollection(ArrayList::new));

        res.get(0).setValue("wrong1");
        res.get(2).setValue("wrong3");
        res.get(4).setValue("wrong5");
        res.get(6).setValue("wrong7");

        wordsTrainingService.finish(res);
/*
        User u = userDao.loadByUsername("Kotlin").orElseThrow(null);
        NumberTrainingService numberTrainingService = new NumberTrainingService(u, em);

        numberTrainingService.setUp(100);
        var data = numberTrainingService.start();

        var res = data.stream().map((num) -> new TrainingResult(num, 3))
                .collect(Collectors.toCollection(ArrayList::new));

        res.get(0).setValue("wrong1");
        res.get(2).setValue("555");
        res.get(4).setValue("36");
        res.get(6).setValue("wrong7");

        numberTrainingService.finish(res);

        System.out.println(data);*/
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
