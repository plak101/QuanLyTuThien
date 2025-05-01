package charity.service;

import charity.model.Account;
import charity.model.User;
import charity.repository.AccountRepository;
import java.util.List;

public class AccountService {

    private AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new AccountRepository();
    }

    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    public boolean addAccount(Account account){
        return accountRepository.addAccount(account);
    }

    public boolean updateAccount(Account account){
        return accountRepository.updateAccount(account);
    }

    public boolean deleteAccount(int id){
        return accountRepository.deleteAccount(id);
    }
    
    public Account checkAccount(String username, String password){
        return accountRepository.checkAccount(username, password);
    }

    public boolean isUsernameTaken(String username) {
        return accountRepository.isUsernameTaken(username);
    }
    public Account getAccountByUserId(int userId){
        return accountRepository.getAccountByUserId(userId);
    }
    public Account getAccountById(int accountId){
        return accountRepository.getAccountById(accountId);
    }
    public boolean addAccountWithUser(Account account, User user){
        return accountRepository.insertAccountWithUser(account, user);
    }
    public boolean updateAccountWithUser(Account account, User user) {
        return accountRepository.updateAccountWithUser(account, user);
    }
    public boolean isUserExist(int accountId){
        return accountRepository.isUserExist(accountId);
    }
    public boolean isEmailExist(String email){
        return accountRepository.isEmailExist(email);
    }
    public int getAccountCount(){
        return accountRepository.getAccountCount();
    }
}
