package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class JdbcDao {
    public static Connection dbLink;
    String dbname = "dbprojectbd";
    String dbUrl = "jdbc:mysql://localhost/" + dbname;
    String dbUser = "root";
    String dbPass = "";

    public void insertData(String fullname, String email, String password) throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO registrasi (full_name, email_id, password) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setString(1, fullname);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public  void insertBranch(int idBranch, String namaBranch, String lokasi) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO branch (id_branch, nama_branch, lokasi) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idBranch);
            preparedStatement.setString(2, namaBranch);
            preparedStatement.setString(3, lokasi);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editBranch(int idBranch, String namaBranch, String lokasi, int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE branch SET id_branch = ?,nama_branch = ? ,lokasi = ? WHERE id_branch = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idBranch);
            preparedStatement.setString(2, namaBranch);
            preparedStatement.setString(3, lokasi);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteBranch(int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM branch WHERE id_branch = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertCustomer(int idCustomer, String namaCustomer, String alamat, String notelp) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO customer (id_customer, nama_customer, alamat, nomor_telepon) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idCustomer);
            preparedStatement.setString(2, namaCustomer);
            preparedStatement.setString(3, alamat);
            preparedStatement.setString(4, notelp);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editCustomer(int idCustomer, String namaCustomer, String alamat, String notelp, int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE customer set id_customer = ?,nama_customer = ? ,alamat = ?, nomor_telepon = ? where id_customer = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idCustomer);
            preparedStatement.setString(2, namaCustomer);
            preparedStatement.setString(3, alamat);
            preparedStatement.setString(4, notelp);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteCustomer(int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM customer WHERE id_customer = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertDelivery(int idDelivery, String jenisDelivery, int idTransportasi) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO delivery (id_delivery , jenis_delivery, id_transportasi) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idDelivery);
            preparedStatement.setString(2, jenisDelivery);
            preparedStatement.setInt(3, idTransportasi);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editDelivery(int idDelivery, String jenisDelivery, int idTransportasi, int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE delivery set id_delivery = ?,jenis_delivery = ? ,id_transportasi = ? where id_delivery = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idDelivery);
            preparedStatement.setString(2, jenisDelivery);
            preparedStatement.setInt(3, idTransportasi);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteDelivery(int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM delivery WHERE id_delivery = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertDetail(int idProduct, int idOrder, String namaProduct, int Jumlah) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO productorder (id_product, id_order, nama_product, jumlah) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.setString(3, namaProduct);
            preparedStatement.setInt(4, Jumlah);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editDetail(int idProduct, int idOrder, String namaProduct, int Jumlah, int idP, int idO) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE productorder set id_product = ?,id_order = ? ,nama_product = ?, jumlah = ? WHERE id_product = ? AND id_order = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setInt(2, idOrder);
            preparedStatement.setString(3, namaProduct);
            preparedStatement.setInt(4, Jumlah);
            preparedStatement.setInt(5, idP);
            preparedStatement.setInt(6, idO);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteDetail(int idP, int idO) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM productorder WHERE id_product = ? AND id_order = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idP);
            preparedStatement.setInt(2, idO);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertNotaSupplier(int idSupplier, int idDelivery, String detail) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO supplierdelivery (id_supplier, id_delivery, detail) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idSupplier);
            preparedStatement.setInt(2, idDelivery);
            preparedStatement.setString(3, detail);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editNotaSupplier(int idSupplier, int idDelivery, String detail, int idS, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE supplierdelivery set id_supplier = ?,id_delivery = ? ,detail = ? where id_supplier = ? AND id_delivery = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idSupplier);
            preparedStatement.setInt(2, idDelivery);
            preparedStatement.setString(3, detail);
            preparedStatement.setInt(4, idS);
            preparedStatement.setInt(5, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteNotaSupplier(int idS, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM supplierdelivery WHERE id_supplier = ? AND id_delivery = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idS);
            preparedStatement.setInt(2, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertOrder(int idOrder, String Tanggal, int idDelivery, int idWarehouse) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO orderr (id_order, tanggal_order, id_delivery, id_warehouse) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setString(2, Tanggal);
            preparedStatement.setInt(3, idDelivery);
            preparedStatement.setInt(4, idWarehouse);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editOrder(int idOrder, String Tanggal, int idDelivery, int idWarehouse, int idO, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE orderr set id_order = ?,tanggal_order = ? ,id_delivery = ?, id_warehouse = ? WHERE id_order = ? AND id_delivery = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idOrder);
            preparedStatement.setString(2, Tanggal);
            preparedStatement.setInt(3, idDelivery);
            preparedStatement.setInt(4, idWarehouse);
            preparedStatement.setInt(5, idO);
            preparedStatement.setInt(6, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteOrder(int idO, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM orderr WHERE id_order = ? AND id_delivery = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idO);
            preparedStatement.setInt(2, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertProduct(int idProduct, String namaProduct, String jenisProduct, int hargaProduct) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO product (id_product, nama_product, jenis_product, harga_product) VALUE (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setString(2, namaProduct);
            preparedStatement.setString(3, jenisProduct);
            preparedStatement.setInt(4, hargaProduct);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editProduct(int idProduct, String namaProduct, String jenisProduct, int hargaProduct, int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE product set id_product = ?,nama_product = ? ,jenis_product = ?, harga_product = ? WHERE id_product = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setString(2, namaProduct);
            preparedStatement.setString(3, jenisProduct);
            preparedStatement.setInt(4, hargaProduct);
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteProduct(int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM product WHERE id_product = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertResi(int idCust, int idDelivery, String total) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO deliverycustomer (id_customer, id_delivery, total) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idCust);
            preparedStatement.setInt(2, idDelivery);
            preparedStatement.setString(3, total);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editResi(int idCust, int idDelivery, String total, int idC, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE deliverycustomer set id_customer = ?,id_delivery = ? ,total = ? where id_customer = ? AND id_delivery = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idCust);
            preparedStatement.setInt(2, idDelivery);
            preparedStatement.setString(3, total);
            preparedStatement.setInt(4, idC);
            preparedStatement.setInt(5, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteResi(int idC, int idD) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM deliverycustomer WHERE id_customer = ? AND id_delivery = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idC);
            preparedStatement.setInt(2, idD);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertSupplier(int id_supplier, String nama_supplier, String lokasi) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO supplier (id_supplier, nama_supplier, lokasi) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id_supplier);
            preparedStatement.setString(2, nama_supplier);
            preparedStatement.setString(3, lokasi);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editSupplier(int id_supplier, String nama_supplier, String lokasi, int idS) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE supplier set id_supplier = ?,nama_supplier = ? ,lokasi = ? where id_supplier = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id_supplier);
            preparedStatement.setString(2, nama_supplier);
            preparedStatement.setString(3, lokasi);
            preparedStatement.setInt(4, idS);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteSupplier(int idS) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM supplier WHERE id_supplier = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idS);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertTransportasi(int id_transportasi, String nomor_lisensi, String jenis_transportasi, int status_keberangkatan, int id_branch) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO transportasi (id_transportasi, nomor_lisensi, jenis_transportasi, status_keberangkatan, id_branch) VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id_transportasi);
            preparedStatement.setString(2, nomor_lisensi);
            preparedStatement.setString(3, jenis_transportasi);
            preparedStatement.setInt(4, status_keberangkatan);
            preparedStatement.setInt(5, id_branch);

            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editTransportasi(int idProduct, String namaProduct, String jenisProduct, int hargaProduct,int id_branch, int idT, int idB) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE transportasi set id_transportasi = ?,nomor_lisensi = ? ,jenis_transportasi = ?, status_keberangkatan = ?, id_branch=? WHERE id_transportasi = ? AND id_branch = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idProduct);
            preparedStatement.setString(2, namaProduct);
            preparedStatement.setString(3, jenisProduct);
            preparedStatement.setInt(4, hargaProduct);
            preparedStatement.setInt(5, id_branch);
            preparedStatement.setInt(6, idT);
            preparedStatement.setInt(7, idB);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteTransportasi(int idT, int idB) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM transportasi WHERE id_transportasi = ? AND id_branch = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, idT);
            preparedStatement.setInt(2, idB);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void insertWarehouse(int id_warehouse, String nama_warehouse, String lokasi) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "INSERT INTO warehouse (id_warehouse, nama_warehouse, lokasi) VALUE (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id_warehouse);
            preparedStatement.setString(2, nama_warehouse);
            preparedStatement.setString(3, lokasi);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void editWarehouse(int id_warehouse, String nama_warehouse, String lokasi, int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "UPDATE warehouse SET id_warehouse = ?,nama_warehouse = ? ,lokasi = ? WHERE id_warehouse = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id_warehouse);
            preparedStatement.setString(2, nama_warehouse);
            preparedStatement.setString(3, lokasi);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public  void deleteWarehouse(int id) throws SQLException{
        try {
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
            String Query = "DELETE FROM warehouse WHERE id_warehouse = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(Query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
