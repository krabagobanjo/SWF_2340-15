package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Collection;
import java.util.Set;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

public class addFriend extends Activity {
    private String currUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        currUser = ((MyApplication) this.getApplication()).getCurrentUser();
        if (!((MyApplication) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    public void addFriend(View view) {
        EditText etxt1 = (EditText) findViewById(R.id.editText);
        EditText etxt2 = (EditText) findViewById(R.id.editText2);
        String name = etxt1.getText().toString();
        String email = etxt2.getText().toString();
        User[] users = new User[((MyApplication) this.getApplication()).getUsers().size()];
        users = ((MyApplication) this.getApplication()).getUsers().values().toArray(users);
        User cUser = ((MyApplication) this.getApplication()).getUsers().get(currUser);
        boolean foundFriend = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getEmail().equals(email) && users[i].getName().equals(name) && !users[i].equals(cUser)) {
                ((MyApplication) this.getApplication()).getUsers().get(currUser).addFriend(users[i]);
                foundFriend = true;
                break;
            }
        }

        if (foundFriend) Toast.makeText(getApplicationContext(), "Friend Added",
                Toast.LENGTH_LONG).show();
        else Toast.makeText(getApplicationContext(), "Couldn't find user",
                Toast.LENGTH_LONG).show();

    }

}
