package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DAO {
    public void insertdata(){
        try{
            Connection koneksi = DB.getConnection();
            PreparedStatement preparedquery = koneksi.prepareStatement("INSERT INTO mahasiswa (`nama`, `jurusan`) VALUE('aurum', 'infor')");
            preparedquery.executeUpdate();
            System.out.println("SUKSES INSERT");
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("GAGAL");
        }
    }

}
