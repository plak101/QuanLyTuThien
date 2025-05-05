package charity.view.User;

import charity.controller.UserController.OrganizationPanelController;
import charity.component.GButton;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class OrganizationPanel extends JPanel {
    private OrganizationPanelController controller;
    private GradientPanel header;
    private JLabel lblTitle, lblSearch;
    private JTextField txtSearch;
    private GButton gbtReset;
    private JPanel jpnTable;

    public OrganizationPanel() {
        initComponents();
        controller = new OrganizationPanelController(txtSearch, gbtReset, jpnTable);
        controller.setOrganizationTable();
        controller.setEvent();
        init();
    }

    private void init() {
        header.changeColor("#74ebd5", "#ACB6E5");
    }

    public OrganizationPanelController getController() {
        return controller;
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // Header
        header = new GradientPanel();
        header.setPreferredSize(new Dimension(180, 55));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 15));

        lblTitle = new JLabel("Tổ chức từ thiện");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);

        JPanel centerPanel = new JPanel(new BorderLayout());
        
        // Search Panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.white);
        searchPanel.setBorder(new EmptyBorder(20,50,20,50));
                
        JPanel searchPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchPanelLeft.setBackground(Color.WHITE);
        JPanel searchPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        searchPanelRight.setBackground(Color.WHITE);

        lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));

        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.gray));

        gbtReset = new GButton("Làm mới");
        gbtReset.setPreferredSize(new Dimension(100, 30));

        searchPanelLeft.add(lblSearch);
        searchPanelLeft.add(txtSearch);
        searchPanelRight.add(gbtReset);

        // Table Panel
        jpnTable = new JPanel();
        jpnTable.setBackground(Color.WHITE);
        jpnTable.setPreferredSize(new Dimension(0, 291));
        jpnTable.setBorder(new EmptyBorder(10 ,50,150,50));

        // Main Layout
        add(header, BorderLayout.NORTH);
        searchPanel.add(searchPanelLeft, BorderLayout.WEST);
        searchPanel.add(searchPanelRight, BorderLayout.EAST);
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(jpnTable, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
    }
}