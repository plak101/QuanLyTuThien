package charity.service;

import charity.model.DonationAllocation;
import charity.repository.AllocationRepository;
import java.util.List;

public class AllocationService {
    private AllocationRepository repository;
    private DonationService donationService;
    
    public AllocationService() {
        this.repository = new AllocationRepository();
        this.donationService = new DonationService();
    }
    
    public boolean createAllocationPlan(DonationAllocation allocation) {
        // Validate số tiền phân bổ không vượt quá số đã quyên góp
        double totalDonation = donationService.getTotalDonationByEvent(allocation.getEventId());
        double totalAllocated = repository.getTotalAllocatedAmount(allocation.getEventId());
        
        if (allocation.getAmount() > (totalDonation - totalAllocated)) {
            return false;
        }
        
        return repository.save(allocation);
    }
    
    public boolean updateAllocationStatus(int allocationId, String status, double usedAmount) {
        // Cập nhật trạng thái và số tiền đã sử dụng
        return repository.updateStatus(allocationId, status, usedAmount);
    }
    
    public boolean uploadEvidence(int allocationId, String evidenceUrl) {
        // Upload minh chứng sử dụng tiền
        return repository.updateEvidence(allocationId, evidenceUrl);
    }
    
    public List<DonationAllocation> getAllocationsByEvent(int eventId) {
        return repository.getAllByEventId(eventId);
    }
    
    public double getTotalAllocatedAmount(int eventId) {
        return repository.getTotalAllocatedAmount(eventId);
    }
    
    public double getRemainingAmount(int eventId) {
        double totalDonation = donationService.getTotalDonationByEvent(eventId);
        double totalAllocated = repository.getTotalAllocatedAmount(eventId);
        return totalDonation - totalAllocated;
    }
    
    public DonationAllocation getAllocationById(int allocationId) {
        return repository.getById(allocationId);
    }
    
    public boolean deleteAllocationById(int allocationId) {
        return repository.deleteById(allocationId);
    }
    
    public boolean updateAllocation(DonationAllocation allocation) {
        return repository.updateAllocation(allocation);
    }
}
