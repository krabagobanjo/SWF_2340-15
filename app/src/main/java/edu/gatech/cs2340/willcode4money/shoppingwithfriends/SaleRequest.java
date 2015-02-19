package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

/**
 * Contains a request made by a user for certain items
 */
public class SaleRequest {
    private final String owner;
    private final String item;
    private double maxPrice;

    public SaleRequest(String owner, String item, double maxPrice) {
        this.owner = owner;
        this.item = item;
        this.maxPrice = maxPrice;
    }

    /**
     * Gets the username of the creator of this request
     * @return the username of the creator
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Gets the item that this is for
     * @return the name of the requested item
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the maximum price the owner wants the item for
     * @return the max. price of the item
     */
    public double getMaxPrice() {
        return maxPrice;
    }

    /**
     * Updates the max. price the owner is looking for
     * @param maxPrice the new price of the requested item
     */
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
}
