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

public class topProductController implements Initializable {

    @FXML
    private ImageView backImage;

    @FXML
    private Button btnBack;

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


    public void table() {
        JdbcDao jdbcDao = new JdbcDao();

        ObservableList<product> products = FXCollections.observableArrayList();
        try {
            Connection connection = DriverManager.getConnection(jdbcDao.dbUrl, jdbcDao.dbUser, jdbcDao.dbPass);
            String query = "SELECT p.id_product, p.nama_product, p.jenis_product, p.harga_product, COUNT(pd.id_product) AS record_count " +
                    "FROM product p " +
                    "LEFT JOIN productdelivery pd ON p.id_product = pd.id_product " +
                    "GROUP BY p.id_product, p.nama_product, p.jenis_product, p.harga_product " +
                    "ORDER BY record_count DESC " +
                    "LIMIT 3";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
