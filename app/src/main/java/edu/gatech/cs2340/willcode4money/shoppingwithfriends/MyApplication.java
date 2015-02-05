package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JakeWilliams on 2/5/2015.
 */
public class MyApplication extends Application {
    private Map<String, String> users = new HashMap<String, String>();

    public Map<String, String> getUsers() {
        return users;
    }

    public void setUsers(Map<String, String> users) {
        this.users = users;
    }
}
