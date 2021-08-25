package com.company.Actions;

import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static void deleteTask() {

        //print out all tasks for user to see

        System.out.print("Enter the ID number of the task you wish to remove: ");
        int id = scanner.nextInt();

        // ask user: are you sure you wish to delete this task?

        try {
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM Tasks WHERE ID=" + id);
            ps.execute();

            System.out.println("Task removed successfully");

        } catch (SQLException e) {
            System.out.println("Could not remove the chosen task. Try again!");
            e.printStackTrace();
        }

    }

    public static void completeAndArchiveTask() {

        //print out all the tasks for user to see

        System.out.print("Enter the completed task's ID number: ");
        int id = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO Task_archive SELECT * FROM Tasks WHERE ID=" + id);
            ps.execute();
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM Tasks WHERE ID=" + id);
            ps.execute();

            System.out.println("Successfully completed and archived the chosen task.");

        } catch (SQLException e) {
            System.out.println("Failed to complete and archive the chosen task. Please try again!");
            e.printStackTrace();
        }
    }

    public static void returnTaskFromArchive() {
        //print out all the archived tasks for user to see

        System.out.print("Enter the archived task's ID number: ");
        int id = scanner.nextInt();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO Tasks SELECT * FROM Task_archive WHERE ID=" + id);
            ps.execute();
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM Task_archive WHERE ID=" + id);
            ps.execute();

            System.out.println("Successfully returned the chosen task to the list of active tasks.");

        } catch (SQLException e) {
            System.out.println("Failed to pull the chosen task from the archive. Please try again!");
            e.printStackTrace();
        }
    }

    public static void clearArchive(){
        //print out all archived tasks for user to see

        // ask user: are you sure you wish to clear the archive?

        try {
            ps = dbConnection.getConnection().prepareStatement("DELETE FROM Task_archive");
            ps.execute();

            System.out.println("Task archive cleared successfully");

        } catch (SQLException e) {
            System.out.println("Could not clear the archive. Try again!");
            e.printStackTrace();
        }
    }
}
