package persistence;

import model.Book;
import model.BookCollection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a book collection from stored Json data in file
// NOTE: code for this class is highly based on the JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads book collection from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BookCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBookCollection(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //TODO: double check the inputs below meet book collection requirements

    // EFFECTS: parses book collection from JSON object and returns it
    private BookCollection parseBookCollection(JSONObject jsonObject) {
        String name = jsonObject.getString("owner");
        BookCollection bc = new BookCollection(name);
        addBooks(bc, jsonObject);
        return bc;
    }

    // MODIFIES: bc
    // EFFECTS: parses thingies from JSON object and adds them to book collection
    private void addBooks(BookCollection bc, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("books");
        for (Object json : jsonArray) {
            JSONObject nextBook = (JSONObject) json;
            addBook(bc, nextBook);
        }
    }

    // MODIFIES: bc
    // EFFECTS: parses thingy from JSON object and adds it to book collection
    private void addBook(BookCollection bc, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        int pages = jsonObject.getInt("pages");
        int rating = jsonObject.getInt("rating");
        Book book = new Book(title, author, pages, rating);
        bc.addBook(book);
    }
}
