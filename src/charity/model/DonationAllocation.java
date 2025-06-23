package charity.model;

import java.sql.Date;

public class DonationAllocation {
    private int id;
    private int eventId;
    private double amount;
    private String purpose;
    private String status;
    private Date allocationDate;
    private double usedAmount;
    private int createdBy;
    private String recipient;

    public DonationAllocation() {
    }

    public DonationAllocation(int id, int eventId, double amount, String purpose, String status, Date allocationDate, double usedAmount, int createdBy, String recipient) {
        this.id = id;
        this.eventId = eventId;
        this.amount = amount;
        this.purpose = purpose;
        this.status = status;
        this.allocationDate = allocationDate;
        this.usedAmount = usedAmount;
        this.createdBy = createdBy;
        this.recipient = recipient;
    }

    public int getId() {
        return id;
    }

    public int getEventId() {
        return eventId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getStatus() {
        return status;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

   
}
