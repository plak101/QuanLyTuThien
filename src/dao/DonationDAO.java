/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Donation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phaml
 */
public class DonationDAO implements IDonationDAO {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    @Override
    public List<Donation> getAllDonation() {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM donation";
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
                        rs.getDate("donationDate")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
                        rs.getDate("donationDate")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
                        rs.getDate("donationDate")
                );
                donations.add(donation);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
            return donations;
        }
    }

    @Override
    public boolean addDonation(Donation donation) {
        String query = "INSERT INTO donation (donationId, eventId, userId, amount, donationDate)"+
                "VALUES(?,?,?,?,?)";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donation.getId());
            ps.setInt(2, donation.getEventId());
            ps.setInt(3, donation.getUserId());
            ps.setLong(4, donation.getAmount());
            ps.setDate(5, (Date) donation.getDonationDate());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally{
            closeResources(conn, ps);
        }

    }

    @Override
    public boolean updateDonation(Donation donation) {
        String query = "UPDATE donation SET eventId=?, userId=?, amount=?, donationDate=?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, donation.getEventId());
            ps.setInt(2, donation.getUserId());
            ps.setLong(3, donation.getAmount());
            ps.setDate(4, (Date) donation.getDonationDate());
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            closeResources(conn, ps);
        }
        
    }

    @Override
    public boolean deleteDonation(int donationId) {
        String query = "DELETE FROM donation WHERE donationId=?";
        conn=ConnectionDB.getConnection();
        try {
            ps= conn.prepareStatement(query);
            ps.setInt(1, donationId);
            return ps.executeUpdate()>0;
        } catch (SQLException ex) {
            Logger.getLogger(DonationDAO.class.getName()).log(Level.SEVERE, null, ex);    
            return false;
        }finally{
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
            Logger.getLogger(CharityEventDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeResources(Connection conn,PreparedStatement ps,  ResultSet rs) {
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
            Logger.getLogger(CharityEventDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
