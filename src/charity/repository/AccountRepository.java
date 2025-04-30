/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package charity.repository;

import charity.model.Account;
import charity.model.Role;
import charity.repository.IRepository.IAccountRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phaml
 */
public class AccountRepository implements IAccountRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public AccountRepository() {
        conn = ConnectionDB.getConnection();
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> accounts = new ArrayList<>();
        conn = ConnectionDB.getConnection();
        String query = "SELECT * FROM account";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Account account = new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role"))
                );

                accounts.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return accounts;
    }

    @Override
    public boolean addAccount(Account account) {
        String query = "INSERT INTO account (username, password, email, role) VALUES (?, ?, ?, ?)";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, "User");
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean updateAccount(Account account) {
        String query = "UPDATE account SET username = ?, password = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.setString(3, account.getEmail());
            ps.setString(4, account.getRole().name());
            ps.setInt(5, account.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean deleteAccount(int id) {
        String query = "DELETE FROM account WHERE id = ?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public void closeResources(Connection conn, PreparedStatement ps) {
        try {
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
            if (conn != null) {
                conn.close();
            }
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Account checkAccount(String username, String password) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT * FROM account WHERE username LIKE ? AND password LIKE ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            
            if (rs.next()){
                return new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role"))
                ) ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
        }
        return null;
    }

    @Override
    public boolean isUsernameTaken(String username) {
        List<Account> accounts = getAllAccount();
        for (Account account: accounts){
            if (account.getUsername().equalsIgnoreCase(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        conn = ConnectionDB.getConnection();
        String query = "SELECT a.* FROM account a JOIN user u ON a.id = u.accountId WHERE u.userId =?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    @Override
    public Account getAccountById(int accountId) {
        conn = ConnectionDB.getConnection();
        String query = "SELECT * FROM account WHERE id =?";
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        Role.valueOf(rs.getString("role"))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    @Override
    public boolean isUserExist(int accountId) {
        String sql= "SELECT 1 FROM user WHERE accountId=?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs= ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
        }
        return false;
    }

    @Override
    public int getAccountCount() {
        int count =0;
        String query = "SELECT COUNT(*) FROM account";
        conn = ConnectionDB.getConnection();
        try {
            ps =conn.prepareStatement(query);
            rs= ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
        }
        return count;
    }

}
