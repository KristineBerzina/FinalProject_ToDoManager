package com.company.Menu;

import com.company.Actions.TaskManager;
import com.company.Objects.Task;

import java.util.Scanner;

public class Menu {

    public static void startMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("You have opened your To Do Manager. What would you like to do next?");
        System.out.println(" ");
        System.out.println("1. Sign up and start using the program");
        System.out.println("2. Log in");
        System.out.println("3. Close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");

        if (scanner.hasNextInt()){
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    Login.userSignUp();
                    Menu.mainMenu();
                    break;
                case 2:
                    Login.login();
                    Menu.mainMenu();
                    break;
                case 3:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.startMenu1();
            }
        }else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.startMenu1();
        }
    }

    public static void startMenu1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("1. Sign up and start using the program");
        System.out.println("2. Log in");
        System.out.println("3. Close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");

        if (scanner.hasNextInt()){
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    Login.userSignUp();
                    Menu.mainMenu();
                    break;
                case 2:
                    Login.login();
                    Menu.mainMenu();
                    break;
                case 3:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.startMenu1();
            }
        }else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.startMenu1();
        }
    }

    public static void mainMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("What would you like to do next?");
        System.out.println(" ");
        System.out.println("1. Go to task menu");
        System.out.println("2. Go to task archive menu");
        System.out.println("3. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    Menu.taskMenu1();
                    break;
                case 2:
                    Menu.archiveMenu();
                    break;
                case 3:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.mainMenu1();
            }
        } else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.mainMenu1();
        }
    }

    public static void mainMenu1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("1. Go to task menu");
        System.out.println("2. Go to task archive menu");
        System.out.println("3. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    Menu.taskMenu1();
                    break;
                case 2:
                    Menu.archiveMenu();
                    break;
                case 3:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.mainMenu1();
            }
        } else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.mainMenu1();
        }
    }

    public static void taskMenu1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("HERE ARE YOUR ACTIVE TASKS:");
        TaskManager.seeActiveTasksByID();
        System.out.println(" ");
        System.out.println("What would you like to do next?");
        System.out.println(" ");
        System.out.println("1. Add new task");
        System.out.println("2. Edit existing task");
        System.out.println("3. Delete a task");
        System.out.println("4. Complete and archive a task");
        System.out.println("5. Sort tasks by priority");
        System.out.println("6. Sort tasks by category");
        System.out.println("7. Sort tasks by status");
        System.out.println("8. Sort tasks by deadline");
        System.out.println("9. Return to the main menu");
        System.out.println("10. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    TaskManager.addNewTask();
                    Menu.taskMenu1();
                    break;
                case 2:
                    TaskManager.editTask();
                    Menu.taskMenu1();
                    break;
                case 3:
                    TaskManager.deleteTask();
                    Menu.taskMenu1();
                    break;
                case 4:
                    TaskManager.completeAndArchiveTask();
                    Menu.taskMenu1();
                    break;
                case 5:
                    TaskManager.sortByPriority();
                    Menu.taskMenu2();
                    break;
                case 6:
                    TaskManager.sortByCategory();
                    Menu.taskMenu2();
                    break;
                case 7:
                    TaskManager.sortByStatus();
                    Menu.taskMenu2();
                    break;
                case 8:
                    TaskManager.sortByDeadline();
                    Menu.taskMenu2();
                    break;
                case 9:
                    Menu.mainMenu();
                    break;
                case 10:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.taskMenu2();
            }
        } else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.taskMenu2();
        }
    }

    public static void taskMenu2() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("1. Add new task");
        System.out.println("2. Edit existing task");
        System.out.println("3. Delete a task");
        System.out.println("4. Complete and archive a task");
        System.out.println("5. Sort tasks by priority");
        System.out.println("6. Sort tasks by category");
        System.out.println("7. Sort tasks by status");
        System.out.println("8. Sort tasks by deadline");
        System.out.println("9. Return to the main menu");
        System.out.println("10. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    TaskManager.addNewTask();
                    Menu.taskMenu1();
                    break;
                case 2:
                    TaskManager.editTask();
                    Menu.taskMenu1();
                    break;
                case 3:
                    TaskManager.deleteTask();
                    Menu.taskMenu1();
                    break;
                case 4:
                    TaskManager.completeAndArchiveTask();
                    Menu.taskMenu1();
                    break;
                case 5:
                    TaskManager.sortByPriority();
                    Menu.taskMenu2();
                    break;
                case 6:
                    TaskManager.sortByCategory();
                    Menu.taskMenu2();
                    break;
                case 7:
                    TaskManager.sortByStatus();
                    Menu.taskMenu2();
                    break;
                case 8:
                    TaskManager.sortByDeadline();
                    Menu.taskMenu2();
                    break;
                case 9:
                    Menu.mainMenu();
                    break;
                case 10:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.taskMenu2();
            }
        }else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.taskMenu2();
        }
    }

    public static void archiveMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("HERE IS YOUR TASK ARCHIVE:");
        TaskManager.seeTaskArchiveByID();
        System.out.println(" ");
        System.out.println("What would you like to do next?");
        System.out.println(" ");
        System.out.println("1. Return a task from archive");
        System.out.println("2. Clear all tasks from archive");
        System.out.println("3. Return to the main menu");
        System.out.println("4. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    TaskManager.returnTaskFromArchive();
                    Menu.archiveMenu();
                    break;
                case 2:
                    TaskManager.clearArchive();
                    System.out.println("You have been redirected to the main menu.");
                    Menu.mainMenu();
                    break;
                case 3:
                    Menu.mainMenu();
                    break;
                case 4:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.archiveMenu1();
            }
        } else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.archiveMenu1();
        }
    }

    public static void archiveMenu1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ");
        System.out.println("1. Return a task from archive");
        System.out.println("2. Clear all tasks from archive");
        System.out.println("3. Return to the main menu");
        System.out.println("4. Log out and close the program");
        System.out.println(" ");

        System.out.print("Select an option by entering its number: ");
        if (scanner.hasNextInt()) {
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    TaskManager.returnTaskFromArchive();
                    Menu.archiveMenu();
                    break;
                case 2:
                    TaskManager.clearArchive();
                    System.out.println("You have been redirected to the main menu.");
                    Menu.mainMenu();
                    break;
                case 3:
                    Menu.mainMenu();
                    break;
                case 4:
                    System.out.println("You have cancelled any further actions and closed the program.");
                    break;
                default:
                    System.out.println("INVALID OPTION. Please select another number.");
                    Menu.archiveMenu1();
            }
        } else {
            System.out.println(" ");
            System.out.println("INVALID INPUT. Please select a number.");
            Menu.archiveMenu1();
        }
    }
}
