package edu.gatech.cs2340.willcode4money.shoppingwithfriends;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Activity ACTIVITY = this;
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(ACTIVITY, Login.class));
//            }
//        },5000); //adding five sec delay
    }

    public void buttonPressed(View view) {
        if (view == findViewById(R.id.sign_in)) {
            startActivity(new Intent(this, Login.class));
        }
    }
}
