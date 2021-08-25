package com.company.connectToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11431647", "sql11431647", "xDwxWusqqs");
        }catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
