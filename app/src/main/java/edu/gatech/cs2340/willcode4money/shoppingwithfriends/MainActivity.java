package edu.gatech.cs2340.willcode4money.shoppingwithfriends;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * The main splash screen for Shopping with Friends
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Either logs in or registers a new user, depending on the button pressed
     * @param view the button that was pressed
     */
    public void buttonPressed(View view) {
        if (view == findViewById(R.id.sign_in)) {
            startActivity(new Intent(this, Login.class));
        } else if (view == findViewById(R.id.register)) {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
        }
    }
}
