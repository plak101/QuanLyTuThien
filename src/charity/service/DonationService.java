package charity.service;

import charity.model.Donation;
import charity.repository.DonationRepository;
import charity.repository.IRepository.IDonationRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class DonationService {

    private IDonationRepository donationRepository;

    public DonationService() {
        donationRepository = new DonationRepository();
    }

    public List<Donation> getAllDonation() {
        return donationRepository.getAllDonation();
    }

    public List<Donation> getDonationByUserId(int userId) {
        return donationRepository.getDonationByUserId(userId);
    }

    public List<Donation> getDonationByEventId(int eventId) {
        return donationRepository.getDonationByEventId(eventId);
    }

    public boolean addDonation(Donation donation) {
        return donationRepository.addDonation(donation);
    }

    public boolean updateDonation(Donation donation) {
        return donationRepository.updateDonation(donation);
    }

    public boolean deleteDonation(int donationId) {
        return donationRepository.deleteDonation(donationId);
    }
    public int getDonationCount(){
        return donationRepository.getDonationCount();
    }
}
