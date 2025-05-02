package charity.service;

import charity.model.User;
import charity.repository.IRepository.IUserRepository;
import charity.repository.UserRepository;
import charity.service.IService.IUserService;
import java.util.List;

/**
 *
 * @author phaml
 */
public class UserService implements IUserService{

    private IUserRepository userRepository;

    public UserService() {
        userRepository = new UserRepository();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.getAllUser();
    }

    @Override
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }
    @Override
    public boolean updateUser(User user) {
        return userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(int userId) {
        return userRepository.deleteUser(userId);
    }

    @Override
    public String getUserNameById(int userId) {
        return userRepository.getUserNameById(userId);
    }
    
    @Override
    public User getUserByAccountId(int accountId) {
        return userRepository.getUserByAccountId(accountId);
    }
    
    @Override
    public boolean isPhoneNumberExist(String phoneNumber){
        return userRepository.isPhoneNumberExist(phoneNumber);
    }
}
