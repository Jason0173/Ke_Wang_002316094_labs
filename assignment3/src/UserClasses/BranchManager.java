/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserClasses;

import LibraryandBranchClasses.Library;

/**
 *
 * @author Jason
 */

public class BranchManager extends User {
    private static int empCounter = 200;
    private int employeeId;
    private int experience; // in years
    private Library library; // the library managed by this branch manager
    
    public BranchManager(String username, String password, int experience) {
        super(username, password);
        this.employeeId = empCounter++;
        this.experience = experience;
    }
    
    public int getEmployeeId() {
        return employeeId;
    }
    
    public int getExperience() {
        return experience;
    }
    
    public void setLibrary(Library library) {
        this.library = library;
    }
    
    public Library getLibrary() {
        return library;
    }
    
    @Override
    public String toString() {
        return "BranchManager[ID=" + employeeId + ", Username=" + username + ", Experience=" + experience + "]";
    }
}


