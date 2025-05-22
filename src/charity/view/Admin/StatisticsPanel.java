package charity.view.Admin;

import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.StatisticsController;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.border.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class StatisticsPanel extends JPanel {

    private StatisticsController controller;
    private JDateChooser startDate;
    private JDateChooser endDate;
    private JPanel chartPanel;
    private JComboBox<String> chartTypeCombo;
    private GButton btnGenerate;
    private GButton btnExport;

    // Dashboard components
    private JLabel lblTotalEvents;
    private JLabel lblTotalDonations;
    private JLabel lblTotalAmount;
    private JLabel lblActiveDonors;
    private JLabel lblMonthlyGrowth;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public StatisticsPanel() {
        initComponents();
        setupLayout();

        //khoi tao controller
        controller = new StatisticsController(startDate, endDate, chartPanel, chartTypeCombo, btnGenerate, btnExport, lblTotalEvents, lblTotalDonations, lblTotalAmount, lblActiveDonors, lblMonthlyGrowth);

    }

    private void initComponents() {

        //khoi tao components
        startDate = new JDateChooser();
        startDate.setDateFormatString("dd/MM/yyyy");
        endDate = new JDateChooser();
        endDate.setDateFormatString("dd/MM/yyyy");
        chartPanel = new JPanel();
        chartPanel.setLayout(new BorderLayout());

        chartTypeCombo = new JComboBox<>(new String[]{
            "Thống kê theo thời gian",
            "Thống kê theo danh mục",
            "Top người quyên góp"
        });

        btnGenerate = new GButton("Tạo báo cáo");
        btnExport = new GButton("Xuất PDF");

        // dashboard labels
        lblTotalEvents = createStatsLabel("Tổng số sự kiện");
        lblTotalDonations = createStatsLabel("Tổng số lượt quyên góp");
        lblTotalAmount = createStatsLabel("Tổng số tiền");
        lblActiveDonors = createStatsLabel("Số người quyên góp");
        lblMonthlyGrowth = createStatsLabel("Tăng trưởng tháng");
    }

    private JLabel createStatsLabel(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        //Dashboard Panel
        JPanel dashboardPanel = new JPanel(new GridLayout(1, 5, 50, 10));
        dashboardPanel.setBackground(Color.white);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

//        Them cac panel thống kê
        dashboardPanel.add(createStatBox("Sự kiện", lblTotalEvents));
        dashboardPanel.add(createStatBox("Lượt quyên góp", lblTotalDonations));
        dashboardPanel.add(createStatBox("Tổng tiền", lblTotalAmount));
        dashboardPanel.add(createStatBox("Người quyên góp", lblActiveDonors));
//        dashboardPanel.add(createStatBox("Tăng trưởng", lblMonthlyGrowth));

        //Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controlPanel.setBackground(Color.white);
        controlPanel.add(padding10());
        controlPanel.add(new JLabel("Từ ngày:"));
        startDate.setPreferredSize(new Dimension(200, 25));
        controlPanel.add(startDate);
        controlPanel.add(padding10());
        controlPanel.add(new JLabel("Đến ngày:"));
        endDate.setPreferredSize(new Dimension(200, 25));
        controlPanel.add(endDate);
        controlPanel.add(padding10());
        chartTypeCombo.setBackground(Color.WHITE);
        controlPanel.add(chartTypeCombo);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnPanel.setBackground(Color.WHITE);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 0));
        btnPanel.add(btnGenerate);
//        btnPanel.add(btnExport);

        controlPanel.add(btnPanel);

        // Main layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(dashboardPanel, BorderLayout.NORTH);
        topPanel.add(controlPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(chartPanel, BorderLayout.CENTER);
    }

    private JPanel createStatBox(String title, JLabel valueLabel) {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBackground(new Color(245, 245, 245));

        // Viền trên màu đỏ dày 5px
        MatteBorder topBorder = BorderFactory.createMatteBorder(5, 0, 0, 0, new Color(25, 118, 210));

        // Viền đệm 10px cho tất cả các cạnh
        EmptyBorder padding = (EmptyBorder) BorderFactory.createEmptyBorder(10, 10, 10, 10);

        // Kết hợp 2 viền: viền màu đỏ ở ngoài và padding ở trong
        panel.setBorder(BorderFactory.createCompoundBorder(topBorder, padding));
        JLabel titleLabel = new JLabel(title);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(FontCustom.Arial12());

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel padding10() {
        JPanel padding = new JPanel();
        padding.setPreferredSize(new Dimension(10, 10));
        padding.setOpaque(false);
        return padding;
    }
}
