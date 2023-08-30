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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class warehouseController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<warehouse, String> colIdWh;

    @FXML
    private TableColumn<warehouse, String> colLokasiWh;

    @FXML
    private TableColumn<warehouse, String> colNamaWh;

    @FXML
    private TableView<warehouse> tableWarehouse;

    @FXML
    private TextField txtIdWh;

    @FXML
    private TextField txtLokasiWh;

    @FXML
    private TextField txtNamaWh;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdWh.getText());
        System.out.println(txtNamaWh.getText());
        System.out.println(txtLokasiWh.getText());


        if(txtIdWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtLokasiWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idWh = txtIdWh.getText();
        String namaWh = txtNamaWh.getText();
        String lokasi = txtLokasiWh.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertWarehouse(Integer.parseInt(idWh),namaWh,lokasi);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableWarehouse.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableWarehouse.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdWh.getText());
        System.out.println(txtNamaWh.getText());
        System.out.println(txtLokasiWh.getText());


        if(txtIdWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtLokasiWh.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idWh = txtIdWh.getText();
        String namaWh = txtNamaWh.getText();
        String lokasi = txtLokasiWh.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editWarehouse(Integer.parseInt(idWh),namaWh,lokasi, id);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableWarehouse.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableWarehouse.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteWarehouse(id);
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

        ObservableList<warehouse> warehouses = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_warehouse, nama_warehouse, lokasi FROM warehouse";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    warehouse b = new warehouse();
                    b.setId(rs.getString("id_warehouse"));
                    b.setNama(rs.getString("nama_warehouse"));
                    b.setLokasi(rs.getString("lokasi"));
                    warehouses.add(b);
                }
            }
            tableWarehouse.setItems(warehouses);
            colIdWh.setCellValueFactory(f -> f.getValue().idProperty());
            colNamaWh.setCellValueFactory(f -> f.getValue().namaProperty());
            colLokasiWh.setCellValueFactory(f -> f.getValue().lokasiProperty());


        } catch (SQLException ex) {
            Logger.getLogger(warehouseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableWarehouse.setRowFactory(tv -> {
            TableRow<warehouse> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableWarehouse.getSelectionModel().getSelectedIndex();

                    txtIdWh.setText(tableWarehouse.getItems().get(myIndex).getId());
                    txtNamaWh.setText(tableWarehouse.getItems().get(myIndex).getNama());
                    txtLokasiWh.setText(tableWarehouse.getItems().get(myIndex).getLokasi());

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
    int id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
