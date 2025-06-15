/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.service.IService;

import charity.model.Donation;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IDonationService {
    public List<Donation> getAllDonation();
    public List<Donation> getDonationByUserId(int userId);
    public List<Donation> getDonationByEventId(int eventId);
    public Donation getDonationById(int id);
    public boolean addDonation(Donation donation);
    public boolean updateDonation(Donation donation);
    public boolean deleteDonation(int donationId);
    public int getDonationCount();
    public boolean processDonation(int donationId, int adminId, String note);
    public boolean distributeDonation(int donationId, int eventId, double amount, String note, int adminId);
    public double getTotalUnprocessedAmount();
    public List<Donation> getUnprocessedDonations();
    public List<Donation> getDonationByStatus(String status);
    public double getTotalDonationByEvent(int eventId);
}
