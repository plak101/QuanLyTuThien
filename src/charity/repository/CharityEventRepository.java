package charity.repository;

import charity.repository.IRepository.ICharityEventRepository;
import charity.model.CharityEvent;
import charity.utils.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phaml
 */
public class CharityEventRepository implements ICharityEventRepository {

    Connection connection = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    // Lấy danh sách tất cả sự kiện từ thiện

    public CharityEventRepository() {
        connection = ConnectionDB.getConnection();
    }

    @Override
    public List<CharityEvent> getEventListCall() {
        connection = ConnectionDB.getConnection();
        List<CharityEvent> eventList = new ArrayList<>();

        try {
            String query = "SELECT * FROM event WHERE status = 'Kêu gọi'";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CharityEvent event = new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                );
                eventList.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return eventList;
    }
    @Override
    public List<CharityEvent> getEventList() {
        connection = ConnectionDB.getConnection();
        List<CharityEvent> eventList = new ArrayList<>();

        try {
            String query = "SELECT * FROM event";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CharityEvent event = new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                );
                eventList.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return eventList;
    }
    @Override
    public List<CharityEvent> getEventListDistribution() {
        connection = ConnectionDB.getConnection();
        List<CharityEvent> eventList = new ArrayList<>();

        try {
            String query = "SELECT * FROM event WHERE status = 'Phân phát'";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                CharityEvent event = new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                );
                eventList.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(connection, ps, rs);
        }
        return eventList;
    }

    @Override
    public boolean addEvent(CharityEvent event) {
        Connection connection = ConnectionDB.getConnection();
        PreparedStatement ps = null;
        String query = "INSERT INTO event (organizationId, eventName, categoryId, targetAmount, currentAmount, dateBegin, dateEnd, description, imageUrl, status)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, event.getOrganizationId());
            ps.setString(2, event.getName());
            ps.setInt(3, event.getCategoryId());
            ps.setLong(4, event.getTargetAmount());
            ps.setLong(5, event.getCurrentAmount());
            ps.setDate(6, (Date) event.getDateBegin());
            ps.setDate(7, (Date) event.getDateEnd());
            ps.setString(8, event.getDescription());
            ps.setString(9, event.getImageUrl());
            ps.setString(10, event.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(connection, ps);
        }
    }

    @Override
    public boolean updateEvent(CharityEvent event) {
        connection = ConnectionDB.getConnection();
        ps = null;
        String query = "UPDATE event SET organizationId=?, eventName=?, categoryId=?, targetAmount=?, currentAmount=?, dateBegin=?, dateEnd=?, description=?, imageUrl=?, status = ?"
                + "WHERE eventId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, event.getOrganizationId());
            ps.setString(2, event.getName());
            ps.setInt(3, event.getCategoryId());
            ps.setLong(4, event.getTargetAmount());
            ps.setLong(5, event.getCurrentAmount());
            ps.setDate(6, (Date) event.getDateBegin());
            ps.setDate(7, (Date) event.getDateEnd());
            ps.setString(8, event.getDescription());
            ps.setString(9, event.getImageUrl());
            ps.setString(10, event.getStatus());
            ps.setInt(11, event.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(connection, ps);
        }

    }

    @Override
    public boolean deleteEvent(int eventId) {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM event WHERE eventId = ?";
        try {
            conn = ConnectionDB.getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, eventId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public CharityEvent getEventById(int eventId) {
        CharityEvent event = null;
        String query = "SELECT * FROM event WHERE eventId=?";
        connection = ConnectionDB.getConnection();
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();
            if (rs.next()) {
                event = new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            closeResources(connection, ps, rs);
        }
        return event;
    }

    @Override
    public List<CharityEvent> getActiveEventList() {
        List<CharityEvent> events = new ArrayList<>();
        connection = ConnectionDB.getConnection();
        String sql = "SELECT * FROM event WHERE dateEnd >= CURRENT_DATE AND status = 'Kêu gọi'";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                events.add(new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, ps, rs);
        }
        return events;
    }

    @Override
    public List<CharityEvent> getExpiredEventList() {
        List<CharityEvent> events = new ArrayList<>();
        connection = ConnectionDB.getConnection();
        String sql = "SELECT * FROM event WHERE dateEnd < CURRENT_DATE AND status = 'Kêu gọi'";
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                events.add(new CharityEvent(
                        rs.getInt("eventId"),
                        rs.getInt("organizationId"),
                        rs.getString("eventName"),
                        rs.getInt("categoryId"),
                        rs.getLong("targetAmount"),
                        rs.getLong("currentAmount"),
                        rs.getDate("dateBegin"),
                        rs.getDate("dateEnd"),
                        rs.getString("description"),
                        rs.getString("imageUrl"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException ex) {
            DBUtils.showSQLError(ex, "Lỗi");
        } finally {
            closeResources(connection, ps, rs);
        }
        return events;
    }

    @Override
    public String getEventNameById(int eventId) {
        connection = ConnectionDB.getConnection();
        String sql = "SELECT eventname FROM event WHERE eventId=?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, eventId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("eventName");
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, ps, rs);
        }
        return null;
    }

    @Override
    public int getEventCount() {
        int count = 0;
        String query = "SELECT COUNT(*) FROM Event";
        connection = ConnectionDB.getConnection();
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(connection, ps, rs);
        }
        return count;
    }

}
