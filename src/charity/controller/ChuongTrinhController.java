package charity.controller;

import charity.model.ChuongTrinh;
import charity.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ChuongTrinhController {
    private List<ChuongTrinh> chuongTrinhList;
    
    public ChuongTrinhController() {
        chuongTrinhList = new ArrayList<>();
        // Ensure database table exists
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS chuong_trinh (" +
                         "id INT PRIMARY KEY AUTO_INCREMENT," +
                         "ten_chuong_trinh VARCHAR(255) NOT NULL," +
                         "mo_ta TEXT," +
                         "ngay_bat_dau DATE," +
                         "ngay_ket_thuc DATE," +
                         "tong_kinh_phi DOUBLE DEFAULT 0," +
                         "trang_thai VARCHAR(50)" +
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
    
    public List<ChuongTrinh> getAllChuongTrinh() {
        List<ChuongTrinh> chuongTrinhList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM chuong_trinh";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                ChuongTrinh chuongTrinh = new ChuongTrinh();
                chuongTrinh.setId(rs.getInt("id"));
                chuongTrinh.setTenChuongTrinh(rs.getString("ten_chuong_trinh"));
                chuongTrinh.setMoTa(rs.getString("mo_ta"));
                
                // Handle date conversions
                Date sqlStartDate = rs.getDate("ngay_bat_dau");
                if (sqlStartDate != null) {
                    chuongTrinh.setNgayBatDau(new java.util.Date(sqlStartDate.getTime()));
                }
                
                Date sqlEndDate = rs.getDate("ngay_ket_thuc");
                if (sqlEndDate != null) {
                    chuongTrinh.setNgayKetThuc(new java.util.Date(sqlEndDate.getTime()));
                }
                
                chuongTrinh.setTongKinhPhi(rs.getDouble("tong_kinh_phi"));
                chuongTrinh.setTrangThai(rs.getString("trang_thai"));
                
                chuongTrinhList.add(chuongTrinh);
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
        
        return chuongTrinhList;
    }
    
    public void addChuongTrinh(ChuongTrinh chuongTrinh) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO chuong_trinh (ten_chuong_trinh, mo_ta, ngay_bat_dau, ngay_ket_thuc, tong_kinh_phi, trang_thai) " +
                         "VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, chuongTrinh.getTenChuongTrinh());
            pstmt.setString(2, chuongTrinh.getMoTa());
            
            // Handle date conversions
            if (chuongTrinh.getNgayBatDau() != null) {
                pstmt.setDate(3, new Date(chuongTrinh.getNgayBatDau().getTime()));
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }
            
            if (chuongTrinh.getNgayKetThuc() != null) {
                pstmt.setDate(4, new Date(chuongTrinh.getNgayKetThuc().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            
            pstmt.setDouble(5, chuongTrinh.getTongKinhPhi());
            pstmt.setString(6, chuongTrinh.getTrangThai());
            
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
    
    public void updateChuongTrinh(ChuongTrinh chuongTrinh) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE chuong_trinh SET ten_chuong_trinh = ?, mo_ta = ?, ngay_bat_dau = ?, " +
                         "ngay_ket_thuc = ?, tong_kinh_phi = ?, trang_thai = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, chuongTrinh.getTenChuongTrinh());
            pstmt.setString(2, chuongTrinh.getMoTa());
            
            // Handle date conversions
            if (chuongTrinh.getNgayBatDau() != null) {
                pstmt.setDate(3, new Date(chuongTrinh.getNgayBatDau().getTime()));
            } else {
                pstmt.setNull(3, java.sql.Types.DATE);
            }
            
            if (chuongTrinh.getNgayKetThuc() != null) {
                pstmt.setDate(4, new Date(chuongTrinh.getNgayKetThuc().getTime()));
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            
            pstmt.setDouble(5, chuongTrinh.getTongKinhPhi());
            pstmt.setString(6, chuongTrinh.getTrangThai());
            pstmt.setInt(7, chuongTrinh.getId());
            
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
    
    public void deleteChuongTrinh(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM chuong_trinh WHERE id = ?";
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
    
    public ChuongTrinh findById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ChuongTrinh chuongTrinh = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM chuong_trinh WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                chuongTrinh = new ChuongTrinh();
                chuongTrinh.setId(rs.getInt("id"));
                chuongTrinh.setTenChuongTrinh(rs.getString("ten_chuong_trinh"));
                chuongTrinh.setMoTa(rs.getString("mo_ta"));
                
                // Handle date conversions
                Date sqlStartDate = rs.getDate("ngay_bat_dau");
                if (sqlStartDate != null) {
                    chuongTrinh.setNgayBatDau(new java.util.Date(sqlStartDate.getTime()));
                }
                
                Date sqlEndDate = rs.getDate("ngay_ket_thuc");
                if (sqlEndDate != null) {
                    chuongTrinh.setNgayKetThuc(new java.util.Date(sqlEndDate.getTime()));
                }
                
                chuongTrinh.setTongKinhPhi(rs.getDouble("tong_kinh_phi"));
                chuongTrinh.setTrangThai(rs.getString("trang_thai"));
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
        
        return chuongTrinh;
    }
    
    public int countChuongTrinh() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT COUNT(*) FROM chuong_trinh";
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
    
    public double getTotalBudget() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        double total = 0;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(tong_kinh_phi) FROM chuong_trinh";
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total budget: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return total;
    }
    
    public List<ChuongTrinh> searchChuongTrinh(String keyword) {
        List<ChuongTrinh> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM chuong_trinh WHERE " +
                         "ten_chuong_trinh LIKE ? OR " +
                         "mo_ta LIKE ? OR " +
                         "trang_thai LIKE ?";
            pstmt = conn.prepareStatement(sql);
            
            String searchPattern = "%" + keyword + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ChuongTrinh chuongTrinh = new ChuongTrinh();
                chuongTrinh.setId(rs.getInt("id"));
                chuongTrinh.setTenChuongTrinh(rs.getString("ten_chuong_trinh"));
                chuongTrinh.setMoTa(rs.getString("mo_ta"));
                
                // Handle date conversions
                Date sqlStartDate = rs.getDate("ngay_bat_dau");
                if (sqlStartDate != null) {
                    chuongTrinh.setNgayBatDau(new java.util.Date(sqlStartDate.getTime()));
                }
                
                Date sqlEndDate = rs.getDate("ngay_ket_thuc");
                if (sqlEndDate != null) {
                    chuongTrinh.setNgayKetThuc(new java.util.Date(sqlEndDate.getTime()));
                }
                
                chuongTrinh.setTongKinhPhi(rs.getDouble("tong_kinh_phi"));
                chuongTrinh.setTrangThai(rs.getString("trang_thai"));
                
                results.add(chuongTrinh);
            }
        } catch (SQLException e) {
            System.err.println("Error searching data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return results;
    }
    
    public List<ChuongTrinh> getChuongTrinhByStatus(String trangThai) {
        List<ChuongTrinh> results = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM chuong_trinh WHERE trang_thai = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, trangThai);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                ChuongTrinh chuongTrinh = new ChuongTrinh();
                chuongTrinh.setId(rs.getInt("id"));
                chuongTrinh.setTenChuongTrinh(rs.getString("ten_chuong_trinh"));
                chuongTrinh.setMoTa(rs.getString("mo_ta"));
                
                // Handle date conversions
                Date sqlStartDate = rs.getDate("ngay_bat_dau");
                if (sqlStartDate != null) {
                    chuongTrinh.setNgayBatDau(new java.util.Date(sqlStartDate.getTime()));
                }
                
                Date sqlEndDate = rs.getDate("ngay_ket_thuc");
                if (sqlEndDate != null) {
                    chuongTrinh.setNgayKetThuc(new java.util.Date(sqlEndDate.getTime()));
                }
                
                chuongTrinh.setTongKinhPhi(rs.getDouble("tong_kinh_phi"));
                chuongTrinh.setTrangThai(rs.getString("trang_thai"));
                
                results.add(chuongTrinh);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving data by status: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return results;
    }
}