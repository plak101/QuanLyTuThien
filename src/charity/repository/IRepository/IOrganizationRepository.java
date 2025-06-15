package charity.repository.IRepository;

import charity.model.Donation;
import charity.model.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IOrganizationRepository {

    public List<Organization> getAllOrganization();
    
    public Organization getOrganizationById(int id);
    public String getNameById(int id);

    public boolean addOrganization(Organization organization);

    public boolean updateOrganization(Organization organization);

    public boolean deleteOrganization(int id);
    
    public int getTotalEvent(int id);
    
    public int getOrganizationCount();
    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);

    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
    
    public Organization findByEmailOrHotline(String email, String hotline) throws SQLException;
    public Organization findByEmailOrHotlineExceptId(String email, String hotline, int id) throws SQLException;
}
