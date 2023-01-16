package Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void ThemHoaDon(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        stage.initStyle(StageStyle.DECORATED);
        scene = new Scene(root,400,400);
        stage.setScene(scene);
        stage.show();
    }
}
