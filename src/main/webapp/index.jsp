<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.kiko.web.ToDoList" %>
<%@ page import="com.kiko.web.HibernateUtil" %>
<%@ page import="com.kiko.web.ToDoMain" %>
<%@ page import="com.kiko.web.Task" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
     <link rel="stylesheet" href="styles.css">
</head>
<body>
<h1>View Tasks</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Importance</th>
        </tr>
        <%
            ToDoList toDo = new ToDoList();
            List<Task> tasks = toDo.getAllTasks();
            for (Task task : tasks) {
        %>
        <tr>
            <td><%= task.getId() %></td>
            <td><%= task.getName() %></td>
            <td><%= task.getDescription() %></td>
            <td><%= task.getDone() %></td>
            <td><%= task.getImportance() %></td>
        </tr>
        <% } %>
    </table>

    <div class="container">
      <div class="vertical-center">
            <button onclick="AddRedirect()">Add Task</button>
            <button onclick="EditRedirect()">Edit Task</button>
            <button onclick="DeleteRedirect()">Delete Task</button>
      </div>
    </div>

    <script type="text/javascript">
         function AddRedirect() {
           // Redirect to a new page
           window.location.href = "<%= response.encodeRedirectURL("add.jsp") %>";
         }

         function EditRedirect() {
           // Redirect to a new page
           window.location.href = "<%= response.encodeRedirectURL("edit.jsp") %>";
         }

         function DeleteRedirect() {
           // Redirect to a new page
           window.location.href = "<%= response.encodeRedirectURL("delete.jsp") %>";
         }
    </script>

</body>
</html>