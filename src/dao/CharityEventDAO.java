package dao;

import entity.CharityEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;
/**
 *
 * @author phaml
 */
public class CharityEventDAO implements ICharityEventDAO{
    
    // Lấy danh sách tất cả sự kiện từ thiện
    public List<CharityEvent> getEventList() {
        List<CharityEvent> eventList = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        try {
            conn = DataConnection.getConnection();
            String query = "SELECT * FROM event";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                CharityEvent event = new CharityEvent(
                    rs.getInt("eventId"),
                    rs.getString("eventName"),
                    rs.getString("category"),
                    rs.getLong("targetAmount"),
                    rs.getLong("currentAmount"),
                    rs.getDate("dateBegin"),
                    rs.getDate("dateEnd"),
                    rs.getString("description")
                );
                
                eventList.add(event);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeResources(conn, rs);
        }
        return eventList;
    }

    @Override
    public boolean addEvent(CharityEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateEvent(CharityEvent event) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    private void closeResources(Connection conn, Statement st) {
    }

    private void closeResources(Connection conn, ResultSet rs) {
    }

}
