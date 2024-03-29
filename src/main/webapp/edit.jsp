<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kiko.web.ToDoList" %>
<%@ page import="com.kiko.web.HibernateUtil" %>
<%@ page import="com.kiko.web.ToDoMain" %>
<%@ page import="com.kiko.web.Task" %>
<%@ page import="java.util.*" %>


<%
    // Check if the form is submitted.
    boolean redirect = false;
    String unparsedTaskId = request.getParameter("taskId");
    if (request.getMethod().equalsIgnoreCase("post")){
        ToDoList list = new ToDoList();
        int taskId = Integer.parseInt(unparsedTaskId);
        list.changeTaskStatus(taskId);
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
    <title>Edit</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>Set task completion</h1>
    <form action="edit.jsp" method="post">
        <label for="taskId">Task ID:</label><br>
        <input type="number" id="taskId" name="taskId" required><br>
        <input type="submit" value="Toggle Status">
    </form>
</body>
</html>