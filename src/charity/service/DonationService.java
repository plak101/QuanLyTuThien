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

    @Override
    public boolean processDonation(int donationId, int adminId, String note) {
        Donation donation = donationRepository.getDonationById(donationId);
        if (donation == null || !"Pending".equals(donation.getStatus())) {
            return false;
        }
        
        donation.setStatus("Processed");
        donation.setProcessedBy(adminId);
        donation.setProcessedDate(new java.sql.Date(System.currentTimeMillis()));
        donation.setProcessingNote(note);
        
        return donationRepository.updateDonation(donation);
    }

    @Override
    public boolean distributeDonation(int donationId, int eventId, double amount, String note, int adminId) {
        Donation donation = donationRepository.getDonationById(donationId);
        if (donation == null || !"Processed".equals(donation.getStatus())) {
            return false;
        }
        
        // Kiểm tra số tiền phân phối không vượt quá số tiền quyên góp
        if (amount > donation.getAmount()) {
            return false;
        }
        
        // Ghi nhận việc phân phối tiền
        return donationRepository.addDistribution(donationId, eventId, amount, note, adminId);
    }

    @Override
    public double getTotalUnprocessedAmount() {
        List<Donation> unprocessedDonations = getDonationByStatus("Pending");
        return unprocessedDonations.stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }

    @Override
    public List<Donation> getUnprocessedDonations() {
        return getDonationByStatus("Pending");
    }

    @Override
    public List<Donation> getDonationByStatus(String status) {
        return donationRepository.getDonationByStatus(status);
    }

    @Override
    public double getTotalDonationByEvent(int eventId) {
        List<Donation> donations = getDonationByEventId(eventId);
        return donations.stream()
                .mapToDouble(Donation::getAmount)
                .sum();
    }
}
