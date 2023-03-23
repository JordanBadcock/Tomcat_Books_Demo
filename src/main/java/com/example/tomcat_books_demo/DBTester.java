package com.example.tomcat_books_demo;

import java.sql.SQLException;
import java.util.List;

/**
 * Test DB connection.
 *
 * @author Jordan
 */
public class DBTester {

    public static void main(String[] args) {
        System.out.println("Database Tester");
        System.out.println();

        List<Book> bookList = null;
        try {
            bookList = DBConnection.getAllBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(Book book: bookList) {
            book.printBookInformation(System.out);
        }
    }

}

