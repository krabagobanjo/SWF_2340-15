package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.User;

/**
 * An SQLite database helper that allows the application to save and retrieve sale request information.
 */

class ReportedDBHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "ReportedSales.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "reports";

    private static final String CREATE_TABLE = "";
    //DROP TABLE IF EXISTS users;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Delete all entries from table: DELETE FROM users
    private static final String DELETE_ALL = "DELETE FROM " + TABLE_NAME;

    public ReportedDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    public void readReports(User user) {

    }

    public void saveReports(User user) {

    }

}
