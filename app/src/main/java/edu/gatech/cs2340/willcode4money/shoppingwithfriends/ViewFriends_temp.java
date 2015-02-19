package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * The user's friends list.
 */
public class ViewFriends extends Activity {
    private String currUser;

    /**
     * Creates and displays the user's friends list
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) {
            finish();
        }
        Object[] friendsArray = ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getFriends().toArray();
        String[] friendsList = new String[friendsArray.length];
        for (int i = 0; i < friendsArray.length; i++) {
            friendsList[i] = friendsArray[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, friendsList);
        ListView listview = (ListView) findViewById(R.id.friends_list);
        listview.setAdapter(adapter);
    }
}