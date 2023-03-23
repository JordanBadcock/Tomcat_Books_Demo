<%--
  Created by IntelliJ IDEA.
  User: Jordan
  Date: 2023-03-22
  Time: 10:23 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css">
  <title>Add Author</title>
</head>
<body>
<h1>Add Author</h1>
<form action="AddAuthor" method="POST">
  <label for="firstName">First Name:</label>
  <input type="text" id="firstName" name="firstName" required><br>
  <label for="lastName">Last Name:</label>
  <input type="text" id="lastName" name="lastName" required><br>
  <button type="submit">Add Author</button>
</form>
</body>
<a href="index.jsp">Back to menu</a>
</html>