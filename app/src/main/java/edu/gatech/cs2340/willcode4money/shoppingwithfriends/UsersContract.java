package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.provider.BaseColumns;

/**
 * Created by Tyler on 2/9/2015.
 */
public final class UsersContract {
    public UsersContract() {}

    public static abstract class RegUsers implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_NAME_ID = "userid";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_PASSWORD = "password";
        //Insert more User attributes here
    }
}
