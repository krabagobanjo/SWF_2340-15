package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Created by JakeWilliams on 2/5/2015.
 */
public class Register extends Activity {
    // UI references.
    private EditText usernameEditText;
    private EditText passwordEditText;
    private TextView infoTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameEditText = (EditText) findViewById(R.id.usernameET);
        passwordEditText = (EditText) findViewById(R.id.passwordET);
        infoTextView = (TextView) findViewById(R.id.infoTV);
    }

    public void register(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        Map<String, String> tempUsers = ((MyApplication) this.getApplication()).getUsers();
        if (!tempUsers.containsKey(username)) {
            tempUsers.put(username, password);
            ((MyApplication) this.getApplication()).setUsers(tempUsers);
            infoTextView.setText("Success");

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);
        } else {
            infoTextView.setText("That username already exists");
            usernameEditText.setText("");
            passwordEditText.setText("");
        }
    }

    /**
     * returns to the previous activity
     */
    public void cancel(View view) {
        finish();
    }
}
