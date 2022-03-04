package persistence;

import model.Book;
import model.BookCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// NOTE: tests are highly based on JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BookCollection bc = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            BookCollection bc = reader.read();
            assertEquals("Keilah", bc.getOwner());
            assertEquals(0, bc.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            BookCollection bc = reader.read();
            assertEquals("Keilah", bc.getOwner());
            List<Book> books = bc.getBookList();
            assertEquals(2, books.size());
            checkBook("Another Sappy Love Story", "Nicholas Sparks",
                    8, 5, books.get(0));
            checkBook("I want a drink", "Every student ever", 500,
                    3, books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}