/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModel;

import LibraryandBranchClasses.Library;

/**
 *
 * @author Jason
 */
public class RentalRequest {
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
        // Mark book as rented (unavailable)
        if (book != null) {
            book.setAvailable(false);
        }
    }
    
    public int getRentalId() {
        return rentalId;
    }
    
    public double getPrice() {
        return price;
    }
    
    public RentalStatus getStatus() {
        return status;
    }
    
    public void setStatus(RentalStatus status) {
        this.status = status;
        if (status == RentalStatus.RETURNED && book != null) {
            book.setAvailable(true);
        }
    }
    
    public int getRentDuration() {
        return rentDuration;
    }
    
    public Library getLibrary() {
        return library;
    }
    
    public Book getBook() {
        return book;
    }
    
    @Override
    public String toString() {
        return "RentalRequest[ID=" + rentalId + ", Book=" + book.getName() + ", Status=" + status + "]";
    }
}