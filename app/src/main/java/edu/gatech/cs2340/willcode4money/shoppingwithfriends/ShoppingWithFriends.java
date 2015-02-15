package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to carry information between activities
 */
public class ShoppingWithFriends extends Application {
    //Contains the registered users in the application
    private Map<String, User> users = new HashMap<String, User>();

    private String currentUser;

    /**
     * Gets a list of registered users
     * @return the list of registered users
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /**
     * Sets the list of registered users. Mainly used for loading saved data on open.
     * @param users the list of registered users
     */
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    /**
     * Adds a user to the registered users list.
     * @param user the user to add to the registered users list
     */
    public void addUser(User user) {
        this.users.put(user.getUsername(), user);
    }

    /**
     * Keeps track of the user that is currently logged in
     * @param username the username of the currently logged-in user
     */
    public void setCurrentUser(String username) {
        currentUser = username;
    }

    /**
     * Returns the user that's currently logged in
     * @return the username of the current user
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
