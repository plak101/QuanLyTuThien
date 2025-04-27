package charity.controller;

import charity.model.NhaTaiTro;
import charity.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NhaTaiTroController {
    
    public NhaTaiTroController() {
        // Ensure database table exists
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS nha_tai_tro (" +
                         "id INT PRIMARY KEY AUTO_INCREMENT," +
                         "ten VARCHAR(100) NOT NULL," +
                         "dia_chi VARCHAR(255)," +
                         "so_dien_thoai VARCHAR(20)," +
                         "email VARCHAR(100)" +
                         ")";
            
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    public List<NhaTaiTro> getAllNhaTaiTro() {
        List<NhaTaiTro> nhaTaiTroList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM nha_tai_tro";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                NhaTaiTro nhaTaiTro = new NhaTaiTro();
                nhaTaiTro.setId(rs.getInt("id"));
                nhaTaiTro.setTen(rs.getString("ten"));
                nhaTaiTro.setDiaChi(rs.getString("dia_chi"));
                nhaTaiTro.setSoDienThoai(rs.getString("so_dien_thoai"));
                nhaTaiTro.setEmail(rs.getString("email"));
                
                nhaTaiTroList.add(nhaTaiTro);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return nhaTaiTroList;
    }
    
    public void addNhaTaiTro(NhaTaiTro nhaTaiTro) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO nha_tai_tro (ten, dia_chi, so_dien_thoai, email) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, nhaTaiTro.getTen());
            pstmt.setString(2, nhaTaiTro.getDiaChi());
            pstmt.setString(3, nhaTaiTro.getSoDienThoai());
            pstmt.setString(4, nhaTaiTro.getEmail());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding data: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    public void updateNhaTaiTro(NhaTaiTro nhaTaiTro) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE nha_tai_tro SET ten = ?, dia_chi = ?, so_dien_thoai = ?, email = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, nhaTaiTro.getTen());
            pstmt.setString(2, nhaTaiTro.getDiaChi());
            pstmt.setString(3, nhaTaiTro.getSoDienThoai());
            pstmt.setString(4, nhaTaiTro.getEmail());
            pstmt.setInt(5, nhaTaiTro.getId());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating data: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    public void deleteNhaTaiTro(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM nha_tai_tro WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting data: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    public NhaTaiTro findById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NhaTaiTro nhaTaiTro = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM nha_tai_tro WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                nhaTaiTro = new NhaTaiTro();
                nhaTaiTro.setId(rs.getInt("id"));
                nhaTaiTro.setTen(rs.getString("ten"));
                nhaTaiTro.setDiaChi(rs.getString("dia_chi"));
                nhaTaiTro.setSoDienThoai(rs.getString("so_dien_thoai"));
                nhaTaiTro.setEmail(rs.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error finding data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return nhaTaiTro;
    }
    
    public int countNhaTaiTro() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM nha_tai_tro";
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error counting data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return count;
    }
}