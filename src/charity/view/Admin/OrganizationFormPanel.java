package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.GButton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Pattern;

public class OrganizationFormPanel extends JPanel {
    private JTextField txtOrgName;
    private JTextField txtEmail;
    private JTextField txtHotline;
    private JTextField txtAddress;
    private GButton btnSave;
    private GButton btnCancel;
    private JLabel titleLabel;

    public OrganizationFormPanel(boolean isEditMode) {
        initComponents(isEditMode);
    }

    private void initComponents(boolean isEditMode) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.decode("#B4EBE6"));
        titlePanel.setPreferredSize(new Dimension(400, 60));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        titleLabel = new JLabel(isEditMode ? "SỬA THÔNG TIN TỔ CHỨC" : "THÊM TỔ CHỨC MỚI");
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

        // Create labels with required field indicators
        JLabel lblOrgName = new JLabel("Tên tổ chức: *");
        JLabel lblEmail = new JLabel("Email: *");
        JLabel lblHotline = new JLabel("Hotline: *");
        JLabel lblAddress = new JLabel("Địa chỉ: *");

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

        // Note about required fields
        JLabel lblRequired = new JLabel("* Tất cả các trường đều bắt buộc");
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

        // Add all panels
        add(titlePanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Validation methods
    public boolean validateForm() throws Exception {
        // Kiểm tra tên tổ chức
        String name = getOrgName();
        if (name.isEmpty()) {
            throw new Exception("Vui lòng nhập tên tổ chức!");
        }
        if (name.length() < 3) {
            throw new Exception("Tên tổ chức phải có ít nhất 3 ký tự!");
        }
        
        // Kiểm tra email
        String email = getEmail();
        if (email.isEmpty()) {
            throw new Exception("Vui lòng nhập email!");
        }
        if (!isValidEmail(email)) {
            throw new Exception("Email không hợp lệ!\nVí dụ email hợp lệ: example@domain.com");
        }

        // Kiểm tra số điện thoại
        String hotline = getHotline();
        if (hotline.isEmpty()) {
            throw new Exception("Vui lòng nhập số điện thoại!");
        }
        if (!isValidPhone(hotline)) {
            throw new Exception("Số điện thoại không hợp lệ!\nSố điện thoại phải có 10 số và bắt đầu bằng số 0");
        }

        // Kiểm tra địa chỉ
        String address = getAddress();
        if (address.isEmpty()) {
            throw new Exception("Vui lòng nhập địa chỉ!");
        }
        if (address.length() < 10) {
            throw new Exception("Địa chỉ phải có ít nhất 10 ký tự!");
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPhone(String phone) {
        // Số điện thoại phải có 10 số và bắt đầu bằng số 0
        String phoneRegex = "^0\\d{9}$";
        return Pattern.matches(phoneRegex, phone);
    }

    // Getters for text fields
    public String getOrgName() { return txtOrgName.getText().trim(); }
    public String getEmail() { return txtEmail.getText().trim(); }
    public String getHotline() { return txtHotline.getText().trim(); }
    public String getAddress() { return txtAddress.getText().trim(); }

    // Setters for text fields
    public void setOrgName(String name) { txtOrgName.setText(name); }
    public void setEmail(String email) { txtEmail.setText(email); }
    public void setHotline(String hotline) { txtHotline.setText(hotline); }
    public void setAddress(String address) { txtAddress.setText(address); }

    // Button getters
    public GButton getSaveButton() { return btnSave; }
    public GButton getCancelButton() { return btnCancel; }

    // Request focus for first field
    public void requestOrgNameFocus() {
        txtOrgName.requestFocusInWindow();
    }

    // Clear all fields
    public void clearFields() {
        txtOrgName.setText("");
        txtEmail.setText("");
        txtHotline.setText("");
        txtAddress.setText("");
    }
}
