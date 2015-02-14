package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JakeWilliams on 2/5/2015.
 */
public class MyApplication extends Application {
    private Map<String, User> users = new HashMap<String, User>();
    private String currentUser = "";

    public void setCurrentUser(String currentUser) { this.currentUser = currentUser; }
    public String getCurrentUser() { return currentUser; }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
}
