package charity.model;

import java.sql.Date;

public class Distribution {
    private int id;
    private int donationId;
    private int eventId;
    private double amount;
    private Date distributionDate;
    private String note;
    private int distributedBy;

    public Distribution() {
    }

    public Distribution(int id, int donationId, int eventId, double amount, 
            Date distributionDate, String note, int distributedBy) {
        this.id = id;
        this.donationId = donationId;
        this.eventId = eventId;
        this.amount = amount;
        this.distributionDate = distributionDate;
        this.note = note;
        this.distributedBy = distributedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDistributionDate() {
        return distributionDate;
    }

    public void setDistributionDate(Date distributionDate) {
        this.distributionDate = distributionDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getDistributedBy() {
        return distributedBy;
    }

    public void setDistributedBy(int distributedBy) {
        this.distributedBy = distributedBy;
    }
}
