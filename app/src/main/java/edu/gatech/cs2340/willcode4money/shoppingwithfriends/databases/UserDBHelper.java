package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * An SQLite database helper that allows the application to save and retrieve user information.
 */
public class UserDBHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "RegisteredUsers.db";
    private static final int DATABASE_VERSION = 1;

    //Databases holding reported sales and item requests
    private SQLiteOpenHelper reportedDBhelper, requestsDBhelper;

    //Constant values
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_NAME_ID = "username";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_NAME_PASSWORD = "password";
    private static final String COLUMN_NAME_EMAIL = "email";
    private static final String COLUMN_NAME_FRIENDS = "friends";

    //CREATE TABLE users(_ID TEXT PRIMARY KEY, username TEXT UNIQUE, password TEXT, name TEXT, email TEXT,...
    //friends TEXT);
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID + " TEXT PRIMARY KEY," +
            COLUMN_NAME_ID + " TEXT UNIQUE," + COLUMN_NAME_PASSWORD + " TEXT," + COLUMN_NAME_NAME + " TEXT," +
            COLUMN_NAME_EMAIL + " TEXT," + COLUMN_NAME_FRIENDS + " TEXT" + ")";

    //DROP TABLE IF EXISTS users;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Delete all entries from table: DELETE FROM users
    private static final String DELETE_ALL = "DELETE FROM " + TABLE_NAME;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        reportedDBhelper = new ReportedDBHelper(context);
        requestsDBhelper = new RequestsDBHelper(context);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void onOpen(SQLiteDatabase db) {

    }

    public void saveUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        List<User> friends = user.getFriends();
        StringBuilder friendsList = new StringBuilder();
        for (User friend : friends) {
            friendsList.append(friend.getUsername());
        }
        String friendsString = friendsList.toString();
        //TODO DON'T REALLY NEED AN IDENTIFIER FOR REPORTED AND REQUESTS. JUST USERNAME TO IDENTIFY THEM.
        //INSERT INTO users(username, password, name, email, friends, reported, requests)...
        //VALUES(user.getUsername(), user.getPassword(), user.getName(), user.getEmail(), friendsString);
        db.close();
    }
}
