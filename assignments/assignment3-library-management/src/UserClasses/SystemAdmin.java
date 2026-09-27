/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserClasses;

/**
 *
 * @author Jason
 */
public class SystemAdmin extends User {
    public SystemAdmin(String username, String password) {
        super(username, password);
    }
    
    @Override
    public String toString() {
        return "SystemAdmin: " + username;
    }
}

