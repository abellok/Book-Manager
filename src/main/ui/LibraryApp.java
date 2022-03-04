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
        System.out.println("What action would you like to take?");
        System.out.println("-----------------------------------");
        System.out.println("See Titles | See Authors | Add | Delete | Quit");
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

            if ((command.equals("quit")) || (command.equals("q"))) {
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
        if ((input.equals("see titles")) || (input.equals("titles"))) {
            seeTitleList();
        } else if (input.equals("add")) {
            addABook();
        } else if ((input.equals("see authors")) || (input.equals("authors"))) {
            seeAuthorList();
        } else if (input.equals("delete")) {
            deleteABook();
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

    // EFFECTS: prints list
    private void printList(List<String> list) {
        for (String s : list) {
            System.out.println(s);
        }
    }

    // EFFECTS: displays the main menu
    private void mainMenu() {
        String selection = "";

        System.out.println("See Titles | See Authors | Add | Delete | Quit");
        selection = input.next();
        selection = selection.toLowerCase();

        processCommand(selection);
    }

    // EFFECTS: shows a list of titles in collection
    private void seeTitleList() {
        printList(collection.getTitleList());
        System.out.println("-----------------------------------");
        System.out.println("- NO OTHER BOOKS IN COLLECTION -");

        mainMenu();
    }


    // EFFECTS: prints author list
    private void seeAuthorList() {
        printList(collection.getAuthorList());
        System.out.println("-----------------------------------");
        System.out.println("- NO OTHER AUTHORS IN COLLECTION -");

        mainMenu();
    }

    // MODIFIES: this
    // EFFECTS: Deletes specified book from collection
    private void deleteABook() {
        printList(collection.getTitleList());

        String selection = "";

        while (selection.equals("")) {
            System.out.println("-----------------------------------");
            System.out.println("What title would you like to delete?");
            selection = input.next();

            if (null == collection.findBook(collection.getBookList(), selection)) {
                System.out.println("BOOK NOT FOUND. TRY AGAIN");
                System.out.println("-----------------------------------");
            } else {
                collection.removeBook(collection.findBook(collection.getBookList(), selection));
                System.out.println("BOOK DELETED!");
                System.out.println("-----------------------------------");
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

//TODO: IMPLEMENT ANOTHER USER STORY FROM PHASE 1
//TODO: change how you do ratings > go into a book and change its rating?
//TODO: find ways to show book collection statistics (Number of books, number of authors,
//      number of pages, etc.)