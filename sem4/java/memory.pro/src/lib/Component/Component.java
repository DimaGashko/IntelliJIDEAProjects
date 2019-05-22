package lib.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lib.Alerts.Alerts;
import lib.Router.Router;
import lib.Screen.ScreenException;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

abstract public class Component {
    protected Router router = new Router();
    protected EntityManager em;

    protected HashMap<String, String> params;
    protected Alerts alerts = new Alerts();

    public void setRouter(Router router) {
        this.router = router;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected Parent loadScreen(String path, HashMap<String, String> params) throws ScreenException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        var originalControllerFactory = loader.getControllerFactory();

        loader.setControllerFactory((ControllerClass) -> {
            try {
                Component component = (Component) ControllerClass.getDeclaredConstructor().newInstance();
                component.setEntityManager(em);
                component.setRouter(router);
                component.setParams(params);

                return component;
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                alerts.showError("Screen Error", e);
                return originalControllerFactory.call(ControllerClass);
            }
        });

        try {
            root = loader.load();
        } catch (Exception e) {
            throw new ScreenException("Screen not found");
        }

        return root;
    }

    public void setParams(HashMap<String, String> params) {
        this.params = params;
    }
}
