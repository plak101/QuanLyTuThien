/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author phaml
 */
public class CharityEvent {
    private int id; // ID tự tăng
    private String name;
    private String category;
    private String description;
    private long targetAmount;
    private long currentAmount;

    private Date dateBegin, dateEnd;
    private List<Donation> donations;// Danh sách đóng góp

    public CharityEvent() {
    }

    public CharityEvent(int id, String name, String category, long targetAmount, long currentAmount, Date dateBegin, Date dateEnd, String description) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public CharityEvent(String name, String category, long targetAmount, long currentAmount, Date dateBegin, Date dateEnd, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(long targetAmount) {
        this.targetAmount = targetAmount;
    }

    public long getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(long currentAmount) {
        this.currentAmount = currentAmount;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
    
}
