<%--
  Created by IntelliJ IDEA.
  User: Jordan
  Date: 2023-03-22
  Time: 10:10 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.tomcat_books_demo.Author" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tomcat_books_demo.DBConnection" %>
<%
  List<Author> authorList = DBConnection.viewAuthors();
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css">
  <title>View Authors</title>
</head>
<body>
<h1>All Authors</h1>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>First Name</th>
    <th>Last Name</th>
  </tr>
  </thead>
  <tbody>
  <% for (Author author : authorList) { %>
  <tr>
    <td><%= author.getId() %></td>
    <td><%= author.getFirstName() %></td>
    <td><%= author.getLastName() %></td>
  </tr>
  <% } %>
  </tbody>
</table>
</body>
<a href="index.jsp">Back to menu</a>
</html>
