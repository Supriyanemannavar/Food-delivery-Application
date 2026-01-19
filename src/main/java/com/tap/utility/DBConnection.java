package com.tap.utility;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {

    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Properties props = new Properties();
                
                // Load the properties file from the classpath
                try (InputStream is = DBConnection.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
                    if (is == null) {
                        throw new RuntimeException("dbconfig.properties file not found in classpath!");
                    }
                    props.load(is);
                }

                // Load MySQL Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Get values from the properties file
                connection = DriverManager.getConnection(
                    props.getProperty("db.url"), 
                    props.getProperty("db.user"), 
                    props.getProperty("db.password")
                );
                
                System.out.println("Connected Successfully: " + connection);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();
    }
}