package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

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

    //Automatically saves information after a set time period
    private Timer saveTimer;
    private final long AUTOSAVE_PERIOD = 120000L;

    /**
     * Opens or creates the save-state database and reads in any information.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        usersDB = new UserDBHelper(this);
        users = usersDB.readUsers();
        saveTimer = new Timer(true);
        saveTimer.schedule(new SaveTask(this), AUTOSAVE_PERIOD, AUTOSAVE_PERIOD);
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

    /**
     * Used to repeatedly save application information at regular intervals (an auto-save)
     */
    private class SaveTask extends TimerTask {
        //The parent application class
        private Application app;

        public SaveTask(Application app) {
            super();
            this.app = app;
        }

        /**
         * Saves all information about the application to permanent storage
         */
        public void run() {
            usersDB.saveUsers(app);
        }
    }
}
