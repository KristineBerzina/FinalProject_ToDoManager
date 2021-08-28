package com.company.Actions;

import com.company.Menu.Login;
import com.company.Menu.Menu;
import com.company.connectToDB.dbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager {

    private static PreparedStatement ps;
    private static ResultSet rs;
    private static Scanner scanner = new Scanner(System.in);

    public static void addNewTask() {

        System.out.println("Please enter the new task (max. 500 symbols): ");
        String task = scanner.next();
        task += scanner.nextLine();

        System.out.println("Enter the task priority number 1, 2 or 3: ");
        System.out.println("NOTE: priority number 1 - high importance, 2 - medium importance, or 3 - low importance.");
        int priority = scanner.nextInt();

        System.out.println("Enter the task category: "); // user can create his own category if needed e.g.: shopping, holidays, work etc.
        String category = scanner.next();

        System.out.println("Enter one of the statuses 'Have to do', 'In progress' or 'Done': ");
        String status = scanner.next();
        status += scanner.nextLine();

        System.out.println("Enter the date YYYY-MM-DD (deadline) till when task has to be finished (if any): ");
        String deadline = scanner.next();

        try {
            //have to define user ID from login data
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO Tasks(Task, Priority, Category, Status, Deadline) " +
                    "VALUES ('" + task + "', '" + priority + "', '" + category + "', '" + status + "', '" + deadline + "')");
            ps.execute();
            System.out.println("Task added. Do you wish to: ");
            System.out.println(" ");
            System.out.println("1. Add another task; ");
            System.out.println("2. Return to the main menu; ");
            System.out.println("3. Log out and close the program? ");
            System.out.println(" ");

            System.out.print("Select an option by entering its number: ");
            int option = scanner.nextInt();

            switch(option) {
                case 1:
                    TaskManager.addNewTask();
                    break;
                case 2:
                    Menu.mainMenu();
                    break;
                case 3:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("Invalid option. You have been redirected to the main menu.");
                    Menu.mainMenu();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteTask() {

        int userId = 1; // this has to be removed, id has to be taken from login data.

        System.out.println("Here are your current tasks: ");

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + userId);
            rs = ps.executeQuery();

            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");

            while(rs.next()) {
                taskId = rs.getInt("ID");
                task = rs.getString("Task");
                priority = rs.getInt("Priority");
                category = rs.getString("Category");
                status = rs.getString("Status");
                deadline = rs.getString("Deadline");

                System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", taskId, task, priority, category, status, deadline);
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the ID number of the task you wish to remove: ");
        int id = scanner.nextInt();

        System.out.print("Are you sure you wish to delete this task? Enter 'Y' for yes or 'N' for no. ");
        String answer = scanner.next();

        if (answer.equals("y") || answer.equals("Y")) {
            try {
                ps = dbConnection.getConnection().prepareStatement("DELETE FROM Tasks WHERE ID=" + id);
                ps.execute();

                System.out.println("Task removed successfully");

            } catch (SQLException e) {
                System.out.println("Could not remove the chosen task. Try again!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Action cancelled. The task was not deleted.");
        }
    }

    public static void completeAndArchiveTask() {

        int userId = 1; // this has to be removed, id has to be taken from login data.

        System.out.println("Here are your current tasks: ");

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + userId);
            rs = ps.executeQuery();

            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");

            while(rs.next()) {
                taskId = rs.getInt("ID");
                task = rs.getString("Task");
                priority = rs.getInt("Priority");
                category = rs.getString("Category");
                status = rs.getString("Status");
                deadline = rs.getString("Deadline");

                System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", taskId, task, priority, category, status, deadline);
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

        int userId = 1; // this has to be removed, id has to be taken from login data.

        System.out.println("Here are your archived tasks: ");

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Task_archive WHERE User_ID=" + userId);
            rs = ps.executeQuery();

            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");

            while(rs.next()) {
                taskId = rs.getInt("ID");
                task = rs.getString("Task");
                priority = rs.getInt("Priority");
                category = rs.getString("Category");
                status = rs.getString("Status");
                deadline = rs.getString("Deadline");

                System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", taskId, task, priority, category, status, deadline);
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.print("Enter the ID number of the task you wish to pull back from archive: ");
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

        int userId = 1; // this has to be removed, id has to be taken from login data.

        System.out.println("Here are your archived tasks: ");

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Task_archive WHERE User_ID=" + userId);
            rs = ps.executeQuery();

            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");

            while(rs.next()) {
                taskId = rs.getInt("ID");
                task = rs.getString("Task");
                priority = rs.getInt("Priority");
                category = rs.getString("Category");
                status = rs.getString("Status");
                deadline = rs.getString("Deadline");

                System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", taskId, task, priority, category, status, deadline);
                System.out.println(" ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Are you sure you wish to clear the archive? Enter Y for yes or N for no.");
        String answer = scanner.next();

        if (answer.equals("y") || answer.equals("Y")) {
            try {
                ps = dbConnection.getConnection().prepareStatement("DELETE FROM Task_archive");
                ps.execute();

                System.out.println("Task archive cleared successfully");

            } catch (SQLException e) {
                System.out.println("Could not clear the archive. Try again!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Action cancelled. The archive was not cleared.");
        }
    }
}
