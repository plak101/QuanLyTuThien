package charity.repository.IRepository;

import charity.model.Donation;
import charity.model.Organization;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IOrganizationRepository {

    public List<Organization> getAllOrganization();

    public boolean addOrganization(Organization organization);

    public boolean updateOrganization(Organization organization);

    public boolean deleteOrganization(int id);

    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);

    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);
}
