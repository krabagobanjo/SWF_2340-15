package edu.gatech.cs2340.willcode4money.shoppingwithfriends;

import java.io.Serializable;

/**
 * Report of sales found by users.
 */
public class SaleReport implements Serializable {

    private final String item;
    private double price;
    private final String reportedBy;
    private final String reportedTo;
    //private Location location;

    public SaleReport(String item, String reportedBy, String reportedTo, double price) {
        this.item = item;
        this.reportedBy = reportedBy;
        this.reportedTo = reportedTo;
        this.price = price;
    }

    /**
     * Gets the username of who this was reported to.
     * @return the username of the user this is reported to
     */
    public String getReportedTo() {
        return reportedTo;
    }

    /**
     * Gets the item this is for
     * @return the name of the item this is reporting
     */
    public String getItem() {
        return item;
    }

    /**
     * Gets the price that the reported item is selling for
     * @return price of the reported item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Updates the price of the reported item
     * @param price the new price of the reported item
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the username of who this was reported by.
     * @return the username of the user this is reported by
     */
    public String getReportedBy() {
        return reportedBy;
    }

}
