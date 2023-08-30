package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class orderController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<order, String> colIdDelivery;

    @FXML
    private TableColumn<order, String> colIdOrder;

    @FXML
    private TableColumn<order, String> colIdWh;

    @FXML
    private TableColumn<order, String> colTanggalOrder;

    @FXML
    private TableView<order> tableOrder;

    @FXML
    private TextField txtIdDelivery;

    @FXML
    private TextField txtIdOrder;

    @FXML
    private TextField txtIdWh;

    @FXML
    private TextField txtTanggalOrder;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdOrder.getText());
        System.out.println(txtTanggalOrder.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtIdWh.getText());


        if(txtIdOrder.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idOrderText = txtIdOrder.getText();
        String tanggalOrderText = txtTanggalOrder.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String idWhText = txtIdWh.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertOrder(Integer.parseInt(idOrderText), tanggalOrderText,Integer.parseInt(idDeliveryText), Integer.parseInt(idWhText));
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableOrder.getSelectionModel().getSelectedIndex();

        idO = Integer.parseInt(String.valueOf(tableOrder.getItems().get(myIndex).getidOrder()));
        idD = Integer.parseInt(String.valueOf(tableOrder.getItems().get(myIndex).getidDelivery()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdOrder.getText());
        System.out.println(txtTanggalOrder.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtIdWh.getText());

        if(txtIdOrder.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtTanggalOrder.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "TANGGAL BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idOrderText = txtIdOrder.getText();
        String tanggalOrderText = txtTanggalOrder.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String idWhText = txtIdWh.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editOrder(Integer.parseInt(idOrderText), tanggalOrderText,Integer.parseInt(idDeliveryText), Integer.parseInt(idWhText), idO, idD);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableOrder.getSelectionModel().getSelectedIndex();

        idO = Integer.parseInt(String.valueOf(tableOrder.getItems().get(myIndex).getidOrder()));
        idD = Integer.parseInt(String.valueOf(tableOrder.getItems().get(myIndex).getidDelivery()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteOrder(idO, idD);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"DELETE SUCCESSFUL", "DATA BERHASIL DIHAPUS");
    }
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public void table() {
        JdbcDao jdbcDao = new JdbcDao();

        ObservableList<order> orders = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_order, tanggal_order, id_delivery, id_warehouse FROM orderr";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    order o = new order();
                    o.setidOrder(rs.getString("id_order"));
                    o.settanggal(rs.getString("tanggal_order"));
                    o.setidDelivery(rs.getString("id_delivery"));
                    o.setidWarehouse(rs.getString("id_warehouse"));
                    orders.add(o);
                }
            }
            tableOrder.setItems(orders);
            colIdOrder.setCellValueFactory(f -> f.getValue().idOrderProperty());
            colTanggalOrder.setCellValueFactory(f -> f.getValue().tanggalProperty());
            colIdDelivery.setCellValueFactory(f -> f.getValue().idDeliveryProperty());
            colIdWh.setCellValueFactory(f -> f.getValue().idWarehouseProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableOrder.setRowFactory(tv -> {
            TableRow<order> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableOrder.getSelectionModel().getSelectedIndex();

                    txtIdOrder.setText(tableOrder.getItems().get(myIndex).getidOrder());
                    txtTanggalOrder.setText(tableOrder.getItems().get(myIndex).gettanggal());
                    txtIdDelivery.setText(tableOrder.getItems().get(myIndex).getidDelivery());
                    txtIdWh.setText(tableOrder.getItems().get(myIndex).getidWarehouse());

                }
            });
            return myRow;
        });
    }
    public void SwitchtoMain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    private Stage stage;
    private Scene scene;
    private Parent root;
    int myIndex;
    int idO;
    int idD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
