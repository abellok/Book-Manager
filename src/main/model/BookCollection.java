package model;

import java.util.ArrayList;

// Represents a book collection, having an owner name,
// total number of books, list of books, and list of authors
public class BookCollection {
    private static String name;             // name of the collection owner
    private int size;                       // current size of collection
    private ArrayList<Book> books;          // list of books in collection
    private ArrayList<String> titles;       // list of titles in collection
    private ArrayList<String> authors;      // list of authors in collection


    // REQUIRES: ownerName has a length >0
    // EFFECTS: creates a new book collection; owner of collection is set to ownerName,
    // collection size is set to 0, and creates new empty lists for books and authors
    public BookCollection(String ownerName) {
        name = ownerName;
        size = 0;
        books = new ArrayList<>();
        titles = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public String getOwner() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Book> getBookList() {
        return books;
    }


    public ArrayList<String> getTitleList() {
        return titles;
    }


    public ArrayList<String> getAuthorList() {
        return authors;
    }


    public void addBook(Book book) {
        books.add(book);
    }


    public void removeBook(Book book) {
        books.remove(book);
    }

}
