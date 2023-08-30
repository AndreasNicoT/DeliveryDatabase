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

public class deliveryController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<delivery, String> colIdDelivery;

    @FXML
    private TableColumn<delivery, String> colIdTranspor;

    @FXML
    private TableColumn<delivery, String> colJenisDelivery;

    @FXML
    private TableView<delivery> tableDelivery;

    @FXML
    private TextField txtIdDelivery;

    @FXML
    private TextField txtIdTranspor;

    @FXML
    private TextField txtJenisDelivery;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtJenisDelivery.getText());
        System.out.println(txtIdTranspor.getText());


        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtJenisDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idDelivery = txtIdDelivery.getText();
        String jenisDelivery = txtJenisDelivery.getText();
        String idTransport = txtIdTranspor.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertDelivery(Integer.parseInt(idDelivery),jenisDelivery,Integer.parseInt(idTransport));
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableDelivery.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableDelivery.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtJenisDelivery.getText());
        System.out.println(txtIdTranspor.getText());

        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtJenisDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idDelivery = txtIdDelivery.getText();
        String jenisDelivery = txtJenisDelivery.getText();
        String idTranspor = txtIdTranspor.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editDelivery(Integer.parseInt(idDelivery),jenisDelivery,Integer.parseInt(idTranspor), id);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableDelivery.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableDelivery.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteDelivery(id);
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

        ObservableList<delivery> deliveries = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_delivery, jenis_delivery, id_transportasi FROM delivery";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    delivery d = new delivery();
                    d.setId(rs.getString("id_delivery"));
                    d.setjenisDelivery(rs.getString("jenis_delivery"));
                    d.setidTransportasi(rs.getString("id_transportasi"));
                    deliveries.add(d);
                }
            }
            tableDelivery.setItems(deliveries);
            colIdDelivery.setCellValueFactory(f -> f.getValue().idProperty());
            colJenisDelivery.setCellValueFactory(f -> f.getValue().jenisDeliveryProperty());
            colIdTranspor.setCellValueFactory(f -> f.getValue().idTransportasiProperty());


        } catch (SQLException ex) {
            Logger.getLogger(deliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableDelivery.setRowFactory(tv -> {
            TableRow<delivery> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableDelivery.getSelectionModel().getSelectedIndex();

                    txtIdDelivery.setText(tableDelivery.getItems().get(myIndex).getId());
                    txtJenisDelivery.setText(tableDelivery.getItems().get(myIndex).getjenisDelivery());
                    txtIdTranspor.setText(tableDelivery.getItems().get(myIndex).getidTransportasi());
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