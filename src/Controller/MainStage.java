package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainStage extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
            stage.initStyle(StageStyle.DECORATED);
            Scene scene = new Scene(root,400,400);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e){

        }
    }
}
