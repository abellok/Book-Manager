package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a single book, consisting of title, author,
// number of pages, and rating (out of 5 stars)
// NOTE: some code for this class is based on the JsonSerializationDemo
public class Book implements Writable {
    private String title;            // title of the book
    private String author;           // author of the book
    private int pages;               // number of pages the book has
    private int rating;              // number of rating stars (out of 5)
    private String genre;             // book genre (CURRENTLY UNUSED)


    // REQUIRES: bookTitle has a length >0
    //           authorName has a length >0
    //           bookLength has a size >0
    //           stars has a size >=0 and <=5
    // EFFECTS: creates a new book; sets book name to bookTitle, sets author to authorName,
    //          sets size of book to bookLength, sets rating to inputted stars.
    public Book(String bookTitle, String authorName, int bookLength, int stars) {
        title = bookTitle;
        author = authorName;
        pages = bookLength;
        if ((stars <= 5) && (stars >= 1)) {
            rating = stars;
        } else {
            rating = 0;
        }
    }

    // EFFECTS: returns the title of a book
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns the author of a book
    public String getAuthor() {
        return author;
    }

    // EFFECTS: returns the number of pages of a book
    public int getPages() {
        return pages;
    }

    // EFFECTS: returns the rating of a book
    public int getRating() {
        return rating;
    }

    // REQUIRES: stars >= 0 and <= 5
    // MODIFIES: this
    // EFFECTS: changes the rating of the book; if rating is not
    //          within the range, it remains unchanged
    public int changeRating(int stars) {
        if ((stars >= 1) && (stars <= 5)) {
            rating = stars;
            return rating;
        }
        return rating;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("pages", pages);
        json.put("rating", rating);
        return json;
    }
}
