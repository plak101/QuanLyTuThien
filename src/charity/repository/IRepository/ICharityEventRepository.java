
package charity.repository.IRepository;

import charity.model.CharityEvent;
import java.util.List;
import java.sql.*;
        
/**
 *
 * @author phaml
 */
public interface ICharityEventRepository {
    public List<CharityEvent> getEventList();
    public List<CharityEvent> getActiveEventList();
    public List<CharityEvent> getExpiredEventList();
    public CharityEvent getEventById(int eventId) ;
    public String getEventNameById(int eventId) ;
    public boolean addEvent(CharityEvent event);
    public boolean updateEvent(CharityEvent event);
    public boolean deleteEvent(int eventId);
    public int getEventCount();
    //Đóng kết nối
    public void closeResources(Connection conn, PreparedStatement ps);
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
    
    
}
