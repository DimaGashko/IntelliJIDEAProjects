package lib.Router;

import java.util.ArrayList;
import java.util.HashMap;

public class Router {
    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();

    private HashMap<String, String> screens = new HashMap<>();
    private HashMap<String, String> components = new HashMap<>();

    public void setScreen(String alias, HashMap<String, String> params) {
        onScreenChangeCallbacks.forEach((callback) -> callback.call(screens.get(alias), params));
    }

    public void setScreen(String alias) {
        setScreen(alias, null);
    }

    public void addScreen(String alias, String path) {
        screens.put(alias, path);
    }

    public void addComponent(String alias, String path) {
        components.put(alias, path);
    }

    public ArrayList<onScreenChangeCallback> getOnScreenChangeCallbacks() {
        return onScreenChangeCallbacks;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(String path, HashMap<String, String> params);
    }

}
