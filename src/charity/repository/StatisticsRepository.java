package charity.repository;

import charity.model.otherModel.DonationStatistics;
import charity.repository.IRepository.IStatisticsRepository;
import charity.service.CategoryService;
import charity.utils.MessageDialog;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phaml
 */
public class StatisticsRepository implements IStatisticsRepository {

    private Connection conn;
    private CategoryService categoryService = new CategoryService();

    @Override
    public List<Map<String, Object>> getStatisticsByDateRange(Date startDate, Date endDate) {
        List<Map<String, Object>> statistics = new ArrayList<>();
        String sql = """
                SELECT DATE(d.donationDate) as date,
                        SUM(d.amount) as totalAmount,
                        COUNT(*) as totalDonation,
                        e.categoryId,
                        e.eventName,
                        o.name as organizationName
                FROM Donation d
                JOIN Event e ON e.eventId = d.eventId
                JOIN Organization o ON o.id = e.OrganizationId
                WHERE DATE(d.donationDate) BETWEEN ? AND ?
                GROUP BY DATE(d.donationDate), e.categoryId, e.eventName, o.name
                ORDER BY date DESC;
        """;

        try (
                Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, startDate);
            ps.setObject(2, endDate);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> stat = new HashMap<>();
                String categoryName = categoryService.getCategoryNameById(rs.getInt("categoryId"));
                stat.put("date", rs.getDate("date"));
                stat.put("totalAmount", rs.getLong("totalAmount"));
                stat.put("totalDonation", rs.getInt("totalDonation"));
                stat.put("category", categoryName);
                stat.put("eventName", rs.getString("eventName"));
                stat.put("organizationName", rs.getString("organizationName"));
                statistics.add(stat);
            }
        } catch (SQLException ex) {
            MessageDialog.showError("Lỗi khi lấy thống kê theo thời gian!");
        }
        return statistics;
    }

    @Override
    public List<Map<String, Object>> getStatisticsByCategory() {
        List<Map<String, Object>> categoryStatistics = new ArrayList<>();
        String sql = """
                   SELECT 
                       e.categoryId,  
                       COUNT(DISTINCT e.eventId) AS totalEvent,
                       COUNT(d.donationId) AS totalDonation,
                       SUM(d.amount) AS totalAmount
                   FROM Event e
                   LEFT JOIN Donation d ON d.eventId = e.eventId
                   GROUP BY e.categoryId
                   ORDER BY totalAmount DESC;
                   """;
        conn = ConnectionDB.getConnection();
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> stat = new HashMap<>();
                String categoryName = categoryService.getCategoryNameById(rs.getInt("categoryId"));
                stat.put("category", categoryName);
                stat.put("totalEvent", rs.getInt("totalEvent"));
                stat.put("totalDonation", rs.getInt("totalDonation"));
                stat.put("totalAmount", rs.getBigDecimal("totalAmount"));
                categoryStatistics.add(stat);
            }
        } catch (SQLException ex) {
            MessageDialog.showError("Lỗi khi lấy thống kê theo loại!");
        }
        return categoryStatistics;
    }

    @Override
    public List<Map<String, Object>> getTopDonors(int limit) {
        List<Map<String, Object>> topDonors = new ArrayList<>();
        String sql = """
                    SELECT u.userId, u.userName,
                    	SUM(d.amount) as totalAmount,
                        MAX(d.amount) as maxDonation,
                        MIN(d.amount) as minDonation,
                        AVG(d.amount) as avgAmount
                    FROM User u
                    JOIN Donation d ON d.userId = u.userId
                    GROUP BY u.userId, u.userName
                    ORDER BY totalAmount DESC
                    LIMIT ?
                     """;
        try (
                Connection conn = ConnectionDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> donor = new HashMap<>();
                donor.put("userId", rs.getInt("userId"));
                donor.put("userName", rs.getString("userName"));
                donor.put("totalAmount", rs.getBigDecimal("totalAmount"));
                donor.put("maxDonation", rs.getBigDecimal("maxDonation"));
                donor.put("minDonation", rs.getBigDecimal("minDonation"));
                donor.put("avgAmount", rs.getBigDecimal("avgAmount"));
                topDonors.add(donor);
            }
        } catch (SQLException ex) {
            MessageDialog.showError("Lỗi khi lấy danh sách top người quyên góp!");
        }
        return topDonors;
    }

    @Override
    public Map<String, Object> getOverallStatistics() {
        Map<String, Object> stats = new HashMap<>();
        String sql = """
            SELECT 
                (SELECT COUNT(*) FROM Event) as totalEvents,
                (SELECT COUNT(*) FROM User) as totalUsers,
                (SELECT COUNT(*) FROM Donation) as totalDonations,
                (SELECT SUM(amount) FROM Donation) as totalAmount,
                (SELECT COUNT(DISTINCT userId) FROM Donation) as activeDonors,
                (SELECT COUNT(*) FROM Event WHERE currentAmount >= targetAmount) as achievedEvents
        """;

        try (Connection conn = ConnectionDB.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                stats.put("totalEvents", rs.getInt("totalEvents"));
                stats.put("totalUsers", rs.getInt("totalUsers"));
                stats.put("totalDonations", rs.getInt("totalDonations"));
                stats.put("totalAmount", rs.getLong("totalAmount"));
                stats.put("activeDonors", rs.getInt("activeDonors"));
                stats.put("achievedEvents", rs.getInt("achievedEvents"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi lấy thống kê tổng quan", e);
        }
        return stats;
    }

    @Override
    public List<DonationStatistics> getStatistics(Date fromDate, Date toDate) {
        List<DonationStatistics> result = new ArrayList<>();
//        String sql = "SELECT u.fullName, d.amount, d.donationDate " +
//                     "FROM donation d JOIN user u ON d.userId = u.userId " +
//                     "WHERE d.donationDate BETWEEN ? AND ? " +
//                     "ORDER BY d.amount DESC";
//                     
//        try (Connection conn = ConnectionDB.getConnection();
//             PreparedStatement pstmt = conn.prepareStatement(sql)) {
//             
//            pstmt.setDate(1, fromDate);
//            pstmt.setDate(2, toDate);
//            
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                String donorName = rs.getString("fullName");
//                long amount = rs.getLong("amount");
//                Date donationDate = rs.getDate("donationDate"); // Lấy java.sql.Date và chuyển thành java.util.Date
//                
//                result.add(new DonationStatistics(donorName, amount, donationDate));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return result;
    }

}
