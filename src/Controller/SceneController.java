package Controller;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;

import javax.security.auth.callback.Callback;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;
public class SceneController {
    private Stage stage;
    private Parent root;
    private Scene scene;
    @FXML
    public TableView<DonHangDataBase> donHangTableView;
    @FXML
    private TableColumn<DonHangDataBase, String> AddressCol;
    @FXML
    private TableColumn<DonHangDataBase, Double> AreaCol;
    @FXML
    private TableColumn<DonHangDataBase, Double> CostCol;
    @FXML
    private TableColumn<DonHangDataBase, Double> CostPerm2Col;
    @FXML
    private TableColumn<DonHangDataBase, String> CusCol;
    @FXML
    private TableColumn<DonHangDataBase, String> TimeCol;
    @FXML
    private TableColumn<DonHangDataBase, String> TypeCol;
    ObservableList<DonHangDataBase> DonHangList= FXCollections.observableArrayList();
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

            System.out.println(conn.getCatalog());
            String query = "SELECT * FROM `receipttable`";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                    DonHangList.add(new DonHangDataBase(rs.getString("CustomerName"),
                            rs.getString("Address"),
                            rs.getString("TimeAdd"),
                            rs.getDouble("Area"),
                            rs.getDouble("CostPer1m2"),
                            rs.getDouble("Cost"),
                            rs.getString("Type")));
            }



        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private void loadTable() {

        refreshTable();
        CusCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
        AddressCol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("ThoiGianThem"));
        AreaCol.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
        CostPerm2Col.setCellValueFactory(new PropertyValueFactory<>("ChiPhi1m2"));
        CostCol.setCellValueFactory(new PropertyValueFactory<>("TongPhi"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("tenBang"));
        donHangTableView.setItems(DonHangList);




//        }
//        catch (SQLException throwables){
//
//        }
    }

    @FXML
    public void ThemHoaDon(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/ThemDonHangScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void BackToMain(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
