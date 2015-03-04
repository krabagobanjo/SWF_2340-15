package edu.gatech.cs2340.willcode4money.shoppingwithfriends.email;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender extends AsyncTask<String, Void, Void> {
    private String mailhost = "smtp.gmail.com";
    private String appName = "Shopping With Friends Team 39";
    private String user;
    private String password;
    private String subject = "Password Recovery for Shopping With Friends";
    private String body;
    private Session session;

    public MailSender(String user, String password) {
        super();
        this.user = user;
        this.password = password;

        Properties props = System.getProperties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", mailhost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", this.user);
        props.setProperty("mail.smpt.password", this.password);
        session = Session.getInstance(props, new MailAuthenticator(this.user, this.password));

        //TODO REMOVE THIS IMMEDIATELY
        /*StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);*/
    }

    public void sendMail(String name, String recipient, String theirPass) {
        this.execute(name, recipient, theirPass);
    }

    public synchronized Void doInBackground(String... params) {
        Transport transport = null;
        String name = params[0];
        String recipient = params[1];
        String theirPass = params[2];

        try {
            transport = session.getTransport("smtp");
            body = "Hello, " + name + "!\n\tYour password is below. Please do try and remember it next time!\n\n\n" +
                    theirPass + "\n\n\nSincerely,\nShopping With Friends, Team 39";
            MimeMessage message = new MimeMessage(session);
            message.setSender(new InternetAddress(user));
            message.setFrom(new InternetAddress(user, appName));
            message.setSubject(subject);
            message.setSentDate(new Date());
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipient));
            message.setContent(body, "text/plain");
            //Transport.send(message);
            transport.connect(mailhost, user, password);
            message.saveChanges();
            transport.sendMessage(message, message.getAllRecipients());
        } catch(MessagingException e){
            Log.d("[EMAIL]", "Could not send message (MessagingException)!");
            e.printStackTrace();
        } catch(Exception e) {
            Log.d("[EMAIL]", "Could not send message (Exception)!");
            e.printStackTrace();
        } finally {
            try {
                transport.close();
            } catch(Exception e) {}
        }
        return null;
    }
}