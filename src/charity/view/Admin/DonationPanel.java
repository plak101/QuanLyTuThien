package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.DonationListController;
import com.toedter.calendar.JDateChooser;
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

    private JDateChooser jdcStartDate, jdcEndDate;
    private GButton gbtAdd, gbtUpdate, gbtDelete;

    public DonationPanel(JFrame parent, int accountId, int userId) {
        this.accountId = accountId;
        this.userId = userId;
        initComponents();
        controller = new DonationListController(txtSearch, jrbtId, jrbtEvent, jrbtUser, gbtReset,
                table, gbtPrint, gbtAdd, gbtUpdate, gbtDelete, jdcStartDate, jdcEndDate);
        controller.setDonationListTable();
        controller.setEvent();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel pnTop = new JPanel(new BorderLayout());
        pnTop.setBorder(new EmptyBorder(10, 20, 10, 20)); // Khoảng đệm trên và dưới
        pnTop.setOpaque(false);
        // Panel tìm kiếm (phía Bắc)
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        pnTop.add(searchPanel, BorderLayout.NORTH);
        pnTop.add(createTopSouthPanel(), BorderLayout.SOUTH);
        add(pnTop, BorderLayout.NORTH);
        // Panel bên trái chứa các thành phần tìm kiếm
        JPanel searchLeftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        searchLeftPanel.setBackground(Color.WHITE);
        searchPanel.add(searchLeftPanel, BorderLayout.WEST);

        JLabel lblSearch = new JLabel("Tìm kiếm");
        lblSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchLeftPanel.add(lblSearch);

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchLeftPanel.add(txtSearch);

        typeSearch = new ButtonGroup();
        jrbtId = new JRadioButton("Tất cả");
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
        gbtPrint.setForeground(Color.white);
        buttonRightPanel.add(gbtPrint);

        gbtReset = new charity.component.GButton();
        gbtReset.setText("Làm mới");
        gbtReset.setPreferredSize(new Dimension(90, 30));
        gbtReset.setForeground(Color.white);
        gbtReset.setColor(ColorCustom.colorBtnReset());
        buttonRightPanel.add(gbtReset);

        // Panel bảng dữ liệu (ở giữa)
        jpnTable = new JPanel(new BorderLayout());

        addTable();
        add(jpnTable, BorderLayout.CENTER);

    }

    private JPanel createTopSouthPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel pnLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JPanel pnRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        panel.setOpaque(false);
        pnLeft.setOpaque(false);
        pnRight.setOpaque(false);

        //left
        jdcStartDate = new JDateChooser();
        jdcStartDate.setPreferredSize(new Dimension(200, 30));
        jdcStartDate.setDateFormatString("dd/MM/yyyy");
        jdcEndDate = new JDateChooser();
        jdcEndDate.setPreferredSize(new Dimension(200, 30));
        jdcEndDate.setDateFormatString("dd/MM/yyyy");

        JLabel lbStartDate = new JLabel("Bắt đầu");
        lbStartDate.setFont(FontCustom.Arial13());
        JLabel lbEndDate = new JLabel("Kết thúc");
        lbEndDate.setFont(FontCustom.Arial13());

        pnLeft.add(lbStartDate);
        pnLeft.add(jdcStartDate);
        pnLeft.add(lbEndDate);
        pnLeft.add(jdcEndDate);

        //right
        gbtAdd = createButton("Thêm", ColorCustom.colorBtnAdd());
        gbtUpdate = createButton("Sửa", ColorCustom.colorBtnUpdate());
        gbtDelete = createButton("Xóa", ColorCustom.colorBtnDelete());
        pnRight.add(gbtAdd);
        pnRight.add(gbtUpdate);
        pnRight.add(gbtDelete);
        panel.add(pnLeft, BorderLayout.WEST);
        panel.add(pnRight, BorderLayout.EAST);
        return panel;
    }

    private GButton createButton(String text, Color color) {
        GButton btn = new GButton(text, color);
        btn.setFont(FontCustom.Arial13());
        btn.setPreferredSize(new Dimension(90, 30));
        btn.setForeground(Color.white);
        return btn;
    }

    public void addTable() {
        jpnTable.setBackground(Color.white);
        jpnTable.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        table = new JTable();
        table.setBackground(Color.white); // Nền bảng
        table.setFillsViewportHeight(true); // Đảm bảo nền được vẽ đầy
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setBackground(Color.white); // Nền viewport (khoảng trống)
        scroll.setBackground(Color.white); // Nền scrollpane

        jpnTable.add(scroll, BorderLayout.CENTER);
    }
}
