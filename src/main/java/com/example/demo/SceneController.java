package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void Switchtobranch(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("branch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void Switchtomain(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("registration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoCustomer(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("customer.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoDelivery(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("delivery.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoDetail(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("detail.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoNotaSupplier(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("notasupplier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoOrder(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("order.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoProduct(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("product.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoResi(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("resi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoSupplier(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("supplier.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoTransportasi(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("transportasi.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoWarehouse(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("warehouse.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoTopCust(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("topcust.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoTopProduct(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("topproduct.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void SwitchtoTopWh(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("topwh.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
