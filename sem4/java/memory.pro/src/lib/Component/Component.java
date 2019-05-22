package lib.Component;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lib.Alerts.Alerts;
import lib.Screen.Router;
import lib.Screen.ScreenException;

import javax.persistence.EntityManager;
import java.lang.reflect.InvocationTargetException;

abstract public class Component {
    protected Router router = new Router();
    protected EntityManager em;

    protected Alerts alerts = new Alerts();

    public void setRouter(Router router) {
        this.router = router;
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    protected Parent loadScreen(String path) throws ScreenException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;

        var originalControllerFactory = loader.getControllerFactory();

        loader.setControllerFactory((ControllerClass) -> {
            try {
                Component component = (Component) ControllerClass.getDeclaredConstructor().newInstance();
                component.setEntityManager(em);
                component.setRouter(router);

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
}
