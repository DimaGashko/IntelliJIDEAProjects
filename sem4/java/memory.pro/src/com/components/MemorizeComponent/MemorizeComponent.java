package com.components.MemorizeComponent;

import lib.Component.Component;
import java.net.URL;
import java.util.ResourceBundle;

public class MemorizeComponent extends Component {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Memorize Component");
    }

    public void run(String trainingType, int dataCount) {
        System.out.println(trainingType + " | " + dataCount);
    }

}
