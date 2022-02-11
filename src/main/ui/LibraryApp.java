package ui;

import model.BookCollection;
import model.Book;

import java.util.List;
import java.util.Scanner;

// Library application
// NOTE: code for this class is highly based on the TellerApp code (given in Phase 1 Instructions)
public class LibraryApp {
    private BookCollection collection;
    private Scanner input;

    // EFFECTS: displays main menu to user
    private void showMainMenu() {
        System.out.println("WELCOME TO YOUR BOOK COLLECTION!");
        System.out.println("titles -> see titles in collection");
        System.out.println("authors -> see authors in collection");
        System.out.println("add -> add book");
        System.out.println("q -> quit");
    }

    // EFFECTS: runs the Library application
    public LibraryApp() {
        runLibrary();
    }

    // MODIFIES: this
    // EFFECT: processes user inputs
    private void runLibrary() {
        boolean stillWorking = true;
        String command = null;

        createCollection();

        while (stillWorking) {
            showMainMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                stillWorking = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("HAVE A GOOD DAY!");
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs
    private void processCommand(String input) {
        if (input.equals("titles")) {
            seeTitleList(collection.getTitleList());
        } else if (input.equals("add")) {
            addABook();
        } else if (input.equals("authors")) {
            seeAuthorList((collection.getAuthorList()));
        } else {
            System.out.println("NOT A VALID INPUT, PLEASE TRY AGAIN");
        }

    }

    // MODIFIES: this
    // EFFECTS: creates collection
    private void createCollection() {
        collection = new BookCollection("Keilah");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: prints title list
    private void seeTitleList(List<String> titles) {
        for (String s : titles) {
            System.out.println(s);
        }
        System.out.println("- NO OTHER BOOKS IN COLLECTION -");

        String selection = "";

        while (!(selection.equals("m"))) {
            System.out.println("m -> menu");
            selection = input.next();
            selection = selection.toLowerCase();

            if (!(selection.equals("m"))) {
                System.out.println("NOT A VALID INPUT, PLEASE TRY AGAIN");
            }
        }
    }

    // EFFECTS: prints author list
    private void seeAuthorList(List<String> authors) {
        for (String s : authors) {
            System.out.println(s);
        }
        System.out.println("- NO OTHER AUTHORS IN COLLECTION -");

        String selection = "";

        while (!(selection.equals("m"))) {
            System.out.println("m -> menu");
            selection = input.next();
            selection = selection.toLowerCase();

            if (!(selection.equals("m"))) {
                System.out.println("NOT A VALID INPUT, PLEASE TRY AGAIN");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Adds new book to collection
    private void addABook() {
        Book newBook = (new Book(addNewTitle(), addNewAuthor(),
                addNewPages(), addNewRating()));
        collection.addBook(newBook);
    }

    // EFFECTS: prompts user to input new book title
    private String addNewTitle() {
        System.out.println("Title of new book?");
        String newTitle = input.next();
        if (newTitle.length() <= 0) {
            System.out.println("PLEASE INPUT A TITLE");
            addNewTitle();
        }
        return newTitle;
    }

    // EFFECTS: prompts user to input new author
    private String addNewAuthor() {
        System.out.println("Author of new book?");
        String newAuthor = input.next();
        if (newAuthor.length() <= 0) {
            System.out.println("PLEASE INPUT AN AUTHOR");
            addNewAuthor();
        }
        return newAuthor;
    }

    // EFFECTS: prompts user to input new page length
    private int addNewPages() {
        System.out.println("How many pages is the book?");
        int newPages = input.nextInt();
        if (newPages <= 0) {
            System.out.println("PLEASE INPUT A NUMBER GREATER THAN 0");
            addNewPages();
        }
        return newPages;
    }

    // EFFECTS: prompts user to input new rating
    private int addNewRating() {
        System.out.println("Rating out of 5 stars?");
        int newRating = input.nextInt();
        if (newRating <= 0) {
            System.out.println("PLEASE INPUT A RATING BETWEEN 1-5");
            addNewRating();
        }
        return newRating;
    }
}

