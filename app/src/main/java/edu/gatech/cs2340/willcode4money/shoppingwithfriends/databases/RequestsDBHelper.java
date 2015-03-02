package edu.gatech.cs2340.willcode4money.shoppingwithfriends.databases;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.SaleRequest;
import edu.gatech.cs2340.willcode4money.shoppingwithfriends.User;

/**
 * An SQLite database helper that allows the application to save and retrieve item request information.
 */

class RequestsDBHelper extends SQLiteOpenHelper implements BaseColumns {
    private static final String DATABASE_NAME = "Requests.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "requests";
    private static final String COLUMN_NAME_USER = "user";
    private static final String COLUMN_NAME_ITEM = "item";
    private static final String COLUMN_NAME_PRICE = "price";

    //CREATE TABLE requests(_ID TEXT PRIMARY KEY,user TEXT,item TEXT,price TEXT)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + _ID +
            " TEXT PRIMARY KEY," + COLUMN_NAME_USER + " TEXT," + COLUMN_NAME_ITEM + " TEXT," +
            COLUMN_NAME_PRICE + " TEXT" + ")";

    //DROP TABLE IF EXISTS users;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    //Delete all entries from table: DELETE FROM users
    private static final String DELETE_ALL = "DELETE FROM " + TABLE_NAME;

    public RequestsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        this.onCreate(db);
    }

    public void readAllRequests(Map<String, User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users.values()) {
            this.readRequests(db, user);
        }
        db.close();
    }

    private void readRequests(SQLiteDatabase db, User user) {
        String username = user.getUsername();
        String[] proj = {
                COLUMN_NAME_ITEM,
                COLUMN_NAME_PRICE};
        String selection = COLUMN_NAME_USER + "='" + username + "'";
        String sortOrder = COLUMN_NAME_ITEM + " DESC";
        Cursor c = db.query(TABLE_NAME,
                proj,
                selection,
                null,
                null,
                null,
                sortOrder);
        if (!c.moveToFirst()) {
            return;
        }
        do {
            user.addRequest(c.getString(0), Double.parseDouble(c.getString(1)));
        } while (c.moveToNext());
        c.close();
        db.close();
    }

    public void saveAllRequests(Map<String, User> users) {
        SQLiteDatabase db = this.getWritableDatabase();
        for (User user : users.values()) {
            this.saveRequests(db, user);
        }
        db.close();
    }

    private void saveRequests(SQLiteDatabase db, User user) {
        List<SaleRequest> requests = user.getRequests();
        for (SaleRequest request : requests) {
            String owner = request.getOwner();
            String item = request.getItem();
            String price = (Double.valueOf(request.getMaxPrice())).toString();
            db.execSQL("INSERT OR IGNORE INTO " + TABLE_NAME + "(" + COLUMN_NAME_USER + "," + COLUMN_NAME_ITEM +
                    "," + COLUMN_NAME_PRICE + ") VALUES(" + owner + "," + item + "," + price + ")");
        }
    }
}
