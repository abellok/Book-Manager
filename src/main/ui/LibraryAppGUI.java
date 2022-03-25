package ui;

import model.Book;
import model.BookCollection;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

// NOTE: code for this class is highly based on the ListDemoProject and SimpleDrawingPlayer
class LibraryAppGUI extends JFrame implements ActionListener, ListSelectionListener, DocumentListener {
    private JButton addButton;
    private JButton deleteButton;
    private JButton loadButton;
    private JButton saveButton;
    private JList list;
    private DefaultListModel listModel;
    private JTextField bookTitle;
    private JTextField author;
    private boolean alreadyEnabled = false;
    private BookCollection collection;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/library.json";
    private ImageIcon graphic;

    public LibraryAppGUI() {
        super("Library App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        initializeHomeScreen(this.getContentPane());
        initializeButtonListeners(this.getContentPane());
        collection = new BookCollection("Keilah");

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: sets up home screen
    public void initializeHomeScreen(Container screen) {
        screen.setLayout(new BoxLayout(screen, BoxLayout.Y_AXIS));

        // sets up title
        screen.add(initializeTitle());

        // sets up graphic
        graphic = new ImageIcon("C:\\Users\\keila\\Pictures\\books.png");
        Image transformGraphic = graphic.getImage();
        Image scaledGraphic = transformGraphic.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        graphic = new ImageIcon(scaledGraphic);
        JLabel picture = new JLabel(graphic);
        picture.setAlignmentX(CENTER_ALIGNMENT);
        screen.add(picture);

        // sets up list and scroller
        initializeScroller(screen);

        // lays out add button and text fields
        initializeTextFields(screen);

        // lays out other action buttons
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.setAlignmentX(CENTER_ALIGNMENT);
        buttons.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        initializeButtons(buttons);
        screen.add(buttons);
    }

    // EFFECTS: creates text fields
    public void initializeTextFields(Container screen) {
        bookTitle = new JTextField(5);
        author = new JTextField(5);

        bookTitle.getDocument().addDocumentListener(this);
        author.getDocument().addDocumentListener(this);


        JPanel newBook = new JPanel();
        newBook.setLayout(new BoxLayout(newBook, BoxLayout.X_AXIS));
        JLabel newTitle = new JLabel("Title:");
        newTitle.setFont(new Font("Calibri", Font.PLAIN, 15));
        JLabel newAuthor = new JLabel("Author:");
        newAuthor.setFont(new Font("Calibri", Font.PLAIN, 15));

        newBook.add(newTitle);
        newBook.add(Box.createHorizontalStrut(5));
        newBook.add(bookTitle);
        newBook.add(Box.createHorizontalStrut(5));
        newBook.add(newAuthor);
        newBook.add(Box.createHorizontalStrut(5));
        newBook.add(author);

        screen.add(newBook);
    }

    // EFFECTS: creates title
    public JLabel initializeTitle() {
        JLabel welcome = new JLabel("Welcome to your book collection!");
        welcome.setFont(new Font("Calibri", Font.BOLD, 30));
        welcome.setAlignmentX(CENTER_ALIGNMENT);
        return welcome;
    }

    // EFFECTS: creates scrollable book collection panel
    public void initializeScroller(Container screen) {
        // sets up scroller
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setAlignmentX(CENTER_ALIGNMENT);

        // lays out subtitle and book list
        JPanel bookList = new JPanel();
        bookList.setLayout(new BoxLayout(bookList, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Books currently in collection:");
        label.setFont(new Font("Calibri", Font.PLAIN, 15));
        label.setAlignmentX(CENTER_ALIGNMENT);
        bookList.add(label);
        bookList.add(Box.createRigidArea(new Dimension(0, 5)));
        bookList.add(listScroller);
        bookList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        screen.add(bookList);
    }

    // EFFECTS: sets up buttons and their display
    public void initializeButtons(Container container) {
        addButton = new JButton("Add");
        deleteButton = new JButton("Delete");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");

        addAButton(addButton, container);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
        addAButton(deleteButton, container);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
        addAButton(saveButton, container);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
        addAButton(loadButton, container);
        container.add(Box.createRigidArea(new Dimension(10, 0)));
    }

    // EFFECTS: sets up listeners for the buttons
    public void initializeButtonListeners(Container screen) {
        addButton.addActionListener(this);
        addButton.setActionCommand("Add Book");
        addButton.setEnabled(false);

        deleteButton.addActionListener(this);
        deleteButton.setActionCommand("Delete Book");
        deleteButton.setEnabled(false);

        loadButton.addActionListener(this);
        loadButton.setActionCommand("Load File");

        loadButton.addActionListener(this);
        loadButton.setActionCommand("Save File");
    }

    // EFFECTS: adds a button to a panel
    private static void addAButton(JButton button, Container container) {
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        container.add(button);
    }

    // EFFECTS: implements desired behaviour for each of the buttons
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Book")) {
            String title = bookTitle.getText();
            String writer = author.getText();

            if (title.equals("") || writer.equals("")) {
                Toolkit.getDefaultToolkit().beep();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }
            listModel.addElement(bookTitle.getText()
                    + " (" + author.getText() + ")");

            collection.addBook(new Book(bookTitle.getText(), author.getText()));

            bookTitle.requestFocusInWindow();
            bookTitle.setText("");
            author.requestFocusInWindow();
            author.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);


        }
        if (e.getActionCommand().equals("Delete Book")) {
            int index = list.getSelectedIndex();
            listModel.remove(index);
            collection.getBookList().remove(index);


            int size = listModel.getSize();

            // no books left; disable delete button
            if (size == 0) {
                deleteButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }

        // load file
        if (e.getActionCommand().equals("Load File")) {
            try {
                collection = jsonReader.read();
            } catch (IOException f) {
                // stub
            }
            addSavedBooks(collection.getBookList());
        }

        if (e.getActionCommand().equals("Save File")) {
            try {
                jsonWriter.open();
                jsonWriter.write(collection);
                jsonWriter.close();
            } catch (FileNotFoundException f) {
                // stub
            }
        }


    }

    public void addSavedBooks(ArrayList<Book> collection) {
        for (Book b : collection) {
            listModel.addElement(b.getTitle()
                    + " (" + b.getAuthor() + ")");
        }
    }

    //Tests for string equality
    protected boolean alreadyInList(String name) {
        return listModel.contains(name);
    }

    //Required by DocumentListener.
    public void insertUpdate(DocumentEvent e) {
        enableButton();
    }

    //Required by DocumentListener.
    public void removeUpdate(DocumentEvent e) {
        handleEmptyTextField(e);
    }

    //Required by DocumentListener.
    public void changedUpdate(DocumentEvent e) {
        if (!handleEmptyTextField(e)) {
            enableButton();
        }
    }

    // EFFECTS: changes button to enabled
    private void enableButton() {
        if (!alreadyEnabled) {
            addButton.setEnabled(true);
        }
    }

    // EFFECTS: stops making button enabled when text field is empty
    private boolean handleEmptyTextField(DocumentEvent e) {
        if (e.getDocument().getLength() <= 0) {
            addButton.setEnabled(false);
            alreadyEnabled = false;
            return true;
        }
        return false;
    }


    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable delete button.
                deleteButton.setEnabled(false);

            } else {
                //Selection, enable the delete button.
                deleteButton.setEnabled(true);
            }
        }
    }
}