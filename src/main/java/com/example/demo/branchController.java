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

public class branchController implements Initializable {

    @FXML
    private ImageView backImage;

    @FXML
    private Button btnAdd;


    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<branch, String>colIdBranch;

    @FXML
    private TableColumn<branch, String> colLokasiBranch;

    @FXML
    private TableColumn<branch, String> colNamaBranch;

    @FXML
    private TableView<branch> tableBranch;

    @FXML
    private TextField txtIdBranch;

    @FXML
    private TextField txtLokasiBranch;

    @FXML
    private TextField txtNamaBranch;

    @FXML
    public void Add() throws SQLException {
        Window owner = btnAdd.getScene().getWindow();
        System.out.println(txtIdBranch.getText());
        System.out.println(txtNamaBranch.getText());
        System.out.println(txtLokasiBranch.getText());


        if(txtIdBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtLokasiBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idBranch = txtIdBranch.getText();
        String namaBranch = txtNamaBranch.getText();
        String lokasi = txtLokasiBranch.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertBranch(Integer.parseInt(idBranch),namaBranch,lokasi);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"INPUT SUCCESSFUL", "DATA BERHASIL DIMASUKKAN");
    }
    @FXML
    void Update() throws SQLException{

        myIndex = tableBranch.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableBranch.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();
        System.out.println(txtIdBranch.getText());
        System.out.println(txtNamaBranch.getText());
        System.out.println(txtLokasiBranch.getText());


        if(txtIdBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(txtNamaBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(txtLokasiBranch.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String idBranch = txtIdBranch.getText();
        String namaBranch = txtNamaBranch.getText();
        String lokasi = txtLokasiBranch.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.editBranch(Integer.parseInt(idBranch),namaBranch,lokasi, id);
        table();

        showAlert(Alert.AlertType.CONFIRMATION,owner,"UPDATE SUCCESSFUL", "DATA BERHASIL DIEDIT");
    }
    @FXML
    void Delete() throws SQLException {
        myIndex = tableBranch.getSelectionModel().getSelectedIndex();

        id = Integer.parseInt(String.valueOf(tableBranch.getItems().get(myIndex).getId()));

        Window owner = btnUpdate.getScene().getWindow();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.deleteBranch(id);
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

        ObservableList<branch> branches = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String Query = "SELECT id_branch, nama_branch, lokasi FROM branch";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            ResultSet rs = preparedStatement.executeQuery();
            {
                while (rs.next()) {
                    branch b = new branch();
                    b.setId(rs.getString("id_branch"));
                    b.setNama(rs.getString("nama_branch"));
                    b.setLokasi(rs.getString("lokasi"));
                    branches.add(b);
                }
            }
            tableBranch.setItems(branches);
            colIdBranch.setCellValueFactory(f -> f.getValue().idProperty());
            colNamaBranch.setCellValueFactory(f -> f.getValue().namaProperty());
            colLokasiBranch.setCellValueFactory(f -> f.getValue().lokasiProperty());


        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableBranch.setRowFactory(tv -> {
            TableRow<branch> myRow = new TableRow<>();
            myRow.setOnMouseClicked(event ->
            {
                if (event.getClickCount() == 1 && (!myRow.isEmpty())) {
                    myIndex = tableBranch.getSelectionModel().getSelectedIndex();

                    txtIdBranch.setText(tableBranch.getItems().get(myIndex).getId());
                    txtNamaBranch.setText(tableBranch.getItems().get(myIndex).getNama());
                    txtLokasiBranch.setText(tableBranch.getItems().get(myIndex).getLokasi());

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
