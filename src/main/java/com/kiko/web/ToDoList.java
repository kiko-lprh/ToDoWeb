package com.kiko.web;

import com.kiko.web.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.*;

/**
 * com.kiko.web.ToDoList.java
 * This class manages the tasks in the toDo App.
 *
 * @author Diego Cordero
 * @version 1
 * @date February 18, 2024
 * @course CEN 4025 - Software Development 2
 *
 */
public class ToDoList {

    /**
     * Adds a task to the database
     *
     * @param name          The task's name.
     * @param description   The task's description.
     *        done          The task's status (Complete or Incomplete).
     * @param importance    The task's importance (Out of 10).
     */
    public void addTask(String name, String description, int importance){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tcn = session.beginTransaction();
        try { // add new task and commit changes
            Task task = new Task(name, description, false, importance);
            session.save(task);
            tcn.commit();
        } catch (Exception e) {
            tcn.rollback();
            throw e;
        }
        session.close();
    };

    /**
     * Deletes a task from the database
     *
     * @param id   The id of the task that is going to be deleted.
     */
    public void deleteTask(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tcn = session.beginTransaction();
        try { // get task based on id, delete and commit changes
            Task task = session.get(Task.class, id);
            session.delete(task);
            tcn.commit();
            System.out.println("Task deleted successfully.");
        } catch (Exception e) {
            System.out.println("Couldn't delete task.");
        }
        session.close();
    };

    /**
     * Changes the status of the task on the database. If task is 'Completed' on the database it sets it as Incomplete
     * and vice versa.
     *
     * @param id   The id of the task that is going to be updated.
     */
    public void changeTaskStatus(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tcn = session.beginTransaction();
        try { // get task based on id, update status and commit changes
            Task task = session.get(Task.class, id);

            if (!task.getDone()){
                task.setDone();
            }
            else {
                task.unsetDone();
            }
            session.update(task);
            tcn.commit();
            System.out.println("Success.");
        } catch (Exception e) {
            System.out.println("Task not found.");
        }
        session.close();
    };

    /**
     * Prints all tasks to screen.
     */
    public void printList(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tcn = session.beginTransaction();

        List<Task> tasks = session.createQuery("FROM Task", Task.class).list();

        for (Task task : tasks) {
            System.out.println(task.toString());
        }
    };

    public List<Task> getAllTasks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Task> tasks = null;
        try {
            transaction = session.beginTransaction();
            tasks = session.createQuery("FROM Task", Task.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return tasks;
    }

}