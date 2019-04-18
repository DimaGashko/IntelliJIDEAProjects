package learn.javaFx._17_FXML;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class Controller {

    public Button touch;

    public void onMouseTouch(MouseEvent event) {
        System.out.println("Run some code the user doesn't see;" + event);
        touch.setText("Stop touching me!");
    }
}
