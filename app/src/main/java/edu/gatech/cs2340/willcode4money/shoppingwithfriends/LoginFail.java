package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class LoginFail extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_fail);
    }

    /**
     * returns to the previous activity
     */
    public void goBack(View view) {
        finish();
    }
}
