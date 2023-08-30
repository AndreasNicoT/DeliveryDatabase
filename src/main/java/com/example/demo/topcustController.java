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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class topcustController implements Initializable {

    @FXML
    private ImageView backImage;

    @FXML
    private Button btnBack;

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

    public void table() {
        JdbcDao jdbcDao = new JdbcDao();

        ObservableList<customer> customers = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String query = "SELECT c.id_customer, c.nama_customer, c.alamat, c.nomor_telepon, COUNT(dc.id_delivery) AS total_deliveries " +
                    "FROM customer c " +
                    "LEFT JOIN deliverycustomer dc ON c.id_customer = dc.id_customer " +
                    "GROUP BY c.id_customer, c.nama_customer, c.alamat, c.nomor_telepon " +
                    "ORDER BY total_deliveries DESC " +
                    "LIMIT 3";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                customer c = new customer();
                c.setId(rs.getString("id_customer"));
                c.setNama(rs.getString("nama_customer"));
                c.setAlamat(rs.getString("alamat"));
                c.setNotelp(rs.getString("nomor_telepon"));
                customers.add(c);
            }

            tableCustomer.setItems(customers);
            colIdCust.setCellValueFactory(f -> f.getValue().idProperty());
            colNamaCust.setCellValueFactory(f -> f.getValue().namaProperty());
            colAlamat.setCellValueFactory(f -> f.getValue().alamatProperty());
            colNoTelp.setCellValueFactory(f -> f.getValue().notelpProperty());

        } catch (SQLException ex) {
            Logger.getLogger(branchController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table();
    }
}
