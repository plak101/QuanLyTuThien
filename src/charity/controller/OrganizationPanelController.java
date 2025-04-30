package charity.controller;

import charity.component.GButton;
import charity.UserController.ClassTableModel;
import charity.model.Organization;
import charity.service.OrganizationService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import javax.swing.JTable.PrintMode;

public class OrganizationPanelController {

    private JTextField txtSearch;
    private GButton btnReset;
    private JPanel jpnTable;

    // Form inputs
    private JTextField txtOrgId;
    private JTextField txtOrgName;
    private JTextField txtEmail;
    private JTextField txtHotline;
    private JTextField txtAddress;
    private GButton btnAdd;
    private GButton btnEdit;
    private GButton btnDelete;

    private OrganizationService organizationService = null;
    private ClassTableModel classTableModel = null;
    private TableRowSorter<TableModel> rowSorter = null;
    private JTable organizationTable = null;

    // Constructor
    public OrganizationPanelController(
            JTextField txtSearch,
            GButton btnReset,
            JPanel jpnTable,
            JTextField txtOrgId,
            JTextField txtOrgName,
            JTextField txtEmail,
            JTextField txtHotline,
            JTextField txtAddress,
            GButton btnAdd,
            GButton btnEdit,
            GButton btnDelete) {

        this.txtSearch = txtSearch;
        this.btnReset = btnReset;
        this.jpnTable = jpnTable;
        this.txtOrgId = txtOrgId;
        this.txtOrgName = txtOrgName;
        this.txtEmail = txtEmail;
        this.txtHotline = txtHotline;
        this.txtAddress = txtAddress;
        this.btnAdd = btnAdd;
        this.btnEdit = btnEdit;
        this.btnDelete = btnDelete;

        this.organizationService = new OrganizationService();
        this.classTableModel = new ClassTableModel();
    }

    // Setup the organization table
    public void setOrganizationTable() {
        // Get the list of organizations from the service
        List<Organization> organizations = organizationService.getAllOrganization();

        // Create a table model using the organizations
        DefaultTableModel model = classTableModel.getOrganizationTable(organizations);
        organizationTable = new JTable(model);

        // Setup row sorter for filtering
        rowSorter = new TableRowSorter<>(organizationTable.getModel());
        organizationTable.setRowSorter(rowSorter);

        // Add document listener to the search field
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filterTable();
            }

            private void filterTable() {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        // Add mouse listener to handle row selection
        organizationTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int selectedRow = target.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Convert row index to model index in case of sorting/filtering
                        int modelRow = organizationTable.convertRowIndexToModel(selectedRow);

                        // Get data from the selected row
                        Object id = organizationTable.getModel().getValueAt(modelRow, 0);
                        Object name = organizationTable.getModel().getValueAt(modelRow, 1);
                        Object email = organizationTable.getModel().getValueAt(modelRow, 2);
                        Object hotline = organizationTable.getModel().getValueAt(modelRow, 3);
                        Object address = organizationTable.getModel().getValueAt(modelRow, 4);

                        // Set the data to the form fields
                        txtOrgId.setText(id.toString());
                        txtOrgName.setText(name.toString());
                        txtEmail.setText(email.toString());
                        txtHotline.setText(hotline.toString());
                        txtAddress.setText(address.toString());
                    }
                }
            }
        });

        // Apply table design
        designTable(organizationTable);

        // Display the table
        JScrollPane scroll = new JScrollPane(organizationTable);
        scroll.setViewportView(organizationTable);
        organizationTable.setFillsViewportHeight(true);
        organizationTable.setBackground(Color.white);
        scroll.getViewport().setBackground(Color.white);
        scroll.setPreferredSize(new Dimension(jpnTable.WIDTH, 400));
        jpnTable.setBackground(Color.white);

        jpnTable.removeAll();
        jpnTable.setLayout(new CardLayout());
        jpnTable.add(scroll);
        jpnTable.revalidate();
        jpnTable.repaint();
    }

    // Set the design for the table
    public void designTable(JTable table) {
        // Table header design
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
        table.getTableHeader().setBorder(null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Table body design
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Set column widths
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(4).setMaxWidth(500);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);

        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Refresh the table
        table.validate();
        table.repaint();
    }

    // Setup the event handlers for buttons
    public void setEvent() {
        // Reset/Print button to print the table or refresh it
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // If text on button is "PRINT", print the table
                    if ("PRINT".equals(btnReset.getText())) {
                        MessageFormat header = new MessageFormat("DANH SÁCH TỔ CHỨC TỪ THIỆN");
                        MessageFormat footer = new MessageFormat("Trang {0}");

                        try {
                            organizationTable.print(PrintMode.FIT_WIDTH, header, footer);
                            JOptionPane.showMessageDialog(null, "In thành công!");
                        } catch (PrinterException ex) {
                            JOptionPane.showMessageDialog(null, "Lỗi khi in: " + ex.getMessage(),
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        // Otherwise refresh the table
                        setOrganizationTable();
                    }
                } catch (Exception ex) {
                    // If any error occurs, refresh the table
                    setOrganizationTable();
                }
            }
        });

        // Add button to create a new organization
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    // Create new organization object
                    Organization org = new Organization();
                    org.setName(txtOrgName.getText().trim());
                    org.setEmail(txtEmail.getText().trim());
                    org.setHotline(txtHotline.getText().trim());
                    org.setAddress(txtAddress.getText().trim());

                    // Call service to save the organization
                    boolean success = organizationService.addOrganization(org);

                    if (success) {
                        JOptionPane.showMessageDialog(null, "Thêm tổ chức thành công!");
                        clearForm();
                        setOrganizationTable(); // Refresh table
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm tổ chức thất bại!",
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Edit button to update an existing organization
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    String idStr = txtOrgId.getText().trim();
                    if (idStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn tổ chức cần cập nhật!",
                                "Thông báo", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    try {
                        int id = Integer.parseInt(idStr);

                        // Create organization object with updated info
                        Organization org = new Organization();
                        org.setId(id);
                        org.setName(txtOrgName.getText().trim());
                        org.setEmail(txtEmail.getText().trim());
                        org.setHotline(txtHotline.getText().trim());
                        org.setAddress(txtAddress.getText().trim());

                        // Call service to update
                        boolean success = organizationService.updateOrganization(org);

                        if (success) {
                            JOptionPane.showMessageDialog(null, "Cập nhật tổ chức thành công!");
                            clearForm();
                            setOrganizationTable(); // Refresh table
                        } else {
                            JOptionPane.showMessageDialog(null, "Cập nhật tổ chức thất bại!",
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Mã tổ chức không hợp lệ!",
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Delete button to remove an organization
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = txtOrgId.getText().trim();
                if (idStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn tổ chức cần xóa!",
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                try {
                    int id = Integer.parseInt(idStr);

                    // First check if organization has related events
                    int totalEvents = organizationService.getTotalEvent(id);

                    if (totalEvents > 0) {
                        try {
                            // Lấy danh sách các sự kiện liên quan
                            List<String> relatedEvents = organizationService.getRelatedEvents(id);

                            // Tạo thông báo với danh sách các sự kiện
                            StringBuilder message = new StringBuilder();
                            message.append("Không thể xóa tổ chức này vì có ").append(totalEvents).append(" sự kiện liên quan!\n");
                            message.append("Vui lòng xoá các sự kiện liên quan trước.\n\n");
                            message.append("Danh sách sự kiện liên quan:\n");

                            for (String event : relatedEvents) {
                                message.append("- ").append(event).append("\n");
                            }

                            JOptionPane.showMessageDialog(null,
                                    message.toString(),
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            // Nếu có lỗi khi lấy danh sách sự kiện, hiển thị thông báo đơn giản hơn
                            JOptionPane.showMessageDialog(null,
                                    "Không thể xóa tổ chức này vì có " + totalEvents + " sự kiện liên quan!\n"
                                    + "Vui lòng xoá các sự kiện liên quan trước.",
                                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                        return;
                    }

                    // Confirm before deleting
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Bạn có chắc chắn muốn xóa tổ chức này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION);

                    if (confirm == JOptionPane.YES_OPTION) {
                        // Call service to delete
                        boolean success = organizationService.deleteOrganization(id);

                        if (success) {
                            JOptionPane.showMessageDialog(null, "Xóa tổ chức thành công!");
                            clearForm();
                            setOrganizationTable(); // Refresh table
                        } else {
                            // Attempt to get related events again in case they were added between checks
                            try {
                                totalEvents = organizationService.getTotalEvent(id);
                                if (totalEvents > 0) {
                                    List<String> relatedEvents = organizationService.getRelatedEvents(id);

                                    StringBuilder message = new StringBuilder();
                                    message.append("Không thể xóa tổ chức này vì có ").append(totalEvents).append(" sự kiện liên quan!\n");
                                    message.append("Vui lòng xoá các sự kiện liên quan trước.\n\n");
                                    message.append("Danh sách sự kiện liên quan:\n");

                                    for (String event : relatedEvents) {
                                        message.append("- ").append(event).append("\n");
                                    }

                                    JOptionPane.showMessageDialog(null,
                                            message.toString(),
                                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Xóa tổ chức thất bại!",
                                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Xóa tổ chức thất bại!",
                                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                            }
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Mã tổ chức không hợp lệ!",
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Helper method to validate form inputs
    private boolean validateForm() {
        // Check if name is provided
        if (txtOrgName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tổ chức!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtOrgName.requestFocus();
            return false;
        }

        // Check email format
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Email không hợp lệ!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        // Check hotline (optional, but must be valid if provided)
        String hotline = txtHotline.getText().trim();
        if (!hotline.isEmpty() && !isValidPhone(hotline)) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtHotline.requestFocus();
            return false;
        }

        return true;
    }

    // Helper method to validate email format
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    // Helper method to validate phone number
    private boolean isValidPhone(String phone) {
        // Simple regex for phone number (can be adjusted based on country format)
        String phoneRegex = "^[0-9]{10,15}$";
        return phone.matches(phoneRegex);
    }

    // Helper method to clear the form
    private void clearForm() {
        txtOrgId.setText("");
        txtOrgName.setText("");
        txtEmail.setText("");
        txtHotline.setText("");
        txtAddress.setText("");
        txtOrgName.requestFocus();
    }
}
