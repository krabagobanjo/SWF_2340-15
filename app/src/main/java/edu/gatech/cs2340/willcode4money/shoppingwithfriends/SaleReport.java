package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Report of sales found by users.
 */
public class SaleReport extends Activity implements Serializable {
    private String owner;
    private String item;
    private double price;
    //Temporary fix..
    private String location;
    //private Location location;
    private String currUser;

    public SaleReport() {
        owner = "";
        item = "";
        location = "";
    }

    public SaleReport(String owner, String item, double price, String location) {
        this.owner = owner;
        this.item = item;

        this.price = price;
        this.location = location;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_report);
        currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    public void makeReport(View view) {
        EditText input1 = (EditText) findViewById(R.id.itemReport);
        EditText input2 = (EditText) findViewById(R.id.priceReport);
        EditText input3 = (EditText) findViewById(R.id.locationReport);

        owner = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        String itemReported = input1.getText().toString();
        String locationReported = input3.getText().toString();
        double priceReported;
        try {
            priceReported = Double.parseDouble(input2.getText().toString());
        } catch (Exception e) {
            priceReported = 0.0;
        }


        if (itemReported != "" && locationReported != "") {
            this.price = priceReported;
            this.item = itemReported;
            this.location = locationReported;
            if (((ShoppingWithFriends) this.getApplication()).getReportBucket().containsKey(this.item)) {
                ((ShoppingWithFriends) this.getApplication()).getReportBucket().get(this.item).add(this);
            } else {
                ArrayList<SaleReport> newBucket = new ArrayList<SaleReport>();
                newBucket.add(this);
                ((ShoppingWithFriends) this.getApplication()).getReportBucket().put(this.item, newBucket);
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter a valid item or location",
                    Toast.LENGTH_LONG).show();
        }
        input1.setText("");
        input2.setText("");
        input3.setText("");
        input1.requestFocus();
        ((ShoppingWithFriends) this.getApplication()).save();
        finish();
    }

    public void cancel(View view) {
        finish();
    }

    /**
     * Gets the item this is for
     * @return the name of the item this is reporting
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the price that the reported item is selling for
     * @return price of the reported item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the reported item
     * @param price the new price of the reported item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * gets the location
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets the location
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * get the owner
     * @return String the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * sets the owner
     * @param owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String a = item + " @ $" + price + " located at: " + location + " posted by: " + owner;
        return a;
    }

    @Override
    public boolean equals(Object other) {
        if (null == other) {
            return false;
        }
        if (this == other) {
            return true;
        }
        if (!(other instanceof SaleReport)) {
            return false;
        }
        SaleReport tempSaleReport = (SaleReport) other;
        return this.getItem().equals(tempSaleReport.getItem())
                && this.getPrice() == tempSaleReport.getPrice()
                && this.getLocation().equals(tempSaleReport.getLocation());
    }
}
