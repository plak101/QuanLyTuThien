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
    private OrganizationPanelController controller;

    public OrganizationPanel() {
        initComponents();
        // Initialize the controller with all necessary components
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
            btnDelete
        );
        controller.setOrganizationTable();
        controller.setEvent();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Top panel with search
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));

        // Sử dụng màu đen thay vì màu đỏ cho tất cả các label
        Color labelColor = Color.BLACK;
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);

        JLabel lblSearch = new JLabel("SEARCH");
        lblSearch.setForeground(labelColor);
        lblSearch.setFont(labelFont);

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 30));
        txtSearch.setBackground(Color.LIGHT_GRAY);

        // Tạo panel con để căn chỉnh các thành phần search
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.add(lblSearch);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(txtSearch);

        topPanel.add(Box.createHorizontalGlue());
        topPanel.add(searchPanel);
        topPanel.add(Box.createHorizontalGlue());

        // Middle panel with input fields - sử dụng GridLayout để căn đối hơn
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        
        // Tạo các panel con để chứa từng cặp label và textfield
        JPanel row1 = new JPanel();
        row1.setBackground(Color.WHITE);
        row1.setLayout(new BoxLayout(row1, BoxLayout.X_AXIS));
        
        JPanel row2 = new JPanel();
        row2.setBackground(Color.WHITE);
        row2.setLayout(new BoxLayout(row2, BoxLayout.X_AXIS));
        
        // Organization ID field
        JLabel lblOrgId = new JLabel("Mã tổ chức");
        lblOrgId.setForeground(labelColor);
        lblOrgId.setFont(labelFont);
        lblOrgId.setPreferredSize(new java.awt.Dimension(100, 30));
        
        txtOrgId = new JTextField();
        txtOrgId.setPreferredSize(new java.awt.Dimension(100, 30));
        txtOrgId.setBackground(Color.LIGHT_GRAY);
        txtOrgId.setEditable(false);
        
        // Organization Name field
        JLabel lblOrgName = new JLabel("Tên tổ chức");
        lblOrgName.setForeground(labelColor);
        lblOrgName.setFont(labelFont);
        lblOrgName.setPreferredSize(new java.awt.Dimension(100, 30));
        
        txtOrgName = new JTextField();
        txtOrgName.setPreferredSize(new java.awt.Dimension(200, 30));
        txtOrgName.setBackground(Color.LIGHT_GRAY);
        
        // Email field
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(labelColor);
        lblEmail.setFont(labelFont);
        lblEmail.setPreferredSize(new java.awt.Dimension(80, 30));
        
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new java.awt.Dimension(200, 30));
        txtEmail.setBackground(Color.LIGHT_GRAY);
        
        // Hotline field
        JLabel lblHotline = new JLabel("Hotline");
        lblHotline.setForeground(labelColor);
        lblHotline.setFont(labelFont);
        lblHotline.setPreferredSize(new java.awt.Dimension(80, 30));
        
        txtHotline = new JTextField();
        txtHotline.setPreferredSize(new java.awt.Dimension(150, 30));
        txtHotline.setBackground(Color.LIGHT_GRAY);
        
        // Address field
        JLabel lblAddress = new JLabel("Địa chỉ");
        lblAddress.setForeground(labelColor);
        lblAddress.setFont(labelFont);
        lblAddress.setPreferredSize(new java.awt.Dimension(80, 30));
        
        txtAddress = new JTextField();
        txtAddress.setPreferredSize(new java.awt.Dimension(200, 30));
        txtAddress.setBackground(Color.LIGHT_GRAY);
        
        // Thêm các thành phần vào hàng 1
        JPanel orgIdPanel = new JPanel();
        orgIdPanel.setBackground(Color.WHITE);
        orgIdPanel.setLayout(new BoxLayout(orgIdPanel, BoxLayout.X_AXIS));
        orgIdPanel.add(lblOrgId);
        orgIdPanel.add(Box.createHorizontalStrut(5));
        orgIdPanel.add(txtOrgId);
        
        JPanel orgNamePanel = new JPanel();
        orgNamePanel.setBackground(Color.WHITE);
        orgNamePanel.setLayout(new BoxLayout(orgNamePanel, BoxLayout.X_AXIS));
        orgNamePanel.add(lblOrgName);
        orgNamePanel.add(Box.createHorizontalStrut(5));
        orgNamePanel.add(txtOrgName);
        
        JPanel emailPanel = new JPanel();
        emailPanel.setBackground(Color.WHITE);
        emailPanel.setLayout(new BoxLayout(emailPanel, BoxLayout.X_AXIS));
        emailPanel.add(lblEmail);
        emailPanel.add(Box.createHorizontalStrut(5));
        emailPanel.add(txtEmail);
        
        row1.add(orgIdPanel);
        row1.add(Box.createHorizontalStrut(20));
        row1.add(orgNamePanel);
        row1.add(Box.createHorizontalStrut(20));
        row1.add(emailPanel);
        
        // Thêm các thành phần vào hàng 2
        JPanel hotlinePanel = new JPanel();
        hotlinePanel.setBackground(Color.WHITE);
        hotlinePanel.setLayout(new BoxLayout(hotlinePanel, BoxLayout.X_AXIS));
        hotlinePanel.add(lblHotline);
        hotlinePanel.add(Box.createHorizontalStrut(5));
        hotlinePanel.add(txtHotline);
        
        JPanel addressPanel = new JPanel();
        addressPanel.setBackground(Color.WHITE);
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.X_AXIS));
        addressPanel.add(lblAddress);
        addressPanel.add(Box.createHorizontalStrut(5));
        addressPanel.add(txtAddress);
        
        row2.add(hotlinePanel);
        row2.add(Box.createHorizontalStrut(20));
        row2.add(addressPanel);
        row2.add(Box.createHorizontalStrut(20));
        row2.add(Box.createHorizontalGlue());
        
        // Thêm các hàng vào middlePanel
        middlePanel.add(row1);
        middlePanel.add(Box.createVerticalStrut(15));
        middlePanel.add(row2);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        btnAdd = new GButton("THÊM");
        btnAdd.setBackground(new Color(100, 180, 100));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnEdit = new GButton("SỬA");
        btnEdit.setBackground(new Color(100, 180, 100));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnDelete = new GButton("XOÁ");
        btnDelete.setBackground(new Color(100, 180, 100));
        btnDelete.setForeground(Color.WHITE);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnClear = new GButton("CLEAR");
        btnClear.setBackground(new Color(100, 180, 100));
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnReset = new GButton("PRINT");
        btnReset.setBackground(new Color(100, 180, 100));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnAdd);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnEdit);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnDelete);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnClear);
        buttonPanel.add(Box.createHorizontalStrut(15));
        buttonPanel.add(btnReset);
        buttonPanel.add(Box.createHorizontalGlue());
        
        // Table panel for displaying data
        jpnTable = new JPanel();
        jpnTable.setBackground(Color.LIGHT_GRAY);
        jpnTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTableTitle = new JLabel("DANH SÁCH TỔ CHỨC TỪ THIỆN");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTableTitle.setForeground(labelColor);
        lblTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Add all panels to main panel
        add(topPanel);
        add(Box.createVerticalStrut(20));
        add(middlePanel);
        add(Box.createVerticalStrut(20));
        add(buttonPanel);
        add(Box.createVerticalStrut(20));
        add(lblTableTitle);
        add(Box.createVerticalStrut(10));
        add(jpnTable);
        
        // Add action listeners
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