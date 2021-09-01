package com.company.Menu;

import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private static Scanner scanner = new Scanner(System.in);
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static int currentUserID;

    public static void userSignUp() {

        System.out.println(" ");
        System.out.println("You are about to sign up for the To Do Manager.");
        System.out.println(" ");
        System.out.print("Please choose and enter your username: ");
        String login = scanner.next().trim();
        System.out.print("Please choose and enter your password: ");
        String password = scanner.next().trim();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO Users(Login, Password)" +
                    " VALUES('" + login + "', '" + password + "')");
            ps.execute();
            System.out.println(" ");
            System.out.println("ACCOUNT CREATED.");
            System.out.println(" ");
            System.out.println("You can now log into your account.");
            Login.login();

        } catch (SQLException e) {
            System.out.println(" ");
            System.out.println("UNABLE TO SIGN UP. Username probably already exists. Please try again.");
            Menu.startMenu();
        }
    }

    public static int login() {

        System.out.print("To log into your account, enter your username: ");
        String login = scanner.next().trim();

        System.out.print("Enter your password: ");
        String password = scanner.next().trim();

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Users WHERE Login='" + login + "'");
            rs = ps.executeQuery();

            String passwordCheck = "";
            while(rs.next()) {
                passwordCheck = rs.getString("Password");
            }
            if(passwordCheck.equals(password)){
                System.out.println(" ");
                System.out.println("ACCESS GRANTED.");

                ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Users WHERE Login='" + login + "' AND Password='" + password + "'");
                rs = ps.executeQuery();
                while (rs.next()){
                    currentUserID = rs.getInt("ID");
                }

            } else {
                System.out.println(" ");
                System.out.println("Unable to login. Username or password is incorrect. Please try again. ");
                Login.login();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return currentUserID;
    }


}
