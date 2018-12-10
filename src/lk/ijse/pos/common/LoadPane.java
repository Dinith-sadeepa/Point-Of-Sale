package lk.ijse.pos.common;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadPane {
    public static void loadPanel(String path, Event event , Class cl) {
        try {
            Parent root = FXMLLoader.load(cl.getClass().getResource(path));
            Scene mainPanelScene = new Scene(root);
            Stage mainPanelStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainPanelStage.setScene(mainPanelScene);
            mainPanelStage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
