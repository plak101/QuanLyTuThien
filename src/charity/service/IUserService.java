package charity.service;

import charity.model.User;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IUserService {
    public List<User> getAllUser();
    public User getUserById(int userId);
    
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int userId);
}
