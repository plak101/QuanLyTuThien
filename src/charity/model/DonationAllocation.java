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
    private String recipient;
    private String giftType;
    private int totalGifts;
    private double giftValue;
    private int numRecipients;
    private String criteria;
    private String recipientList;
    private double shippingCost;
    private String receipt;
    private String feedback;

    public DonationAllocation() {
    }

    public DonationAllocation(int id, int eventId, double amount, String purpose, 
            String status, Date allocationDate, double usedAmount, 
            String evidenceUrl, int createdBy, String recipient, 
            String giftType, int totalGifts, double giftValue, 
            int numRecipients, String criteria, String recipientList, 
            double shippingCost, String receipt, String feedback) {
        this.id = id;
        this.eventId = eventId;
        this.amount = amount;
        this.purpose = purpose;
        this.status = status;
        this.allocationDate = allocationDate;
        this.usedAmount = usedAmount;
        this.evidenceUrl = evidenceUrl;
        this.createdBy = createdBy;
        this.recipient = recipient;
        this.giftType = giftType;
        this.totalGifts = totalGifts;
        this.giftValue = giftValue;
        this.numRecipients = numRecipients;
        this.criteria = criteria;
        this.recipientList = recipientList;
        this.shippingCost = shippingCost;
        this.receipt = receipt;
        this.feedback = feedback;
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

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public int getTotalGifts() {
        return totalGifts;
    }

    public void setTotalGifts(int totalGifts) {
        this.totalGifts = totalGifts;
    }

    public double getGiftValue() {
        return giftValue;
    }

    public void setGiftValue(double giftValue) {
        this.giftValue = giftValue;
    }

    public int getNumRecipients() {
        return numRecipients;
    }

    public void setNumRecipients(int numRecipients) {
        this.numRecipients = numRecipients;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(String recipientList) {
        this.recipientList = recipientList;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
