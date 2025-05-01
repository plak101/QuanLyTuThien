package charity.service;

import charity.model.User;
import charity.repository.IRepository.IUserRepository;
import charity.repository.UserRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class UserService {

    private IUserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userRepository.deleteUser(userId);
    }

    public String getUserNameById(int userId) {
        return userRepository.getUserNameById(userId);
    }
    public User getUserByAccountId(int accountId) {
        return userRepository.getUserById(accountId);
    }
    public boolean isPhoneNumberExist(String phoneNumber){
        return userRepository.isPhoneNumberExist(phoneNumber);
    }
}
