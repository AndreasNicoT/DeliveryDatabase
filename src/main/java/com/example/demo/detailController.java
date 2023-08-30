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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class detailController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<detail, String> colIdOrder;

    @FXML
    private TableColumn<detail, String> colIdProduct;

    @FXML
    private TableColumn<detail, String> colJumlah;

    @FXML
    private TableColumn<detail, String> colNamaProduct;

    @FXML
    private TableView<detail> tableDetail;

    @FXML
    private TextField txtIdOrder;

    @FXML
    private TextField txtIdProduct;

    @FXML
    private TextField txtJumlah;

    @FXML
    private TextField txtNamaProduct;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdProduct.getText());
        System.out.println(txtIdOrder.getText());
        System.out.println(txtNamaProduct.getText());
        System.out.println(txtJumlah.getText());


        if(txtIdProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtIdOrder.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtNamaProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        if(txtJumlah.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idProduct = txtIdProduct.getText();
        String idOrderText = txtIdOrder.getText();
        String namaProductText = txtNamaProduct.getText();
        String jumlahText = txtJumlah.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertDetail(Integer.parseInt(idProduct),Integer.parseInt(idOrderText),namaProductText, Integer.parseInt(jumlahText));
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableDetail.getSelectionModel().getSelectedIndex();

        idP = Integer.parseInt(String.valueOf(tableDetail.getItems().get(myIndex).getidProduct()));
        idO = Integer.parseInt(String.valueOf(tableDetail.getItems().get(myIndex).getidOrder()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdProduct.getText());
        System.out.println(txtIdOrder.getText());
        System.out.println(txtNamaProduct.getText());
        System.out.println(txtJumlah.getText());

        if(txtIdProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtIdOrder.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtNamaProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        if(txtJumlah.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idProduct = txtIdProduct.getText();
        String idOrder = txtIdOrder.getText();
        String namaProduct = txtNamaProduct.getText();
        String jumlah = txtJumlah.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editDetail(Integer.parseInt(idProduct),Integer.parseInt(idOrder),namaProduct, Integer.parseInt(jumlah), idP, idO);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableDetail.getSelectionModel().getSelectedIndex();

        idP = Integer.parseInt(String.valueOf(tableDetail.getItems().get(myIndex).getidProduct()));
        idO = Integer.parseInt(String.valueOf(tableDetail.getItems().get(myIndex).getidOrder()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteDetail(idP, idO);
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

        ObservableList<detail> details = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_product, id_order, nama_product, jumlah FROM productorder";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    detail d = new detail();
                    d.setidProduct(rs.getString("id_product"));
                    d.setidOrder(rs.getString("id_order"));
                    d.setnamaProduct(rs.getString("nama_product"));
                    d.setjumlah(rs.getString("jumlah"));
                    details.add(d);
                }
            }
            tableDetail.setItems(details);
            colIdProduct.setCellValueFactory(f -> f.getValue().getIdProductProperty());
            colIdOrder.setCellValueFactory(f -> f.getValue().getIdOrderProperty());
            colNamaProduct.setCellValueFactory(f -> f.getValue().namaProductProperty());
            colJumlah.setCellValueFactory(f -> f.getValue().jumlahProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableDetail.setRowFactory(tv -> {
            TableRow<detail> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableDetail.getSelectionModel().getSelectedIndex();

                    txtIdProduct.setText(tableDetail.getItems().get(myIndex).getidProduct());
                    txtIdOrder.setText(tableDetail.getItems().get(myIndex).getidOrder());
                    txtNamaProduct.setText(tableDetail.getItems().get(myIndex).getnamaProduct());
                    txtJumlah.setText(tableDetail.getItems().get(myIndex).getjumlah());

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
    int idP;
    int idO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
