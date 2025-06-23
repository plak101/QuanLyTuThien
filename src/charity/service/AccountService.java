package charity.service;

import charity.model.Account;
import charity.model.User;
import charity.repository.AccountRepository;
import charity.service.IService.IAccountService;
import java.util.List;
import java.util.HashMap;//notuse
import java.util.Map;//notuse

public class AccountService implements IAccountService{

    private AccountRepository accountRepository;

    public AccountService() {
        accountRepository = new AccountRepository();
    }

    @Override
    public List<Account> getAllAccount() {
        return accountRepository.getAllAccount();
    }

    @Override
    public boolean addAccount(Account account){
        return accountRepository.addAccount(account);
    }

    @Override
    public boolean updateAccount(Account account){
        return accountRepository.updateAccount(account);
    }

    @Override
    public boolean deleteAccount(int id){
        return accountRepository.deleteAccount(id);
    }

    @Override
    public Account checkAccount(String username, String password){
        return accountRepository.checkAccount(username, password);
    }

    @Override
    public boolean isUsernameTaken(String username) {
        return accountRepository.isUsernameTaken(username);
    }
    @Override
    public Account getAccountByUserId(int userId){
        return accountRepository.getAccountByUserId(userId);
    }
    @Override
    public Account getAccountById(int accountId){
        return accountRepository.getAccountById(accountId);
    }

    @Override
    public boolean addAccountWithUser(Account account, User user){
        return accountRepository.insertAccountWithUser(account, user);
    }

    @Override
    public boolean updateAccountWithUser(Account account, User user) {
        return accountRepository.updateAccountWithUser(account, user);
    }
    @Override
    public boolean isUserExist(int accountId){
        return accountRepository.isUserExist(accountId);
    }
    @Override
    public boolean isEmailExist(String email){
        return accountRepository.isEmailExist(email);
    }
    @Override
    public int getAccountCount(){
        return accountRepository.getAccountCount();
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountRepository.getAccountByEmail(email);
    }
}
