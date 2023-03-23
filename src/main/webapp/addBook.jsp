<%--
  Created by IntelliJ IDEA.
  User: Jordan
  Date: 2023-03-22
  Time: 9:20 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="styles.css">
  <title>Add Book</title>
</head>
<body>
<h1>Add Book</h1>
<form method="post" action="AddBook">
  <div>
    <label for="isbn">ISBN:</label>
    <input type="text" name="isbn" id="isbn">
  </div>
  <div>
    <label for="title">Title:</label>
    <input type="text" name="title" id="title"><br>
  </div>
  <div>
    <label for="editionNumber">Edition Number:</label>
    <input type="number" name="editionNumber" id="editionNumber"><br>
  </div>
  <div>
    <label for="copyright">Copyright:</label>
    <input type="text" name="copyright" id="copyright"><br>
  </div>
    <input type="submit" value="Add Book">
    <a href="index.jsp">Back to menu</a>
</form>
</body>
</html>
