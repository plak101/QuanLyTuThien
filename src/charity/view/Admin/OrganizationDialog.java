package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.GButton;
import charity.model.Organization;
import charity.service.OrganizationService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrganizationDialog extends JDialog {
    private JTextField txtOrgName;
    private JTextField txtEmail;
    private JTextField txtHotline;
    private JTextField txtAddress;
    private GButton btnSave;
    private GButton btnCancel;
    private boolean confirmed = false;
    private Organization organization;
    private boolean isEditMode = false;

    public OrganizationDialog(Window parent, String title, Organization org) {
        super(parent, title);
        this.isEditMode = org != null;
        this.organization = org != null ? org : new Organization();
        initComponents();
        setLocationRelativeTo(parent);
        if (isEditMode) {
            loadOrganizationData();
        }
    }

    private void loadOrganizationData() {
        txtOrgName.setText(organization.getName());
        txtEmail.setText(organization.getEmail());
        txtHotline.setText(organization.getHotline());
        txtAddress.setText(organization.getAddress());
    }

    private void initComponents() {
        // Main panel with background color and title
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#B4EBE6"));
        titlePanel.setPreferredSize(new Dimension(400, 60));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JLabel titleLabel = new JLabel(isEditMode ? "SỬA THÔNG TIN TỔ CHỨC" : "THÊM TỔ CHỨC MỚI");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        titlePanel.add(titleLabel);

        // Form panel with padding
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.weightx = 1.0;

        // Labels with consistent styling
        Color labelColor = Color.BLACK;
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel lblOrgName = new JLabel("Tên tổ chức: *");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblHotline = new JLabel("Hotline:");
        JLabel lblAddress = new JLabel("Địa chỉ:");

        // Style all labels
        for (JLabel lbl : new JLabel[]{lblOrgName, lblEmail, lblHotline, lblAddress}) {
            lbl.setForeground(labelColor);
            lbl.setFont(labelFont);
        }

        // Text fields
        txtOrgName = new JTextField();
        txtEmail = new JTextField();
        txtHotline = new JTextField();
        txtAddress = new JTextField();

        // Style all text fields
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Dimension fieldSize = new Dimension(300, 35);
        Color fieldBackground = Color.WHITE;
        
        for (JTextField field : new JTextField[]{txtOrgName, txtEmail, txtHotline, txtAddress}) {
            field.setPreferredSize(fieldSize);
            field.setFont(inputFont);
            field.setBackground(fieldBackground);
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#B4EBE6")),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)));
        }

        // Add components to form panel
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 1;
        formPanel.add(lblOrgName, gbc);
        gbc.gridy = 1; gbc.gridwidth = 2;
        formPanel.add(txtOrgName, gbc);

        gbc.gridy = 2; gbc.gridwidth = 1;
        formPanel.add(lblEmail, gbc);
        gbc.gridy = 3; gbc.gridwidth = 2;
        formPanel.add(txtEmail, gbc);

        gbc.gridy = 4; gbc.gridwidth = 1;
        formPanel.add(lblHotline, gbc);
        gbc.gridy = 5; gbc.gridwidth = 2;
        formPanel.add(txtHotline, gbc);

        gbc.gridy = 6; gbc.gridwidth = 1;
        formPanel.add(lblAddress, gbc);
        gbc.gridy = 7; gbc.gridwidth = 2;
        formPanel.add(txtAddress, gbc);

        // Note about required field
        JLabel lblRequired = new JLabel("* Trường bắt buộc");
        lblRequired.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblRequired.setForeground(Color.RED);
        gbc.gridy = 8;
        formPanel.add(lblRequired, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(Color.WHITE);

        btnSave = new GButton("LƯU");
        btnSave.setColor(ColorCustom.colorBtnAdd());
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSave.setPreferredSize(new Dimension(100, 40));

        btnCancel = new GButton("HỦY");
        btnCancel.setColor(ColorCustom.colorBtnDelete());
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancel.setPreferredSize(new Dimension(100, 40));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    try {
                        organization.setName(txtOrgName.getText().trim());
                        organization.setEmail(txtEmail.getText().trim());
                        organization.setHotline(txtHotline.getText().trim());
                        organization.setAddress(txtAddress.getText().trim());
                        confirmed = true;
                        dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(OrganizationDialog.this,
                            "Có lỗi xảy ra: " + ex.getMessage(),
                            "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose();
            }
        });

        // Configure dialog
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(400, 550));
        setResizable(false);
        setModalityType(ModalityType.APPLICATION_MODAL);
        pack();

        // Set focus to name field
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtOrgName.requestFocusInWindow();
            }
        });
    }    private boolean validateForm() {
        // Check if name is provided
        String orgName = txtOrgName.getText().trim();
        if (orgName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tổ chức!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtOrgName.requestFocus();
            return false;
        }

        // Check duplicate organization name
        OrganizationService orgService = new OrganizationService();
        Organization existingOrg;
        if (isEditMode) {
            existingOrg = orgService.findByNameExceptId(orgName, organization.getId());
        } else {
            existingOrg = orgService.findByName(orgName);
        }
        
        if (existingOrg != null) {
            JOptionPane.showMessageDialog(this, "Tên tổ chức đã tồn tại!\nVui lòng chọn tên khác.",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtOrgName.requestFocus();
            return false;
        }

        // Check email
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập email!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!\nVí dụ email hợp lệ: example@domain.com",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        // Check hotline
        String hotline = txtHotline.getText().trim();
        if (hotline.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtHotline.requestFocus();
            return false;
        }
        if (!isValidPhone(hotline)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!\nSố điện thoại phải có 10 số và bắt đầu bằng số 0",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtHotline.requestFocus();
            return false;
        }

        // Check address
        if (txtAddress.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtAddress.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }    private boolean isValidPhone(String phone) {
        String phoneRegex = "^0\\d{9}$";
        return phone.matches(phoneRegex);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Organization getOrganization() {
        return organization;
    }
}
