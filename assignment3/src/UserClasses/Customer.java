/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UserClasses;

/**
 *
 * @author Jason
 */
import DataModel.RentalRequest;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private static int custCounter = 100;
    private int customerId;
    private List<RentalRequest> rentalHistory;
    
    public Customer(String username, String password) {
        super(username, password);
        this.customerId = custCounter++;
        this.rentalHistory = new ArrayList<>();
    }
    
    public int getCustomerId() {
        return customerId;
    }
    
    public void addRentalRequest(RentalRequest request) {
        rentalHistory.add(request);
    }
    
    public List<RentalRequest> getRentalHistory() {
        return rentalHistory;
    }
    
    @Override
    public String toString() {
        return "Customer[ID=" + customerId + ", Username=" + username + "]";
    }
}

