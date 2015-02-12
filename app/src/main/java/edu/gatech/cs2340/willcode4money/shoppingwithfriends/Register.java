package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Created by JakeWilliams on 2/5/2015.
 */
public class Register extends Activity {
    // UI references.
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private TextView infoTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText) findViewById(R.id.nameText);
        emailEditText = (EditText) findViewById(R.id.emailText);
        usernameEditText = (EditText) findViewById(R.id.usernameET);
        passwordEditText = (EditText) findViewById(R.id.passwordET);
        passwordConfirmEditText = (EditText) findViewById(R.id.passwordConfirmET);
        infoTextView = (TextView) findViewById(R.id.infoTV);
    }

    public void register(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        Map<String, User> tempUsers = ((MyApplication) this.getApplication()).getUsers();
        if (password.equals(passwordConfirm)) {
            if (!tempUsers.containsKey(username)) {
                tempUsers.put(username, new User(username, password, email, name));
                ((MyApplication) this.getApplication()).setUsers(tempUsers);
                infoTextView.setText("Success");
                findViewById(R.id.cancelBtn).setVisibility(View.INVISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            } else {
                infoTextView.setText("That username already exists");
                nameEditText.setText("");
                emailEditText.setText("");
                usernameEditText.setText("");
                passwordEditText.setText("");
                passwordConfirmEditText.setText("");
            }
        } else {
            infoTextView.setText("There was a mismatch in the passwords");
            passwordEditText.setText("");
            passwordConfirmEditText.setText("");
        }
    }

    /**
     * returns to the previous activity
     */
    public void cancel(View view) {
        finish();
    }
}
