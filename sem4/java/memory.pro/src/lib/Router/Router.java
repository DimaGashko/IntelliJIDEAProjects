package lib.Router;

import java.util.ArrayList;
import java.util.HashMap;

public class Router {
    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();
    private HashMap<String, String> routes = new HashMap<>();

    public void setScreen(String alias, HashMap<String, String> params) {
        onScreenChangeCallbacks.forEach((callback) -> callback.call(routes.get(alias), params));
    }

    public void setScreen(String alias) {
        setScreen(alias, null);
    }

    public void addScreen(String alias, String path) {
        routes.put(alias, path);
    }

    public ArrayList<onScreenChangeCallback> getOnScreenChangeCallbacks() {
        return onScreenChangeCallbacks;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(String path, HashMap<String, String> params);
    }

}
