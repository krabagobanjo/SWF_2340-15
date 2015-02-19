package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * The home screen after login.
 */
public class MainScreen extends Activity {
    private String currUser;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    /**
     * Logs the user out of the application.
     */
    public void logout(View view) {
        ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).setAuth(false);
        ((ShoppingWithFriends) this.getApplication()).setCurrentUser("");
        finish();
    }

    public void viewFriends(View view) { startActivity(new Intent(this, viewFriends.class)); }

    public void addFriends(View view) { startActivity(new Intent(this, addFriend.class)); }


}
