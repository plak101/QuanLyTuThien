package charity.service;

import charity.model.Organization;
import charity.repository.OrganizationRepository;
import charity.service.IService.IOrganizationService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OrganizationService implements IOrganizationService{

    private OrganizationRepository organizationRepository = new OrganizationRepository();


//    public List<Organization> getAllOrganization() {
//        List<Organization> organizations = new ArrayList<>();
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet rs = null;
//
//        try {
//            connection = ConnectionDB.getConnection(); // Get DB connection
//            String sql = "SELECT * FROM Organization ORDER BY id";
//            statement = connection.createStatement();
//            rs = statement.executeQuery(sql);
//
//            while (rs.next()) {
//                Organization org = new Organization();
//                org.setId(rs.getInt("id"));
//                org.setName(rs.getString("name"));
//                org.setEmail(rs.getString("email"));
//                org.setHotline(rs.getString("hotline"));
//                org.setAddress(rs.getString("address"));
//                organizations.add(org); // Thêm tổ chức vào danh sách
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(OrganizationService.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            // Đảm bảo đóng các tài nguyên sau khi sử dụng
//            try {
//                if (rs != null) {
//                    rs.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException ex) {
//                Logger.getLogger(OrganizationService.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return organizations; // Trả về danh sách các tổ chức
//    }

    public OrganizationService() {
        this.organizationRepository = new OrganizationRepository();
    }

    // Get all organizations from the database using repository
    @Override
    public List<Organization> getAllOrganization() {
        try {
            return organizationRepository.getAllOrganization();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }

    // Get the count of events associated with a specific organization
    @Override
    public int getTotalEvent(int organizationId) {
        try {
            return organizationRepository.getTotalEvent(organizationId);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi đếm số sự kiện: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return 0;
        }
    }

    public List<String> getRelatedEvents(int organizationId) {
        List<String> events = new ArrayList<>();
        // This method needs to be implemented in the repository
        // For now, we'll simulate it by checking if there are any events
        try {
            int totalEvents = organizationRepository.getTotalEvent(organizationId);
            if (totalEvents > 0) {
                // In a real implementation, this would be fetched from the repository
                events.add("Sự kiện liên quan đến tổ chức này");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách sự kiện: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return events;
    }

    // Add a new organization
    @Override
    public boolean addOrganization(Organization org) {
        try {
            return organizationRepository.addOrganization(org);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi thêm tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Update an existing organization
    @Override
    public boolean updateOrganization(Organization org) {
        try {
            return organizationRepository.updateOrganization(org);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Delete an organization
    @Override
    public boolean deleteOrganization(int id) {
        try {
            // Check if organization has related events first
            int totalEvents = getTotalEvent(id);
            if (totalEvents > 0) {
                return false; // Organization has related events, can't delete
            }

            return organizationRepository.deleteOrganization(id);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Get an organization by ID
    @Override
    public Organization getOrganizationById(int id) {
        try {
            return organizationRepository.getOrganizationById(id);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy thông tin tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Search organizations by name, email or address containing the keyword
    public List<Organization> searchOrganizations(String keyword) {
        // This method would ideally use the repository
        // For now, we'll filter from getAllOrganization
        List<Organization> allOrgs = getAllOrganization();
        List<Organization> filteredOrgs = new ArrayList<>();

        for (Organization org : allOrgs) {
            if (org.getName().toLowerCase().contains(keyword.toLowerCase())
                    || (org.getEmail() != null && org.getEmail().toLowerCase().contains(keyword.toLowerCase()))
                    || (org.getAddress() != null && org.getAddress().toLowerCase().contains(keyword.toLowerCase()))) {
                filteredOrgs.add(org);
            }
        }

        return filteredOrgs;
    }

    @Override
    public String getNameById(int organizationId) {
        return organizationRepository.getNameById(organizationId);
    }

    @Override
    public int getOrganizationCount() {
        return organizationRepository.getOrganizationCount();
    }
}
