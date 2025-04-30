package charity.service;

import charity.model.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import charity.utils.DatabaseConnection;

public class OrganizationService {
    
    // Get all organizations from the database
    public List<Organization> getAllOrganization() {
        List<Organization> organizations = new ArrayList<>();
        
        try {
            Connection connection = DatabaseConnection.getConnection(); // Get DB connection
            String sql = "SELECT * FROM Organization ORDER BY id";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                Organization org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                org.setEmail(rs.getString("email"));
                org.setHotline(rs.getString("hotline"));
                org.setAddress(rs.getString("address"));
                
                organizations.add(org);
            }
            
            DatabaseConnection.closeConnection(connection); // Close connection
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return organizations;
    }
    
    // Get the count of events associated with a specific organization
    public int getTotalEvent(int organizationId) {
        int totalEvents = 0;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT COUNT(*) FROM event WHERE organizationId = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, organizationId);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                totalEvents = rs.getInt(1);
            }
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đếm số sự kiện: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        
        return totalEvents;
    }
    
    // Add a new organization
    public boolean addOrganization(Organization org) {
        boolean result = false;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO Organization (name, email, hotline, address) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, org.getName());
            pstmt.setString(2, org.getEmail());
            pstmt.setString(3, org.getHotline());
            pstmt.setString(4, org.getAddress());
            
            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    // Update an existing organization
    public boolean updateOrganization(Organization org) {
        boolean result = false;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "UPDATE Organization SET name = ?, email = ?, hotline = ?, address = ? WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setString(1, org.getName());
            pstmt.setString(2, org.getEmail());
            pstmt.setString(3, org.getHotline());
            pstmt.setString(4, org.getAddress());
            pstmt.setInt(5, org.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    // Delete an organization
    public boolean deleteOrganization(int id) {
        boolean result = false;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "DELETE FROM Organization WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return result;
    }
    
    // Get an organization by ID
    public Organization getOrganizationById(int id) {
        Organization org = null;
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Organization WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                org.setEmail(rs.getString("email"));
                org.setHotline(rs.getString("hotline"));
                org.setAddress(rs.getString("address"));
            }
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return org;
    }
    
    // Search organizations by name, email or address containing the keyword
    public List<Organization> searchOrganizations(String keyword) {
        List<Organization> organizations = new ArrayList<>();
        
        try {
            Connection connection = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM Organization WHERE name LIKE ? OR email LIKE ? OR address LIKE ? ORDER BY id";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Organization org = new Organization();
                org.setId(rs.getInt("id"));
                org.setName(rs.getString("name"));
                org.setEmail(rs.getString("email"));
                org.setHotline(rs.getString("hotline"));
                org.setAddress(rs.getString("address"));
                
                organizations.add(org);
            }
            
            DatabaseConnection.closeConnection(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm tổ chức: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return organizations;
    }
}