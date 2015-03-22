package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import edu.gatech.cs2340.willcode4money.shoppingwithfriend.R;

/**
 * Created by Jeongsoo on 2015-03-19.
 */
public class SaleInfo extends Activity {
    private String loCation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saleinfo);

        Intent intent = getIntent();
        SaleReport Report = (SaleReport) intent.getSerializableExtra("report_info");

        TextView name = (TextView) findViewById(R.id.friend_name);
        TextView item = (TextView) findViewById(R.id.friend_Item);
        TextView price = (TextView) findViewById(R.id.friend_Price);
        TextView location = (TextView) findViewById(R.id.friend_Location);

        name.setText("Name: " + Report.getOwner());
        item.setText("Item: " + Report.getItem());
        price.setText("Price: " + Report.getPrice());
        location.setText("Location: " + Report.getLocation());

        loCation = Report.getLocation();
        final Activity thisItem = this;
        final Button button = (Button) findViewById(R.id.GoogleMap);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(thisItem, MapsActivity.class);
                intent.putExtra("Location_info", loCation);
                startActivity(intent);
            }
        });
    }
}
