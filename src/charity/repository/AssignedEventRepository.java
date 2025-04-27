package charity.repository;

import charity.model.AssignedEvent;
import charity.model.Organization;
import charity.repository.IRepository.IAssignedEventRepository;
import charity.utils.DBUtils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AssignedEventRepository implements IAssignedEventRepository{

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    public AssignedEventRepository() {
        conn = ConnectionDB.getConnection();
    }
    @Override
    public List<AssignedEvent> getAllDonation() {
        List<AssignedEvent> assignedEvents = new ArrayList<>();
        conn = ConnectionDB.getConnection();
        String query = "SELECT * FROM sssignedEvent";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                AssignedEvent assignedEvent = new AssignedEvent(
                        rs.getInt("organizationId"),
                        rs.getInt("eventId"),
                        rs.getDate("assignedDate")
                );

                assignedEvents.add(assignedEvent);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(AssignedEventRepository.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return assignedEvents;
    }

    @Override
    public boolean addAssignedEvent(AssignedEvent assignedEvent) {
        conn = ConnectionDB.getConnection();
        String sql = "INSER INTO assignedEvent (eventId, organizationId, assignedDate) VALUES (?, ?, ?))";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, assignedEvent.getEventId());
            ps.setInt(2, assignedEvent.getOrganizationId());
            ps.setDate(3, (Date) assignedEvent.getAssignedDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            DBUtils.showSQLError(ex, "Lỗi khi thêm hoạt động bàn giao từ thiện!");
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean updateAssignedEvent(AssignedEvent assignedEvent) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE assignedEvent SET eventId=?, organizationId=?, assignedDate=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, assignedEvent.getEventId());
            ps.setInt(2, assignedEvent.getOrganizationId());
            ps.setDate(3, (Date) assignedEvent.getAssignedDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AssignedEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean deleteAssignedEvent(int id) {
        String query = "DELETE FROM assignedEvent WHERE id=?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AssignedEventRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AssignedEventRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(AssignedEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
