package charity.repository;

import charity.model.Organization;
import charity.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import charity.repository.IRepository.IOrganizationRepository;

public class OrganizationRepository implements IOrganizationRepository {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public OrganizationRepository() {
        conn = ConnectionDB.getConnection();
    }

    @Override
    public List<Organization> getAllOrganization() {
        List<Organization> organizations = new ArrayList<>();
        conn = ConnectionDB.getConnection();
        String query = "SELECT * FROM organization";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Organization organization = new Organization(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("hotline"),
                        rs.getString("address")
                );

                organizations.add(organization);
            }
        } catch (SQLException ex) {
            java.util.logging.Logger.getLogger(OrganizationRepository.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return organizations;

    }

    @Override
    public boolean addOrganization(Organization organization) {
        conn = ConnectionDB.getConnection();
        String sql = "INSERT INTO organization (name, email, hotline, address) VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, organization.getName());
            ps.setString(2, organization.getEmail());
            ps.setString(3, organization.getHotline());
            ps.setString(4, organization.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            DBUtils.showSQLError(ex, "Lỗi khi thêm tổ chức từ thiện!");
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean updateOrganization(Organization organization) {
        conn = ConnectionDB.getConnection();
        String sql = "UPDATE organization SET name=?, email=?, hotline=?, address=? WHERE id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, organization.getName());
            ps.setString(2, organization.getEmail());
            ps.setString(3, organization.getHotline());
            ps.setString(4, organization.getAddress());
            ps.setInt(5, organization.getId());  // Missing WHERE clause with ID
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps);
        }
        return false;
    }

    @Override
    public boolean deleteOrganization(int id) {
        conn = ConnectionDB.getConnection();
        String sql = "DELETE FROM organization WHERE id=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Organization getOrganizationById(int id) {
        try {
            conn = ConnectionDB.getConnection();
            String query = "SELECT * FROM Organization WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return new Organization(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("hotline"),
                        rs.getString("address")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }

        return null;
    }

    @Override
    public String getNameById(int id) {
        try {
            conn = ConnectionDB.getConnection();
            String query = "SELECT name FROM Organization WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return null;
    }

    @Override
    public int getTotalEvent(int id) {
        String sql = "SELECT COUNT(e.eventId) AS totalEvent\n"
                + "FROM Organization o\n"
                + "LEFT JOIN Event e ON o.id = e.organizationId\n"
                + "where o.id =?";
        conn = ConnectionDB.getConnection();
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalEvent");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeResources(conn, ps, rs);
        }
        return 0;
    }

    @Override
    public int getOrganizationCount() {
        int count =0;
        String query = "SELECT COUNT(*) FROM organization";
        conn = ConnectionDB.getConnection();
        try {
            ps =conn.prepareStatement(query);
            rs= ps.executeQuery();
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrganizationRepository.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            closeResources(conn, ps, rs);
        }
        return count;
    }

}
