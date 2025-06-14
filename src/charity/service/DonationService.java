package charity.service;

import charity.model.Donation;
import charity.repository.DonationRepository;
import charity.repository.IRepository.IDonationRepository;
import charity.service.IService.IDonationService;
import java.util.List;

/**
 *
 * @author phaml
 */
public class DonationService implements IDonationService{

    private IDonationRepository donationRepository;

    public DonationService() {
        donationRepository = new DonationRepository();
    }

    @Override
    public List<Donation> getAllDonation() {
        return donationRepository.getAllDonation();
    }

    @Override
    public List<Donation> getDonationByUserId(int userId) {
        return donationRepository.getDonationByUserId(userId);
    }

    @Override
    public List<Donation> getDonationByEventId(int eventId) {
        return donationRepository.getDonationByEventId(eventId);
    }

    @Override
    public boolean addDonation(Donation donation) {
        return donationRepository.addDonation(donation);
    }

    @Override
    public boolean updateDonation(Donation donation) {
        return donationRepository.updateDonation(donation);
    }

    @Override
    public boolean deleteDonation(int donationId) {
        return donationRepository.deleteDonation(donationId);
    }
    
    @Override
    public int getDonationCount(){
        return donationRepository.getDonationCount();
    }

    @Override
    public Donation getDonationById(int id) {
        return donationRepository.getDonationById(id);
    }
}
