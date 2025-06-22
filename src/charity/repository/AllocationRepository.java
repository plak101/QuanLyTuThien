package charity.repository;

import charity.model.DonationAllocation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AllocationRepository {
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public AllocationRepository() {
        conn = ConnectionDB.getConnection();
    }

    public boolean save(DonationAllocation allocation) {
        String query = "INSERT INTO donation_allocations (eventId, amount, purpose, status, " +
                      "allocationDate, usedAmount, evidenceUrl, createdBy) " +
                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, allocation.getEventId());
            ps.setDouble(2, allocation.getAmount());
            ps.setString(3, allocation.getPurpose());
            ps.setString(4, allocation.getStatus());
            ps.setDate(5, allocation.getAllocationDate());
            ps.setDouble(6, allocation.getUsedAmount());
            ps.setString(7, allocation.getEvidenceUrl());
            ps.setInt(8, allocation.getCreatedBy());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    public boolean updateStatus(int allocationId, String status, double usedAmount) {
        String query = "UPDATE donation_allocations SET status = ?, usedAmount = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            ps.setDouble(2, usedAmount);
            ps.setInt(3, allocationId);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    public boolean updateEvidence(int allocationId, String evidenceUrl) {
        String query = "UPDATE donation_allocations SET evidenceUrl = ? WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, evidenceUrl);
            ps.setInt(2, allocationId);

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    public boolean deleteById(int allocationId) {
        String query = "DELETE FROM donation_allocations WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, allocationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    public List<DonationAllocation> getAllByEventId(int eventId) {
        List<DonationAllocation> allocations = new ArrayList<>();
        String query = "SELECT * FROM donation_allocations WHERE eventId = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                DonationAllocation allocation = new DonationAllocation();
                allocation.setId(rs.getInt("id"));
                allocation.setEventId(rs.getInt("eventId"));
                allocation.setAmount(rs.getDouble("amount"));
                allocation.setPurpose(rs.getString("purpose"));
                allocation.setStatus(rs.getString("status"));
                allocation.setAllocationDate(rs.getDate("allocationDate"));
                allocation.setUsedAmount(rs.getDouble("usedAmount"));
                allocation.setEvidenceUrl(rs.getString("evidenceUrl"));
                allocation.setCreatedBy(rs.getInt("createdBy"));
                // Bổ sung các trường minh bạch
                try { allocation.setRecipient(rs.getString("recipient")); } catch (Exception e) {}
                try { allocation.setGiftType(rs.getString("giftType")); } catch (Exception e) {}
                try { allocation.setTotalGifts(rs.getInt("totalGifts")); } catch (Exception e) {}
                try { allocation.setGiftValue(rs.getDouble("giftValue")); } catch (Exception e) {}
                try { allocation.setNumRecipients(rs.getInt("numRecipients")); } catch (Exception e) {}
                try { allocation.setCriteria(rs.getString("criteria")); } catch (Exception e) {}
                try { allocation.setRecipientList(rs.getString("recipientList")); } catch (Exception e) {}
                try { allocation.setShippingCost(rs.getDouble("shippingCost")); } catch (Exception e) {}
                try { allocation.setReceipt(rs.getString("receipt")); } catch (Exception e) {}
                try { allocation.setFeedback(rs.getString("feedback")); } catch (Exception e) {}
                allocations.add(allocation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return allocations;
    }

    public double getTotalAllocatedAmount(int eventId) {
        double total = 0;
        String query = "SELECT SUM(amount) as total FROM donation_allocations WHERE eventId = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return total;
    }

    public DonationAllocation getById(int allocationId) {
        DonationAllocation allocation = null;
        String query = "SELECT * FROM donation_allocations WHERE id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, allocationId);
            rs = ps.executeQuery();
            if (rs.next()) {
                allocation = new DonationAllocation();
                allocation.setId(rs.getInt("id"));
                allocation.setEventId(rs.getInt("eventId"));
                allocation.setAmount(rs.getDouble("amount"));
                allocation.setPurpose(rs.getString("purpose"));
                allocation.setStatus(rs.getString("status"));
                allocation.setAllocationDate(rs.getDate("allocationDate"));
                allocation.setUsedAmount(rs.getDouble("usedAmount"));
                allocation.setEvidenceUrl(rs.getString("evidenceUrl"));
                allocation.setCreatedBy(rs.getInt("createdBy"));
                // Bổ sung các trường minh bạch nếu có trong DB
                try { allocation.setRecipient(rs.getString("recipient")); } catch (Exception e) {}
                try { allocation.setGiftType(rs.getString("giftType")); } catch (Exception e) {}
                try { allocation.setTotalGifts(rs.getInt("totalGifts")); } catch (Exception e) {}
                try { allocation.setGiftValue(rs.getDouble("giftValue")); } catch (Exception e) {}
                try { allocation.setNumRecipients(rs.getInt("numRecipients")); } catch (Exception e) {}
                try { allocation.setCriteria(rs.getString("criteria")); } catch (Exception e) {}
                try { allocation.setRecipientList(rs.getString("recipientList")); } catch (Exception e) {}
                try { allocation.setShippingCost(rs.getDouble("shippingCost")); } catch (Exception e) {}
                try { allocation.setReceipt(rs.getString("receipt")); } catch (Exception e) {}
                try { allocation.setFeedback(rs.getString("feedback")); } catch (Exception e) {}
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources();
        }
        return allocation;
    }

    public boolean updateAllocation(DonationAllocation allocation) {
        String query = "UPDATE donation_allocations SET amount=?, purpose=?, status=?, allocationDate=?, usedAmount=?, evidenceUrl=?, createdBy=?, recipient=?, giftType=?, totalGifts=?, giftValue=?, numRecipients=?, criteria=?, recipientList=?, shippingCost=?, receipt=?, feedback=? WHERE id=?";
        try {
            ps = conn.prepareStatement(query);
            ps.setDouble(1, allocation.getAmount());
            ps.setString(2, allocation.getPurpose());
            ps.setString(3, allocation.getStatus());
            ps.setDate(4, allocation.getAllocationDate());
            ps.setDouble(5, allocation.getUsedAmount());
            ps.setString(6, allocation.getEvidenceUrl());
            ps.setInt(7, allocation.getCreatedBy());
            ps.setString(8, allocation.getRecipient());
            ps.setString(9, allocation.getGiftType());
            ps.setInt(10, allocation.getTotalGifts());
            ps.setDouble(11, allocation.getGiftValue());
            ps.setInt(12, allocation.getNumRecipients());
            ps.setString(13, allocation.getCriteria());
            ps.setString(14, allocation.getRecipientList());
            ps.setDouble(15, allocation.getShippingCost());
            ps.setString(16, allocation.getReceipt());
            ps.setString(17, allocation.getFeedback());
            ps.setInt(18, allocation.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AllocationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
