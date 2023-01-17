package Controller;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.stage.StageStyle;
import model.Bang;
import model.BangTamGiac;
import model.BangTron;
import model.DonHang;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;


public class SceneController {

    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    private TableView<DonHang> donHangTableView;
    @FXML
    private TableColumn<DonHang, String> AddressCol;

    @FXML
    private TableColumn<DonHang, Double> AreaCol;

    @FXML
    private TableColumn<DonHang, Double> CostCol;

    @FXML
    private TableColumn<DonHang, Double> CostPerm2Col;

    @FXML
    private TableColumn<DonHang, String> CusCol;

    @FXML
    private TableColumn<DonHang, String> TimeCol;

    @FXML
    private TableColumn<DonHang, String> TypeCol;
    ObservableList<DonHang> DonHangList= FXCollections.observableArrayList();
    @FXML
    public void initialize(){
        loadTable();
    }
    public void refreshTable(){
        System.out.println("rt");
         String url = "jdbc:mysql://localhost:3306/oop";
         String pass = "";
         String username= "root";
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            conn.getCatalog();
            String query = "SELECT * FROM `receipttable`";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                if (rs.getString("Type").equals("Normal")) {
                    System.out.println("Normal");
                    Bang b = new Bang(rs.getDouble("Area"),rs.getDouble("CostPer1m2"));

                    DonHangList.add(new DonHang(rs.getString("CustomerName"),
                                                rs.getString("Address"),
                                                b,
                                                rs.getString("TimeAdd")));
                }
                else if (rs.getString("Type").equals("Circle")) {
                    Bang b = new BangTron(rs.getDouble("Area"),rs.getDouble("CostPer1m2"));

                    DonHangList.add(new DonHang(rs.getString("CustomerName"),
                            rs.getString("Address"),
                            b,
                            rs.getString("TimeAdd")));
                }
                else {
                    Bang b = new BangTamGiac(rs.getDouble("Area"),rs.getDouble("CostPer1m2"));

                    DonHangList.add(new DonHang(rs.getString("CustomerName"),
                            rs.getString("Address"),
                            b,
                            rs.getString("TimeAdd")));
                }
                donHangTableView.setItems(DonHangList);
            }
        }
        catch (SQLException throwables){

        }
    }
    private void loadTable() {

//        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            refreshTable();
//            CusCol.setCellValueFactory(new PropertyValueFactory<DonHang, String>("ten"));
//            AddressCol.setCellValueFactory(new PropertyValueFactory<DonHang, String>("diachi"));
//            TimeCol.setCellValueFactory(new PropertyValueFactory<DonHang,String>("ThoiGianThem"));
//            AreaCol.setCellValueFactory(new PropertyValueFactory<DonHang,Double>("bang.getDienTich()"));
//            CostPerm2Col.setCellValueFactory(new PropertyValueFactory<DonHang,Double>("bang.getPhiMotMet()"));
//            CostCol.setCellValueFactory(new PropertyValueFactory<DonHang,Double>("bang.getChiPhi()"));
//            TypeCol.setCellValueFactory(new PropertyValueFactory<DonHang,String>("bang.getNameBang()"));
//        }
//        catch (SQLException throwables){
//
//        }
    }

    @FXML
    public void ThemHoaDon(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../View/ThemDonHangScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    
    public void BackToMain(javafx.event.ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
