package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * user's requested item list with price
 */
public class ViewReports extends Activity {

    /**
     * Creates and displays the user's list of requested items
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        String currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) {
            finish();
        }

        Object[] reportArray = ((ShoppingWithFriends) this.getApplication()).getValidReports().toArray();
        String[] reportList = new String[reportArray.length];

        for (int i = 0; i < reportArray.length; i++) {
            reportList[i] = reportArray[i].toString();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_reports, reportList);
        ListView listview = (ListView) findViewById(R.id.report_list);
        listview.setAdapter(adapter);
    }
}