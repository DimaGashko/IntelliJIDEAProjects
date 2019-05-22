package Global;

import dao.UserDao;
import lib.Auth.Auth;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.HashMap;

public class Global {
    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();

    private HashMap<String, String> screens = new HashMap<>();
    private HashMap<String, String> components = new HashMap<>();

    private EntityManager em;
    private UserDao userDao;

    private Auth auth;

    public Global() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPU");
        em = factory.createEntityManager();

        userDao = new UserDao(em);
        auth = new Auth(userDao);

        initScreens();
        initComponents();
    }

    private void initScreens() {
        screens.put("index", "/com/screens/index/IndexScreen.fxml");
        screens.put("auth", "/com/screens/auth/AuthScreen.fxml");
        screens.put("profile", "/com/screens/profile/ProfileScreen.fxml");
        screens.put("statistic", "/com/screens/statistic/StatisticScreen.fxml");
        screens.put("training", "/com/screens/training/TrainingScreen.fxml");
    }

    private void initComponents() {
        components.put("header", "/com/components/header/HeaderComponent.fxml");
        components.put("login", "/com/components/login/LoginComponent.fxml");
        components.put("signup", "/com/components/signup/SignupComponent.fxml");
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
