/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModel;

/**
 *
 * @author Jason
 */
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
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
        // Register current date for simplicity
        this.registeredDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.isAvailable = true;
        this.numPages = numPages;
        this.language = language;
        this.author = author;
    }
    
    public int getSerialNumber() {
        return serialNumber;
    }
    
    public String getName() {
        return name;
    }
    
    public String getRegisteredDate() {
        return registeredDate;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public int getNumPages() {
        return numPages;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    @Override
    public String toString() {
        return "Book[Serial=" + serialNumber + ", Name=" + name + ", Author=" + author.getName() + ", Available=" + isAvailable + "]";
    }
}