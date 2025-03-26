
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
    
    //Đóng kết nối

    
}
