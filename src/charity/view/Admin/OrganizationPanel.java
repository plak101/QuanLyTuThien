package charity.view.Admin;

import charity.component.ColorCustom;
import charity.controller.AdminController.OrganizationPanelController;
import charity.component.GButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.border.LineBorder;

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
    private JRadioButton rdoId;
    private JRadioButton rdoEvent;
    private JRadioButton rdoDonator;
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
            btnDelete, 
            btnClear
        );
        controller.setOrganizationTable();
        controller.setEvent();
        controller.setHoverButton();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Top panel with search and filter options
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setLayout(new GridBagLayout());
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Search field
        JLabel lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(300, 30));
        txtSearch.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.decode("#B4EBE6")),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        // Radio buttons for search type
        rdoId = new JRadioButton("ID");
        rdoEvent = new JRadioButton("Sự kiện");
        rdoDonator = new JRadioButton("Người quyên góp");
        
        ButtonGroup searchTypeGroup = new ButtonGroup();
        searchTypeGroup.add(rdoId);
        searchTypeGroup.add(rdoEvent);
        searchTypeGroup.add(rdoDonator);
        
        rdoId.setSelected(true);

        // Style radio buttons
        for (JRadioButton rdo : new JRadioButton[]{rdoId, rdoEvent, rdoDonator}) {
            rdo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            rdo.setBackground(Color.WHITE);
        }

        // Buttons
        btnAdd = new GButton("THÊM");
        btnEdit = new GButton("SỬA");
        btnDelete = new GButton("XÓA");
        btnClear = new GButton("LÀM MỚI");
        btnReset = new GButton("IN");

        // Style buttons
        for (GButton btn : new GButton[]{btnAdd, btnEdit, btnDelete, btnClear, btnReset}) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setForeground(Color.WHITE);
            btn.setPreferredSize(new Dimension(120, 30));
        }

        btnAdd.setColor(ColorCustom.colorBtnAdd());
        btnEdit.setColor(ColorCustom.colorBtnUpdate());
        btnDelete.setColor(ColorCustom.colorBtnDelete());
        btnClear.setColor(ColorCustom.colorBtnReset());
        btnReset.setColor(new Color(100, 180, 100));

        // Add components to panel using GridBagLayout
        gbc.gridx = 0; gbc.gridy = 0;
        topPanel.add(lblSearch, gbc);

        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        topPanel.add(txtSearch, gbc);

        // Radio buttons in a sub panel
//        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
//        radioPanel.setBackground(Color.WHITE);
//        radioPanel.add(rdoId);
//        radioPanel.add(rdoEvent);
//        radioPanel.add(rdoDonator);

//        gbc.gridx = 3; gbc.gridy = 0; gbc.gridwidth = 3;
//        topPanel.add(radioPanel, gbc);

        // Hidden fields for selected organization data
        txtOrgId = new JTextField();
        txtOrgName = new JTextField();
        txtEmail = new JTextField();
        txtHotline = new JTextField();
        txtAddress = new JTextField();
        
        // These fields are not visible but needed for controller
        txtOrgId.setVisible(false);
        txtOrgName.setVisible(false);
        txtEmail.setVisible(false);
        txtHotline.setVisible(false);
        txtAddress.setVisible(false);

        // Buttons in a sub panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnReset);

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        topPanel.add(buttonPanel, gbc);

        // Table panel
        jpnTable = new JPanel();
        jpnTable.setLayout(new BorderLayout());
        jpnTable.setBackground(Color.WHITE);
        jpnTable.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        JLabel lblTableTitle = new JLabel("DANH SÁCH TỔ CHỨC TỪ THIỆN");
        lblTableTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTableTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTableTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        
        jpnTable.add(lblTableTitle, BorderLayout.NORTH);

        // Add panels to main panel
        add(topPanel, BorderLayout.NORTH);
        add(jpnTable, BorderLayout.CENTER);

        // Add clear button action
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                rdoId.setSelected(true);
            }
        });
    }
}