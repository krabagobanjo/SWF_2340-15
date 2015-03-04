package edu.gatech.cs2340.willcode4money.shoppingwithfriends.email;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


class MailAuthenticator extends Authenticator {
    String user;
    String pw;
    public MailAuthenticator (String username, String password)
    {
        super();
        this.user = username;
        this.pw = password;
    }
    public PasswordAuthentication getPasswordAuthentication()
    {
        return new PasswordAuthentication(user, pw);
    }
}