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
    public boolean addDonation(Donation donation);
    public boolean updateDonation(Donation donation);
    public boolean deleteDonation(int donationId);
    public int getDonationCount();
}
