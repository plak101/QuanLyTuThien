package charity.repository;

import charity.repository.IRepository.IUserRepository;
import charity.model.User;
import charity.utils.DBUtils;
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
public class UserRepository implements IUserRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public UserRepository() {
        conn = ConnectionDB.getConnection();
    }

    @Override
    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();
        conn = ConnectionDB.getConnection();
        String query = "SELECT * FROM user";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getDate("birthday")
                );

                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return users;

    }

    @Override
    public boolean addUser(User user) {
        String query = "INSERT INTO User (accountId, userName, address, phone, gender, birthday) VALUES (?, ?, ?, ?, ?,?)";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, user.getAccountId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getAddress());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getGender());
            ps.setDate(6, user.getBirthday());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            DBUtils.handleDuplicateEntry((SQLException) ex);

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }
    }

    @Override
    public boolean updateUser(User user) {
        String query = "UPDATE user SET userName = ?, address=?, phone=?, gender=?, birthday=? WHERE userid= ?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, user.getName());
            ps.setString(2, user.getAddress());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getGender());
            ps.setDate(5, user.getBirthday());
            ps.setInt(6, user.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            DBUtils.handleDuplicateEntry((SQLException) ex);

            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }

    }

    @Override
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM user WHERE userId=?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            closeResources(conn, ps);
        }

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
    public User getUserById(int userId) {
        User user = null;
        String sql = "SELECT * FROM user WHERE userId =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getDate("birthday")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
            return user;
        }

    }

    @Override
    public String getUserNameById(int userId) {
        conn = ConnectionDB.getConnection();
        String sql = "SELECT userName FROM user WHERE userId=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("userName");
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CharityEventRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    @Override
    public User getUserByAccountId(int accountId) {
        User user = null;
        String sql = "SELECT u.* FROM user u JOIN account a ON a.id=u.accountId WHERE accountId =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("gender"),
                        rs.getDate("birthday")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
            return user;
        }
    }

    @Override
    public boolean isPhoneNumberExist(String phoneNumber) {
        String query = "(SELECT 1 FROM user WHERE phone = ? LIMIT 1)\n"
                + "UNION\n"
                + "(SELECT 1 FROM organization WHERE hotline = ? LIMIT 1);";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, phoneNumber);
            ps.setString(2, phoneNumber);
            rs = ps.executeQuery();
            return rs.next();//neu co tra ve la da ton tai
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return false;

    }

}
