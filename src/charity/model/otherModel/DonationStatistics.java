
package charity.model.otherModel;

import java.sql.Date;

public class DonationStatistics {
    private Date date;
    private Long totalAmount;
    private int totalDonation;
    private String category;
    private String eventName;
    private String organizationName;

    public DonationStatistics(Date date, Long totalAmount, int totalDonation, String category, String eventName, String organizationName) {
        this.date = date;
        this.totalAmount = totalAmount;
        this.totalDonation = totalDonation;
        this.category = category;
        this.eventName = eventName;
        this.organizationName = organizationName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getTotalDonation() {
        return totalDonation;
    }

    public void setTotalDonation(int totalDonation) {
        this.totalDonation = totalDonation;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }
    
    
}
