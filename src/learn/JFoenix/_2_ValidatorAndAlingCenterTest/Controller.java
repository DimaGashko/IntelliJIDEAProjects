package learn.JFoenix._2_ValidatorAndAlingCenterTest;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML public JFXTextField fxField1;
    @FXML public JFXTextField fxField2;

@Override
public void initialize(URL location, ResourceBundle resources) {
    fxField1.getValidators().add(new RequiredFieldValidator("Required field"));

    fxField1.focusedProperty().addListener((o, oldValue, newVal) -> {
        fxField1.setAlignment(Pos.BASELINE_LEFT);
        if (!newVal) fxField1.validate();
        fxField1.setAlignment(Pos.CENTER);
    });

    fxField1.textProperty().addListener((o, oldVal, newVal) -> {
        fxField1.setAlignment(Pos.BASELINE_LEFT);
        fxField1.validate();
        fxField1.setAlignment(Pos.CENTER);
    });
}

}
