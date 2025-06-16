package charity.view.User;

import charity.component.GButton;
import charity.controller.UserController.DonationListController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class DonationPanel extends JPanel {

    private int accountId;
    private int userId;
    private DonationListController controller = null;

    private GradientPanel header;
    private JLabel lblTitle;
    private JTextField txtSearch;
    private JRadioButton jrbtId, jrbtEvent, jrbtUser;
    private ButtonGroup typeSearch;
    private GButton gbtReset, gbtPrint;
    private JPanel jpnTable;
    private JTable table;

    public DonationPanel(JFrame parent, int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
        initComponents();
        controller = new DonationListController(txtSearch, jrbtId, jrbtEvent, jrbtUser, gbtReset, table, gbtPrint);
        controller.setDonationListTable();
        controller.setEvent();
        init();
    }

    private void init() {
        header.changeColor("#74ebd5", "#ACB6E5");
        gbtPrint.setVisible(false);
    }

    public DonationListController getController() {
        return controller;
    }

    private void initComponents() {
        typeSearch = new ButtonGroup();
        header = new GradientPanel();
        lblTitle = new JLabel("Danh sách quyên góp");
        JPanel centerPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(new EmptyBorder(20, 50, 20, 50));
        JPanel topPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel topPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        topPanelLeft.setBackground(Color.white);
        topPanelRight.setBackground(Color.white);
        txtSearch = new JTextField();
        
        jrbtId = new JRadioButton("ID");
        jrbtEvent = new JRadioButton("Sự kiện");
        jrbtUser = new JRadioButton("Người quyên góp");
        
        jrbtId.setFont(new Font("Segoe UI", Font.PLAIN,14));
        jrbtEvent.setFont(new Font("Segoe UI", Font.PLAIN,14));
        jrbtUser.setFont(new Font("Segoe UI", Font.PLAIN,14));
        
        gbtReset = new GButton("Làm mới");
        gbtReset.setForeground(Color.white);
        gbtPrint = new GButton("In danh sách");
        jpnTable = new JPanel();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ===== HEADER =====
        lblTitle.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        header.setPreferredSize(new java.awt.Dimension(900, 55));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // ===== SEARCH PANEL =====
        
        topPanel.setBackground(Color.WHITE);
        
        
        txtSearch.setFont(new java.awt.Font("Segoe UI", 0, 14));
        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        JLabel lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new java.awt.Font("Segoe UI", 1, 14));

        typeSearch.add(jrbtId);
        typeSearch.add(jrbtEvent);
        typeSearch.add(jrbtUser);
        jrbtId.setSelected(true);
        jrbtId.setBackground(Color.WHITE);
        jrbtEvent.setBackground(Color.WHITE);
        jrbtUser.setBackground(Color.WHITE);

        gbtReset.setPreferredSize(new java.awt.Dimension(91, 30));
        gbtPrint.setPreferredSize(new java.awt.Dimension(91, 30));



        topPanelLeft.add(lblSearch);
        topPanelLeft.add(txtSearch);
        topPanelLeft.add(jrbtId);
        topPanelLeft.add(jrbtEvent);
        topPanelLeft.add(jrbtUser);
        topPanelRight.add(gbtPrint);
        topPanelRight.add(gbtReset);

        topPanel.add(topPanelLeft, BorderLayout.WEST);
        topPanel.add(topPanelRight, BorderLayout.EAST);
        centerPanel.add(topPanel, BorderLayout.NORTH);


        // ===== TABLE PANEL =====
        jpnTable.setPreferredSize(new java.awt.Dimension(800, 300));
        jpnTable.setBorder(new EmptyBorder(10, 30, 120, 60));
        jpnTable.setBackground(Color.WHITE);
        
        addTable();
        
        centerPanel.add(jpnTable, BorderLayout.CENTER);
        add(centerPanel);
    }
    
    public void addTable(){
        jpnTable.setLayout(new BorderLayout());
        jpnTable.setBackground(Color.white);

        table = new JTable();
        table.setBackground(Color.white); // Nền bảng
        table.setFillsViewportHeight(true); // Đảm bảo nền được vẽ đầy
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.white); // Nền viewport (khoảng trống)
        scroll.setBackground(Color.white); // Nền scrollpane
        
        jpnTable.add(scroll, BorderLayout.CENTER);
    }
}