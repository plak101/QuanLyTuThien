package charity.view.Admin;

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
    private DonationListController controller;

    private JTextField txtSearch;
    private JRadioButton jrbtId;
    private JRadioButton jrbtEvent;
    private JRadioButton jrbtUser;
    private ButtonGroup typeSearch;
    private charity.component.GButton gbtReset;
    private charity.component.GButton gbtPrint;
    private JPanel jpnTable;
    private JScrollPane scroll;
    private JTable table;

    public DonationPanel(JFrame parent, int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
        initComponents();
        controller = new DonationListController(txtSearch, jrbtId, jrbtEvent, jrbtUser, gbtReset,
                table, gbtPrint);
        controller.setDonationListTable();
        controller.setEvent();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel tìm kiếm (phía Bắc)
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(new EmptyBorder(50, 20, 20, 20)); // Khoảng đệm trên và dưới
        add(searchPanel, BorderLayout.NORTH);

        // Panel bên trái chứa các thành phần tìm kiếm
        JPanel searchLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchLeftPanel.setBackground(Color.WHITE);
        searchPanel.add(searchLeftPanel, BorderLayout.WEST);

        JLabel lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new Font("Segoe UI", Font.BOLD, 14));
        searchLeftPanel.add(lblSearch);

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchLeftPanel.add(txtSearch);

        typeSearch = new ButtonGroup();
        jrbtId = new JRadioButton("ID");
        jrbtId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jrbtId.setBackground(Color.WHITE);
        jrbtId.setSelected(true);
        searchLeftPanel.add(jrbtId);

        jrbtEvent = new JRadioButton("Sự kiện");
        jrbtEvent.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jrbtEvent.setBackground(Color.WHITE);
        searchLeftPanel.add(jrbtEvent);

        jrbtUser = new JRadioButton("Người quyên góp");
        jrbtUser.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        jrbtUser.setBackground(Color.WHITE);
        searchLeftPanel.add(jrbtUser);

        typeSearch.add(jrbtId);
        typeSearch.add(jrbtEvent);
        typeSearch.add(jrbtUser);

        // Panel bên phải chứa các nút bấm
        JPanel buttonRightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        buttonRightPanel.setBackground(Color.WHITE);
        searchPanel.add(buttonRightPanel, BorderLayout.EAST);

        gbtPrint = new GButton();
        gbtPrint.setText("In");
        gbtPrint.setPreferredSize(new Dimension(80, 30));
        buttonRightPanel.add(gbtPrint);

        gbtReset = new charity.component.GButton();
        gbtReset.setText("Làm mới");
        gbtReset.setPreferredSize(new Dimension(90, 30));
        buttonRightPanel.add(gbtReset);

        // Panel bảng dữ liệu (ở giữa)
        jpnTable = new JPanel(new BorderLayout());

        addTable();
        add(jpnTable, BorderLayout.CENTER);

    }

    public void addTable() {
        jpnTable.setBackground(Color.white);
        jpnTable.setBorder( BorderFactory.createEmptyBorder(20 , 20, 20, 20));
        table = new JTable();
        table.setBackground(Color.white); // Nền bảng
        table.setFillsViewportHeight(true); // Đảm bảo nền được vẽ đầy
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.white); // Nền viewport (khoảng trống)
        scroll.setBackground(Color.white); // Nền scrollpane

        jpnTable.add(scroll, BorderLayout.CENTER);
    }
}
