package charity.viewMain;

import charity.controller.OrganizationPanelController;
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

        JLabel lblBranch = new JLabel("CHI NHÁNH");
        lblBranch.setForeground(Color.RED);
        lblBranch.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JComboBox<String> cmbBranch = new JComboBox<>(new String[]{"Tất cả"});
        cmbBranch.setBackground(Color.LIGHT_GRAY);
        cmbBranch.setPreferredSize(new java.awt.Dimension(150, 30));

        JLabel lblAddressSearch = new JLabel("ĐỊA CHỈ");
        lblAddressSearch.setForeground(Color.RED);
        lblAddressSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JTextField txtAddressSearch = new JTextField();
        txtAddressSearch.setPreferredSize(new java.awt.Dimension(150, 30));
        txtAddressSearch.setBackground(Color.LIGHT_GRAY);

        JLabel lblSearch = new JLabel("SEARCH");
        lblSearch.setForeground(Color.RED);
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new java.awt.Dimension(200, 30));
        txtSearch.setBackground(Color.LIGHT_GRAY);

        topPanel.add(lblBranch);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(cmbBranch);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(lblAddressSearch);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(txtAddressSearch);
        topPanel.add(Box.createHorizontalStrut(20));
        topPanel.add(lblSearch);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(txtSearch);

        // Middle panel with input fields
        JPanel middlePanel = new JPanel();
        middlePanel.setBackground(Color.WHITE);
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
        
        // Organization ID field
        JLabel lblOrgId = new JLabel("Mã tổ chức");
        lblOrgId.setForeground(Color.RED);
        lblOrgId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtOrgId = new JTextField();
        txtOrgId.setPreferredSize(new java.awt.Dimension(100, 30));
        txtOrgId.setBackground(Color.LIGHT_GRAY);
        txtOrgId.setEditable(false);
        
        // Organization Name field
        JLabel lblOrgName = new JLabel("Tên tổ chức");
        lblOrgName.setForeground(Color.RED);
        lblOrgName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtOrgName = new JTextField();
        txtOrgName.setPreferredSize(new java.awt.Dimension(200, 30));
        txtOrgName.setBackground(Color.LIGHT_GRAY);
        
        // Email field
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.RED);
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new java.awt.Dimension(150, 30));
        txtEmail.setBackground(Color.LIGHT_GRAY);
        
        // Hotline field
        JLabel lblHotline = new JLabel("Hotline");
        lblHotline.setForeground(Color.RED);
        lblHotline.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtHotline = new JTextField();
        txtHotline.setPreferredSize(new java.awt.Dimension(120, 30));
        txtHotline.setBackground(Color.LIGHT_GRAY);
        
        // Address field
        JLabel lblAddress = new JLabel("Địa chỉ");
        lblAddress.setForeground(Color.RED);
        lblAddress.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        txtAddress = new JTextField();
        txtAddress.setPreferredSize(new java.awt.Dimension(200, 30));
        txtAddress.setBackground(Color.LIGHT_GRAY);
        
        // Add components to middle panel
        middlePanel.add(lblOrgId);
        middlePanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(txtOrgId);
        middlePanel.add(Box.createHorizontalStrut(20));
        middlePanel.add(lblOrgName);
        middlePanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(txtOrgName);
        middlePanel.add(Box.createHorizontalStrut(20));
        middlePanel.add(lblEmail);
        middlePanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(txtEmail);
        middlePanel.add(Box.createHorizontalStrut(20));
        middlePanel.add(lblHotline);
        middlePanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(txtHotline);
        middlePanel.add(Box.createHorizontalStrut(20));
        middlePanel.add(lblAddress);
        middlePanel.add(Box.createHorizontalStrut(10));
        middlePanel.add(txtAddress);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        
        btnAdd = new GButton("ADD");
        btnAdd.setBackground(new Color(100, 180, 100));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnEdit = new GButton("EDIT");
        btnEdit.setBackground(new Color(100, 180, 100));
        btnEdit.setForeground(Color.WHITE);
        btnEdit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        btnDelete = new GButton("DELETE");
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
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnEdit);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnDelete);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnClear);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(btnReset);
        buttonPanel.add(Box.createHorizontalGlue());
        
        // Table panel for displaying data
        jpnTable = new JPanel();
        jpnTable.setBackground(Color.LIGHT_GRAY);
        jpnTable.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel lblTableTitle = new JLabel("DANH SÁCH TỔ CHỨC TỪ THIỆN");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
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