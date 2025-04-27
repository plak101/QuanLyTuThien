/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.repository.IRepository;

import charity.model.AssignedEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface IAssignedEventRepository {

    public List<AssignedEvent> getAllDonation();

    public boolean addAssignedEvent(AssignedEvent assignedEvent);

    public boolean updateAssignedEvent(AssignedEvent assignedEvent);

    public boolean deleteAssignedEvent(int id);

    //Dong ket noi
    public void closeResources(Connection conn, PreparedStatement ps);

    public void closeResources(Connection conn, PreparedStatement ps, ResultSet rs);

}
