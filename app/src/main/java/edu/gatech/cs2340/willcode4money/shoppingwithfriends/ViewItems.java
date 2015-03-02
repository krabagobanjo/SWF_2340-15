package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Created by Hanbeen on 2/25/2015.
 * user's requested item list with price
 */
public class ViewItems extends Activity {

    /**
     * Creates and displays the user's item list
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        String currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) {
            finish();
        }
        Object[] requestArray = ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getRequests().toArray();
        String[] itemsList = new String[requestArray.length];
        double[] priceList = new double[requestArray.length];
        for (int i = 0; i < requestArray.length; i++) {
            itemsList[i] = requestArray[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.listitem, itemsList);
        ListView listview = (ListView) findViewById(R.id.item_list);
        listview.setAdapter(adapter);
    }
}
