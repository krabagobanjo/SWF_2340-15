package willcode4money.cs2340.gatech.edu.shoppingwithfriends;

import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import edu.gatech.cs2340.willcode4money.shoppingwithfriends.Login;
import edu.gatech.cs2340.willcode4money.shoppingwithfriends.R;
import edu.gatech.cs2340.willcode4money.shoppingwithfriends.Register;
import edu.gatech.cs2340.willcode4money.shoppingwithfriends.User;


/**
 * Created by JakeWilliams on 3/27/2015.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<Login> {

    public LoginActivityTest() {
        super(Login.class);
    }

    private Login activity;
    private TextView mUserViewTesting;
    private TextView mPasswordViewTesting;
    private Map<String, User> usersTesting;

    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        activity = getActivity();

    }

    public void testUIElements() throws Exception {
        Button login = (Button) activity.findViewById(R.id.signinBtn);
        Button cancel = (Button) activity.findViewById(R.id.button9);
        mUserViewTesting = (TextView) activity.findViewById(R.id.username);
        mPasswordViewTesting = (TextView) activity.findViewById(R.id.password);
        assertEquals("Incorrect label", "Register", login.getText());
        assertEquals("Incorrect label", "Cancel", cancel.getText());
        assertEquals("Incorrect hint", "Username", mUserViewTesting.getHint());
        assertEquals("Incorrect hint", "Name", mPasswordViewTesting.getHint());
    }

    public void testLogin() throws  Exception {

    }

    public void testCancel() throws Exception {
        Button cancel = (Button) activity.findViewById(R.id.button9);
        TouchUtils.clickView(this, cancel);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {assertTrue(activity.isFinishing()); }
        }, 1200);
    }
}
