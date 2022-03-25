package ui;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // console program
//        try {
//            new LibraryApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to run application: file not found");
//        }

        // GUI program
        new LibraryAppGUI();

    }
}
