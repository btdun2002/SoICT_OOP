package Controller;

import com.sun.javafx.fxml.builder.web.WebEngineBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Bang;
import model.BangTamGiac;
import model.BangTron;
import model.DonHang;

import javafx.scene.control.TextField;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemDonHangController {
    @FXML
    TextField CustomerName;
    @FXML
    TextField CustomerAddress;
    @FXML
    TextField Area;
    @FXML
    TextField CostPer1m2;
    @FXML
    TextField Time;
    @FXML
    TextField Type;

    @FXML
    public void ThemButton(ActionEvent ActionEvent) throws IOException {
        DonHang a;
        Bang b;
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
//        System.out.println(Type.getText());

        if (Type.getText().equals("Thường")) {
            b = new Bang(Double.parseDouble(Area.getText()),Double.parseDouble(CostPer1m2.getText()));

        }
        else if (Type.getText().equals("Tam Giác")){
            // UpCasting
            b = new BangTamGiac(Double.parseDouble(Area.getText()),Double.parseDouble(CostPer1m2.getText()));

        }
        else if (Type.getText().equals("Tròn")){
            // UpCasting
            b = new BangTron(Double.parseDouble(Area.getText()),Double.parseDouble(CostPer1m2.getText()));

        }
        else {b=new Bang();

        }
        a = new DonHang(CustomerName.getText(),CustomerAddress.getText(),b,Time.getText());

        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String Insert = "INSERT INTO `receipttable`(`CustomerName`, `TimeAdd`, `Address`, `Area`, `CostPer1m2`, `Cost`, `Type`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(Insert);
            ps.setString(1,a.getTen());
            ps.setString(2,a.getThoiGianThem());
            ps.setString(3,a.getDiaChi());
            ps.setDouble(4,a.getBang().getDienTich());
            ps.setDouble(5,a.getBang().getPhiMotMet());
            ps.setDouble(6,a.getBang().getChiPhi());
            ps.setString(7,a.getBang().getNameBang());
            ps.execute();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       JOptionPane.showMessageDialog(null,"Thêm thành công","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }

}
