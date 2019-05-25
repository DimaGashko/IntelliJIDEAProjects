package lib.Component;

import com.Common.Common;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.util.Pair;
import lib.Alerts.Alerts;
import lib.Screen.Screen;
import schemas.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;

abstract public class Component implements Initializable {
    protected HashMap<String, String> params;
    protected Alerts alerts = new Alerts();
    protected Common common;

    protected Pair<Parent, Component> loadComponent(String alias, HashMap<String, String> params) throws ComponentException {
        return _loadComponent(getCommon().getComponents().get(alias), params);
    }

    protected Pair<Parent, Component> loadComponent(String alias) throws ComponentException {
        return loadComponent(alias,null);
    }

    protected Pair<Parent, Screen> loadScreen(String alias, HashMap<String, String> params) throws ComponentException {
        Pair<Parent, Component> componentPair = _loadComponent(getCommon().getScreens().get(alias), params);
        return new Pair<>(componentPair.getKey(), (Screen)componentPair.getValue());
    }

    protected Pair<Parent, Screen> loadScreen(String alias) throws ComponentException {
        return loadScreen(alias,null);
    }

    private Pair<Parent, Component> _loadComponent(String path, HashMap<String, String> params) throws ComponentException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        loader.setControllerFactory((ControllerClass) -> {
            Component component = null;

            try {
                component = (Component) ControllerClass.getDeclaredConstructor().newInstance();
                component.setCommon(getCommon());
                component.setParams(params);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return component;
        });

        try {
            root = loader.load();
        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
            throw new ComponentException("Internal Error");
        }

        Component component = loader.getController();

        return new Pair<>(root, component);
    }

    public Optional<User> getUser() {
        Optional<User> userOpt = common.getUser();

        if (userOpt.isEmpty()) {
            alerts.show(Alerts.alertErr, "Cannot load user data");
        }

        return userOpt;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    private void setParams(HashMap<String, String> params) {
        this.params = params;
    }

}
