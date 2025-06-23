package charity.controller.AdminController;

import charity.component.ChartCustom;
import charity.component.GButton;
import charity.service.StatisticsService;
import charity.utils.MessageDialog;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
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
    private JTable table;

    private StatisticsService statisticsService = new StatisticsService();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private List<Map<String, Object>> stats;
    private List<Map<String, Object>> categoryStats;
    private List<Map<String, Object>> topDonors;

    public StatisticsController(JDateChooser startDate, JDateChooser endDate, JPanel chartPanel, JComboBox<String> chartTypeCombo, GButton btnGenerate, GButton btnExport, JLabel lblTotalEvents, JLabel lblTotalDonations, JLabel lblTotalAmount, JLabel lblActiveDonors, JLabel lblMonthlyGrowth, JTable table) {
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
        this.table = table;

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
        chartTypeCombo.addActionListener(e -> generateChart());
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
                    updateTimeSeriesTable();
                    setDateChooser(true);
                    break;
                case "Thống kê theo danh mục":
                    chart = createCategoryPieChart();
                    ChartCustom.customizePieChart(chart);
                    updateCategoryPieChartTable();
                    setDateChooser(false);
                    break;
                case "Top người quyên góp":
                    chart = createTopDonorsChart(5);
                    ChartCustom.customizeBarChart(chart);
                    updateTopDonorTable();
                    setDateChooser(false);
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
            Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, e);

            MessageDialog.showError("Lỗi khi tạo biểu đồ");
        }
    }

    //tat datepicker
    private void setDateChooser(boolean b) {
        startDate.setEnabled(b);
        endDate.setEnabled(b);
    }

    // tao bieu do theo thoi gian
    private JFreeChart createTimeSeriesChart(Date start, Date end) {
        stats = statisticsService.getStatisticsByDateRange(start, end);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //map cong don so tien theo loai
        Map<Date,Map<String, Number>> sumMap = new TreeMap<>();
        for (Map<String, Object> stat: stats){
            Date date = (Date) stat.get("date");
            String category = (String) stat.get("category");
            Number amount = (Number) stat.get("totalAmount");
            
            sumMap.computeIfAbsent(date, k -> new HashMap<>())
                    .merge(category, amount, (oldValue, newValue)-> oldValue.longValue()+newValue.longValue());
        }
        
        for (Map.Entry<Date,Map<String, Number>> dateEntry: sumMap.entrySet()){
            String formatedDate = sdf.format(dateEntry.getKey());
            for (Map.Entry<String, Number> catEntry: dateEntry.getValue().entrySet()){
                dataset.addValue(catEntry.getValue(), catEntry.getKey(), formatedDate);
            }
        }
        
        return ChartFactory.createLineChart(
                "Thống kê quyên góp theo thời gian",
                "Ngày",
                "Số tiền",
                dataset);
        
    }

    private void updateTimeSeriesTable() {
        String[] columns = {"Ngày", "Danh mục", "Sư kiện", "Số tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        Object[] obj = new Object[4];
        for (Map<String, Object> stat : stats) {
            obj[0] = sdf.format((Date) stat.get("date"));
            obj[1] = stat.get("category");
            obj[2] = stat.get("eventName");
            obj[3] = String.format("%,d", stat.get("totalAmount"));
            model.addRow(obj);
        }
        table.setModel(model);
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);
        table.setDefaultRenderer(Object.class, renderer);
        table.setDefaultEditor(Object.class, null);
        //sap xep
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter(model);
        sorter.setComparator(0, Comparator.comparing(o->{
            try {
                return sdf.parse((String) o);
            } catch (ParseException ex) {
                Logger.getLogger(StatisticsController.class.getName()).log(Level.SEVERE, null, ex);
                return new Date(0);
            }
        }));
        sorter.setComparator(3, Comparator.comparingLong(o -> Long.valueOf(o.toString().replace(",", ""))));
        table.setRowSorter(sorter);
    }

    //tao bieu do theo loai
    private JFreeChart createCategoryPieChart() {
        categoryStats = statisticsService.getCategoryStatistics();
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

    private void updateCategoryPieChartTable() {
        String[] columns = {"Id", "Tên danh mục", "Số sự kiện", "Số lượt quyên góp", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] obj = new Object[5];
        for (Map<String, Object> stat : categoryStats) {
            obj[0] = stat.get("categoryId");
            obj[1] = stat.get("category");
            obj[2] = stat.get("totalEvent");
            obj[3] = stat.get("totalDonation");
            obj[4] = String.format("%,d", ((BigDecimal) stat.get("totalAmount")).longValue());
            model.addRow(obj);
        }

        table.setModel(model);
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(0, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(2, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(3, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(4, Comparator.comparingLong(o -> Long.valueOf(o.toString().replace(",", ""))));
        table.setRowSorter(sorter);
    }

    //tao bieu do top người quyên góp
    private JFreeChart createTopDonorsChart(int limit) {
        topDonors = statisticsService.getTopDonors(limit);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int i = 0;
        for (Map<String, Object> donor : topDonors) {
            if (i == limit) {
                break;
            }
            i++;
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

    private void updateTopDonorTable() {
        String[] columns = {"Id", "Họ tên", "Số lượt quyên góp", "Số sự kiện", "Tổng tiền"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] obj = new Object[5];
        for (Map<String, Object> stat : topDonors) {
            obj[0] = stat.get("userId");
            obj[1] = stat.get("userName");
            obj[2] = stat.get("totalDonation");
            obj[3] = stat.get("totalEvent");
            obj[4] = String.format("%,d", ((BigDecimal) stat.get("totalAmount")).longValue());
            model.addRow(obj);
        }
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setComparator(0, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(2, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(3, Comparator.comparingInt(o -> ((Number) o).intValue()));
        sorter.setComparator(4, Comparator.comparingLong(o -> Long.valueOf(o.toString().replace(",", ""))));
        table.setModel(model);
        table.setRowSorter(sorter);
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
