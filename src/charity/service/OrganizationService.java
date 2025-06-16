package charity.service;

import charity.model.Organization;
import charity.repository.OrganizationRepository;
import charity.service.IService.IOrganizationService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class OrganizationService implements IOrganizationService{

    private OrganizationRepository organizationRepository = new OrganizationRepository();



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

    // Kiểm tra trùng lặp email hoặc số điện thoại
    public Organization findByEmailOrHotline(String email, String hotline) {
        try {
            return organizationRepository.findByEmailOrHotline(email, hotline);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra trùng lặp: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Kiểm tra trùng lặp email hoặc số điện thoại, trừ tổ chức có id tương ứng
    public Organization findByEmailOrHotlineExceptId(String email, String hotline, int id) {
        try {
            return organizationRepository.findByEmailOrHotlineExceptId(email, hotline, id);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra trùng lặp: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Kiểm tra trùng lặp tên tổ chức
    public Organization findByName(String name) {
        try {
            return organizationRepository.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra tên tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    // Kiểm tra trùng lặp tên tổ chức, trừ tổ chức có id tương ứng
    public Organization findByNameExceptId(String name, int id) {
        try {
            return organizationRepository.findByNameExceptId(name, id);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra tên tổ chức: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
