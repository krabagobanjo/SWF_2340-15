package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Map;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.User;

import static edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases.DatabaseStrings.ReportStrings.DATABASE_NAME;
import static edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases.DatabaseStrings.ReportStrings.DATABASE_VERSION;
import static edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases.DatabaseStrings.ReportStrings.DROP_TABLE;

/**
 * An SQLite database helper that allows the application to save and retrieve sale report information.
 */

class ReportedDBHelper extends SQLiteOpenHelper implements BaseColumns {


    public ReportedDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Initializes the table in this database
     * @param db - the database in which to create the table
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(CREATE_TABLE);
    }

    /**
     * Updates the database when a newer version is created
     * @param db - the database to update
     * @param oldVersion - the version of the old database
     * @param newVersion - the version of the new database
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    /**
     * Reads from disk all users' sale reports
     * @param users - a map containing the users in the application
     */
    public void readAllReports(Map<String, User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users.values()) {
            this.readReports(db, user);
        }
        db.close();
    }

    //Reads the sale reports for a user from disk
    private void readReports(SQLiteDatabase db, User user) {
        //user.setSalesReported(null);
        //user.setSalesReceived(null);
    }

    /**
     * Saves to disk all users' sale reports
     * @param users - a map containing the users in the application
     */
    public void saveAllReports(Map<String, User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users.values()) {
            this.saveReports(db, user);
        }
        db.close();
    }

    //Saves the sale reports for a user to the disk
    private void saveReports(SQLiteDatabase db, User user) {

    }
}
