package dao;

import entity.Donation;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IUserDAO {
    public List<User> getAllUser();
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int userId);
    
    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
}
