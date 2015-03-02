package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.ShoppingWithFriends;
import edu.gatech.cs2340.willcode4money.shoppingwithfriends.User;

/**
 * An SQLite database helper that allows the application to save and retrieve user information.
 */
public class UserDBHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "RegisteredUsers.db";
    private static final int DATABASE_VERSION = 1;

    //Databases holding reported sales and item requests
    private ReportedDBHelper reportedDBhelper;
    private RequestsDBHelper requestsDBhelper;

    //Constant values
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME_ID = "username";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_PASSWORD = "password";
    private static final String COLUMN_NAME_EMAIL = "email";
    private static final String COLUMN_NAME_FRIENDS = "friends";
    private static final String COLUMN_NAME_RATINGS = "ratings";
    private static final String COLUMN_NAME_AUTH = "authenticated";

    //CREATE TABLE users(_ID TEXT PRIMARY KEY, username TEXT UNIQUE, password TEXT, name TEXT, email TEXT,...
    //friends TEXT, ratings TEXT, authenticated TEXT);
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME_ID + " TEXT UNIQUE," + COLUMN_NAME_PASSWORD + " TEXT," + COLUMN_NAME_NAME + " TEXT," +
            COLUMN_NAME_EMAIL + " TEXT," + COLUMN_NAME_FRIENDS + " TEXT," + COLUMN_NAME_RATINGS + " TEXT," +
            COLUMN_NAME_AUTH + " TEXT" + ")";

    //DROP TABLE IF EXISTS users;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Delete all entries from table: DELETE FROM users
    private static final String DELETE_ALL = "DELETE FROM " + TABLE_NAME;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        reportedDBhelper = new ReportedDBHelper(context);
        requestsDBhelper = new RequestsDBHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void saveUsers(Application application) {
        Map<String, User> users = ((ShoppingWithFriends) application).getUsers();
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users.values()) {
            this.saveUser(db, user);
        }
        db.close();
        requestsDBhelper.saveAllRequests(users);
        //reportedDBhelper.saveAllReports(user);
    }

    private void saveUser(SQLiteDatabase db, User user) {
        //Save friends list as comma-separated string of usernames
        List<User> friends = user.getFriends();
        StringBuilder friendsList = new StringBuilder();
        for (User friend : friends) {
            friendsList.append(friend.getUsername());
            friendsList.append(",");
        }
        friendsList.deleteCharAt(friendsList.length() - 1);
        String friendsString = friendsList.toString();
        //Save ratings as a comma-separated string of integers
        List<Integer> ratings = user.getRatingsList();
        StringBuilder rate = new StringBuilder();
        for (Integer rating : ratings) {
            rate.append(rating);
            rate.append(",");
        }
        rate.deleteCharAt(rate.length() - 1);
        String ratingString = rate.toString();
        db.execSQL("INSERT OR IGNORE INTO " + TABLE_NAME + "(" + COLUMN_NAME_ID + "," + COLUMN_NAME_PASSWORD +
                "," + COLUMN_NAME_NAME + "," + COLUMN_NAME_EMAIL + "," + COLUMN_NAME_FRIENDS + "," + COLUMN_NAME_RATINGS +
                COLUMN_NAME_AUTH + ") VALUES(" + user.getUsername() + "," + user.getPassword() + "," + user.getName() + "," +
                user.getEmail() + "," + friendsString + "," + ratingString + "," +
                (user.getAuth() ? "yes" : "no") + ")");
    }

    public Map<String, User> readUsers() {
        Map<String, User> users = new HashMap<String, User>();
        SQLiteDatabase db = this.getWritableDatabase();
        String[] proj = {COLUMN_NAME_ID};
        String sortOrder = COLUMN_NAME_ID + " DESC";
        Cursor c = db.query(
                TABLE_NAME, //Name of table
                proj, //Columns to return
                null, //No selection criteria
                null, //see above
                null, //no grouping
                null, //no filters
                sortOrder);
        boolean hasUsers = c.moveToFirst(); //Make sure there are actually users to retrieve
        if (!hasUsers) {
            return users;
        }
        do {
            String username = c.getString(0);
            users.put(username, this.readUser(db, username));
        } while(c.moveToNext());
        c.close();
        //Read friends list and list of ratings
        this.readFriends(db, users);
        this.readRatings(db, users);
        db.execSQL(DELETE_ALL);
        db.close();
        //reportedDBhelper.readAllReports(user);
        requestsDBhelper.readAllRequests(users);
        return users;
    }

    private User readUser(SQLiteDatabase db, String username) {
        User user;
        String password, email, name;

        String[] proj = {
                COLUMN_NAME_PASSWORD,
                COLUMN_NAME_NAME,
                COLUMN_NAME_EMAIL,
                COLUMN_NAME_AUTH};
        String selection = COLUMN_NAME_ID + "=" + "'" + username + "'";
        String sortOrder = COLUMN_NAME_NAME + " DESC";
        Cursor c = db.query(
                TABLE_NAME,
                proj,
                selection,
                null,
                null,
                null,
                sortOrder);
        c.moveToFirst();
        password = c.getString(0);
        name = c.getString(1);
        email = c.getString(2);
        user = new User(username, password, email, name);
        user.setAuth(c.getString(3).equals("yes"));
        c.close();
        return user;
    }

    private void readFriends(SQLiteDatabase db, Map<String, User> users) {
        Set<String> usernames = users.keySet();
        String[] proj = {COLUMN_NAME_FRIENDS};
        String sortOrder = COLUMN_NAME_ID + " DESC";
        for (String username : usernames) {
            User user = users.get(username);
            String selection = COLUMN_NAME_ID + "='" + username + "'";
            Cursor c = db.query(
                    TABLE_NAME,
                    proj,
                    selection,
                    null,
                    null,
                    null,
                    sortOrder);
            c.moveToFirst();
            String[] friends = c.getString(0).split(",");
            for (String friend : friends) {
                user.addFriend(users.get(friend));
            }
            c.close();
        }
    }

    private void readRatings(SQLiteDatabase db, Map<String, User> users) {
        Set<String> usernames = users.keySet();
        String[] proj = {COLUMN_NAME_RATINGS};
        String sortOrder = COLUMN_NAME_ID + " DESC";
        for (String username : usernames) {
            User user = users.get(username);
            String selection = COLUMN_NAME_ID + "='" + username + "'";
            Cursor c = db.query(
                    TABLE_NAME,
                    proj,
                    selection,
                    null,
                    null,
                    null,
                    sortOrder);
            c.moveToFirst();
            String[] ratings = c.getString(0).split(",");
            for (String rating : ratings) {
                user.addRating(Integer.parseInt(rating));
            }
            c.close();
        }
    }
}
