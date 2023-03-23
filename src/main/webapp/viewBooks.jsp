<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Jordan
  Date: 2023-03-21
  Time: 9:33 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.tomcat_books_demo.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.tomcat_books_demo.DBConnection" %>
<%
  List<Book> bookList = DBConnection.viewBooks();
%>
<html>
<head>
  <link rel="stylesheet" type="text/css" href="styles.css">
  <title>View Books</title>
</head>
<body>
<h1>All Books</h1>
<table>
  <thead>
  <tr>
    <th>ISBN</th>
    <th>Title</th>
    <th>Edition Number</th>
    <th>Copyright</th>
  </tr>
  </thead>
  <tbody>
  <% for (Book book : bookList) { %>
      <tr>
        <td><%= book.getIsbn() %> </td>
        <td><%= book.getTitle() %></td>
        <td><%= book.getEditionNumber() %></td>
        <td><%= book.getCopyright() %></td>
      </tr>
    <% } %>
  </tbody>
</table>
</body>
<a href="index.jsp">Back to menu</a>
</html>