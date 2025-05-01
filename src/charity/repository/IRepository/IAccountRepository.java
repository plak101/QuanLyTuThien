package charity.repository.IRepository;

import charity.model.Account;
import charity.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public interface IAccountRepository {
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
    public boolean insertAccountWithUser(Account account, User user);
    public boolean updateAccountWithUser(Account account, User user);
    
    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);

    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
}
