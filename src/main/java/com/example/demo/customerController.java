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

public class customerController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<customer, String> colAlamat;

    @FXML
    private TableColumn<customer, String> colIdCust;

    @FXML
    private TableColumn<customer, String> colNamaCust;

    @FXML
    private TableColumn<customer, String> colNoTelp;

    @FXML
    private TableView<customer> tableCustomer;

    @FXML
    private TextField txtAlamat;

    @FXML
    private TextField txtIdCust;

    @FXML
    private TextField txtNamaCust;

    @FXML
    private TextField txtNoTelp;
    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdCust.getText());
        System.out.println(txtNamaCust.getText());
        System.out.println(txtAlamat.getText());
        System.out.println(txtNoTelp.getText());


        if(txtIdCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtAlamat.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        if(txtNoTelp.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idCust = txtIdCust.getText();
        String namaCust = txtNamaCust.getText();
        String alamat = txtAlamat.getText();
        String notelp = txtNoTelp.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertCustomer(Integer.parseInt(idCust),namaCust,alamat, notelp);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableCustomer.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableCustomer.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdCust.getText());
        System.out.println(txtNamaCust.getText());
        System.out.println(txtAlamat.getText());
        System.out.println(txtNoTelp.getText());

        if(txtIdCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtAlamat.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        if(txtNoTelp.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idCust = txtIdCust.getText();
        String namaCust = txtNamaCust.getText();
        String alamat = txtAlamat.getText();
        String notelp = txtNoTelp.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editCustomer(Integer.parseInt(idCust),namaCust,alamat, notelp, id);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableCustomer.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableCustomer.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteCustomer(id);
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

        ObservableList<customer> customers = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_customer, nama_customer, alamat, nomor_telepon FROM customer";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    customer c = new customer();
                    c.setId(rs.getString("id_customer"));
                    c.setNama(rs.getString("nama_customer"));
                    c.setAlamat(rs.getString("alamat"));
                    c.setNotelp(rs.getString("nomor_telepon"));
                    customers.add(c);
                }
            }
            tableCustomer.setItems(customers);
            colIdCust.setCellValueFactory(f -> f.getValue().idProperty());
            colNamaCust.setCellValueFactory(f -> f.getValue().namaProperty());
            colAlamat.setCellValueFactory(f -> f.getValue().alamatProperty());
            colNoTelp.setCellValueFactory(f -> f.getValue().notelpProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableCustomer.setRowFactory(tv -> {
            TableRow<customer> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableCustomer.getSelectionModel().getSelectedIndex();

                    txtIdCust.setText(tableCustomer.getItems().get(myIndex).getId());
                    txtNamaCust.setText(tableCustomer.getItems().get(myIndex).getNama());
                    txtAlamat.setText(tableCustomer.getItems().get(myIndex).getAlamat());
                    txtNoTelp.setText(tableCustomer.getItems().get(myIndex).getNotelp());

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
