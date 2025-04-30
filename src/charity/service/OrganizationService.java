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
import java.text.SimpleDateFormat;
import java.util.Date;

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

    /**
     * Lấy danh sách các sự kiện liên quan đến một tổ chức
     *
     * @param organizationId ID của tổ chức cần kiểm tra
     * @return Danh sách tên các sự kiện liên quan
     */
    public List<String> getRelatedEvents(int organizationId) {
        List<String> events = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            // Sửa truy vấn để phù hợp với cấu trúc bảng thực tế
            String query = "SELECT e.eventId, e.eventName, e.description FROM event e WHERE e.organizationId = ?";

            statement = connection.prepareStatement(query);
            statement.setInt(1, organizationId);

            resultSet = statement.executeQuery();

            // Duyệt qua kết quả và thêm vào danh sách
            while (resultSet.next()) {
                int eventId = resultSet.getInt("eventId");
                String eventName = resultSet.getString("eventName");
                String description = resultSet.getString("description");

                // Định dạng: ID - Tên sự kiện (Mô tả)
                String eventInfo = String.format("#%d - %s (%s)",
                        eventId, eventName,
                        description != null ? description.substring(0, Math.min(50, description.length())) + "..." : "Không có mô tả");

                events.add(eventInfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách sự kiện: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    DatabaseConnection.closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return events;
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
        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = DatabaseConnection.getConnection();

            // Start a transaction
            connection.setAutoCommit(false);

            // Check for related events before attempting deletion
            String checkSql = "SELECT COUNT(*) FROM event WHERE organizationId = ?";
            pstmt = connection.prepareStatement(checkSql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                // Found related events - rollback and return false
                connection.rollback();
                return false; // Controller will separately fetch event details using getRelatedEvents
            }

            // Close the first prepared statement
            pstmt.close();

            // No events found, proceed with deletion
            String deleteSql = "DELETE FROM Organization WHERE id = ?";
            pstmt = connection.prepareStatement(deleteSql);
            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            result = rowsAffected > 0;

            // If successful, commit the transaction
            if (result) {
                connection.commit();
            } else {
                connection.rollback();
            }

        } catch (SQLException e) {
            // Log the specific SQL error
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());

            try {
                // Rollback on error
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Show a more specific error message based on the error code
            String errorMessage = "Lỗi khi xóa tổ chức: ";

            // Check for common error codes
            if (e.getErrorCode() == 1451) {
                // MySQL error code for foreign key constraint violation
                errorMessage += "Không thể xóa tổ chức vì có dữ liệu liên quan đến tổ chức này trong các bảng khác.";
            } else {
                errorMessage += e.getMessage();
            }

            JOptionPane.showMessageDialog(null, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                // Reset auto-commit mode
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Close resources
            DatabaseConnection.closeConnection(connection);
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
