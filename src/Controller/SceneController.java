package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import model.*;

//import javax.security.auth.callback.Callback;

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
    private TableColumn<DonHangDataBase, String> CusCol;
    @FXML
    private TableColumn<DonHangDataBase, String> TimeCol;
    @FXML
    private TableColumn<DonHangDataBase, String> TypeCol;
    @FXML
    private TableColumn<DonHangDataBase,String> IDCol;
    @FXML
    private TableColumn<DonHangDataBase,String> editCol;
    @FXML
    private ComboBox<String> SearchCBox;
    @FXML
    private TextField SearchField;
    private ObservableList<DonHangDataBase> DonHangList= FXCollections.observableArrayList();
    private ObservableList<String> SelectList = FXCollections.observableArrayList("Name","Address","Type","Area Above","Cost Above","Exit Search");
    private DonHangDataBase donHang = null;
    private String query;
    private String url = "jdbc:mysql://localhost:3306/oop";
    private String pass = "";
    private String username= "root";
    private PreparedStatement ps;

    @FXML
    public void initialize(){
        SearchCBox.setValue("Name");
        SearchCBox.setItems(SelectList);
        loadTable();
    }
    public void refreshTable(){
        DonHangList.clear();
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
            String query = "SELECT * FROM `receipttable`";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs =  ps.executeQuery();
            while (rs.next()) {
                    DonHangList.add(new DonHangDataBase(rs.getInt("ID"),
                            rs.getString("CustomerName"),
                            rs.getString("Address"),
                            rs.getString("TimeAdd"),
                            rs.getDouble("Area"),
                            rs.getDouble("Cost"),
                            rs.getString("Type")));
            }
            CusCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
            AddressCol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
            TimeCol.setCellValueFactory(new PropertyValueFactory<>("ThoiGianThem"));
            AreaCol.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
            CostCol.setCellValueFactory(new PropertyValueFactory<>("TongPhi"));
            TypeCol.setCellValueFactory(new PropertyValueFactory<>("tenBang"));
            IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            donHangTableView.setItems(DonHangList);
        }
        catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    private void loadTable() {
        refreshTable();
        //add cell of button edit
        Callback<TableColumn<DonHangDataBase, String>, TableCell<DonHangDataBase, String>> cellFoctory = (TableColumn<DonHangDataBase, String> param) -> {
            // make cell containing buttons
            final TableCell<DonHangDataBase, String> cell = new TableCell<DonHangDataBase, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                          Button xoaButton = new Button("Delete");
                          Button suaButton = new Button("Edit");
                        xoaButton.setOnMouseClicked((MouseEvent event) -> {
                                  try(Connection conn = DriverManager.getConnection(url,username,pass)) {
                                      donHang = donHangTableView.getSelectionModel().getSelectedItem();
                                      query = "DELETE FROM `receipttable` WHERE ID =" + donHang.getID();
                                      PreparedStatement ps = conn.prepareStatement(query);
                                      ps.execute();
                                      refreshTable();
                                  }
                                  catch (SQLException e){
                                  }
                        });
                        suaButton.setOnMouseClicked((MouseEvent event) -> {
                            try {
                                donHang = donHangTableView.getSelectionModel().getSelectedItem();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SuaDonHang.fxml"));
                                root = loader.load();
                                SuaDonHangController suaDonHangController = loader.getController();
                                suaDonHangController.setEdit(donHang);
                                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                scene = new Scene(root);
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });

                        HBox managebtn = new HBox(xoaButton,suaButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(xoaButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(suaButton, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };
            return cell;
        };
        editCol.setCellFactory(cellFoctory);
        donHangTableView.setItems(DonHangList);

    }
    @FXML
    public void ThemHoaDon(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/ThemDonHangScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Search(MouseEvent event) throws IOException {
      if(SearchCBox.getValue().equals("Name")){
          SearchController("CustomerName");
      }
      else if (SearchCBox.getValue().equals("Address")){
          SearchController("Address");
      }
      else if (SearchCBox.getValue().equals("Type")){
          SearchController("Type");
      }
      else if (SearchCBox.getValue().equals("Exit Search")) refreshTable();
      else if (SearchCBox.getValue().equals("Area Above")) {
        SearchController("Area Above");
      }
      else SearchController("Cost Above");
    }
    public void BackToMain(javafx.event.ActionEvent actionEvent) throws IOException{
        root = FXMLLoader.load(getClass().getResource("../View/Main.fxml"));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SearchController(String TypeSearh){
        try (Connection conn = DriverManager.getConnection(url,username,pass)){
                if (TypeSearh.equals("Area Above")) {
                    System.out.println("In");
                    query = "SELECT * FROM `receipttable` WHERE Area > ?";
                    ps = conn.prepareStatement(query);
                    ps.setDouble(1,Double.parseDouble(SearchField.getText()));
                }
                else if (TypeSearh.equals("Cost Above")){
                    query = "SELECT * FROM `receipttable` WHERE Cost > ?";
                    ps = conn.prepareStatement(query);
                    ps.setDouble(1,Double.parseDouble(SearchField.getText()));
                }
                else {
                    query = "SELECT * FROM `receipttable` WHERE " + TypeSearh + " = ?";
                    ps = conn.prepareStatement(query);
                    ps.setString(1,SearchField.getText());
                }
            ResultSet rs = ps.executeQuery();
            DonHangList.clear();
                while (rs.next()){
                    System.out.println(SearchField.getText());
                    DonHangList.add(new DonHangDataBase(rs.getInt("ID"),
                            rs.getString("CustomerName"),
                            rs.getString("Address"),
                            rs.getString("TimeAdd"),
                            rs.getDouble("Area"),
                            rs.getDouble("Cost"),
                            rs.getString("Type")));
                }
                CusCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
                AddressCol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
                TimeCol.setCellValueFactory(new PropertyValueFactory<>("ThoiGianThem"));
                AreaCol.setCellValueFactory(new PropertyValueFactory<>("dienTich"));
                CostCol.setCellValueFactory(new PropertyValueFactory<>("TongPhi"));
                TypeCol.setCellValueFactory(new PropertyValueFactory<>("tenBang"));
                IDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
//                donHangTableView.setItems(DonHangList);
                Callback<TableColumn<DonHangDataBase, String>, TableCell<DonHangDataBase, String>> cellFoctory = (TableColumn<DonHangDataBase, String> param) -> {
                    // make cell containing buttons
                    final TableCell<DonHangDataBase, String> cell = new TableCell<DonHangDataBase, String>() {
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //that cell created only on non-empty rows
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                Button xoaButton = new Button("Delete");
                                Button suaButton = new Button("Edit");
                                xoaButton.setOnMouseClicked((MouseEvent event) -> {
                                    try(Connection conn = DriverManager.getConnection(url,username,pass)) {
                                        donHang = donHangTableView.getSelectionModel().getSelectedItem();
                                        query = "DELETE FROM `receipttable` WHERE ID =" + donHang.getID();
                                        PreparedStatement ps = conn.prepareStatement(query);
                                        ps.execute();
                                        refreshTable();
                                    }
                                    catch (SQLException e){
                                    }
                                });
                                suaButton.setOnMouseClicked((MouseEvent event) -> {
                                    try {
                                        donHang = donHangTableView.getSelectionModel().getSelectedItem();
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/SuaDonHang.fxml"));
                                        root = loader.load();
                                        SuaDonHangController suaDonHangController = loader.getController();
                                        suaDonHangController.setEdit(donHang);
                                        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                                        scene = new Scene(root);
                                        stage.setScene(scene);
                                        stage.show();
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                });

                                HBox managebtn = new HBox(xoaButton,suaButton);
                                managebtn.setStyle("-fx-alignment:center");
                                HBox.setMargin(xoaButton, new Insets(2, 2, 0, 3));
                                HBox.setMargin(suaButton, new Insets(2, 3, 0, 2));

                                setGraphic(managebtn);
                                setText(null);
                            }
                        }
                    };
                    return cell;
                };
                editCol.setCellFactory(cellFoctory);
                donHangTableView.setItems(DonHangList);
        }
        catch (SQLException sqlException){

        }
    }
    @FXML
    public void EditPriceButton(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("../View/SuaDonGia.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
