package com.kiko.web;

import java.util.*;


/**
 * Task.java
 * This is the main class of the ToDo App. It handles the menu and displays it to the user
 * IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS
 * IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS
 * IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS
 * IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS - IGNORE THIS CLASS.
 *
 * @author Diego Cordero
 * @version 1
 * @date February 18, 2024
 * @course CEN 4025 - Software Development 2
 *
 */
public class ToDoMain {

    static ToDoList toDo = new ToDoList();

    /**
     *
     * The main method calls the displayMenu() method on loop until the user desires to quit the program.
     *
     * @param args String args[]
     */
    public static void main (String args[]){

        Scanner sc = new Scanner(System.in);
        int choice = -1;
        do {
            displayMenu();
            try {
                choice = sc.nextInt();

            } catch (Exception e) {
                menuError();
                sc.nextLine();
                continue;
            }
            System.out.println();
            if (choice != 0 && choice != -1){
                menuSwitch(choice,sc);
            }
        } while (choice != 0);
        sc.close();
    }

    /**
     * Prints the menu.
     */
    public static void displayMenu(){
        System.out.println("\nTask manager:\n");
        System.out.println("1. Add task");
        System.out.println("2. Delete task");
        System.out.println("3. Change task status");
        System.out.println("4. Print tasks");
        System.out.println("0. Quit program\n");
        System.out.print("Enter your choice: ");
    }

    /**
     *
     * Handles the menu.
     *
     * @param sc        Scanner that is used to get the user's input for navigating the menu.
     * @param choice    The user's choice.
     */
    public static void menuSwitch(int choice, Scanner sc){
        switch (choice) {
            case 1:
                String temp = addTaskInput(sc);
                String arr[] = temp.split("`");
                toDo.addTask(arr[0], arr[1], Integer.valueOf(arr[2]));
                System.out.println("Added task successfully.");
                break;

            case 2:
                modifyTaskInput(true, sc);
                break;

            case 3:
                modifyTaskInput(false, sc);
                break;

            case 4:
                toDo.printList();
                break;

            default:
                menuError();
                break;
        }
    }

    /**
     * Prints error message.
     */
    public static void menuError(){
        System.out.println("\nEnter a valid choice");
    }

    /**
     *
     * Gets the user input required for adding a task.
     *
     * @param sc   Scanner that is used to get the user's input for the task that is going to be added.
     */
    public static String addTaskInput(Scanner sc){

        sc.nextLine();

        String name, desc, importance;

        Boolean loop = true;

        System.out.print("Enter task name: ");
        name = sc.nextLine();
        System.out.println();

        System.out.print("Enter task description: ");
        desc = sc.nextLine();
        System.out.println();

        do {
            System.out.print("Enter task importance: ");
            importance = sc.nextLine();
            System.out.println();
            try {
                if ((Integer.parseInt(importance) >= 1 && Integer.parseInt(importance) <= 10)) {
                    loop = false;
                }
                else{
                    System.out.println("Enter a number between 1-10");
                }

            } catch (Exception e) {
                System.out.println("Enter a valid number between 1-10");
            }
        } while (loop);

        return name + "`" + desc + "`" + importance;
    }

    /**
     *
     * Gets the user input required for modifying/deleting a task.
     *
     * @param sc   Scanner that is used to get the user's input for the task that is going to be modified/deleted.
     */
    public static void modifyTaskInput(boolean delete, Scanner sc){
        sc.nextLine();

        int id;

        if (delete){
            System.out.print("Enter task's id: ");
            id = sc.nextInt();
            System.out.println();
            toDo.deleteTask(id);
        }
        else {
            System.out.print("Enter task's id: ");
            id = sc.nextInt();
            System.out.println();
            toDo.changeTaskStatus(id);
        }
    }
}