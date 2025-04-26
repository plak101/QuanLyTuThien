package charity.controller;

import charity.model.QuyenGop;
import charity.utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuyenGopController {
    
    public QuyenGopController() {
        // Ensure database table exists
        createTableIfNotExists();
    }
    
    private void createTableIfNotExists() {
        Connection conn = null;
        Statement stmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS quyen_gop (" +
                         "id INT PRIMARY KEY AUTO_INCREMENT," +
                         "nha_tai_tro_id INT NOT NULL," +
                         "chuong_trinh_id INT NOT NULL," +
                         "so_tien DOUBLE NOT NULL," +
                         "ngay_quyen_gop DATE," +
                         "phuong_thuc_thanh_toan VARCHAR(50)," +
                         "ghi_chu TEXT," +
                         "FOREIGN KEY (nha_tai_tro_id) REFERENCES nha_tai_tro(id) ON DELETE CASCADE" +
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
    
    public List<QuyenGop> getAllQuyenGop() {
        List<QuyenGop> quyenGopList = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM quyen_gop";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                QuyenGop quyenGop = new QuyenGop();
                quyenGop.setId(rs.getInt("id"));
                quyenGop.setNhaTaiTroId(rs.getInt("nha_tai_tro_id"));
                quyenGop.setChuongTrinhId(rs.getInt("chuong_trinh_id"));
                quyenGop.setSoTien(rs.getDouble("so_tien"));
                quyenGop.setNgayQuyenGop(rs.getDate("ngay_quyen_gop"));
                quyenGop.setPhuongThucThanhToan(rs.getString("phuong_thuc_thanh_toan"));
                quyenGop.setGhiChu(rs.getString("ghi_chu"));
                
                quyenGopList.add(quyenGop);
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
        
        return quyenGopList;
    }
    
    public void addQuyenGop(QuyenGop quyenGop) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "INSERT INTO quyen_gop (nha_tai_tro_id, chuong_trinh_id, so_tien, ngay_quyen_gop, phuong_thuc_thanh_toan, ghi_chu) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, quyenGop.getNhaTaiTroId());
            pstmt.setInt(2, quyenGop.getChuongTrinhId());
            pstmt.setDouble(3, quyenGop.getSoTien());
            
            java.util.Date utilDate = quyenGop.getNgayQuyenGop();
            if (utilDate != null) {
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pstmt.setDate(4, sqlDate);
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            
            pstmt.setString(5, quyenGop.getPhuongThucThanhToan());
            pstmt.setString(6, quyenGop.getGhiChu());
            
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
    
    public void updateQuyenGop(QuyenGop quyenGop) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "UPDATE quyen_gop SET nha_tai_tro_id = ?, chuong_trinh_id = ?, so_tien = ?, ngay_quyen_gop = ?, phuong_thuc_thanh_toan = ?, ghi_chu = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, quyenGop.getNhaTaiTroId());
            pstmt.setInt(2, quyenGop.getChuongTrinhId());
            pstmt.setDouble(3, quyenGop.getSoTien());
            
            java.util.Date utilDate = quyenGop.getNgayQuyenGop();
            if (utilDate != null) {
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                pstmt.setDate(4, sqlDate);
            } else {
                pstmt.setNull(4, java.sql.Types.DATE);
            }
            
            pstmt.setString(5, quyenGop.getPhuongThucThanhToan());
            pstmt.setString(6, quyenGop.getGhiChu());
            pstmt.setInt(7, quyenGop.getId());
            
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
    
    public void deleteQuyenGop(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "DELETE FROM quyen_gop WHERE id = ?";
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
    
    public QuyenGop findById(int id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QuyenGop quyenGop = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM quyen_gop WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                quyenGop = new QuyenGop();
                quyenGop.setId(rs.getInt("id"));
                quyenGop.setNhaTaiTroId(rs.getInt("nha_tai_tro_id"));
                quyenGop.setChuongTrinhId(rs.getInt("chuong_trinh_id"));
                quyenGop.setSoTien(rs.getDouble("so_tien"));
                quyenGop.setNgayQuyenGop(rs.getDate("ngay_quyen_gop"));
                quyenGop.setPhuongThucThanhToan(rs.getString("phuong_thuc_thanh_toan"));
                quyenGop.setGhiChu(rs.getString("ghi_chu"));
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
        
        return quyenGop;
    }
    
    public List<QuyenGop> findByNhaTaiTroId(int nhaTaiTroId) {
        List<QuyenGop> quyenGopList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM quyen_gop WHERE nha_tai_tro_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, nhaTaiTroId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                QuyenGop quyenGop = new QuyenGop();
                quyenGop.setId(rs.getInt("id"));
                quyenGop.setNhaTaiTroId(rs.getInt("nha_tai_tro_id"));
                quyenGop.setChuongTrinhId(rs.getInt("chuong_trinh_id"));
                quyenGop.setSoTien(rs.getDouble("so_tien"));
                quyenGop.setNgayQuyenGop(rs.getDate("ngay_quyen_gop"));
                quyenGop.setPhuongThucThanhToan(rs.getString("phuong_thuc_thanh_toan"));
                quyenGop.setGhiChu(rs.getString("ghi_chu"));
                
                quyenGopList.add(quyenGop);
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
        
        return quyenGopList;
    }
    
    public List<QuyenGop> findByChuongTrinhId(int chuongTrinhId) {
        List<QuyenGop> quyenGopList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM quyen_gop WHERE chuong_trinh_id = ?";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, chuongTrinhId);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                QuyenGop quyenGop = new QuyenGop();
                quyenGop.setId(rs.getInt("id"));
                quyenGop.setNhaTaiTroId(rs.getInt("nha_tai_tro_id"));
                quyenGop.setChuongTrinhId(rs.getInt("chuong_trinh_id"));
                quyenGop.setSoTien(rs.getDouble("so_tien"));
                quyenGop.setNgayQuyenGop(rs.getDate("ngay_quyen_gop"));
                quyenGop.setPhuongThucThanhToan(rs.getString("phuong_thuc_thanh_toan"));
                quyenGop.setGhiChu(rs.getString("ghi_chu"));
                
                quyenGopList.add(quyenGop);
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
        
        return quyenGopList;
    }
    
    public double getTotalDonationAmount() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        double total = 0;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(so_tien) FROM quyen_gop";
            rs = stmt.executeQuery(sql);
            
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.err.println("Error calculating total: " + e.getMessage());
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
    
    // Join with NhaTaiTro to get donation details with sponsor name
    public List<Object[]> getQuyenGopWithNhaTaiTro() {
        List<Object[]> result = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT qg.id, qg.nha_tai_tro_id, ntt.ten, qg.chuong_trinh_id, " +
                         "qg.so_tien, qg.ngay_quyen_gop, qg.phuong_thuc_thanh_toan, qg.ghi_chu " +
                         "FROM quyen_gop qg " +
                         "JOIN nha_tai_tro ntt ON qg.nha_tai_tro_id = ntt.id";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] row = new Object[8];
                row[0] = rs.getInt("id");
                row[1] = rs.getInt("nha_tai_tro_id");
                row[2] = rs.getString("ten");
                row[3] = rs.getInt("chuong_trinh_id");
                row[4] = rs.getDouble("so_tien");
                row[5] = rs.getDate("ngay_quyen_gop");
                row[6] = rs.getString("phuong_thuc_thanh_toan");
                row[7] = rs.getString("ghi_chu");
                
                result.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving joined data: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
            DatabaseConnection.closeConnection(conn);
        }
        
        return result;
    }
}