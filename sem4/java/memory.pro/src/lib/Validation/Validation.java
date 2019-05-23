package lib.Validation;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

public class Validation {

    public static void initValidation(JFXTextField input) {
        input.focusedProperty().addListener((o,oldVal,newVal) -> {
            if(!newVal) input.validate();
        });

        input.textProperty().addListener((o,oldVal,newVal) -> input.validate());
    }

    public static void initValidation(JFXPasswordField input) {
        input.focusedProperty().addListener((o,oldVal,newVal) -> {
            if(!newVal) input.validate();
        });

        input.textProperty().addListener((o,oldVal,newVal) -> input.validate());
    }

}
