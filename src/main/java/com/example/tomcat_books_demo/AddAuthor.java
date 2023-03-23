package com.example.tomcat_books_demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

@WebServlet("/AddAuthor")
public class AddAuthor extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the addAuthor.jsp form
        RequestDispatcher dispatcher = request.getRequestDispatcher("addAuthor.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the values submitted from the form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        try {
            // Insert the new author into the database and get the auto-generated authorID
            int authorID = DBConnection.insertAuthor(new Author(firstName, lastName));
            System.out.println("Inserted author with ID: " + authorID);

            // Redirect to the viewAuthors.jsp page to show the updated list of authors
            response.sendRedirect("viewAuthors.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle the error
        }
    }
}

