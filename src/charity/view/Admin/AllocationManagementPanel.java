package charity.view.Admin;

import charity.model.DonationAllocation;
import charity.service.AllocationService;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AllocationManagementPanel extends JPanel {
    private AllocationService allocationService;
    private JTable allocationTable;
    private DefaultTableModel tableModel;
    private JTextField amountField;
    private JTextField purposeField;
    private JButton createButton;
    private JButton updateStatusButton;
    private JButton uploadEvidenceButton;
    private int currentEventId;
    private int currentUserId;

    public AllocationManagementPanel(int eventId, int userId) {
        this.allocationService = new AllocationService();
        this.currentEventId = eventId;
        this.currentUserId = userId;
        
        initComponents();
        loadAllocations();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Amount Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Amount:"), gbc);

        gbc.gridx = 1;
        amountField = new JTextField(20);
        formPanel.add(amountField, gbc);

        // Purpose Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Purpose:"), gbc);

        gbc.gridx = 1;
        purposeField = new JTextField(20);
        formPanel.add(purposeField, gbc);

        // Create Button
        gbc.gridx = 1;
        gbc.gridy = 2;
        createButton = new JButton("Create Allocation");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAllocation();
            }
        });
        formPanel.add(createButton, gbc);

        add(formPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"ID", "Amount", "Purpose", "Status", "Date", "Used Amount"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        allocationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(allocationTable);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        updateStatusButton = new JButton("Update Status");
        updateStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatus();
            }
        });
        buttonPanel.add(updateStatusButton);

        uploadEvidenceButton = new JButton("Upload Evidence");
        uploadEvidenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadEvidence();
            }
        });
        buttonPanel.add(uploadEvidenceButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadAllocations() {
        tableModel.setRowCount(0);
        List<DonationAllocation> allocations = allocationService.getAllocationsByEvent(currentEventId);
        
        for (DonationAllocation allocation : allocations) {
            Object[] row = {
                allocation.getId(),
                allocation.getAmount(),
                allocation.getPurpose(),
                allocation.getStatus(),
                allocation.getAllocationDate(),
                allocation.getUsedAmount()
            };
            tableModel.addRow(row);
        }

        // Update remaining amount label if exists
        double remaining = allocationService.getRemainingAmount(currentEventId);
        // You might want to add a label to show this information
    }

    private void createAllocation() {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String purpose = purposeField.getText();

            DonationAllocation allocation = new DonationAllocation();
            allocation.setEventId(currentEventId);
            allocation.setAmount(amount);
            allocation.setPurpose(purpose);
            allocation.setStatus("PENDING");
            allocation.setAllocationDate(new Date(System.currentTimeMillis()));
            allocation.setUsedAmount(0);
            allocation.setCreatedBy(currentUserId);

            if (allocationService.createAllocationPlan(allocation)) {
                JOptionPane.showMessageDialog(this, "Allocation created successfully!");
                loadAllocations();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to create allocation. Please check the amount.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
        }
    }

    private void updateStatus() {
        int selectedRow = allocationTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an allocation to update.");
            return;
        }

        int allocationId = (int) tableModel.getValueAt(selectedRow, 0);
        String newStatus = JOptionPane.showInputDialog("Enter new status (PENDING/APPROVED/COMPLETED):");
        if (newStatus != null && !newStatus.isEmpty()) {
            String usedAmountStr = JOptionPane.showInputDialog("Enter used amount:");
            try {
                double usedAmount = Double.parseDouble(usedAmountStr);
                if (allocationService.updateAllocationStatus(allocationId, newStatus, usedAmount)) {
                    JOptionPane.showMessageDialog(this, "Status updated successfully!");
                    loadAllocations();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update status.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            }
        }
    }

    private void uploadEvidence() {
        int selectedRow = allocationTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an allocation to update.");
            return;
        }

        int allocationId = (int) tableModel.getValueAt(selectedRow, 0);
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            String evidencePath = fileChooser.getSelectedFile().getPath();
            if (allocationService.uploadEvidence(allocationId, evidencePath)) {
                JOptionPane.showMessageDialog(this, "Evidence uploaded successfully!");
                loadAllocations();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to upload evidence.");
            }
        }
    }

    private void clearForm() {
        amountField.setText("");
        purposeField.setText("");
    }
}
