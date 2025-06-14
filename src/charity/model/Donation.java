/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.model;

//import java.util.Date;
import java.sql.*;

/**
 *
 * @author phaml
 */
public class Donation {

    private int id; // ID tự tăng
    private int eventId; // ID của sự kiện từ thiện
    private int userId;  // ID của người quyên góp
    private long amount;
    private Timestamp donationDate;
    private String description;

    public Donation() {
    }

    public Donation(int id, int eventId, int userId, long amount, Timestamp donationDate, String description) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.amount = amount;
        this.donationDate = donationDate;
        this.description= description;
    }

    public Donation(int eventId, int userId, long amount, Timestamp donationDate) {
        this.eventId = eventId;
        this.userId = userId;
        this.amount = amount;
        this.donationDate = donationDate;
    }
    
    public Donation(int eventId, int userId, long amount, Timestamp donationDate, String desciption) {
        this.eventId = eventId;
        this.userId = userId;
        this.amount = amount;
        this.donationDate = donationDate;
        this.description= desciption;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

}
