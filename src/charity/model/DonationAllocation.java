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
    private String evidenceUrl;
    private int createdBy;

    public DonationAllocation() {
    }

    public DonationAllocation(int id, int eventId, double amount, String purpose, 
            String status, Date allocationDate, double usedAmount, 
            String evidenceUrl, int createdBy) {
        this.id = id;
        this.eventId = eventId;
        this.amount = amount;
        this.purpose = purpose;
        this.status = status;
        this.allocationDate = allocationDate;
        this.usedAmount = usedAmount;
        this.evidenceUrl = evidenceUrl;
        this.createdBy = createdBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAllocationDate() {
        return allocationDate;
    }

    public void setAllocationDate(Date allocationDate) {
        this.allocationDate = allocationDate;
    }

    public double getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(double usedAmount) {
        this.usedAmount = usedAmount;
    }

    public String getEvidenceUrl() {
        return evidenceUrl;
    }

    public void setEvidenceUrl(String evidenceUrl) {
        this.evidenceUrl = evidenceUrl;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
