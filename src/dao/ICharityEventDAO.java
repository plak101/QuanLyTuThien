
package dao;

import entity.CharityEvent;
import java.util.List;
import java.sql.*;
        
/**
 *
 * @author phaml
 */
public interface ICharityEventDAO {
    public List<CharityEvent> getEventList();
    public boolean addEvent(CharityEvent event);
    public boolean updateEvent(CharityEvent event);
    public boolean deleteEvent(int eventId);
    
    //Đóng kết nối
    public void closeResources(Connection conn, PreparedStatement ps);
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
    
    
}
