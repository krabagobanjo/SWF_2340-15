package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases.UserDBHelper;

/**
 * A class to carry information between activities
 */
public class ShoppingWithFriends extends Application {
    //Contains the registered users in the application
    private Map<String, User> users = new HashMap<String, User>();

    //The username of the currently logged-in user
    private String currentUser = "";

    //The database information is saved to and read from
    UserDBHelper usersDB;

    /**
     * Opens or creates the save-state database and reads in any information that exists.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        usersDB = new UserDBHelper(this);
        Log.d("[DATABASE]", "Reading from disk!");
        users = usersDB.readUsers();
        Log.d("[DATABASE]", "Done reading!");
    }

    /**
     * Saves this application's data to disk. To be run after all methods which nodify
     * application data.
     */
    public void save() {
        Log.d("[DATABASE]","Saving to disk!");
        usersDB.saveUsers(this);
        Log.d("[DATABASE]", "Done saving!");
    }

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
        this.currentUser = username;
    }

    /**
     * Returns the user that's currently logged in
     * @return the username of the current user
     */
    public String getCurrentUser() {
        return currentUser;
    }
}
