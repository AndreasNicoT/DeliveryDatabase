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

public class notasupplierController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<notaSupplier, String> colDetail;

    @FXML
    private TableColumn<notaSupplier, String> colIdDelivery;

    @FXML
    private TableColumn<notaSupplier, String> colIdSupplier;

    @FXML
    private TableView<notaSupplier> tableNotaSupplier;

    @FXML
    private TextField txtDetail;

    @FXML
    private TextField txtIdDelivery;

    @FXML
    private TextField txtIdSupplier;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdSupplier.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtDetail.getText());


        if(txtIdSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtDetail.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idSupplierText = txtIdSupplier.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String detailText = txtDetail.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertNotaSupplier(Integer.parseInt(idSupplierText),Integer.parseInt(idDeliveryText),detailText);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableNotaSupplier.getSelectionModel().getSelectedIndex();

        idS = Integer.parseInt(String.valueOf(tableNotaSupplier.getItems().get(myIndex).getidSupplier()));
        idD = Integer.parseInt(String.valueOf(tableNotaSupplier.getItems().get(myIndex).getidDelivery()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdSupplier.getText());
        System.out.println(txtIdDelivery.getText());
        System.out.println(txtDetail.getText());

        if(txtIdSupplier.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtIdDelivery.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "JENIS DELIVERY BELUM TERISI");
            return;
        }
        if(txtDetail.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID TRANSPORT BELUM TERISI");
            return;
        }
        String idSupplierText = txtIdSupplier.getText();
        String idDeliveryText = txtIdDelivery.getText();
        String detailText = txtDetail.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editNotaSupplier(Integer.parseInt(idSupplierText),Integer.parseInt(idDeliveryText),detailText, idS, idD);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableNotaSupplier.getSelectionModel().getSelectedIndex();

        idS = Integer.parseInt(String.valueOf(tableNotaSupplier.getItems().get(myIndex).getidSupplier()));
        idD = Integer.parseInt(String.valueOf(tableNotaSupplier.getItems().get(myIndex).getidDelivery()));

        Window owner = btnDelete.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteNotaSupplier(idS, idD);
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

        ObservableList<notaSupplier> notaSuppliers = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_supplier, id_delivery, detail FROM supplierdelivery";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    notaSupplier n = new notaSupplier();
                    n.setidSupplier(rs.getString("id_supplier"));
                    n.setidDelivery(rs.getString("id_delivery"));
                    n.setdetail(rs.getString("detail"));
                    notaSuppliers.add(n);
                }
            }
            tableNotaSupplier.setItems(notaSuppliers);
            colIdSupplier.setCellValueFactory(f -> f.getValue().idSupplierProperty());
            colIdDelivery.setCellValueFactory(f -> f.getValue().idDeliveryProperty());
            colDetail.setCellValueFactory(f -> f.getValue().detailProperty());


        } catch (SQLException ex) {
            Logger.getLogger(deliveryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableNotaSupplier.setRowFactory(tv -> {
            TableRow<notaSupplier> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableNotaSupplier.getSelectionModel().getSelectedIndex();

                    txtIdSupplier.setText(tableNotaSupplier.getItems().get(myIndex).getidSupplier());
                    txtIdDelivery.setText(tableNotaSupplier.getItems().get(myIndex).getidDelivery());
                    txtDetail.setText(tableNotaSupplier.getItems().get(myIndex).getdetail());
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
    int idD;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
