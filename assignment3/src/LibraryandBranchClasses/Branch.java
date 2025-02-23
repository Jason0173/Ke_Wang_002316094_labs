/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LibraryandBranchClasses;

import UserClasses.BranchManager;

/**
 *
 * @author Jason
 */
public class Branch {
    private String name;
    private Library library;
    private BranchManager branchManager;
    
    public Branch(String name, Library library, BranchManager branchManager) {
        this.name = name;
        this.library = library;
        this.branchManager = branchManager;
        branchManager.setLibrary(library);
    }
    
    public String getName() {
        return name;
    }
    
    public Library getLibrary() {
        return library;
    }
    
    public BranchManager getBranchManager() {
        return branchManager;
    }
    
    @Override
    public String toString() {
        return "Branch[Name=" + name + ", Manager=" + branchManager.getUsername() + "]";
    }
}
