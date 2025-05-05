package charity.view.User;

import charity.controller.UserController.MyDonationController;
import charity.component.GButton;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class MyDonationPanel extends JPanel {

    private final int userId;
    private final int accountId;
    private MyDonationController controller;

    private GradientPanel header;
    private JLabel lblTitle, lblSearch;
    private JTextField txtSearch;
    private JRadioButton jrbtId, jrbtEvent, jrbtUser;
    private GButton gbtReset, gbtPrint;
    private JPanel jpnTable;

    public MyDonationPanel(JFrame parent, int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
        initComponents();
        controller = new MyDonationController(accountId, userId, txtSearch, jrbtId, jrbtEvent, jrbtUser, gbtReset, gbtPrint, jpnTable);
        controller.setDonationListTable();
        controller.setEvent();
        init();
    }

    private void init() {
        header.changeColor("#74ebd5", "#ACB6E5");
    }

    public MyDonationController getController() {
        return controller;
    }

    private void initComponents() {
        setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        // ===== Header =====
        header = new GradientPanel();
        header.setPreferredSize(new Dimension(180, 55));
        header.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 15));

        lblTitle = new JLabel("Lịch sử quyên góp");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        header.add(lblTitle);
        add(header, BorderLayout.NORTH);

        // ===== Center Panel =====
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // ===== Search Panel =====
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new EmptyBorder(20, 50, 20, 50));

        // Left side
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        leftPanel.setBackground(Color.WHITE);
        lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(200, 30));
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSearch.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // Radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        jrbtId = new JRadioButton("ID");
        jrbtId.setSelected(true);
        jrbtEvent = new JRadioButton("Nội dung");
        jrbtUser = new JRadioButton("Sự kiện");

        leftPanel.add(lblSearch);
        leftPanel.add(txtSearch);

        JRadioButton[] radios = {jrbtId, jrbtEvent, jrbtUser};
        for (JRadioButton rb : radios) {
            rb.setBackground(Color.WHITE);
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            buttonGroup.add(rb);
            leftPanel.add(rb);
        }

        // Right side
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        rightPanel.setBackground(Color.WHITE);
        gbtPrint = new GButton("Xuất danh sách");
        gbtPrint.setPreferredSize(new Dimension(130, 30));
        gbtPrint.setForeground(Color.white);
        gbtReset = new GButton("Làm mới");
        gbtReset.setPreferredSize(new Dimension(100, 30));
        gbtReset.setForeground(Color.white);
        rightPanel.add(gbtPrint);
        rightPanel.add(gbtReset);

        searchPanel.add(leftPanel, BorderLayout.WEST);
        searchPanel.add(rightPanel, BorderLayout.EAST);

        // ===== Table Panel =====
        jpnTable = new JPanel();
        jpnTable.setBackground(Color.WHITE);
        jpnTable.setPreferredSize(new Dimension(0, 267));
        jpnTable.setBorder(new EmptyBorder(10, 50, 150, 50));

        // ===== Add to center panel =====
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(jpnTable, BorderLayout.CENTER);

        // ===== Add to main panel =====
        add(centerPanel, BorderLayout.CENTER);
    }
}
