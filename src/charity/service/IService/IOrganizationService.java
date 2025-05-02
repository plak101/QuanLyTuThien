/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.service.IService;

import charity.model.Organization;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IOrganizationService {
    public List<Organization> getAllOrganization();
    
    public Organization getOrganizationById(int id);
    public String getNameById(int id);

    public boolean addOrganization(Organization organization);

    public boolean updateOrganization(Organization organization);

    public boolean deleteOrganization(int id);
    
    public int getTotalEvent(int id);
    
    public int getOrganizationCount();
}
