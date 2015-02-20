package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * The user's friends list.
 */
public class ViewFriend extends Activity {
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
        registerForContextMenu(listview);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
                TextView clickedFriend = (TextView) view;
                Toast.makeText(getApplicationContext(), "Friend [" + clickedFriend.getText() + "]", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Options");
        menu.add(1, 1, 1, "Details");
        menu.add(1, 2, 2, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == 1) {startActivity(new Intent(this, detail_Info.class));}
        Toast.makeText(this, "Item id ["+itemId+"]", Toast.LENGTH_SHORT).show();
        return true;
    }
}