package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Created by Jeongsoo on 2/19/2015.
 */
public class detail_Info extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_info);

        Intent intent = getIntent();
        User friend = (User)intent.getSerializableExtra("friend_info");

        TextView name = (TextView) findViewById(R.id.friend_name);
        TextView e_mail = (TextView) findViewById(R.id.friend_Email);
        TextView rating = (TextView) findViewById(R.id.friend_rate);
        TextView num_sales = (TextView) findViewById(R.id.friend_sales);

        name.setText("Name: " + friend.getName());
        e_mail.setText("E-mail: " + friend.getEmail());
        rating.setText("Rating: " + friend.getRating());
        num_sales.setText("Reports: " +friend.getNumSaleReports());
    }

}
