package com.conn;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnect {

    private static Connection conn = null;
    private static volatile DBConnect instance = null;

    private DBConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:sqlite:C:/Users/Swapnil/eclipse-workspace/Online Electronic Shopping/mydatabase.db");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConn() {
        return conn;
    }
}
