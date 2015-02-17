package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

public class viewFriends extends Activity {
    private String currUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        currUser = ((MyApplication) this.getApplication()).getCurrentUser();
        if (!((MyApplication) this.getApplication()).getUsers().get(currUser).getAuth()) {
            finish();
        }
        Object[] friendsArray = ((MyApplication) this.getApplication()).getUsers().get(currUser).getFriends().toArray();
        String[] friendsList = new String[friendsArray.length];
        for (int i=0; i<friendsArray.length; i++) {
            friendsList[i] = friendsArray[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, friendsList);
        ListView listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);
    }


}