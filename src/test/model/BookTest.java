package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    Book B1;

    @BeforeEach
    public void setupBook() {
        B1 = new Book("Hunger Games", "Suzanne Collins");

    }

    @Test
     void testBookConstructor() {
        assertEquals("Hunger Games", B1.getTitle());
        assertEquals("Suzanne Collins", B1.getAuthor());
//        assertEquals(150, B1.getPages());
//        assertEquals(3, B1.getRating());
    }

//    @Test
//    public void testChangeRating() {
//        assertEquals(5, B1.changeRating(5));   // change rating from 3 to 5
//        assertEquals(5, B1.changeRating(10));  // input too large; rating stays the same
//        assertEquals(2, B1.changeRating(2));   // change rating from 5 to 2
//        assertEquals(2, B1.changeRating(0));   // input too small; rating stays the same
//    }
}
