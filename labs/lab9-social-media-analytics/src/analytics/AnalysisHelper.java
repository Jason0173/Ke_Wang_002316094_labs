/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analytics;

/**
 *
 * @author harshalneelkamal
 */

import data.DataStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import model.Comment;
import model.Post;
import model.User;


public class AnalysisHelper {
    //Find Average number of likes per comment.
    //TODO
    public void getAverageLikesPerComments() {
        Map<Integer, Comment> comments = DataStore.getInstance().getComments();
        int likeNumber = 0;
        int commentNumber = comments.size();
        for (Comment c : comments.values()) {
            likeNumber += c.getLikes();
        }
        
        System.out.println("Average number of likes per comments: " + likeNumber / commentNumber);
            
    }
    
    public void getMaxLikeCommentPost() {
        DataStore data = DataStore.getInstance();
        Comment commentWithMaxLikes = null;
        
        for (Comment c : data.getComments().values()) {
            if (commentWithMaxLikes == null) {
                commentWithMaxLikes = c;
            }
            if (c.getLikes() > commentWithMaxLikes.getLikes()) {
                commentWithMaxLikes = c;
            }
        }
        int postId = commentWithMaxLikes.getPostId();
        
        System.out.println("Q2 - post with most likes per comment: " + data.getPosts().get(postId).getPostId());
    }
    
    public void getPostWithMostComments() {
        DataStore data = DataStore.getInstance();
        Post postWithMostCommentts = null;
        for (Post p : data.getPosts().values()) {
            if (postWithMostCommentts == null) {
                postWithMostCommentts = p;
               
            }
            if (p.getComments().size() > postWithMostCommentts.getComments().size()) {
                postWithMostCommentts = p;
            }
        }
        
        System.out.println("Q3 - post with most comments: " + postWithMostCommentts.getPostId());
    }
    
    
    public void getPassiveUsers() {
    DataStore data = DataStore.getInstance();

    // Map to store the number of posts per user
    HashMap<Integer, Integer> postNumbers = new HashMap<>();

    // Loop through all posts and count how many each user has made
    for (Post p : data.getPosts().values()) {
        int userId = p.getUserId();
        if (postNumbers.containsKey(userId)) {
            postNumbers.put(userId, postNumbers.get(userId) + 1);
        } else {
            postNumbers.put(userId, 1);
        }
    }

    // Create a list of users
    ArrayList<User> users = new ArrayList<>(data.getUsers().values());

    // Sort users by number of posts in ascending order (least posts first)
    Collections.sort(users, new UserMapComparator(postNumbers));

    // Output the sorted list
    System.out.println("Q4 - The following users have the least posts: ");
    for (int i = 0; i < 5; i++) {
        System.out.println(users.get(i) + ", - Post count: " + postNumbers.get(users.get(i).getId()));
    }
    }

    public void getPassiveCommentUsers() {
    DataStore data = DataStore.getInstance();

    // Map to store number of comments per user
    HashMap<Integer, Integer> commentNumbers = new HashMap<>();

    // Loop through all comments and count how many each user has made
    for (Comment c : data.getComments().values()) {
        int userId = c.getUserId();
        if (commentNumbers.containsKey(userId)) {
            commentNumbers.put(userId, commentNumbers.get(userId) + 1);
        } else {
            commentNumbers.put(userId, 1);
        }
    }

    // Create a list of all users
    ArrayList<User> users = new ArrayList<>(data.getUsers().values());

    // Sort users by comment count in ascending order
    Collections.sort(users, new UserMapComparator(commentNumbers));

    // Display the top 5 users with the least comments
    System.out.println("Q5 - The following users have the least comments: ");
    for (int i = 0; i < 5; i++) {
        System.out.println(users.get(i) + " - Comment count: " + commentNumbers.get(users.get(i).getId()));
    }
}
    public void getPassiveAndActiveOverallUsers() {
    DataStore data = DataStore.getInstance();
    HashMap<Integer, Integer> overallNumbers = new HashMap<>();

    // Step 1: Count activity from comments (including likes)
    for (Comment c : data.getComments().values()) {
        int userId = c.getUserId();
        int activityScore = 1 + c.getLikes(); // 1 point for the comment + likes
        if (overallNumbers.containsKey(userId)) {
            overallNumbers.put(userId, overallNumbers.get(userId) + activityScore);
        } else {
            overallNumbers.put(userId, activityScore);
        }
    }

    // Step 2: Count activity from posts (1 point per post)
    for (Post p : data.getPosts().values()) {
        int userId = p.getUserId();
        if (overallNumbers.containsKey(userId)) {
            overallNumbers.put(userId, overallNumbers.get(userId) + 1);
        } else {
            overallNumbers.put(userId, 1);
        }
    }

    // Step 3: Sort users
    ArrayList<User> users = new ArrayList<>(data.getUsers().values());

    // Q6: Print the 5 most passive users (least overall activity)
    Collections.sort(users, new UserMapComparator(overallNumbers));
    System.out.println("Q6 - The following users have overall been passive:");
    for (int i = 0; i < 5; i++) {
        System.out.println(users.get(i) + " - Overall count: " + overallNumbers.get(users.get(i).getId()));
    }

    // Q7: Print the 5 most active users (most overall activity)
    Collections.sort(users, new UserMapComparator(overallNumbers).reversed());
    System.out.println("Q7 - The following users have overall been active:");
    for (int i = 0; i < 5; i++) {
        System.out.println(users.get(i) + " - Overall count: " + overallNumbers.get(users.get(i).getId()));
    }
}

}

