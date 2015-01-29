package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * A login screen that offers login via username/password.
 */
public class Login extends Activity {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String USERNAME = "user";
    private static final String PASSWORD = "pass";

    // UI references.
    private EditText mUserView;
    private EditText mPasswordView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mUserView = (EditText) findViewById(R.id.username);
        mPasswordView = (EditText) findViewById(R.id.password);
    }

    public void login(View view) {
        String username = mUserView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean yay;
        Intent intent;

        yay = username.equals(USERNAME);
        if (yay) {
            yay = password.equals(PASSWORD);
        }
        if (yay) {
            intent = new Intent(this, LoginSuccessful.class);
        } else {
            intent = new Intent(this, LoginFail.class);
        }
        startActivity(intent);
    }
}



