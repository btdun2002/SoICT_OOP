package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SuaDonHangController
{
    @FXML
    private TextField Area;
    @FXML
    private TextField CustomerAddress;

    @FXML
    private TextField CustomerName;
    @FXML
    private Button EditCommit;
    @FXML
    private TextField Time;
    @FXML
    private TextField Type;
    private int ID;
    @FXML
    void EditButton(ActionEvent ActionEvent) throws IOException {
        DonHang a;
        Bang b;
        String url = "jdbc:mysql://localhost:3306/oop";
        String pass = "";
        String username= "root";
        if (Type.getText().equals("Normal")) {
            b = new Bang(Double.parseDouble(Area.getText()));

        }
        else if (Type.getText().equals("Triangle")){
            // UpCasting
            b = new BangTamGiac(Double.parseDouble(Area.getText()));

        }
        else if (Type.getText().equals("Circle")){
            // UpCasting
            b = new BangTron(Double.parseDouble(Area.getText()));

        }
        else {b=new Bang();

        }
        a = new DonHang(CustomerName.getText(),CustomerAddress.getText(),b,Time.getText());
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String Insert = "UPDATE `receipttable` SET`CustomerName`=?," +
                    "`TimeAdd`=?,`Address`=?,`Area`=?," +
                    "`Cost`=?,`Type`= ? WHERE `ID` = "+this.ID;
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
        JOptionPane.showMessageDialog(null,"Edit successfully","Success",JOptionPane.PLAIN_MESSAGE);
        SceneController sceneController=new SceneController();
        sceneController.BackToMain(ActionEvent);
    }
    public void  setEdit(DonHangDataBase donHangDataBase){
        CustomerName.setText(donHangDataBase.getTen());
        this.Area.setText(String.valueOf(donHangDataBase.getDienTich()));
        this.CustomerAddress.setText(donHangDataBase.getDiaChi());
        this.Time.setText(donHangDataBase.getThoiGianThem());
        this.Type.setText(donHangDataBase.getTenBang());
        ID = donHangDataBase.getID();
    }
}
