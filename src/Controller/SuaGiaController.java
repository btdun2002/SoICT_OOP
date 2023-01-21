package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Bang;
import model.BangTamGiac;
import model.BangTron;

import javax.swing.*;
import java.io.IOException;

public class SuaGiaController {

    @FXML
    private TextField AdditionalCostTriangle;
    @FXML
    private TextField AdditionalCostCircle;

    @FXML
    private TextField CostPerm2;

    @FXML
    private Button EditCommit;
    @FXML
    public void initialize(){
        CostPerm2.setText(String.valueOf(Bang.phiMotMet));
        AdditionalCostTriangle.setText(String.valueOf(BangTamGiac.phiTang));
        AdditionalCostCircle.setText(String.valueOf(BangTron.phiTang));
    }

    @FXML
    void EditButton(ActionEvent ActionEvent) throws IOException {
        Bang.phiMotMet = Double.parseDouble(CostPerm2.getText());
        BangTron.phiTang= Double.parseDouble(AdditionalCostCircle.getText());
        BangTamGiac.phiTang= Double.parseDouble(AdditionalCostTriangle.getText());
        JOptionPane.showMessageDialog(null,"Edit successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }
}
