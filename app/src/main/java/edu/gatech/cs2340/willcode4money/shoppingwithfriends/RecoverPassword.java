package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import willcode4money.cs2340.gatech.edu.shoppingwithfriends.R;

/**
 * Allows the user to retrieve a lost password.
 */
public class RecoverPassword extends Activity {
    private static final String USERNAME = "swf.team39@gmail.com";
    private static final String PASSWORD = "wc4mteam39";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);

        final Button send = (Button) this.findViewById(R.id.send);
        /*send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                try {
                    MailSender sender = new MailSender(USERNAME, PASSWORD);
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            "user@yahoo.com");
                } catch (Exception e) {
                    Log.e("[EMAIL]", e.getMessage(), e);
                }

            }
        });*/

    }
}
