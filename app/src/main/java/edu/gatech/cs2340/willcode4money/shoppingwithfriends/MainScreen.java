package edu.gatech.cs2340.willcode4money.shoppingwithfriends;




import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Created by Kyle on 2/12/2015.
 */
public class MainScreen extends Activity {
    private String currUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        currUser = ((MyApplication) this.getApplication()).getCurrentUser();
        if (!((MyApplication) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    public void logout(View view) {
        ((MyApplication) this.getApplication()).getUsers().get(currUser).setAuth(false);
        ((MyApplication) this.getApplication()).setCurrentUser("");
        finish();
    }

    public void viewFriends(View view) { startActivity(new Intent(this, viewFriends.class)); }

    public void addFriends(View view) { startActivity(new Intent(this, addFriend.class)); }


}
