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
    private String status; // Pending, Processed, Distributed
    private String processingNote;
    private Date processedDate;
    private int processedBy;
    private String donorName;
    private String eventName;
    private String processorName;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProcessingNote() {
        return processingNote;
    }

    public void setProcessingNote(String processingNote) {
        this.processingNote = processingNote;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

    public int getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(int processedBy) {
        this.processedBy = processedBy;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getProcessorName() {
        return processorName;
    }

    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }

}
