package charity.controller.AdminController;

import charity.component.ColorCustom;
import charity.component.GButton;
import charity.model.Organization;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddOrganizationDialog extends JDialog {
    private JTextField txtOrgName;
    private JTextField txtEmail;
    private JTextField txtHotline;
    private JTextField txtAddress;
    private GButton btnSave;
    private GButton btnCancel;
    private boolean confirmed = false;
    private Organization organization;    public AddOrganizationDialog(Window parent) {
        super(parent, "Thêm tổ chức mới");
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Labels
        Color labelColor = Color.BLACK;
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel lblOrgName = new JLabel("Tên tổ chức:");
        lblOrgName.setForeground(labelColor);
        lblOrgName.setFont(labelFont);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(labelColor);
        lblEmail.setFont(labelFont);

        JLabel lblHotline = new JLabel("Hotline:");
        lblHotline.setForeground(labelColor);
        lblHotline.setFont(labelFont);

        JLabel lblAddress = new JLabel("Địa chỉ:");
        lblAddress.setForeground(labelColor);
        lblAddress.setFont(labelFont);

        // Text fields
        txtOrgName = new JTextField();
        txtOrgName.setPreferredSize(new Dimension(300, 30));
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(300, 30));
        txtHotline = new JTextField();
        txtHotline.setPreferredSize(new Dimension(300, 30));
        txtAddress = new JTextField();
        txtAddress.setPreferredSize(new Dimension(300, 30));

        // Add components to form panel
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblOrgName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtOrgName, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblHotline, gbc);
        gbc.gridx = 1;
        formPanel.add(txtHotline, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(lblAddress, gbc);
        gbc.gridx = 1;
        formPanel.add(txtAddress, gbc);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        btnSave = new GButton("LƯU");
        btnSave.setColor(ColorCustom.colorBtnAdd());
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnCancel = new GButton("HỦY");
        btnCancel.setColor(ColorCustom.colorBtnDelete());
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        // Add panels to main panel
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(buttonPanel);

        // Add action listeners
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateForm()) {
                    organization = new Organization();
                    organization.setName(txtOrgName.getText().trim());
                    organization.setEmail(txtEmail.getText().trim());
                    organization.setHotline(txtHotline.getText().trim());
                    organization.setAddress(txtAddress.getText().trim());
                    confirmed = true;
                    dispose();
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
        pack();
        setResizable(false);
    }

    private boolean validateForm() {
        // Check if name is provided
        if (txtOrgName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tổ chức!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtOrgName.requestFocus();
            return false;
        }

        // Check email format
        String email = txtEmail.getText().trim();
        if (!email.isEmpty() && !isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Email không hợp lệ!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtEmail.requestFocus();
            return false;
        }

        // Check hotline
        String hotline = txtHotline.getText().trim();
        if (!hotline.isEmpty() && !isValidPhone(hotline)) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ!",
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            txtHotline.requestFocus();
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9]{10,15}$";
        return phone.matches(phoneRegex);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Organization getOrganization() {
        return organization;
    }
}
