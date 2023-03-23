package com.example.tomcat_books_demo;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/AddBook")
public class AddBook extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display the addBook.jsp form
        RequestDispatcher dispatcher = request.getRequestDispatcher("addBook.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the values submitted from the form
        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        int editionNumber = Integer.parseInt(request.getParameter("editionNumber"));
        String copyright = request.getParameter("copyright");

        try {
            // Insert the new book into the database
            DBConnection.insertBook(new Book(isbn, title, editionNumber, copyright));
            System.out.println("Inserted book with ISBN: " + isbn);

            // Redirect to the viewBooks.jsp page to show the updated list of books
            response.sendRedirect("viewBooks.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            // TODO: Handle the error
        }
    }

}

