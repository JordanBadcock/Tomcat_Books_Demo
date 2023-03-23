package com.example.tomcat_books_demo;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 *
 *
 * @author Jordan
 */
@WebServlet(name="/libraryData", value="/library-data")
public class LibraryData extends HttpServlet {

    private String message;
    public void init() {
        message = "Library Servlet!";
    }
    private final DBConnection conn = new DBConnection();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        //TODO Use a variable "view" to determine book or author query
        String view = request.getParameter("view");

        // If the view is null, redirect to a default page
        if (view == null) {
            response.sendRedirect("index.jsp");
            return;
        }
        List<Book> bookList = null;
        List<Author> authorList = null;
//        try {
//            bookList = DBConnection.getAllBooks();
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBooks.jsp");
//            request.setAttribute("booklist", bookList);
//            authorList = DBConnection.getAllAuthors();
//            //TODO add the list to the request
//            requestDispatcher.forward(request, response);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            //TODO Navigate to some error page
//        }
        try {
            if (view.equals("books")) {
                bookList = DBConnection.getAllBooks();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBooks.jsp");
                request.setAttribute("bookList", bookList);
                requestDispatcher.forward(request, response);
            } else if (view.equals("authors")) {
                authorList = DBConnection.getAllAuthors();
                RequestDispatcher authorDispatcher = request.getRequestDispatcher("viewAuthors.jsp");
                request.setAttribute("authorList", authorList);
                authorDispatcher.forward(request, response);
            } else if (view.equals("add_book")) {
                bookList = DBConnection.getAllBooks();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("addBook.jsp");
                request.setAttribute("bookList", bookList);
                requestDispatcher.forward(request, response);
            } else if (view.equals("add_author")) {
                authorList = DBConnection.getAllAuthors();
                RequestDispatcher authorDispatcher = request.getRequestDispatcher("addAuthor.jsp");
                request.setAttribute("authorList", authorList);
                authorDispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO Navigate to some error page
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        List<Book> bookList = null;
        List<Author> authorList = null;

        if(view.equals("add_book")){
            try {
                String isbn = request.getParameter("isbn");
                String title = request.getParameter("title");
                int editionNumber = Integer.valueOf(request.getParameter("edition_number"));
                String copyright = request.getParameter("copyright");

                Book book = new Book(isbn, title, editionNumber, copyright);
                DBConnection.insertBook(book);
            } catch (SQLException e) {
                e.printStackTrace();
                //Navigate somewhere?
            }
            response.sendRedirect("viewBooks.jsp");
        }
        if(view.equals("add_author")){
            try {
                String firstName = request.getParameter("first_name");
                String lastName = request.getParameter("last_name");
                int id = Integer.valueOf(request.getParameter("id"));

                Author author = new Author(id, firstName, lastName);
                DBConnection.insertAuthor(author);

                response.sendRedirect("viewAuthors.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (view.equals("books")) {
            try {
                bookList = DBConnection.getAllBooks();
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("viewBooks.jsp");
                request.setAttribute("bookList", bookList);

                //TODO add the list to the request
                requestDispatcher.forward(request, response);
                DBConnection.viewBooks();
                response.sendRedirect("viewBooks.jsp");
            } catch (SQLException e) {
                e.printStackTrace();
                //TODO Navigate to some error page
            }
        }
        if (view.equals("authors")) {
            try {
                authorList = DBConnection.getAllAuthors();
                RequestDispatcher authorDispatcher = request.getRequestDispatcher("viewAuthors.jsp");
                request.setAttribute("authorList", authorList);
                //TODO add the list to the request
                authorDispatcher.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}