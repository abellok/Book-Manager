package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookCollectionTest {
    BookCollection Collection1;
    BookCollection Collection2;
    BookCollection Collection3;
    Book B1;
    Book B2;
    Book B3;

    @BeforeEach
    public void setupCollection() {
        Collection1 = new BookCollection("Allie");
        Collection2 = new BookCollection("Bernadette");
        Collection3 = new BookCollection("Casper");
        B1 = new Book("Hunger Games", "Suzanne Collins",
                150, 3);
        B2 = new Book("Divergent", "Veronica Roth",
                200, 5);
        B3 = new Book("Twilight", "Stephanie Meyer",
                300, 1);
    }

    @Test
    void testCollectionConstructor() {
        assertEquals("Bernadette", Collection2.getOwner());
        assertEquals(0, Collection1.getSize());
        assertEquals(0, Collection1.getBookList().size());
        assertEquals(0, Collection1.getTitleList().size());
        assertEquals(0, Collection1.getAuthorList().size());
    }

    @Test
    public void testAddBook() {
        Collection1.addBook(B1);
        assertEquals(1, Collection1.getBookList().size());
    }

    @Test
    public void testMultipleAddBook() {
        Collection1.addBook(B1);
        Collection1.addBook(B2);
        assertEquals(2, Collection1.getBookList().size());
    }

    @Test
    public void testMultipleAddBookDifferentCollections() {
        Collection1.addBook(B1);
        Collection2.addBook(B2);
        assertEquals(1, Collection1.getBookList().size());
        assertEquals(1, Collection2.getBookList().size());
    }


    @Test
    public void testRemoveBook() {
        Collection1.addBook(B1);
        Collection1.removeBook(B1);
        assertEquals(0, Collection1.getBookList().size());
    }

    @Test
    public void testMultipleRemoveBook() {
        Collection1.addBook(B1);
        Collection1.addBook(B2);
        Collection1.addBook(B3);
        Collection1.removeBook(B1);
        Collection1.removeBook(B2);
        assertEquals(1, Collection1.getBookList().size());
    }

    @Test
    public void testRemoveNonExistentBook() {
        Collection1.addBook(B1);
        Collection1.removeBook(B3);
        assertEquals(1, Collection1.getBookList().size());
    }

    @Test
    public void testFindBook() {
        Collection1.addBook(B1);
        Collection1.addBook(B2);
        Collection1.addBook(B3);
        assertEquals(B1, Collection1.findBook(Collection1.getBookList(), "Hunger Games"));
        assertEquals(null, Collection1.findBook(Collection1.getBookList(), "Dodger"));
    }
}