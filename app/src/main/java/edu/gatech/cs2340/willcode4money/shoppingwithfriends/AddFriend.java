package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.gatech.cs2340.willcode4money.shoppingwithfriend.R;

public class AddFriend extends Activity {
    private String currUser;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        currUser = ((ShoppingWithFriends) this.getApplication()).getCurrentUser();
        if (!((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getAuth()) finish();
    }

    /**
     * Searches for and adds a friend to the user's friends list
     * @param view the add friend button
     */
    public void addFriend(View view) {
        EditText etxt1 = (EditText) findViewById(R.id.addFriend_name);
        EditText etxt2 = (EditText) findViewById(R.id.addFriend_email);
        String name = etxt1.getText().toString();
        String email = etxt2.getText().toString();
        User[] users = new User[((ShoppingWithFriends) this.getApplication()).getUsers().size()];
        users = ((ShoppingWithFriends) this.getApplication()).getUsers().values().toArray(users);
        User cUser = ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser);

        boolean foundFriend = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i].getEmail().equals(email) && users[i].getName().equals(name) && !users[i].equals(cUser)
                    && !((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).getFriends().contains(users[i])) {
                ((ShoppingWithFriends) this.getApplication()).getUsers().get(currUser).addFriend(users[i]);
                foundFriend = true;
                break;
            }
        }

        if (foundFriend) {
            Toast.makeText(getApplicationContext(), "Friend Added",
                    Toast.LENGTH_LONG).show();
            ((ShoppingWithFriends) this.getApplication()).save();
        }
        else {
            Toast.makeText(getApplicationContext(), "Couldn't find user",
                    Toast.LENGTH_LONG).show();
        }
        etxt1.setText("");
        etxt2.setText("");
        etxt1.requestFocus();
    }

}
