<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kiko.web.ToDoList" %>
<%@ page import="com.kiko.web.HibernateUtil" %>
<%@ page import="com.kiko.web.ToDoMain" %>
<%@ page import="com.kiko.web.Task" %>
<%@ page import="java.util.*" %>


<%
    // Check if the form is submitted.
    boolean redirect = false;
    if (request.getMethod().equalsIgnoreCase("post")) {
        ToDoList list = new ToDoList();
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int importance = Integer.parseInt(request.getParameter("importance"));
        list.addTask(name,description,importance);
        redirect = true;
%>
    <script>alert("Task Status Changed");</script>
<%
    }
    if(redirect){
        response.sendRedirect(response.encodeRedirectURL("index.jsp"));
    }
%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Add a new task</h1>
<form action="add.jsp" method="post">
        <label for="name">Task Name:</label><br>
        <input type="text" id="name" name="name" required><br>
        <label for="description">Description:</label><br>
        <input type="text" id="description" name="description" required><br>
        <label for="importance">Importance:</label><br>
        <input type="number" id="importance" name="importance" min="1" max="10" required><br>
        <input type="submit" value="Add Task">
    </form>
</html>