/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.service.IService;

import charity.model.User;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IUserService {
    public List<User> getAllUser();
    public List<User> getUserRoleUser();
    public User getUserById(int userId);
    public User getUserByAccountId(int accountId);
    public String getUserNameById(int userId);
    
    public boolean addUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(int userId);
    public boolean isPhoneNumberExist(String phoneNumber);
}
