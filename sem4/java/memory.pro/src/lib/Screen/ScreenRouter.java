package lib.Screen;

import java.util.ArrayList;
import java.util.HashMap;

public class ScreenRouter {
    private ArrayList<onScreenChangeCallback> onScreenChangeCallbacks = new ArrayList<>();
    private HashMap<String, String> routes = new HashMap<>();

    public void setScreen(String alias) {
        onScreenChangeCallbacks.forEach((callback) -> callback.call(routes.get(alias)));
    }

    public void addScreen(String alias, String path) {
        routes.put(alias, path);
    }

    public ArrayList<onScreenChangeCallback> getOnScreenChangeCallbacks() {
        return onScreenChangeCallbacks;
    }

    @FunctionalInterface
    public interface onScreenChangeCallback {
        void call(String path);
    }

}
