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

public class transportasiController implements Initializable {
    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<transportasi, String> colIdBranch;

    @FXML
    private TableColumn<transportasi, String> colIdTranspor;

    @FXML
    private TableColumn<transportasi, String> colJenisTranspor;

    @FXML
    private TableColumn<transportasi, String> colLisensi;

    @FXML
    private TableColumn<transportasi, String> colStatusDeliv;

    @FXML
    private TableView<transportasi> tableTransportasi;

    @FXML
    private TextField statusDeliv;

    @FXML
    private TextField txtIdBranch;

    @FXML
    private TextField txtIdTranspor;

    @FXML
    private TextField txtJenisTranspor;

    @FXML
    private TextField txtLisensi;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdTranspor.getText());
        System.out.println(txtLisensi.getText());
        System.out.println(txtJenisTranspor.getText());
        System.out.println(statusDeliv.getText());
        System.out.println(txtIdBranch.getText());


        if(txtIdTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtLisensi.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "TANGGAL BELUM TERISI");
            return;
        }
        if(txtJenisTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(statusDeliv.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        if(txtIdBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idTransporText = txtIdTranspor.getText();
        String lisensiText = txtLisensi.getText();
        String jenisTransporText = txtJenisTranspor.getText();
        String statusDelivText = statusDeliv.getText();
        String idBranchText = txtIdBranch.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertTransportasi(Integer.parseInt(idTransporText), lisensiText,jenisTransporText, Integer.parseInt(statusDelivText),Integer.parseInt(idBranchText));
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableTransportasi.getSelectionModel().getSelectedIndex();

        idT = Integer.parseInt(String.valueOf(tableTransportasi.getItems().get(myIndex).getid_transportasi()));
        idB = Integer.parseInt(String.valueOf(tableTransportasi.getItems().get(myIndex).getid_branch()));


        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdTranspor.getText());
        System.out.println(txtLisensi.getText());
        System.out.println(txtJenisTranspor.getText());
        System.out.println(statusDeliv.getText());
        System.out.println(txtIdBranch.getText());

        if(txtIdTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID ORDER BELUM TERISI");
            return;
        }
        if(txtLisensi.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "TANGGAL BELUM TERISI");
            return;
        }
        if(txtJenisTranspor.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID DELIVERY BELUM TERISI");
            return;
        }
        if(statusDeliv.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        if(txtIdBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "ID WH BELUM TERISI");
            return;
        }
        String idTransporText = txtIdTranspor.getText();
        String lisensiText = txtLisensi.getText();
        String jenisTransporText = txtJenisTranspor.getText();
        String statusDelivText = statusDeliv.getText();
        String idBranchText = txtIdBranch.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editTransportasi(Integer.parseInt(idTransporText), lisensiText,jenisTransporText, Integer.parseInt(statusDelivText),Integer.parseInt(idBranchText), idT, idB);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableTransportasi.getSelectionModel().getSelectedIndex();

        idT = Integer.parseInt(String.valueOf(tableTransportasi.getItems().get(myIndex).getid_transportasi()));
        idB = Integer.parseInt(String.valueOf(tableTransportasi.getItems().get(myIndex).getid_branch()));


        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteTransportasi(idT, idB);
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

        ObservableList<transportasi> products = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_transportasi, nomor_lisensi, jenis_transportasi, status_keberangkatan, id_branch FROM transportasi";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    transportasi t = new transportasi();
                    t.setid_transportasi(rs.getString("id_transportasi"));
                    t.setnomor_lisensi(rs.getString("nomor_lisensi"));
                    t.setjenis_transportasi(rs.getString("jenis_transportasi"));
                    t.setstatus_keberangkatan(rs.getString("status_keberangkatan"));
                    t.setId_branch(rs.getString("id_branch"));
                    products.add(t);
                }
            }
            tableTransportasi.setItems(products);
            colIdTranspor.setCellValueFactory(f -> f.getValue().id_transportasiProperty());
            colLisensi.setCellValueFactory(f -> f.getValue().nomor_lisensiProperty());
            colJenisTranspor.setCellValueFactory(f -> f.getValue().jenis_transportasiProperty());
            colStatusDeliv.setCellValueFactory(f -> f.getValue().status_keberangkatanProperty());
            colIdBranch.setCellValueFactory(f -> f.getValue().id_branchProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableTransportasi.setRowFactory(tv -> {
            TableRow<transportasi> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableTransportasi.getSelectionModel().getSelectedIndex();

                    txtIdTranspor.setText(tableTransportasi.getItems().get(myIndex).getid_transportasi());
                    txtLisensi.setText(tableTransportasi.getItems().get(myIndex).getnomor_lisensi());
                    txtJenisTranspor.setText(tableTransportasi.getItems().get(myIndex).getjenis_transportasit());
                    statusDeliv.setText(tableTransportasi.getItems().get(myIndex).getstatus_keberangkatan());
                    txtIdBranch.setText(tableTransportasi.getItems().get(myIndex).getid_branch());

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
    int idT;
    int idB;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
