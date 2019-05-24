package com.Common;

import dao.UserDao;
import lib.Auth.Auth;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;

public class Common {
    static public final String EMAIL_REGEXP = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();

    private HashMap<String, String> screens = new HashMap<>();
    private HashMap<String, String> components = new HashMap<>();

    private EntityManager em;
    private UserDao userDao;

    private Auth auth;

    public Common() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        auth = new Auth(userDao);

        initScreens();
        initComponents();
    }

    private void initScreens() {
        screens.put("index", "/com/screens/IndexScreen/IndexScreen.fxml");
        screens.put("auth", "/com/screens/AuthScreen/AuthScreen.fxml");
        screens.put("profile", "/com/screens/ProfileScreen/ProfileScreen.fxml");
        screens.put("training", "/com/screens/TrainingScreen/TrainingScreen.fxml");
        screens.put("statistic", "/com/screens/StatisticScreen/StatisticScreen.fxml");
    }

    private void initComponents() {
        components.put("header", "/com/components/HeaderComponent/HeaderComponent.fxml");
        components.put("login", "/com/components/LoginComponent/LoginComponent.fxml");
        components.put("signup", "/com/components/SignupComponent/SignupComponent.fxml");
    }

    public void setScreen(String alias, HashMap<String, String> params) {
        onScreenChangeCallbacks.forEach((callback) -> callback.call(alias, params));
    }

    public void setScreen(String alias) {
        setScreen(alias, null);
    }

    public ArrayList<onScreenChangeCallback> getOnScreenChangeCallbacks() {
        return onScreenChangeCallbacks;
    }

    public HashMap<String, String> getScreens() {
        return screens;
    }

    public HashMap<String, String> getComponents() {
        return components;
    }

    public EntityManager getEm() {
        return em;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public Auth getAuth() {
        return auth;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(String path, HashMap<String, String> params);
    }
}
