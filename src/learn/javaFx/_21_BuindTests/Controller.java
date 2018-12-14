package learn.javaFx._21_BuindTests;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private SimpleIntegerProperty min = new SimpleIntegerProperty(10);
    private SimpleIntegerProperty max = new SimpleIntegerProperty(100);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        min.set(20);
        max.set(200);
    }

    @FXML void fxOnButton() {
        min.set(min.get() + 1);
        max.set(max.get() + 2);
    }

    public int getMin() {
        return min.get();
    }

    public SimpleIntegerProperty minProperty() {
        return min;
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public int getMax() {
        return max.get();
    }

    public SimpleIntegerProperty maxProperty() {
        return max;
    }

    public void setMax(int max) {
        this.max.set(max);
    }
}
