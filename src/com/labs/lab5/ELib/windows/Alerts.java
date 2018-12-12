package com.labs.lab5.ELib.windows;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Alerts {

    private Alert alertInfo;
    private Alert alertConfirm;
    private Alert alertErr;

    public Alerts() {
        initAlerts();
    }

    private void initAlerts() {
        initAlertInfo();
        initAlertConfirm();
        initAlertErr();
    }

    public Optional<ButtonType> show(Alert alert, String header, String content) {
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }

    public Optional<ButtonType> show(Alert alert, String header) {
        return show(alert, header, "");
    }

    /**
     * @return Возвращает ответ пользователя в AlertConfirm
     */
    public boolean getAnswer(Optional<ButtonType> option) {
        // TODO: add isPresent check
        return (option.get() == ButtonType.OK);
    }

    private void initAlertInfo() {
        alertInfo = new Alert(Alert.AlertType.INFORMATION);
        alertInfo.setTitle("ELib - your world of books");
    }

    private void initAlertConfirm() {
        alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("ELib - your world of books");
    }

    private void initAlertErr() {
        alertErr = new Alert(Alert.AlertType.ERROR);
        alertErr.setTitle("ELib - your world of books");
    }

    public Alert getAlertInfo() {
        return alertInfo;
    }

    public Alert getAlertConfirm() {
        return alertConfirm;
    }

    public Alert getAlertErr() {
        return alertErr;
    }
}
