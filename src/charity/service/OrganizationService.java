package charity.service;

import charity.model.Organization;
import charity.repository.IRepository.IOrganizationRepository;
import charity.repository.OrganizationRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class OrganizationService {
    private IOrganizationRepository organizationRepository=new OrganizationRepository();
    
    public List<Organization> getAllOrganization(){
        return organizationRepository.getAllOrganization();
    }

    public boolean addOrganization(Organization organization){
        return organizationRepository.addOrganization(organization);
    }

    public boolean updateOrganization(Organization organization){
        return organizationRepository.updateOrganization(organization);
    }

    public boolean deleteOrganization(int id){
        return organizationRepository.deleteOrganization(id);
    }
    
    public Organization getOrganizationById(int id){
        return organizationRepository.getOrganizationById(id);
    }
    public String getNameById(int id){
        return organizationRepository.getNameById(id);
    }
    public int getTotalEvent(int id){
        return organizationRepository.getTotalEvent(id);
    }
    public int getOrganizationCount(){
        return organizationRepository.getOrganizationCount();
    }
}
