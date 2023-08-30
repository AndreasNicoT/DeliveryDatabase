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

public class productController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<product, String> colHarga;

    @FXML
    private TableColumn<product, String> colIdProduct;

    @FXML
    private TableColumn<product, String> colJenisProduct;

    @FXML
    private TableColumn<product, String> colNamaProduct;

    @FXML
    private TableView<product> tableProduct;

    @FXML
    private TextField txtHarga;

    @FXML
    private TextField txtIdProduct;

    @FXML
    private TextField txtJenisProduct;

    @FXML
    private TextField txtNamaProduct;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdProduct.getText());
        System.out.println(txtNamaProduct.getText());
        System.out.println(txtJenisProduct.getText());
        System.out.println(txtHarga.getText());


        if(txtIdProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtNamaProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "TANGGAL BELUM TERISI");
            return;
        }
        if(txtJenisProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtHarga.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idProductText = txtIdProduct.getText();
        String namaProductText = txtNamaProduct.getText();
        String jenisProductText = txtJenisProduct.getText();
        String hargaText = txtHarga.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertProduct(Integer.parseInt(idProductText), namaProductText,jenisProductText, Integer.parseInt(hargaText));
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableProduct.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableProduct.getItems().get(myIndex).getidProduct()));


        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdProduct.getText());
        System.out.println(txtNamaProduct.getText());
        System.out.println(txtJenisProduct.getText());
        System.out.println(txtHarga.getText());

        if(txtIdProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtNamaProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "TANGGAL BELUM TERISI");
            return;
        }
        if(txtJenisProduct.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(txtHarga.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idProductText = txtIdProduct.getText();
        String namaProductText = txtNamaProduct.getText();
        String jenisProductText = txtJenisProduct.getText();
        String hargaText = txtHarga.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editProduct(Integer.parseInt(idProductText), namaProductText,jenisProductText, Integer.parseInt(hargaText), id);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableProduct.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableProduct.getItems().get(myIndex).getidProduct()));


        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteProduct(id);
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

        ObservableList<product> products = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_product, nama_product, jenis_product, harga_product FROM product";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    product p = new product();
                    p.setidProduct(rs.getString("id_product"));
                    p.setnamaProduct(rs.getString("nama_product"));
                    p.setjenisProduct(rs.getString("jenis_product"));
                    p.sethargaProduct(rs.getString("harga_product"));
                    products.add(p);
                }
            }
            tableProduct.setItems(products);
            colIdProduct.setCellValueFactory(f -> f.getValue().idProductProperty());
            colNamaProduct.setCellValueFactory(f -> f.getValue().namaProductProperty());
            colJenisProduct.setCellValueFactory(f -> f.getValue().jenisProductProperty());
            colHarga.setCellValueFactory(f -> f.getValue().hargaProductProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableProduct.setRowFactory(tv -> {
            TableRow<product> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableProduct.getSelectionModel().getSelectedIndex();

                    txtIdProduct.setText(tableProduct.getItems().get(myIndex).getidProduct());
                    txtNamaProduct.setText(tableProduct.getItems().get(myIndex).getnamaProduct());
                    txtJenisProduct.setText(tableProduct.getItems().get(myIndex).getjenisProduct());
                    txtHarga.setText(tableProduct.getItems().get(myIndex).gethargaProduct());

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
