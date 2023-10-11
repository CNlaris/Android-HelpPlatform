package com.potech.helpform.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnOpen {
    private static ConnOpen instance;
    Connection con;
    public static ConnOpen getInstance(){
        if (instance ==null){
            instance = new ConnOpen();
        }
        return instance;
    }

    public Connection getConnection(String dbName,String name,String password) {
        con = null;
        Thread a = new Thread(){
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://123.56.90.187:3306/"+dbName;
                    con = DriverManager.getConnection(url,name,password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        a.start();
        try{
            a.join(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (con !=null){
            return con;
        }else{
            Log.v("FATAL","连接失败");
            return null;
        }
    }
    public Connection getConnection(String dbname){
        Connection con = getConnection(dbname,"helpform","e7X4c4dfjGa3Fb6s");
        return con;
    }
}