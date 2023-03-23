package com.example.tomcat_books_demo;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Retrieve all books from the database into a LinkedList.
 *
 * @author Jordan
 */
public class DBConnection {

    public static List<Book> getAllBooks() throws SQLException {
        LinkedList bookList = new LinkedList();

        Connection connection = initDatabase();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * from " + BooksDatabaseSQL.BOOK_TABLE_NAME;

        ResultSet resultSet = statement.executeQuery(sqlQuery);
        while(resultSet.next()) {
            bookList.add(
                    new Book(
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_ISBN),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_TITLE),
                            resultSet.getInt(BooksDatabaseSQL.BOOK_COL_NAME_EDITION_NUMBER),
                            resultSet.getString(BooksDatabaseSQL.BOOK_COL_NAME_COPYRIGHT)
                    )
            );
        }
        return bookList;
    }

    public static List<Author> getAllAuthors() throws SQLException {
        LinkedList authorList = new LinkedList();

        Connection connection = initDatabase();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * from " + BooksDatabaseSQL.AUTHOR_TABLE_NAME;

        ResultSet rs = statement.executeQuery(sqlQuery);
        while(rs.next()) {
            authorList.add(
                    new Author(
                            rs.getInt(BooksDatabaseSQL.AUTHOR_COL_NAME_ID),
                            rs.getString(BooksDatabaseSQL.AUTHOR_COL_NAME_FIRST_NAME),
                            rs.getString(BooksDatabaseSQL.AUTHOR_COL_NAME_LAST_NAME)
                    )
            );
        }
        return authorList;
    }

    /**
     * Insert book into the database
     * @param book
     * @throws SQLException
     */
    public static void insertBook(Book book) throws SQLException {
        Connection connection = initDatabase();

        String sqlQuery = "INSERT INTO " + BooksDatabaseSQL.BOOK_TABLE_NAME +
                " VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        //The 4 values are the books attributes
        preparedStatement.setString(1, book.getIsbn());
        preparedStatement.setString(2, book.getTitle());
        preparedStatement.setInt(3, book.getEditionNumber());
        preparedStatement.setString(4, book.getCopyright());

        preparedStatement.executeQuery();

    }

    /**
     * Get a connection to the Books Database - details in the inner class Books Database SQL
     * @return connection
     * @throws SQLException
     */
    static Connection initDatabase() throws SQLException {

        try {
            Class.forName("org.mariadb.jdbc.Driver").newInstance();
            System.out.println("Option 1: Find the class worked!");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error: unable to load driver class!");
        } catch(IllegalAccessException ex) {
            System.err.println("Error: access problem while loading!");
        } catch(InstantiationException ex){
            System.err.println("Error: unable to instantiate driver!");
        }

        return DriverManager.getConnection(BooksDatabaseSQL.DB_URL, BooksDatabaseSQL.USER, BooksDatabaseSQL.PASS);
    }

    public static int insertAuthor(Author author) throws SQLException {
        int authorID = -1;
        String sql = "INSERT INTO authors (firstName, lastName) VALUES (?, ?)";

        try (Connection conn = initDatabase();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Inserting author failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    authorID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Inserting author failed, no ID obtained.");
                }
            }
        }
        return authorID;
    }



    /**
     * Simple inner class to abstract all the specific SQL Information
     */
    private static class BooksDatabaseSQL{

        //Login information
        public static final String DB_URL = "jdbc:mariadb://localhost:3306/books";
        public static final String USER = "root";
        public static final String PASS = "root";

        //Book Table Information
        public static final String BOOK_TABLE_NAME = "titles";
        public static final String BOOK_COL_NAME_ISBN = "isbn";
        public static final String BOOK_COL_NAME_TITLE = "title";
        public static final String BOOK_COL_NAME_EDITION_NUMBER = "editionNumber";
        public static final String BOOK_COL_NAME_COPYRIGHT = "copyright";

        //Author Table Information
        public static final String AUTHOR_TABLE_NAME = "authors";
        public static final String AUTHOR_COL_NAME_ID = "authorid";
        public static final String AUTHOR_COL_NAME_FIRST_NAME = "firstName";
        public static final String AUTHOR_COL_NAME_LAST_NAME = "lastName";

    }

    public static List<Book> viewBooks() {

        List<Book> books = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(BooksDBConnectionTest.DB_URL, BooksDBConnectionTest.USER, BooksDBConnectionTest.PASS);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM titles")) {
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                int editionNumber = Integer.parseInt(rs.getString("editionNumber"));
                String copyright = String.valueOf(rs.getDouble("copyright"));
                Book book = new Book(isbn, title, editionNumber, copyright);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;

    }

    public static List<Author> viewAuthors() {
        List<Author> authors = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(BooksDBConnectionTest.DB_URL, BooksDBConnectionTest.USER, BooksDBConnectionTest.PASS);
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery("SELECT * FROM authors")) {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("authorID"));
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Author author = new Author(id, lastName, firstName);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }
}
