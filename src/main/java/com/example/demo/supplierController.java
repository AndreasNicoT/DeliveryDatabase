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

public class supplierController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<supplier, String> colIdSupplier;

    @FXML
    private TableColumn<supplier, String> colLokasiSupplier;

    @FXML
    private TableColumn<supplier, String> colNamaSupplier;

    @FXML
    private TableView<supplier> tableSupplier;

    @FXML
    private TextField txtIdSupplier;

    @FXML
    private TextField txtLokasiSupplier;

    @FXML
    private TextField txtNamaSupplier;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdSupplier.getText());
        System.out.println(txtNamaSupplier.getText());
        System.out.println(txtLokasiSupplier.getText());


        if(txtIdSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtNamaSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtLokasiSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idSupplierText = txtIdSupplier.getText();
        String namaSupplierText = txtNamaSupplier.getText();
        String lokasiSupplierText = txtLokasiSupplier.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertSupplier(Integer.parseInt(idSupplierText),namaSupplierText,lokasiSupplierText);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableSupplier.getSelectionModel().getSelectedIndex();

        idS = Integer.parseInt(String.valueOf(tableSupplier.getItems().get(myIndex).getid_supplier()));


        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdSupplier.getText());
        System.out.println(txtNamaSupplier.getText());
        System.out.println(txtLokasiSupplier.getText());

        if(txtIdSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtNamaSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtLokasiSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idSupplierText = txtIdSupplier.getText();
        String namaSupplierText = txtNamaSupplier.getText();
        String lokasiSupplierText = txtLokasiSupplier.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editSupplier(Integer.parseInt(idSupplierText),namaSupplierText,lokasiSupplierText, idS);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableSupplier.getSelectionModel().getSelectedIndex();

        idS = Integer.parseInt(String.valueOf(tableSupplier.getItems().get(myIndex).getid_supplier()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteSupplier(idS);
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

        ObservableList<supplier> suppliers = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_supplier, nama_supplier, lokasi FROM supplier";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    supplier s = new supplier();
                    s.setid_supplier(rs.getString("id_supplier"));
                    s.setnama_supplier(rs.getString("nama_supplier"));
                    s.setlokasi(rs.getString("lokasi"));
                    suppliers.add(s);
                }
            }
            tableSupplier.setItems(suppliers);
            colIdSupplier.setCellValueFactory(f -> f.getValue().id_supplierProperty());
            colNamaSupplier.setCellValueFactory(f -> f.getValue().nama_supplierProperty());
            colLokasiSupplier.setCellValueFactory(f -> f.getValue().lokasiProperty());


        } catch (SQLException ex) {
            Logger.getLogger(deliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableSupplier.setRowFactory(tv -> {
            TableRow<supplier> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableSupplier.getSelectionModel().getSelectedIndex();

                    txtIdSupplier.setText(tableSupplier.getItems().get(myIndex).getid_supplier());
                    txtNamaSupplier.setText(tableSupplier.getItems().get(myIndex).getnama_supplier());
                    txtLokasiSupplier.setText(tableSupplier.getItems().get(myIndex).getlokasi());
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
    int idS;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
