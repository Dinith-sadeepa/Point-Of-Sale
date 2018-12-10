package lk.ijse.pos.common;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertBox {
    public static void setAlert(Alert.AlertType alertType, String text) {
        Alert a = new Alert(alertType, text, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }
}
