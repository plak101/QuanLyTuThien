package charity.repository.IRepository;

import charity.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IUserRepository {
    public List<User> getAllUser();
    public User getUserById(int userId);
    public String getUserNameById(int userId);
    
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int userId);
    
    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
}
