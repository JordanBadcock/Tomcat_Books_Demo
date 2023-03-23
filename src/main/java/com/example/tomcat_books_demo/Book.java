package com.example.tomcat_books_demo;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Simple class to store all book information.
 *
 * @author Jordan*/
public class Book {

    private final String isbn;
    private final String title;
    private final int editionNumber;
    private final String copyright;
    public static LinkedList authorList;

    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new LinkedList<>();
    }

    /**
//     * Create a book with a all the authors
//     * @param isbn
//     * @param title
//     * @param editionNumber
//     * @param copyright
//     * @param authorList
//     */
    public Book(String isbn, String title, int editionNumber, String copyright, List<Author> authorList) {
        this(isbn, title, editionNumber, copyright);
        this.authorList = (LinkedList) authorList;
    }

    /**
     * Get the ISBN
     * @return isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Get the title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the edition number as an integer
     * @return edition number
     */
    public int getEditionNumber() {
        return editionNumber;
    }

    /**
     * Get the copyright
     * @return copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * Get the list of Authors
     * @return list of Authors
     */
    public List<Author> getAuthorList() {
        return authorList;
    }

    /**
     * Set the list of Authors
     * @param authorList list of Authors
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = (LinkedList) authorList;
    }

    public void printBookInformation(PrintStream printStream){
        printStream.printf("\nISBN: %s \t\t Title: %-80s \t\t Edition #: %d \t\t Copyright: %s",
                this.getIsbn(), this.getTitle(), this.getEditionNumber(), this.getCopyright());
        //TODO Add the Authors - traverse the list
    }

}


