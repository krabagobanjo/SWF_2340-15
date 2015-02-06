package edu.gatech.cs2340.willcode4money.shoppingwithfriends;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View view) {
        if (view == findViewById(R.id.sign_in)) {
            startActivity(new Intent(this, Login.class));
        } else if (view == findViewById(R.id.register)) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}
