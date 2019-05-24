package com;

import com.services.TrainingService.WordsTrainingService;
import dao.UserDao;
import dao.WordDao;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        //launch(args);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        EntityManager em = factory.createEntityManager();

        WordsTrainingService wordsTrainingService = new WordsTrainingService(em);

        wordsTrainingService.setUp("", 1000);
        var words = wordsTrainingService.loadData();

        System.out.println(words);
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
