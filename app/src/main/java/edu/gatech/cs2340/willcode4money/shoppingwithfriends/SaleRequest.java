package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Contains a request made by a user for certain items
 */
public class SaleRequest extends Activity implements Serializable {
    private final String owner;
    private final String item;
    private double maxPrice;

    private String currUser;
    public SaleRequest() {
        owner = currUser;
        item = null;
    }

    public SaleRequest(String owner, String item, double maxPrice) {
        this.owner = owner;
        this.item = item;
        this.maxPrice = maxPrice;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_request);
        currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    public void makeRequest(View view) {
        EditText input1 = (EditText) findViewById(R.id.item);
        EditText input2 = (EditText) findViewById(R.id.price);

        String itemRequested = input1.getText().toString();
        double price = Integer.parseInt(input2.getText().toString());

        ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).addRequest(itemRequested, price);

        Toast.makeText(getApplicationContext(), "Request Made!",
                Toast.LENGTH_LONG).show();

        input1.setText("");
        input2.setText("");
        input1.requestFocus();
        ((ShoppingWithFriends) this.getApplication()).save();
    }

    public void cancel(View view) {
        finish();
    }

    /**
     * Gets the username of the creator of this request
     * @return the username of the creator
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the item that this is for
     * @return the name of the requested item
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the maximum price the owner wants the item for
     * @return the max. price of the item
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Updates the max. price the owner is looking for
     * @param maxPrice the new price of the requested item
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
        ((ShoppingWithFriends) this.getApplication()).save();
    }

    @Override
    public String toString() {
        String a = getOwner() + ": " + getItem() + " @ $" + getMaxPrice();
        return a;
    }
}
