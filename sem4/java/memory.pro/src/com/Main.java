package com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage stage;

    public static void main(String[] args) {
        launch(args);
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
            System.out.println();
        }
    }

    /*private EntityManager em;
    private UserDao userDao;
    private Auth auth;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run();
    }

    private void run() throws Exception {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        auth = new Auth(userDao);


        isLoggedIn();
        //System.out.println(auth.logout());

        //login("Kotlin", "kotlinkotlin");

        String username = auth.getLoggedInUsername().orElseThrow(Exception::new);
        User user = userDao.getUserByUsername(username).orElseThrow(Exception::new);

        System.out.println(user);
    }

    private void login(String username, String password) {
        try {
            auth.login(username, password);
            System.out.println("Success!");
        } catch (AuthException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void isLoggedIn() {
        System.out.println(auth.isLoggedIn() ? "You're online" : "You're offline");
    }

    private void signup() {
        User user = new User();
        user.setFirstName("Jon");
        user.setLastName("Kotlin");
        user.setUsername("Kotlin");
        user.setEmail("kotlinjon@gmail.com");
        user.setPassword("kotlinkotlin");

        try {
            auth.signup(user);
        } catch (AuthException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Success");
    }*/

}
