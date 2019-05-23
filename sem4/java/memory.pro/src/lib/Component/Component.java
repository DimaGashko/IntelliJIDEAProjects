package lib.Component;

import Global.Global;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.util.Pair;
import lib.Alerts.Alerts;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

abstract public class Component implements Initializable {
    protected HashMap<String, String> params;
    protected Alerts alerts = new Alerts();
    protected Global global;

    protected Pair<Parent, Component> loadComponent(String path, HashMap<String, String> params) throws ComponentException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        loader.setControllerFactory((ControllerClass) -> {
            Component component = null;

            try {
                component = (Component) ControllerClass.getDeclaredConstructor().newInstance();
                component.setGlobal(getGlobal());
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

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    private void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
