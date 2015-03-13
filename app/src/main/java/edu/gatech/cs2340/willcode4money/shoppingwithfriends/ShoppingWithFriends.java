package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases.UserDBHelper;

/**
 * A class to carry information between activities
 */
public class ShoppingWithFriends extends Application {
    //Contains the registered users in the application
    private Map<String, User> userMap = new HashMap<String, User>();
    private Map<String, List<SaleReport>> reportMap = new HashMap<String, List<SaleReport>>();

    //The username of the currently logged-in user
    private String currentUserAsKey = "";

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
        userMap = usersDB.readUsers();
        reportMap = usersDB.readReports();
        Log.d("[DATABASE]", "Done reading!");
    }

    public Map<String, List<SaleReport>> getReportBucket() {
        return reportMap;
    }

    public void setReportMap(Map<String, List<SaleReport>> reportMap) {
        this.reportMap = reportMap;
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
        return userMap;
    }

    /**
     * Sets the list of registered users. Mainly used for loading saved data on open.
     * @param users the list of registered users
     */
    public void setUsers(Map<String, User> users) {
        this.userMap = users;
    }

    /**
     * Gets a list of reports posted that match user requests
     * @return the list of valid SaleReport 's
     */
    public List<SaleReport> getValidReports() {
        //the return list
        List<SaleReport> validReports = new ArrayList<SaleReport>();

        //get the current user class
        User tempUser = userMap.get(currentUserAsKey);
        //loop through all of the requests in user to compare to reports in app
        for (SaleRequest tempSaleRequest : tempUser.getRequests()) {
            //check if the equivalent report is in the report bucket
            if (reportMap.containsKey(tempSaleRequest.getItem())) {
                //if a list of items is present loop through them checking for valid prices
                for (SaleReport tempReport : reportMap.get(tempSaleRequest.getItem())) {
                    if (tempReport.getPrice() <= tempSaleRequest.getMaxPrice()
                            && tempUser.getFriends().contains(userMap.get(tempReport.getOwner()))) {
                        validReports.add(tempReport);
                    }
                }
            }
        }
        //return the list;
        return validReports;
    }

    /**
     * Adds a user to the registered users list.
     * @param user the user to add to the registered users list
     */
    public void addUser(User user) {
        this.userMap.put(user.getUsername(), user);
    }

    /**
     * Keeps track of the user that is currently logged in
     * @param username the username of the currently logged-in user
     */
    public void setCurrentUser(String username) {
        this.currentUserAsKey = username;
    }

    /**
     * Returns the user that's currently logged in
     * @return the username of the current user
     */
    public String getCurrentUser() {
        return currentUserAsKey;
    }
}
