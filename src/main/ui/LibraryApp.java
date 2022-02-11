package ui;

import model.BookCollection;

public class LibraryApp {
    private BookCollection collection;


    // EFFECTS: displays main menu to user
    private void showMainMenu() {
        System.out.println("Welcome to your book collection");
        System.out.println("s -> see titles in collection");
        System.out.println("a -> add book");
        System.out.println("q -> quit");
    }


    // EFFECTS: runs the Library application
    public LibraryApp() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECT: processes user inputs
    private void runLibrary() {
        //stub
    }


    // MODIFIES: this
    // EFFECTS: processes user inputs
    private void processInput(String input) {
        if (input.equals("s")) {
            seeTitleList();
        } else
            if (input.equals("a")) {
                addABook();
            } else {
                System.out.println("Not a valid input; please try again");
            }

    }

    // MODIFIES: this
    // EFFECTS: creates collection
    private void createCollection() {
        collection = new BookCollection("Keilah");
    }

    // MODIFIES: this
    // EFFECTS: shows title list
    private void seeTitleList() {
        //stub
    }


    private void addABook() {
        //stub
    }



    public void add() {
        //stub

        //as for each input for book info (title, author, page, rating)
        //
    }


    public void remove() {
        //stub
    }

    public void find() {
        //stub
    }
}
