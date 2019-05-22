package lib.Alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

public class Alerts {

    static public Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    static public Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
    static public Alert alertErr = new Alert(Alert.AlertType.ERROR);
    static public Alert alertWarn = new Alert(Alert.AlertType.WARNING);

    public Optional<ButtonType> show(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert.showAndWait();
    }

    public Optional<ButtonType> show(Alert alert, String title, String content) {
        return show(alert, title, title, content);
    }

    public Optional<ButtonType> show(Alert alert, String title) {
        return show(alert, title, title, "");
    }

    public Optional<ButtonType> showError(String title, Exception e) {
        return show(alertErr, title, title, getStackTrace(e));
    }

    private String getStackTrace(Exception err) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        err.printStackTrace(pw);
        return sw.toString();
    }
}