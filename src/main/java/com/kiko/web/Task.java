package com.kiko.web;

import jakarta.persistence.*;

import javax.management.Descriptor;

/**
 * Task.java
 * This class represents a task in the ToDO App.
 *
 * @author Diego Cordero
 * @version 1
 * @date February 18, 2024
 * @course CEN 4025 - Software Development 2
 *
 */
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For auto-incrementing primary keys
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "done")
    Boolean done; //status

    @Column(name = "importance")
    int importance; //out of 10

    public Task(String name, String description, Boolean done, int importance){
        this.name = name;
        this.description = description;
        this.done = done;
        this.importance = importance;
    }

    public Task(){};

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDone() {
        this.done = true;
    }

    public void unsetDone() {
        this.done = false;
    }

    public Boolean getDone() {
        return done;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getImportance() {
        return importance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }


    /**
     *  Overrides the toString() method to properly print a task to screen.
     */
    @Override
    public String toString(){
        String status;
        if (done){
            status = "Completed";
        }
        else{
            status = "Incomplete";
        }

        return "ID: " + id + ", Task: " + name + ", Description: " + description + ", Status: " + status + ", Importance: " + importance;
    }

}
