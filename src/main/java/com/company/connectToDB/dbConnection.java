package com.company.connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/finalproject6", "java_group6", "SGT_group6");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
