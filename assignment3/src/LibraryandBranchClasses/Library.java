/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryandBranchClasses;

/**
 *
 * @author Jason
 */
import DataModel.Author;
import DataModel.Book;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private String buildingNo;
    private List<Book> books;
    private List<Author> authors;
    
    public Library(String buildingNo) {
        this.buildingNo = buildingNo;
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
    }
    
    public String getBuildingNo() {
        return buildingNo;
    }
    
    public void addBook(Book book) {
        books.add(book);
    }
    
    public void addAuthor(Author author) {
        authors.add(author);
    }
    
    public List<Book> getBooks() {
        return books;
    }
    
    public List<Author> getAuthors() {
        return authors;
    }
    
    @Override
    public String toString() {
        return "Library[Building=" + buildingNo + ", Books=" + books.size() + "]";
    }
}

