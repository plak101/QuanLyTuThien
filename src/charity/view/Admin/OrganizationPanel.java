package charity.view.Admin;

import charity.controller.AdminController.OrganizationPanelController;
import charity.component.GButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class OrganizationPanel extends javax.swing.JPanel {

    private JTextField txtSearch;
    private JTextField txtOrgId;
    private JTextField txtOrgName;
    private JTextField txtEmail;
    private JTextField txtHotline;
    private JTextField txtAddress;
    private JPanel jpnTable;
    private GButton btnAdd;
    private GButton btnEdit;
    private GButton btnDelete;
    private GButton btnClear;
    private GButton btnReset;
    private GButton btnSave;
    private GButton btnCancel;
    private OrganizationPanelController controller;

    public OrganizationPanel() {
        initComponents();
        controller = new OrganizationPanelController(
            txtSearch, 
            btnReset, 
            jpnTable,
            txtOrgId,
            txtOrgName,
            txtEmail,
            txtHotline,
            txtAddress,
            btnAdd,
            btnEdit,
            btnDelete,
            btnCancel,
            btnSave
        );
        controller.setOrganizationTable();
        controller.setEvent();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(Color.WHITE);

        // Main panel initialization
        jpnTable = new JPanel();
        JPanel formPanel = new JPanel();
        JPanel actionPanel = new JPanel();

        // Form panel settings
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createTitledBorder(null, "Thông tin Tổ chức", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 0, 14)));

        // Initialize form components
        JLabel lblOrgId = new JLabel("ID");
        JLabel lblOrgName = new JLabel("Tên tổ chức");
        JLabel lblEmail = new JLabel("Email");
        JLabel lblHotline = new JLabel("Hotline");
        JLabel lblAddress = new JLabel("Địa chỉ");

        txtOrgId = new JTextField();
        txtOrgName = new JTextField();
        txtEmail = new JTextField();
        txtHotline = new JTextField();
        txtAddress = new JTextField();

        // Set form layout
        GroupLayout formLayout = new GroupLayout(formPanel);
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(lblOrgId, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtOrgId, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEmail)
                        .addGap(12, 12, 12)
                        .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(lblOrgName)
                        .addGap(18, 18, 18)
                        .addComponent(txtOrgName, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblHotline)
                        .addGap(12, 12, 12)
                        .addComponent(txtHotline, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                    .addGroup(formLayout.createSequentialGroup()
                        .addComponent(lblAddress)
                        .addGap(18, 18, 18)
                        .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrgId)
                    .addComponent(txtOrgId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOrgName)
                    .addComponent(txtOrgName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHotline)
                    .addComponent(txtHotline, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(formLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Action panel
        actionPanel.setBackground(Color.WHITE);
        JLabel lblSearch = new JLabel("Tìm kiếm");
        txtSearch = new JTextField();
        btnAdd = new GButton("Thêm");
        btnEdit = new GButton("Sửa");
        btnDelete = new GButton("Xóa");
        btnReset = new GButton("In");
        btnClear = new GButton("Làm mới");
        btnSave = new GButton("Lưu");
        btnCancel = new GButton("Hủy");

        // Style buttons
        btnAdd.changeColor("#43A047");
        btnEdit.changeColor("#43A047");
        btnDelete.changeColor("#E53935");
        btnReset.changeColor("#43A047");
        btnClear.changeColor("#43A047");
        btnSave.changeColor("#43A047");
        btnCancel.changeColor("#E53935");

        // Set initial visibility
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

        // Action panel layout
        GroupLayout actionLayout = new GroupLayout(actionPanel);
        actionPanel.setLayout(actionLayout);
        actionLayout.setHorizontalGroup(
            actionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(actionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblSearch)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        actionLayout.setVerticalGroup(
            actionLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(actionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSearch)
                    .addComponent(txtSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        // Table panel
        jpnTable.setBackground(new Color(204, 255, 255));
        GroupLayout tableLayout = new GroupLayout(jpnTable);
        jpnTable.setLayout(tableLayout);
        tableLayout.setHorizontalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 968, Short.MAX_VALUE)
        );
        tableLayout.setVerticalGroup(
            tableLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 293, Short.MAX_VALUE)
        );

        // Main layout
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(formPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jpnTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(formPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actionPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jpnTable, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        // Add button action
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtOrgId.setText("");
                txtOrgName.setText("");
                txtEmail.setText("");
                txtHotline.setText("");
                txtAddress.setText("");
                txtOrgName.requestFocus();
            }
        });
    }
}