package persistence;

import model.Book;
import model.BookCollection;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            BookCollection bc = new BookCollection("Keilah");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            BookCollection bc = new BookCollection("Keilah");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(bc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            bc = reader.read();
            assertEquals("Keilah", bc.getOwner());
            assertEquals(0, bc.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            BookCollection bc = new BookCollection("Keilah");
            bc.addBook(new Book("I got no sleep", "A stupid student",
                    20, 5));
            bc.addBook(new Book("I'm hungry", "Me Myself and I", 10,
                    3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(bc);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            bc = reader.read();
            assertEquals("Keilah", bc.getOwner());
            List<Book> books = bc.getBookList();
            assertEquals(2, books.size());
            checkBook("I got no sleep", "A stupid student",
                    20, 5, books.get(0));
            checkBook("I'm hungry", "Me Myself and I", 10,
                    3, books.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}