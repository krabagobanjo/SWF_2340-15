package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.Map;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * A login screen that offers login via username/password.
 */
public class Login extends Activity {

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;
    private Map<String, User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);

        users = ((MyApplication) this.getApplication()).getUsers();
    }

    public void login(View view) {
        String username = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();
        Intent intent;

        if (users.containsKey(username) && users.get(username).getPassword().equals(password)) {
            intent = new Intent(this, MainScreen.class);
            ((MyApplication) this.getApplication()).setCurrentUser(username);
            ((MyApplication) this.getApplication()).getUsers().get(username).setAuth(true);
        } else {
            intent = new Intent(this, LoginFail.class);
        }
        startActivity(intent);
    }

    public void cancel(View view) {
        finish();
    }
}



