/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;

/**
 *
 * @author phaml
 */
public class Donation {

    private int id; // ID tự tăng
    private int eventId; // ID của sự kiện từ thiện
    private int userId;  // ID của người quyên góp
    private long amount;
    private Date donationDate;

    public Donation() {
    }

    public Donation(int id, int eventId, int userId, long amount, Date donationDate) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.amount = amount;
        this.donationDate = donationDate;
    }

    public Donation(int eventId, int userId, long amount, Date donationDate) {
        this.eventId = eventId;
        this.userId = userId;
        this.amount = amount;
        this.donationDate = donationDate;
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

    public Date getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate = donationDate;
    }

}
