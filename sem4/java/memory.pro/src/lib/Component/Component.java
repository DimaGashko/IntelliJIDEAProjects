package lib.Component;

import Global.Global;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import lib.Alerts.Alerts;
import lib.Router.Router;
import lib.Screen.ScreenException;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Optional;

abstract public class Component implements Initializable {
    protected HashMap<String, String> params;
    protected Alerts alerts = new Alerts();
    protected Global global;


    public void setGlobal(Global global) {
        this.global = global;
    }

    protected Optional<Parent> loadComponent(String path, HashMap<String, String> params) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        loader.setControllerFactory((ControllerClass) -> {
            Component component = null;

            try {
                component = (Component) ControllerClass.getDeclaredConstructor().newInstance();
                component.setGlobal(global);
                component.setParams(params);
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

            return component;
        });

        try {
            root = loader.load();
        } catch (Exception e) {
            return Optional.empty();
        }

        return Optional.of(root);
    }

    private void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
