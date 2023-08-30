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

public class topwhController implements Initializable {

    @FXML
    private ImageView backImage;

    @FXML
    private Button btnBack;

    @FXML
    private TableColumn<warehouse, String> colIdWh;

    @FXML
    private TableColumn<warehouse, String> colLokasiWh;

    @FXML
    private TableColumn<warehouse, String> colNamaWh;

    @FXML
    private TableView<warehouse> tableWarehouse;


    public void table() {
        JdbcDao jdbcDao = new JdbcDao();

        ObservableList<warehouse> warehouses = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String query = "SELECT w.id_warehouse, w.nama_warehouse, w.lokasi, COUNT(b.id_branch) AS branch_count " +
                    "FROM warehouse w " +
                    "LEFT JOIN branch b ON w.id_warehouse = b.id_warehouse " +
                    "GROUP BY w.id_warehouse " +
                    "ORDER BY branch_count DESC " +
                    "LIMIT 3";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                warehouse warehouse = new warehouse();
                warehouse.setId(rs.getString("id_warehouse"));
                warehouse.setNama(rs.getString("nama_warehouse"));
                warehouse.setLokasi(rs.getString("lokasi"));
                warehouses.add(warehouse);
            }
            tableWarehouse.setItems(warehouses);
            colIdWh.setCellValueFactory(f -> f.getValue().idProperty());
            colNamaWh.setCellValueFactory(f -> f.getValue().namaProperty());
            colLokasiWh.setCellValueFactory(f -> f.getValue().lokasiProperty());
        } catch (SQLException ex) {
            Logger.getLogger(warehouseController.class.getName()).log(Level.SEVERE, null, ex);
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
