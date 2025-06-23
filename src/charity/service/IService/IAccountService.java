/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.service.IService;

import charity.model.Account;
import charity.model.User;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IAccountService {
    public List<Account> getAllAccount();

    public Account getAccountById(int accountId);
    public Account getAccountByUserId(int userId);

    public boolean addAccount(Account account);

    public boolean updateAccount(Account account);

    public boolean deleteAccount(int id);

    public Account checkAccount(String username, String password);

    public boolean isUsernameTaken(String username);
    public boolean isUserExist(int accountId);
    public boolean isEmailExist(String email);

    public int getAccountCount();
    public boolean addAccountWithUser(Account account, User user);
    public boolean updateAccountWithUser(Account account, User user);

    Account getAccountByEmail(String email); // << THÊM DÒNG NÀY >>
}
