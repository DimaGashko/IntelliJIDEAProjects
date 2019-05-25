package com.Common;

import com.services.AuthService.AuthService;
import dao.UserDao;
import schemas.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Common {
    static public final String EMAIL_REGEXP = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();

    private HashMap<String, String> screens = new HashMap<>();
    private HashMap<String, String> components = new HashMap<>();

    private EntityManager em;
    private UserDao userDao;

    private AuthService authService;

    public Common() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        authService = new AuthService(userDao);

        initScreens();
        initComponents();
    }

    private void initScreens() {
        screens.put("index", "/com/screens/IndexScreen/IndexScreen.fxml");
        screens.put("auth", "/com/screens/AuthScreen/AuthScreen.fxml");
        screens.put("profile", "/com/screens/ProfileScreen/ProfileScreen.fxml");
        screens.put("training", "/com/screens/TrainingScreen/TrainingScreen.fxml");
        screens.put("statistics", "/com/screens/StatisticsScreen/StatisticsScreen.fxml");
    }

    private void initComponents() {
        components.put("header", "/com/components/HeaderComponent/HeaderComponent.fxml");
        components.put("login", "/com/components/LoginComponent/LoginComponent.fxml");
        components.put("signup", "/com/components/SignupComponent/SignupComponent.fxml");
        components.put("memorize", "/com/components/MemorizeComponent/MemorizeComponent.fxml");
    }

    public void setScreen(String alias, HashMap<String, String> params) {
        onScreenChangeCallbacks.forEach((callback) -> callback.call(alias, params));
    }

    public Optional<User> getUser() {
        String username = getAuthService().getLoggedInUsername();
        return getUserDao().getByUsername(username);
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

    public AuthService getAuthService() {
        return authService;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(String path, HashMap<String, String> params);
    }
}
