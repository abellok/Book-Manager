package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a book collection, having an owner name,
// total number of books, list of books, list of titles, and list of authors
// NOTE: some code for this class is based on the JsonSerializationDemo
public class BookCollection implements Writable {
    private final String name;              // name of the collection owner
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

    // EFFECTS: returns owner name
    public String getOwner() {
        return name;
    }

    // EFFECTS: returns size of collection
    public int getSize() {
        return size;
    }

    // EFFECTS: returns list of books
    public ArrayList<Book> getBookList() {
        return books;
    }

    // EFFECTS: returns list of book titles
    public ArrayList<String> getTitleList() {
        return titles;
    }

    // EFFECTS: returns list of authors
    public ArrayList<String> getAuthorList() {
        return authors;
    }

    // EFFECTS: adds a new book, its title, and its author to their
    //          appropriate lists
    public void addBook(Book book) {
        books.add(book);
        titles.add(book.getTitle());
        authors.add(book.getAuthor());
    }

    // EFFECTS: removes a book, its title, and its author to their
    //          appropriate lists
    public void removeBook(Book book) {
        books.remove(book);
        titles.remove(book.getTitle());
        authors.remove(book.getAuthor());
    }

    public Book findBook(List<Book> collection, String title) {
        for (Book b : collection) {
            if (b.getTitle().equals(title)) {
                return b;
            }
        }
        return null;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("owner", name);
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Book b : books) {
            jsonArray.put(b.toJson());
        }

        return jsonArray;
    }
}
