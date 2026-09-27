/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SwingGUIApplication;

/**
 *
 * @author Jason
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystemApp extends JFrame {
    private LibraryManagementSystem system;
    private JTabbedPane tabbedPane;

    public LibraryManagementSystemApp() {
        system = new LibraryManagementSystem();
        system.prePopulateData();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Library Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tabbedPane = new JTabbedPane();
        
        // Three tabs: System Admin, Branch Manager, Customer.
        tabbedPane.add("System Admin", createAdminPanel());
        tabbedPane.add("Branch Manager", createBranchManagerPanel());
        tabbedPane.add("Customer", createCustomerPanel());
        
        add(tabbedPane);
        setLocationRelativeTo(null);
    }

    // System Admin panel: Create/Delete Branch (and associated Branch Manager).
    private JPanel createAdminPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel for branch creation.
        JPanel createBranchPanel = new JPanel(new GridLayout(0, 2, 5, 5));
        createBranchPanel.setBorder(BorderFactory.createTitledBorder("Create New Branch"));

        createBranchPanel.add(new JLabel("Branch Name:"));
        JTextField branchNameField = new JTextField();
        createBranchPanel.add(branchNameField);

        createBranchPanel.add(new JLabel("Manager Username:"));
        JTextField managerUsernameField = new JTextField();
        createBranchPanel.add(managerUsernameField);

        createBranchPanel.add(new JLabel("Manager Password:"));
        JTextField managerPasswordField = new JTextField();
        createBranchPanel.add(managerPasswordField);

        createBranchPanel.add(new JLabel("Manager Experience (years):"));
        JTextField managerExperienceField = new JTextField();
        createBranchPanel.add(managerExperienceField);

        createBranchPanel.add(new JLabel("Library Building No:"));
        JTextField buildingNoField = new JTextField();
        createBranchPanel.add(buildingNoField);

        JButton createBranchButton = new JButton("Create Branch");
        createBranchPanel.add(createBranchButton);
        createBranchPanel.add(new JLabel("")); // filler

        panel.add(createBranchPanel, BorderLayout.NORTH);

        // Table to display existing branches.
        String[] columnNames = {"Branch Name", "Manager Username", "Library Building"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        updateBranchTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Delete branch button.
        JPanel btnPanel = new JPanel();
        JButton btnDelete = new JButton("Delete Selected Branch");
        btnPanel.add(btnDelete);
        panel.add(btnPanel, BorderLayout.SOUTH);

        // Action Listener for creating a branch.
        createBranchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String branchName = branchNameField.getText().trim();
                String managerUsername = managerUsernameField.getText().trim();
                String managerPassword = managerPasswordField.getText().trim();
                String expStr = managerExperienceField.getText().trim();
                String buildingNo = buildingNoField.getText().trim();

                if (branchName.isEmpty() || managerUsername.isEmpty() || managerPassword.isEmpty() ||
                        expStr.isEmpty() || buildingNo.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "Please fill all fields.");
                    return;
                }

                int experience;
                try {
                    experience = Integer.parseInt(expStr);
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(panel, "Experience must be a number.");
                    return;
                }

                // Create new BranchManager, Library, and Branch.
                BranchManager newManager = new BranchManager(managerUsername, managerPassword, experience);
                Library newLibrary = new Library(buildingNo);
                Branch newBranch = new Branch(branchName, newLibrary, newManager);
                system.addBranch(newBranch);
                system.addBranchManager(newManager);

                // Update table.
                model.addRow(new Object[]{newBranch.getName(), newManager.getUsername(), newLibrary.getBuildingNo()});

                // Clear input fields.
                branchNameField.setText("");
                managerUsernameField.setText("");
                managerPasswordField.setText("");
                managerExperienceField.setText("");
                buildingNoField.setText("");

                JOptionPane.showMessageDialog(panel, "Branch created successfully.");
            }
        });

        // Action Listener for deleting a branch.
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String branchName = (String) model.getValueAt(selectedRow, 0);
                    Branch branchToDelete = null;
                    for (Branch branch : system.getBranches()) {
                        if (branch.getName().equals(branchName)) {
                            branchToDelete = branch;
                            break;
                        }
                    }
                    if (branchToDelete != null) {
                        system.deleteBranch(branchToDelete);
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(panel, "Branch deleted successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a branch to delete.");
                }
            }
        });

        return panel;
    }

    // Helper method to update the branch table.
    private void updateBranchTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (Branch branch : system.getBranches()) {
            model.addRow(new Object[]{
                branch.getName(),
                branch.getBranchManager().getUsername(),
                branch.getLibrary().getBuildingNo()
            });
        }
    }

    // Branch Manager panel: View/Add Books.
    private JPanel createBranchManagerPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        if (system.getBranches().isEmpty()) {
            panel.add(new JLabel("No branches available.", SwingConstants.CENTER), BorderLayout.CENTER);
            return panel;
        }
        // For demonstration, we use the BranchManager from the first branch.
        BranchManager bm = system.getBranches().get(0).getBranchManager();
        Library lib = bm.getLibrary();

        String[] columnNames = {"Serial", "Book Name", "Author", "Available"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Book book : lib.getBooks()) {
            model.addRow(new Object[]{book.getSerialNumber(), book.getName(), book.getAuthor().getName(), book.isAvailable()});
        }
        JTable table = new JTable(model);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnAddBook = new JButton("Add New Book");
        btnPanel.add(btnAddBook);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // For simplicity, add a book with dummy data.
                Author dummyAuthor = new Author("New Author");
                lib.addAuthor(dummyAuthor);
                Book newBook = new Book("New Book", 150, "English", dummyAuthor);
                lib.addBook(newBook);
                model.addRow(new Object[]{newBook.getSerialNumber(), newBook.getName(), newBook.getAuthor().getName(), newBook.isAvailable()});
                JOptionPane.showMessageDialog(panel, "New book added.");
            }
        });
        return panel;
    }

    // Customer panel: View available books and place a rental request.
    private JPanel createCustomerPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    if (system.getBranches().isEmpty()) {
        panel.add(new JLabel("No branches available.", SwingConstants.CENTER), BorderLayout.CENTER);
        return panel;
    }
    // For demonstration, we use the library from the first branch.
    Library lib = system.getBranches().get(0).getLibrary();

    String[] columnNames = {"Serial", "Book Name", "Author", "Available"};
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    JTable table = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(table);
    panel.add(scrollPane, BorderLayout.CENTER);

    // Button to refresh the book list
    JButton refreshButton = new JButton("Refresh Book List");
    refreshButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Clear the table and repopulate from the current book list
            model.setRowCount(0);
            for (Book book : lib.getBooks()) {
                model.addRow(new Object[]{
                    book.getSerialNumber(),
                    book.getName(),
                    book.getAuthor().getName(),
                    book.isAvailable()
                });
            }
        }
    });

    // Button for renting a book
    JButton btnRent = new JButton("Rent Selected Book");
    btnRent.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                int serial = (int) model.getValueAt(selectedRow, 0);
                Book selectedBook = null;
                for (Book book : lib.getBooks()) {
                    if (book.getSerialNumber() == serial && book.isAvailable()) {
                        selectedBook = book;
                        break;
                    }
                }
                if (selectedBook != null) {
                    RentalRequest request = new RentalRequest(15.0, 7, lib, selectedBook);
                    system.addRentalRequest(request);
                    // For demo purposes, use the first customer
                    if (!system.getCustomers().isEmpty()) {
                        Customer demoCustomer = system.getCustomers().get(0);
                        demoCustomer.addRentalRequest(request);
                    }
                    // Update availability in the table
                    model.setValueAt(false, selectedRow, 3);
                    JOptionPane.showMessageDialog(panel, "Book rented successfully.");
                } else {
                    JOptionPane.showMessageDialog(panel, "Selected book is not available.");
                }
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a book to rent.");
            }
        }
    });

    JPanel btnPanel = new JPanel();
    btnPanel.add(refreshButton);
    btnPanel.add(btnRent);
    panel.add(btnPanel, BorderLayout.SOUTH);

    // Initial load of the book list.
    for (Book book : lib.getBooks()) {
        model.addRow(new Object[]{
            book.getSerialNumber(),
            book.getName(),
            book.getAuthor().getName(),
            book.isAvailable()
        });
    }
    return panel;
}


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementSystemApp app = new LibraryManagementSystemApp();
            app.setVisible(true);
        });
    }

    ///////////////// Data Model Classes as Static Inner Classes //////////////////

    static class Author {
        private static int counter = 1;
        private int authorId;
        private String name;
        public Author(String name) {
            this.authorId = counter++;
            this.name = name;
        }
        public int getAuthorId() { return authorId; }
        public String getName() { return name; }
        @Override
        public String toString() {
            return "Author[ID=" + authorId + ", Name=" + name + "]";
        }
    }

    static class Book {
        private static int counter = 1000;
        private int serialNumber;
        private String name;
        private String registeredDate;
        private boolean isAvailable;
        private int numPages;
        private String language;
        private Author author;
        public Book(String name, int numPages, String language, Author author) {
            this.serialNumber = counter++;
            this.name = name;
            this.registeredDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            this.isAvailable = true;
            this.numPages = numPages;
            this.language = language;
            this.author = author;
        }
        public int getSerialNumber() { return serialNumber; }
        public String getName() { return name; }
        public String getRegisteredDate() { return registeredDate; }
        public boolean isAvailable() { return isAvailable; }
        public void setAvailable(boolean available) { isAvailable = available; }
        public int getNumPages() { return numPages; }
        public String getLanguage() { return language; }
        public Author getAuthor() { return author; }
        @Override
        public String toString() {
            return "Book[Serial=" + serialNumber + ", Name=" + name +
                   ", Author=" + author.getName() + ", Available=" + isAvailable + "]";
        }
    }

    enum RentalStatus {
        RENTED,
        RETURNED
    }

    static class RentalRequest {
        private static int counter = 5000;
        private int rentalId;
        private double price;
        private RentalStatus status;
        private int rentDuration;  // in days
        private Library library;
        private Book book;
        public RentalRequest(double price, int rentDuration, Library library, Book book) {
            this.rentalId = counter++;
            this.price = price;
            this.status = RentalStatus.RENTED;
            this.rentDuration = rentDuration;
            this.library = library;
            this.book = book;
            if (book != null) {
                book.setAvailable(false);
            }
        }
        public int getRentalId() { return rentalId; }
        public double getPrice() { return price; }
        public RentalStatus getStatus() { return status; }
        public void setStatus(RentalStatus status) {
            this.status = status;
            if (status == RentalStatus.RETURNED && book != null) {
                book.setAvailable(true);
            }
        }
        public int getRentDuration() { return rentDuration; }
        public Library getLibrary() { return library; }
        public Book getBook() { return book; }
        @Override
        public String toString() {
            return "RentalRequest[ID=" + rentalId + ", Book=" + book.getName() +
                   ", Status=" + status + "]";
        }
    }

    static abstract class User {
        protected String username;
        protected String password;
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        public String getUsername() { return username; }
    }

    static class SystemAdmin extends User {
        public SystemAdmin(String username, String password) {
            super(username, password);
        }
        @Override
        public String toString() { return "SystemAdmin: " + username; }
    }

    static class BranchManager extends User {
        private static int empCounter = 200;
        private int employeeId;
        private int experience;
        private Library library;
        public BranchManager(String username, String password, int experience) {
            super(username, password);
            this.employeeId = empCounter++;
            this.experience = experience;
        }
        public int getEmployeeId() { return employeeId; }
        public int getExperience() { return experience; }
        public void setLibrary(Library library) { this.library = library; }
        public Library getLibrary() { return library; }
        @Override
        public String toString() {
            return "BranchManager[ID=" + employeeId + ", Username=" + username +
                   ", Experience=" + experience + "]";
        }
    }

    static class Customer extends User {
        private static int custCounter = 100;
        private int customerId;
        private List<RentalRequest> rentalHistory;
        public Customer(String username, String password) {
            super(username, password);
            this.customerId = custCounter++;
            this.rentalHistory = new ArrayList<>();
        }
        public int getCustomerId() { return customerId; }
        public void addRentalRequest(RentalRequest request) { rentalHistory.add(request); }
        public List<RentalRequest> getRentalHistory() { return rentalHistory; }
        @Override
        public String toString() { return "Customer[ID=" + customerId + ", Username=" + username + "]"; }
    }

    static class Library {
        private String buildingNo;
        private List<Book> books;
        private List<Author> authors;
        public Library(String buildingNo) {
            this.buildingNo = buildingNo;
            this.books = new ArrayList<>();
            this.authors = new ArrayList<>();
        }
        public String getBuildingNo() { return buildingNo; }
        public void addBook(Book book) { books.add(book); }
        public void addAuthor(Author author) { authors.add(author); }
        public List<Book> getBooks() { return books; }
        public List<Author> getAuthors() { return authors; }
        @Override
        public String toString() {
            return "Library[Building=" + buildingNo + ", Books=" + books.size() + "]";
        }
    }

    static class Branch {
        private String name;
        private Library library;
        private BranchManager branchManager;
        public Branch(String name, Library library, BranchManager branchManager) {
            this.name = name;
            this.library = library;
            this.branchManager = branchManager;
            branchManager.setLibrary(library);
        }
        public String getName() { return name; }
        public Library getLibrary() { return library; }
        public BranchManager getBranchManager() { return branchManager; }
        @Override
        public String toString() {
            return "Branch[Name=" + name + ", Manager=" + branchManager.getUsername() + "]";
        }
    }

    static class LibraryManagementSystem {
        private List<Branch> branches;
        private List<SystemAdmin> systemAdmins;
        private List<BranchManager> branchManagers;
        private List<Customer> customers;
        private List<RentalRequest> rentalRequests;
        public LibraryManagementSystem() {
            branches = new ArrayList<>();
            systemAdmins = new ArrayList<>();
            branchManagers = new ArrayList<>();
            customers = new ArrayList<>();
            rentalRequests = new ArrayList<>();
        }
        public void addBranch(Branch branch) { branches.add(branch); }
        public void deleteBranch(Branch branch) { branches.remove(branch); }
        public List<Branch> getBranches() { return branches; }
        public void addSystemAdmin(SystemAdmin admin) { systemAdmins.add(admin); }
        public void addBranchManager(BranchManager manager) { branchManagers.add(manager); }
        public void addCustomer(Customer customer) { customers.add(customer); }
        public void addRentalRequest(RentalRequest request) { rentalRequests.add(request); }
        public List<RentalRequest> getRentalRequests() { return rentalRequests; }
        public List<Customer> getCustomers() { return customers; }
        public void prePopulateData() {
            // Sample authors
            Author a1 = new Author("J.K. Rowling");
            Author a2 = new Author("George R.R. Martin");
            Author a3 = new Author("J.R.R. Tolkien");
            Author a4 = new Author("Agatha Christie");
            Author a5 = new Author("Stephen King");

            // Sample libraries
            Library lib1 = new Library("B1");
            Library lib2 = new Library("B2");

            lib1.addAuthor(a1);
            lib1.addAuthor(a2);
            lib2.addAuthor(a3);
            lib2.addAuthor(a4);
            lib2.addAuthor(a5);

            // Sample books
            Book b1 = new Book("Book One", 300, "English", a1);
            Book b2 = new Book("Book Two", 250, "English", a2);
            Book b3 = new Book("Book Three", 500, "English", a3);
            Book b4 = new Book("Book Four", 200, "English", a4);
            Book b5 = new Book("Book Five", 350, "English", a5);

            lib1.addBook(b1);
            lib1.addBook(b2);
            lib2.addBook(b3);
            lib2.addBook(b4);
            lib2.addBook(b5);

            // Sample branch managers
            BranchManager bm1 = new BranchManager("manager1", "pass1", 5);
            BranchManager bm2 = new BranchManager("manager2", "pass2", 8);

            // Sample branches
            Branch branch1 = new Branch("Downtown", lib1, bm1);
            Branch branch2 = new Branch("Uptown", lib2, bm2);

            addBranch(branch1);
            addBranch(branch2);
            addBranchManager(bm1);
            addBranchManager(bm2);

            // Sample system admin
            SystemAdmin admin = new SystemAdmin("admin", "adminpass");
            addSystemAdmin(admin);

            // Sample customers
            Customer cust1 = new Customer("cust1", "cpass1");
            Customer cust2 = new Customer("cust2", "cpass2");
            Customer cust3 = new Customer("cust3", "cpass3");
            addCustomer(cust1);
            addCustomer(cust2);
            addCustomer(cust3);

            // Sample rental request (cust1 rents b1)
            RentalRequest req1 = new RentalRequest(10.0, 7, lib1, b1);
            addRentalRequest(req1);
            cust1.addRentalRequest(req1);
        }
    }
}
