package test;

import charity.controller.UserController.DonationListController;
import charity.component.GButton;
import charity.view.User.GradientPanel;
import charity.view.User.GradientPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.nio.file.Files;

import javax.swing.*;

public class DonationPanel extends JPanel {

    private int accountId;
    private int userId;
    private DonationListController controller;

    // Components
    private JTextField searchField;
    private JRadioButton searchById;
    private JRadioButton searchByEvent;
    private JRadioButton searchByUser;
    private GButton resetButton;
    private GButton printButton;
    private JPanel tablePanel;
    private GradientPanel headerPanel;
    private ButtonGroup searchGroup;

    public DonationPanel(JFrame parent, int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
        initComponents();
        controller = new DonationListController(searchField, searchById, searchByEvent, searchByUser, resetButton, tablePanel, printButton);
        controller.setDonationListTable();
        controller.setEvent();
        init();
    }

    private void init() {
        headerPanel.changeColor("#74ebd5", "#ACB6E5");
        printButton.setVisible(false);
    }

    public DonationListController getController() {
        return controller;
    }

    private void initComponents() {
        // Initialize components
        searchField = new JTextField();
        searchById = new JRadioButton("ID");
        searchByEvent = new JRadioButton("Sự kiện");
        searchByUser = new JRadioButton("Người quyên góp");
        resetButton = new GButton("Làm mới");
        printButton = new GButton("In");
        tablePanel = new JPanel();
        headerPanel = new GradientPanel();
        searchGroup = new ButtonGroup();

        // Group radio buttons
        searchGroup.add(searchById);
        searchGroup.add(searchByEvent);
        searchGroup.add(searchByUser);
        searchById.setSelected(true);

        JLabel titleLabel = new JLabel("Danh sách quyên góp");
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
        titleLabel.setForeground(java.awt.Color.WHITE);
        headerPanel.setPreferredSize(new java.awt.Dimension(1028, 55));
        headerPanel.add(titleLabel);

        // Search panel with GridBagLayout
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // padding
        gbc.anchor = GridBagConstraints.WEST;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);

// Label "Tìm kiếm"
        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(new JLabel("Tìm kiếm", JLabel.LEFT), gbc);

// Text field
        searchField = new JTextField(25);
        searchField.setFont(fieldFont);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        searchPanel.add(searchField, gbc);

// Radio buttons
        searchById = new JRadioButton("ID");
        searchByEvent = new JRadioButton("Sự kiện");
        searchByUser = new JRadioButton("Người quyên góp");

        for (JRadioButton rb : new JRadioButton[]{searchById, searchByEvent, searchByUser}) {
            rb.setBackground(Color.WHITE);
            rb.setFont(fieldFont);
        }

        searchGroup = new ButtonGroup();
        searchGroup.add(searchById);
        searchGroup.add(searchByEvent);
        searchGroup.add(searchByUser);
        searchById.setSelected(true);

// Radio buttons layout
        gbc.gridwidth = 1;
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPanel.add(searchById, gbc);

        gbc.gridx = 2;
        searchPanel.add(searchByEvent, gbc);

        gbc.gridx = 3;
        searchPanel.add(searchByUser, gbc);

// "Làm mới" button
        resetButton = new GButton("Làm mới");
        resetButton.setPreferredSize(new Dimension(90, 30));
        resetButton.setFont(fieldFont);

        gbc.gridx = 4;
        searchPanel.add(resetButton, gbc);

        // Table panel
        tablePanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Main layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(headerPanel);
        add(Box.createVerticalStrut(20));
        add(searchPanel);
        add(Box.createVerticalStrut(10));
        add(tablePanel);
    }
}
