package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Will Code 4 Money on 2/9/2015.
 */
public class User {
    private List<User> friends;

    private String name;
    private String username;
    private String password;
    private String email;
    private List<Integer> ratings;

    private List<SaleRequest> requests;
    private List<SaleReport> salesReported;
    private List<SaleReport> salesReceived;

    public User(String username, String password, String email, String name) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.ratings = new ArrayList<Integer>();
        this.friends = new ArrayList<User>();
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
        friend.friends.add(this);
    }

    public void removeFriend(User friend) {
        this.friends.remove(friend);
        friend.friends.remove(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SaleRequest> getRequests() {
        return requests;
    }

    public void setRequests(List<SaleRequest> requests) {
        this.requests = requests;
    }

    public List<SaleReport> getSalesReported() {
        return salesReported;
    }

    public void setSalesReported(List<SaleReport> salesReported) {
        this.salesReported = salesReported;
    }

    public List<SaleReport> getSalesReceived() {
        return salesReceived;
    }

    public void setSalesReceived(List<SaleReport> salesReceived) {
        this.salesReceived = salesReceived;
    }

    public void addRating(int rating) {
        ratings.add(rating);
    }

    public double getRating() {
        int total = 0;
        for (Integer rating : ratings) {
            total += rating;
        }
        return ((double) total) / ratings.size();
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (! (o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (!username.equals(user.username)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}
