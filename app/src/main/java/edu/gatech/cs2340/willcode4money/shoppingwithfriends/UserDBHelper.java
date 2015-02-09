package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tyler on 2/9/2015.
 */
public class UserDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "RegisteredUsers.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_TABLE = "CREATE TABLE " +
            UsersContract.RegUsers.TABLE_NAME + " (" + UsersContract.RegUsers._ID + " TEXT PRIMARY KEY," +
            UsersContract.RegUsers.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
            UsersContract.RegUsers.COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
            //Other attributes for Users
            " )";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " +
            UsersContract.RegUsers.TABLE_NAME;

    public UserDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
}
