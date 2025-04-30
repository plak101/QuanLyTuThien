package charity.controller;

import charity.model.Organization;
import charity.service.OrganizationService;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClassTableModel {
    
    private OrganizationService organizationService = null;
    
    public ClassTableModel() {
        organizationService = new OrganizationService();
    }
    
    public DefaultTableModel getOrganizationTable(List<Organization> organizations) {
        // Define column names
        String[] columnNames = {"ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ"};
        
        // Create a default table model with specified columns
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        // Add data rows
        for (Organization org : organizations) {
            Object[] row = {
                org.getId(),
                org.getName(),
                org.getEmail(),
                org.getHotline(),
                org.getAddress()
            };
            model.addRow(row);
        }
        
        return model;
    }
}