package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;


public class DB {
    public static Connection dbLink;
    public static Connection getConnection(){
        String dbName="tes";
        String username="root";
        String password="";
        String url="jdbc:mysql://localhost/" + dbName;

        try{
            dbLink= DriverManager.getConnection(url,username,password);
            System.out.println("Koneksi tersambung");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Tidak tersambung");
        }
        return dbLink;
    }
}
