package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Implementation of user registration.
 */
public class Register extends Activity {
    // UI references.
    private EditText nameEditText;
    private EditText emailEditText;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText passwordConfirmEditText;
    private TextView infoTextView;

    /**
     * Initializes the page with text fields and buttons
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nameEditText = (EditText) findViewById(R.id.nameText);
        emailEditText = (EditText) findViewById(R.id.emailText);
        usernameEditText = (EditText) findViewById(R.id.usernameET);
        passwordEditText = (EditText) findViewById(R.id.passwordET);
        passwordConfirmEditText = (EditText) findViewById(R.id.passwordConfirmET);
    }

    /**
     * Registers a new user. Fails if the user already exists.
     */
    public void register(View view) {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String passwordConfirm = passwordConfirmEditText.getText().toString();

        if (password.equals(passwordConfirm)) {
            if (!tempUsers.containsKey(username)) {
                ((ShoppingWithFriends) this.getApplication()).addUser(new User(username, password, email, name));
                Toast.makeText(getApplicationContext(), "Success",
                        Toast.LENGTH_LONG).show();
                //Remove cancel button from screen
                findViewById(R.id.cancelBtn).setVisibility(View.INVISIBLE);
                //Wait 1 second before returning back
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 1000);
            } else {
                Toast.makeText(getApplicationContext(), "That username already exists",
                        Toast.LENGTH_LONG).show();
                nameEditText.setText("");
                emailEditText.setText("");
                usernameEditText.setText("");
                passwordEditText.setText("");
                passwordConfirmEditText.setText("");
            }
        } else {
            Toast.makeText(getApplicationContext(), "There was a mismatch in the passwords",
                    Toast.LENGTH_LONG).show();
            passwordEditText.setText("");
            passwordConfirmEditText.setText("");
        }
    }

    /**
     * Returns to the previous activity
     */
    public void cancel(View view) {
        finish();
    }
}
