package charity.controller.AdminController;

import charity.component.ChartCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.service.StatisticsService;
import charity.utils.MessageDialog;
import com.toedter.calendar.JDateChooser;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class StatisticsController {

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

    private StatisticsService statisticsService = new StatisticsService();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public StatisticsController(JDateChooser startDate, JDateChooser endDate, JPanel chartPanel, JComboBox<String> chartTypeCombo, GButton btnGenerate, GButton btnExport, JLabel lblTotalEvents, JLabel lblTotalDonations, JLabel lblTotalAmount, JLabel lblActiveDonors, JLabel lblMonthlyGrowth) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.chartPanel = chartPanel;
        this.chartTypeCombo = chartTypeCombo;
        this.btnGenerate = btnGenerate;
        this.btnExport = btnExport;
        this.lblTotalEvents = lblTotalEvents;
        this.lblTotalDonations = lblTotalDonations;
        this.lblTotalAmount = lblTotalAmount;
        this.lblActiveDonors = lblActiveDonors;
        this.lblMonthlyGrowth = lblMonthlyGrowth;

        init();
        setupListeners();
    }

    private void init() {
        loadDashBoardData();
        setDefaultDateRange();
        generateChart();
    }

    private void setupListeners() {
        btnGenerate.addActionListener(e -> generateChart());
        chartTypeCombo.addActionListener(e_ -> generateChart());
    }

    private void loadDashBoardData() {
        Map<String, Object> dashboardStatistics = statisticsService.getOverallStatistics();

        //cap nhat labels
        lblTotalEvents.setText(formatNumber(dashboardStatistics.get("totalEvents")));
        lblTotalDonations.setText(formatNumber(dashboardStatistics.get("totalDonations")));
        lblTotalAmount.setText(formatNumber(dashboardStatistics.get("totalAmount")));
        lblActiveDonors.setText(formatNumber(dashboardStatistics.get("activeDonors")));
    }

    private void generateChart() {
        if (startDate.getDate() == null || endDate.getDate() == null) {
            MessageDialog.showPlain("Vui lòng chọn khoảng thời gian");
            return;
        }
        java.util.Date utilStart = startDate.getDate();
        java.util.Date utilEnd = endDate.getDate();

        if (utilStart.after(utilEnd)) {
            MessageDialog.showPlain("Ngày bắt đầu không được lớn hơn ngày kết thúc");
            return;
        }

        try {

            //chuyen tu util date sang sql date
            Date start = new Date(utilStart.getTime());
            Date end = new Date(utilEnd.getTime());

            String selectedType = (String) chartTypeCombo.getSelectedItem();
            JFreeChart chart = null;

            switch (selectedType) {
                case "Thống kê theo thời gian":
                    chart = createTimeSeriesChart(start, end);
                    ChartCustom.customizeLineChart(chart);
                    System.out.println("0");
                    break;
                case "Thống kê theo danh mục":
                    chart = createCategoryPieChart();
                    ChartCustom.customizePieChart(chart);
                    break;
                case "Top người quyên góp":
                    chart = createTopDonorsChart(5);
                    ChartCustom.customizeBarChart(chart);
                    break;

            }

            //tao bieu do
            if (chart != null) {
                chartPanel.removeAll();
                ChartPanel chartComponent = new ChartPanel(chart);
                chartComponent.setPreferredSize(new Dimension(800, 400));
                chartPanel.add(chartComponent, BorderLayout.CENTER);
                chartPanel.revalidate();
                chartPanel.repaint();
            }

        } catch (Exception e) {
            MessageDialog.showError("Lỗi khi tạo biểu đồ");
        }
    }

    // tao bieu do theo thoi gian
    private JFreeChart createTimeSeriesChart(Date start, Date end) {
        List<Map<String, Object>> stats = statisticsService.getStatisticsByDateRange(start, end);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> stat : stats) {
            Date date = (Date) stat.get("date");
            String formatedDate = sdf.format(date);
            dataset.addValue(
                    (Number) stat.get("totalAmount"),
                    stat.get("category").toString(),
                    formatedDate
            );

        }

        return ChartFactory.createLineChart(
                "Thống kê quyên góp theo thời gian",
                "Ngày",
                "Số tiền (VNĐ)",
                dataset
        );
    }

    //tao bieu do theo loai
    private JFreeChart createCategoryPieChart() {
        List<Map<String, Object>> categoryStats = statisticsService.getCategoryStatistics();
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Map<String, Object> stat : categoryStats) {
            dataset.setValue(
                    stat.get("category").toString(),
                    (Double) stat.get("percentage")
            );
        }

        return ChartFactory.createPieChart(
                "Thống kê theo loại",
                dataset,
                true,//legend
                true,//tooltip
                false//urls
        );
    }

    //tao bieu do top người quyên góp
    private JFreeChart createTopDonorsChart(int limit) {
        List<Map<String, Object>> topDonors = statisticsService.getTopDonors(limit);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map<String, Object> donor : topDonors) {
            String userName = donor.get("userName").toString();

            dataset.addValue(
                    new BigDecimal(donor.get("totalAmount").toString()),
                    "Tổng tiền", userName
            );
            dataset.addValue(
                    new BigDecimal(donor.get("maxDonation").toString()),
                    "Lớn nhất", userName
            );
            dataset.addValue(
                    new BigDecimal(donor.get("minDonation").toString()),
                    "Nhỏ nhất", userName
            );
            dataset.addValue(
                    new BigDecimal(donor.get("avgAmount").toString()),
                    "Trung bình", userName
            );
        }

        return ChartFactory.createBarChart(
                "Top người quyên góp",
                "Người quyên góp",//label x
                "Số tiền (VNĐ)",//label y
                dataset
        );
    }

    // dinh dang so
    private String formatNumber(Object number) {
        return String.format("%,d", number);
    }

    //gan ngay mac dinh
    private void setDefaultDateRange() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        // Ngày hiện tại
        java.util.Date today = calendar.getTime();
        endDate.setDate(today);

        // Ngày đầu tháng
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1);
        java.util.Date firstDayOfMonth = calendar.getTime();
        startDate.setDate(firstDayOfMonth);
    }

}
