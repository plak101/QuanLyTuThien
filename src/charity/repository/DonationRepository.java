
package charity.repository;

import charity.repository.IRepository.IDonationRepository;
import charity.model.Donation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phaml
 */
public class DonationRepository implements IDonationRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public DonationRepository() {
        conn = ConnectionDB.getConnection();
    }

    @Override
    public List<Donation> getAllDonation() {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM donation ORDER BY donationDate DESC";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Donation donation = new Donation(
                        rs.getInt("donationId"),
                        rs.getInt("eventId"),
                        rs.getInt("userId"),
                        rs.getLong("amount"),
                        rs.getTimestamp("donationDate"),
                        rs.getString("description")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
            return donations;
        }
    }

    @Override
    public List<Donation> getDonationByUserId(int userId) {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM donation WHERE userId =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Donation donation = new Donation(
                        rs.getInt("donationId"),
                        rs.getInt("eventId"),
                        rs.getInt("userId"),
                        rs.getLong("amount"),
                        rs.getTimestamp("donationDate"),
                        rs.getString("description")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
            return donations;
        }
    }

    @Override
    public List<Donation> getDonationByEventId(int eventId) {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM donation WHERE eventId =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Donation donation = new Donation(
                        rs.getInt("donationId"),
                        rs.getInt("eventId"),
                        rs.getInt("userId"),
                        rs.getLong("amount"),
                        rs.getTimestamp("donationDate"),
                        rs.getString("description")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
            return donations;
        }
    }

    @Override
    public boolean addDonation(Donation donation) {
        String query = "INSERT INTO donation ( eventId, userId, amount, donationDate, description)"
                + "VALUES(?,?,?,?,?)";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donation.getEventId());
            ps.setInt(2, donation.getUserId());
            ps.setLong(3, donation.getAmount());
            ps.setTimestamp(4, (Timestamp) donation.getDonationDate());
            ps.setString(5, donation.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }

    }

    @Override
    public boolean updateDonation(Donation donation) {
        String query = "UPDATE donation SET eventId=?, userId=?, amount=?,  donationDate=?, description=? WHERE donationId = ?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donation.getEventId());
            ps.setInt(2, donation.getUserId());
            ps.setLong(3, donation.getAmount());
            ps.setTimestamp(4,  donation.getDonationDate());
            ps.setString(5, donation.getDescription());
            ps.setInt(6, donation.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }

    }

    @Override
    public boolean deleteDonation(int donationId) {
        String query = "DELETE FROM donation WHERE donationId=?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donationId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }

    }

    @Override
    public void closeResources(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getDonationCount() {
        int count =0;
        String query = "SELECT COUNT(*) FROM donation";
        conn = ConnectionDB.getConnection();
        try {
            ps =conn.prepareStatement(query);
            rs= ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
        }
        return count;
    }

    @Override
    public Donation getDonationById(int id) {

        String query = "SELECT * FROM donation WHERE donationId =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Donation donation = new Donation(
                        rs.getInt("donationId"),
                        rs.getInt("eventId"),
                        rs.getInt("userId"),
                        rs.getLong("amount"),
                        rs.getTimestamp("donationDate"),
                        rs.getString("description")
                );
                return donation;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }    @Override
    public List<Donation> getDonationByStatus(String status) {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM donation WHERE status = ?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, status);
            rs = ps.executeQuery();

            while (rs.next()) {
                Donation donation = new Donation();                donation.setId(rs.getInt("donationId"));
                donation.setEventId(rs.getInt("eventId"));
                donation.setUserId(rs.getInt("userId"));
                donation.setAmount(rs.getLong("amount"));
                donation.setDonationDate(rs.getTimestamp("donationDate"));
                donation.setDescription(rs.getString("description"));
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return donations;
    }

    @Override
    public boolean addDistribution(int donationId, int eventId, double amount, String note, int adminId) {
        String query = "INSERT INTO distribution (donationId, eventId, amount, distributionDate, note, distributedBy) "
                + "VALUES (?, ?, ?, NOW(), ?, ?)";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donationId);
            ps.setInt(2, eventId);
            ps.setDouble(3, amount);
            ps.setString(4, note);
            ps.setInt(5, adminId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }
    }



    @Override
    public double getTotalDistributedAmount(int donationId) {
        double total = 0;
        String query = "SELECT SUM(amount) as total FROM distribution WHERE donationId = ?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donationId);
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return total;
    }

    @Override
    public double getTotalDonationByEvent(int eventId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean processDonationBySP(int donationId, int adminId, String note) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean distributeDonationBySP(int donationId, int eventId, double amount, String note, int adminId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public List<Donation> getDonationDetails() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
