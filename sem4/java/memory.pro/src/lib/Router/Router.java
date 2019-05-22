package lib.Router;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Router {
    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();
    private HashMap<String, String> routes = new HashMap<>();
    private Pair<String, String> currentRoute;

    public void setRoute(String alias) {
        if (!routes.containsKey(alias)) return;
        currentRoute = new Pair<>(alias, routes.get(alias));

        onScreenChangeCallbacks.forEach((callback) -> callback.call(currentRoute));
    }

    public void addRoute(String alias, String path) {
        routes.put(alias, path);
    }

    public ArrayList<onScreenChangeCallback> getOnScreenChangeCallbacks() {
        return onScreenChangeCallbacks;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(Pair<String, String> route);
    }

}
