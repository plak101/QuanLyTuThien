/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.service;

import charity.model.User;
import charity.repository.IUserRepository;
import charity.repository.UserRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class UserService implements IUserService{

    private IUserRepository userRepository;
    
    public UserService(){
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
    
}
