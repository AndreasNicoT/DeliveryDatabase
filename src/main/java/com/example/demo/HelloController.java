package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

//implements Initializable  v
public class HelloController {

//    private DAO dao;
//    @FXML
//    private Label welcomeText;
//
//    @FXML
//    protected void onHelloButtonClick() {
//        dao.insertdata();
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle){
//        dao = new DAO();
//    }
    @FXML
    private TextField fullnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button submitButton;

    @FXML
    public void register() throws SQLException{
        Window owner = submitButton.getScene().getWindow();
        System.out.println(fullnameField.getText());
        System.out.println(emailField.getText());
        System.out.println(passwordField.getText());


        if(fullnameField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "NAMA BELUM TERISI");
            return;
        }
        if(emailField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "EMAIL BELUM TERISI");
            return;
        }
        if(passwordField.getText().isEmpty()){
            showAlert(Alert.AlertType.ERROR,owner,"FORM ERROR!!!", "PASSWORD BELUM TERISI");
            return;
        }
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();

        JdbcDao jdbcDao = new JdbcDao();
        jdbcDao.insertData(fullname,email,password);

        showAlert(Alert.AlertType.CONFIRMATION,owner,"REGISTRATION SUCCESSFUL", "WELCOME" + fullname);
    }
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message){
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}