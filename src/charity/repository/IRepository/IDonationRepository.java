/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.repository.IRepository;

import charity.model.Donation;
import java.util.List;
import java.sql.*;
/**
 *
 * @author phaml
 */
public interface IDonationRepository {
    public List<Donation> getAllDonation();
    public List<Donation> getDonationByUserId(int userId);
    public List<Donation> getDonationByEventId(int eventId);
    public boolean addDonation(Donation donation);
    public boolean updateDonation(Donation donation);
    public boolean deleteDonation(int donationId);
    
    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
}
