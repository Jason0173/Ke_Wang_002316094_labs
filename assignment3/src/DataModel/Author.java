/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataModel;

/**
 *
 * @author Jason
 */


public class Author {
    private static int counter = 1;
    private int authorId;
    private String name;
    
    public Author(String name) {
        this.authorId = counter++;
        this.name = name;
    }
    
    public int getAuthorId() {
        return authorId;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Author[ID=" + authorId + ", Name=" + name + "]";
    }
}
