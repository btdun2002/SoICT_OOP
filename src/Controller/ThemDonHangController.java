package Controller;

import com.sun.javafx.fxml.builder.web.WebEngineBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    DatePicker Time;
    @FXML
    ComboBox<String> Type;
    private ObservableList<String> SelectList = FXCollections.observableArrayList("Normal","Triangle","Circle");
    public void initialize() {
        Type.setValue("Normal");
        Type.setItems(SelectList);
    }
    @FXML
    public void ThemButton(ActionEvent ActionEvent) throws IOException {
        DonHang a;
        Bang b;
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
//        System.out.println(Type.getText());

        if (Type.getValue().equals("Normal")) {
            b = new Bang(Double.parseDouble(Area.getText()));

        }
        else if (Type.getValue().equals("Triangle")){
            // UpCasting
            b = new BangTamGiac(Double.parseDouble(Area.getText()));

        }
        else if (Type.getValue().equals("Circle")){
            // UpCasting
            b = new BangTron(Double.parseDouble(Area.getText()));

        }
        else {b=new Bang();

        }
        a = new DonHang(CustomerName.getText(),CustomerAddress.getText(),b,Time.getValue().toString());

        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String Insert = "INSERT INTO `receipttable`(`CustomerName`, `TimeAdd`, `Address`, `Area`,`Cost`,`Type`) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(Insert);
            ps.setString(1,a.getTen());
            ps.setString(2,a.getThoiGianThem());
            ps.setString(3,a.getDiaChi());
            ps.setDouble(4,a.getBang().getDienTich());
            ps.setDouble(5,a.getBang().getChiPhi());
            ps.setString(6,a.getBang().getNameBang());
            ps.execute();
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
       JOptionPane.showMessageDialog(null,"Adding successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }

}
