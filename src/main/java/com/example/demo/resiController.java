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

import static java.lang.Integer.*;

public class resiController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<resi, String> colIdCust;

    @FXML
    private TableColumn<resi, String> colIdDelivery;

    @FXML
    private TableColumn<resi, String> colTotal;

    @FXML
    private TableView<resi> tableResi;

    @FXML
    private TextField txtIdCust;

    @FXML
    private TextField txtIdDelivery;

    @FXML
    private TextField txtTotal;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdCust.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtTotal.getText());


        if(txtIdCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtTotal.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idCustText = txtIdCust.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String totalText = txtTotal.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertResi(parseInt(idCustText), parseInt(idDeliveryText),totalText);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableResi.getSelectionModel().getSelectedIndex();

        idC = parseInt(String.valueOf(tableResi.getItems().get(myIndex).getidCust()));
        idD = parseInt(String.valueOf(tableResi.getItems().get(myIndex).getidDelivery()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdCust.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtTotal.getText());

        if(txtIdCust.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtTotal.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idCustText = txtIdCust.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String totalText = txtTotal.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editResi(parseInt(idCustText), parseInt(idDeliveryText),totalText, idC, idD);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableResi.getSelectionModel().getSelectedIndex();

        idC = parseInt(String.valueOf(tableResi.getItems().get(myIndex).getidCust()));
        idD = parseInt(String.valueOf(tableResi.getItems().get(myIndex).getidDelivery()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteResi(idC, idD);
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

        ObservableList<resi> notaSuppliers = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_customer, id_delivery, total FROM deliverycustomer";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    resi r = new resi();
                    r.setidCust(rs.getString("id_customer"));
                    r.setidDelivery(rs.getString("id_delivery"));
                    r.settotal(rs.getString("total"));
                    notaSuppliers.add(r);
                }
            }
            tableResi.setItems(notaSuppliers);
            colIdCust.setCellValueFactory(f -> f.getValue().idCustProperty());
            colIdDelivery.setCellValueFactory(f -> f.getValue().idDeliveryProperty());
            colTotal.setCellValueFactory(f -> f.getValue().totalProperty());


        } catch (SQLException ex) {
            Logger.getLogger(deliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableResi.setRowFactory(tv -> {
            TableRow<resi> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableResi.getSelectionModel().getSelectedIndex();

                    txtIdCust.setText(tableResi.getItems().get(myIndex).getidCust());
                    txtIdDelivery.setText(tableResi.getItems().get(myIndex).getidDelivery());
                    txtTotal.setText(tableResi.getItems().get(myIndex).gettotal());
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
    int idC;
    int idD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
