/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package analytics;

/**
 *
 * @author Jason
 */
import java.util.Comparator;
import java.util.HashMap;
import model.User;

public class UserMapComparator implements Comparator<User> {

    // Map of user IDs to their post counts
    HashMap<Integer, Integer> userPostMap;

    // Constructor that initializes the map
    public UserMapComparator(HashMap<Integer, Integer> userPostMap) {
        this.userPostMap = userPostMap;
    }

    @Override
    public int compare(User u1, User u2) {
        return Integer.compare(
            userPostMap.get(u1.getId()) == null ? 0 : userPostMap.get(u1.getId()),
            userPostMap.get(u2.getId()) == null ? 0 : userPostMap.get(u2.getId())
        );
    }

}

