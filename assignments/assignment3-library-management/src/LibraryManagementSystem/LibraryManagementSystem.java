/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryManagementSystem;

/**
 *
 * @author Jason
 */
import DataModel.Author;
import DataModel.Book;
import DataModel.RentalRequest;
import LibraryandBranchClasses.Branch;
import LibraryandBranchClasses.Library;
import UserClasses.BranchManager;
import UserClasses.Customer;
import UserClasses.SystemAdmin;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementSystem {
    private List<Branch> branches = new ArrayList<>();
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
    
    public void addBranch(Branch branch) {
        branches.add(branch);
    }
    
    public void deleteBranch(Branch branch) {
        branches.remove(branch);
    }
    
    public List<Branch> getBranches() {
        return branches;
    }
    
    public void addSystemAdmin(SystemAdmin admin) {
        systemAdmins.add(admin);
    }
    
    public void addBranchManager(BranchManager manager) {
        branchManagers.add(manager);
    }
    
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    
    public void addRentalRequest(RentalRequest request) {
        rentalRequests.add(request);
    }
    
    public List<RentalRequest> getRentalRequests() {
        return rentalRequests;
    }
    
    public List<Customer> getCustomers() {
        return customers;
    }
    
    // Pre-populate sample data
    public void prePopulateData() {
        // Create Authors
        Author a1 = new Author("J.K. Rowling");
        Author a2 = new Author("George R.R. Martin");
        Author a3 = new Author("J.R.R. Tolkien");
        Author a4 = new Author("Agatha Christie");
        Author a5 = new Author("Stephen King");
        
        // Create Libraries
        Library lib1 = new Library("B1");
        Library lib2 = new Library("B2");
        
        // Add Authors and Books to libraries
        lib1.addAuthor(a1);
        lib1.addAuthor(a2);
        lib2.addAuthor(a3);
        lib2.addAuthor(a4);
        lib2.addAuthor(a5);
        
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
        
        // Create Branch Managers
        BranchManager bm1 = new BranchManager("manager1", "pass1", 5);
        BranchManager bm2 = new BranchManager("manager2", "pass2", 8);
        
        // Create Branches (each with one library and one branch manager)
        Branch branch1 = new Branch("Downtown", lib1, bm1);
        Branch branch2 = new Branch("Uptown", lib2, bm2);
        addBranch(branch1);
        addBranch(branch2);
        addBranchManager(bm1);
        addBranchManager(bm2);
        
        // Create System Admin
        SystemAdmin admin = new SystemAdmin("admin", "adminpass");
        addSystemAdmin(admin);
        
        // Create Customers
        Customer cust1 = new Customer("cust1", "cpass1");
        Customer cust2 = new Customer("cust2", "cpass2");
        Customer cust3 = new Customer("cust3", "cpass3");
        addCustomer(cust1);
        addCustomer(cust2);
        addCustomer(cust3);
        
        // Create a Rental Request for demonstration (cust1 rents b1)
        RentalRequest req1 = new RentalRequest(10.0, 7, lib1, b1);
        addRentalRequest(req1);
        cust1.addRentalRequest(req1);
    }
}
