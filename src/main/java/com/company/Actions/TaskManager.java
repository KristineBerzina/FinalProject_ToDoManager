package com.company.Actions;

import com.company.Menu.Login;
import com.company.Menu.Menu;
import com.company.connectToDB.dbConnection;
import com.mysql.cj.log.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TaskManager extends Login {

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
        category += scanner.nextLine();

        System.out.println("Enter one of the statuses 'Have to do', 'In progress' or 'Done': ");
        String status = scanner.next();
        status += scanner.nextLine();

        System.out.println("Enter the deadline date YYYY-MM-DD (if any): ");
        String deadline = scanner.next();

        try {
            ps = dbConnection.getConnection().prepareStatement("INSERT INTO Tasks(Task, Priority, Category, Status, Deadline, User_ID) " +
                    "VALUES ('" + task + "', '" + priority + "', '" + category + "', '" + status + "', '" + deadline + "', " + currentUserID + ")");
            ps.execute();
            System.out.println("Task added successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to add the task. Please try again.");
            e.printStackTrace();
        }
    }

    public static void editTask() {

        System.out.println("Enter the ID number of the task you wish to edit");
        int id = scanner.nextInt();

        System.out.println("You can now update your: task, priority, category, status, deadline.");

        System.out.println("Enter the field you want to edit: \n");
        String field = scanner.next();

        System.out.println("Enter the updated value: \n");
        String fieldUpdate = scanner.next();
        fieldUpdate += scanner.nextLine();

        try {
            ps = dbConnection.getConnection().prepareStatement("UPDATE Tasks SET " + field + " = '" + fieldUpdate + "' WHERE ID = " + id);
            ps.execute();
            System.out.println("Successfully updated field.");
        } catch (SQLException e) {
            System.out.println("Failed to updated field. Please try again.");
            e.printStackTrace();
        }
    }

    public static void deleteTask() {

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

        System.out.println("Are you sure you wish to clear the archive? Enter 'Y' for yes or 'N' for no.");
        String answer = scanner.next();

        if (answer.equals("y") || answer.equals("Y")) {
            try {
                ps = dbConnection.getConnection().prepareStatement("DELETE FROM Task_archive WHERE User_ID=" + currentUserID);
                ps.execute();

                System.out.println("Task archive cleared successfully.");

            } catch (SQLException e) {
                System.out.println("Could not clear the archive. Try again!");
                e.printStackTrace();
            }
        } else {
            System.out.println("Action cancelled. The archive was not cleared.");
        }
    }

    public static void seeActiveTasksByID() {

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID);
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
    }

    public static void seeTaskArchiveByID() {

        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Task_archive WHERE User_ID=" + currentUserID);
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
    }

    public static void sortByPriority() {
        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " ORDER BY Priority ASC");
            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");
            rs = ps.executeQuery();

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
    }

    public static void sortByCategory() {
        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " ORDER BY Category ASC");
            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");
            rs = ps.executeQuery();

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
    }

    public static void sortByStatus() {
        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " AND Status = 'Have to do'");
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
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " AND Status = 'In progress'");
            rs = ps.executeQuery();

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
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " AND Status = 'Done'");
            rs = ps.executeQuery();

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
            System.out.println("Failed to sort tasks. Please try again!");
            e.printStackTrace();
        }
    }

    public static void sortByDeadline() {
        try {
            ps = dbConnection.getConnection().prepareStatement("SELECT * FROM Tasks WHERE User_ID=" + currentUserID + " ORDER BY Deadline ASC");
            int taskId, priority;
            String task, category, status, deadline;

            System.out.printf("%-10s %-30.30s %-10s %-15.15s %-15.15s %-10s\n", "ID", "Task", "Priority", "Category", "Status", "Deadline");
            rs = ps.executeQuery();

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
    }
}
