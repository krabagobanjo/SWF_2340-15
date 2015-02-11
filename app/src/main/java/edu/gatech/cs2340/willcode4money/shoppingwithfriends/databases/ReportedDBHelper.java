package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * An SQLite database helper that allows the application to save and retrieve sale request information.
 */

class ReportedDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ReportedSales.db";
    private static final int DATABASE_VERSION = 1;

    public ReportedDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
