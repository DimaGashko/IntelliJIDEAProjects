package learn.javaFx._18_Controller;

import javafx.scene.control.Button;

public class Controller {

    public Button touch;

    public void onClick() {
        System.out.println("Run some code the user doesn't see");
        touch.setText("Stop touching me!");
    }
}
