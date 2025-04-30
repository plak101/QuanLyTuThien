package charity.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import charity.controller.ChuongTrinhController;
import charity.model.ChuongTrinh;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.toedter.calendar.JDateChooser;

public class QuanLyChuongTrinh extends javax.swing.JPanel {

    private ChuongTrinhController controller;
    private DefaultTableModel tableModel;

    public QuanLyChuongTrinh() {
        initComponents();
        controller = new ChuongTrinhController();
        initCustomComponents();
        loadData();
    }

    private void initCustomComponents() {
        // Set panel background
        setBackground(Color.WHITE);

        // Initialize header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(245, 245, 250));
        headerPanel.setLayout(new BorderLayout(10, 10));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Add title to header
        JLabel titleLabel = new JLabel("Quản Lý Chương Trình Từ Thiện");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        // Create search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setOpaque(false);
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Tìm Kiếm");
        searchButton.setBackground(new Color(76, 175, 80));
        searchButton.setForeground(Color.WHITE);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        headerPanel.add(searchPanel, BorderLayout.EAST);

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        contentPanel.setBackground(Color.WHITE);

        // Create table
        String[] columnNames = {"ID", "Tên Chương Trình", "Ngày Bắt Đầu", "Ngày Kết Thúc", "Tổng Kinh Phí", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable table = new JTable(tableModel);
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.setSelectionBackground(new Color(232, 244, 233));
        table.setGridColor(new Color(230, 230, 230));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton addButton = new JButton("Thêm");
        addButton.setBackground(new Color(76, 175, 80));
        addButton.setForeground(Color.WHITE);

        JButton editButton = new JButton("Chỉnh Sửa");
        editButton.setBackground(new Color(33, 150, 243));
        editButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Xóa");
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);

        JButton viewDetailsButton = new JButton("Xem Chi Tiết");
        viewDetailsButton.setBackground(new Color(255, 152, 0));
        viewDetailsButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewDetailsButton);

        contentPanel.add(buttonPanel, BorderLayout.NORTH);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEditDialog(null);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    ChuongTrinh chuongTrinh = controller.findById(id);
                    showAddEditDialog(chuongTrinh);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một chương trình để chỉnh sửa");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Bạn có chắc chắn muốn xóa chương trình này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.deleteChuongTrinh(id);
                        loadData();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một chương trình để xóa");
                }
            }
        });

        viewDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    int id = Integer.parseInt(table.getValueAt(selectedRow, 0).toString());
                    ChuongTrinh chuongTrinh = controller.findById(id);
                    showDetailsDialog(chuongTrinh);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một chương trình để xem chi tiết");
                }
            }
        });

        // Add search functionality
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim().toLowerCase();
                if (searchText.isEmpty()) {
                    loadData(); // Load all data if search field is empty
                    return;
                }

                // Filter data based on search text
                List<ChuongTrinh> allData = controller.getAllChuongTrinh();
                tableModel.setRowCount(0); // Clear table
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                for (ChuongTrinh ct : allData) {
                    // Search in multiple fields
                    if (ct.getTenChuongTrinh().toLowerCase().contains(searchText)
                            || (ct.getMoTa() != null && ct.getMoTa().toLowerCase().contains(searchText))
                            || (ct.getTrangThai() != null && ct.getTrangThai().toLowerCase().contains(searchText))) {

                        Object[] row = {
                            ct.getId(),
                            ct.getTenChuongTrinh(),
                            ct.getNgayBatDau() != null ? dateFormat.format(ct.getNgayBatDau()) : "",
                            ct.getNgayKetThuc() != null ? dateFormat.format(ct.getNgayKetThuc()) : "",
                            String.format("%,.0f VND", ct.getTongKinhPhi()),
                            ct.getTrangThai()
                        };
                        tableModel.addRow(row);
                    }
                }
            }
        });

        // Add components to main panel
        setLayout(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    private void loadData() {
        // Clear existing data
        tableModel.setRowCount(0);

        // Get data from controller
        List<ChuongTrinh> chuongTrinhList = controller.getAllChuongTrinh();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Add data to table model
        for (ChuongTrinh ct : chuongTrinhList) {
            Object[] row = {
                ct.getId(),
                ct.getTenChuongTrinh(),
                ct.getNgayBatDau() != null ? dateFormat.format(ct.getNgayBatDau()) : "",
                ct.getNgayKetThuc() != null ? dateFormat.format(ct.getNgayKetThuc()) : "",
                String.format("%,.0f VND", ct.getTongKinhPhi()),
                ct.getTrangThai()
            };
            tableModel.addRow(row);
        }
    }

    private void showDetailsDialog(ChuongTrinh chuongTrinh) {
        if (chuongTrinh == null) {
            return;
        }

        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Chi Tiết Chương Trình");
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        // Create content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contentPanel.setBackground(Color.WHITE);

        // Create details panel
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new GridLayout(0, 1, 5, 15));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Add details
        addDetailRow(detailsPanel, "ID:", String.valueOf(chuongTrinh.getId()));
        addDetailRow(detailsPanel, "Tên Chương Trình:", chuongTrinh.getTenChuongTrinh());
        addDetailRow(detailsPanel, "Ngày Bắt Đầu:", chuongTrinh.getNgayBatDau() != null ? dateFormat.format(chuongTrinh.getNgayBatDau()) : "");
        addDetailRow(detailsPanel, "Ngày Kết Thúc:", chuongTrinh.getNgayKetThuc() != null ? dateFormat.format(chuongTrinh.getNgayKetThuc()) : "");
        addDetailRow(detailsPanel, "Tổng Kinh Phí:", String.format("%,.0f VND", chuongTrinh.getTongKinhPhi()));
        addDetailRow(detailsPanel, "Trạng Thái:", chuongTrinh.getTrangThai());

        // Add description
        JLabel descLabel = new JLabel("Mô Tả:");
        descLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextArea descArea = new JTextArea(chuongTrinh.getMoTa());
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);
        descArea.setRows(5);
        JScrollPane descScroll = new JScrollPane(descArea);

        JPanel descPanel = new JPanel(new BorderLayout(5, 5));
        descPanel.add(descLabel, BorderLayout.NORTH);
        descPanel.add(descScroll, BorderLayout.CENTER);

        // Add panels to content panel
        contentPanel.add(detailsPanel, BorderLayout.NORTH);
        contentPanel.add(descPanel, BorderLayout.CENTER);

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton closeButton = new JButton("Đóng");
        closeButton.setBackground(new Color(100, 181, 246));
        closeButton.setForeground(Color.WHITE);
        buttonPanel.add(closeButton);

        // Add action listener to close button
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Add panels to dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        dialog.setVisible(true);
    }

    private void addDetailRow(JPanel panel, String label, String value) {
        JPanel rowPanel = new JPanel(new BorderLayout(10, 0));
        rowPanel.setBackground(Color.WHITE);

        JLabel labelComp = new JLabel(label);
        labelComp.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel valueComp = new JLabel(value);
        valueComp.setFont(new Font("Arial", Font.PLAIN, 14));

        rowPanel.add(labelComp, BorderLayout.WEST);
        rowPanel.add(valueComp, BorderLayout.CENTER);

        panel.add(rowPanel);
    }

    private void showAddEditDialog(ChuongTrinh chuongTrinh) {
        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(chuongTrinh == null ? "Thêm Chương Trình" : "Chỉnh Sửa Chương Trình");
        dialog.setSize(600, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create form fields
        JLabel tenLabel = new JLabel("Tên Chương Trình:");
        JTextField tenField = new JTextField(20);

        JLabel moTaLabel = new JLabel("Mô Tả:");
        JTextArea moTaArea = new JTextArea(5, 20);
        moTaArea.setLineWrap(true);
        moTaArea.setWrapStyleWord(true);
        JScrollPane moTaScroll = new JScrollPane(moTaArea);

        JLabel ngayBDLabel = new JLabel("Ngày Bắt Đầu:");
        JDateChooser ngayBDChooser = new JDateChooser();
        ngayBDChooser.setDateFormatString("dd/MM/yyyy");

        JLabel ngayKTLabel = new JLabel("Ngày Kết Thúc:");
        JDateChooser ngayKTChooser = new JDateChooser();
        ngayKTChooser.setDateFormatString("dd/MM/yyyy");

        JLabel kinhPhiLabel = new JLabel("Tổng Kinh Phí:");
        JTextField kinhPhiField = new JTextField(20);

        JLabel trangThaiLabel = new JLabel("Trạng Thái:");
        String[] trangThaiOptions = {"Đang chuẩn bị", "Đang thực hiện", "Đã hoàn thành", "Đã hủy"};
        JComboBox<String> trangThaiCombo = new JComboBox<>(trangThaiOptions);

        // If editing, populate fields
        if (chuongTrinh != null) {
            tenField.setText(chuongTrinh.getTenChuongTrinh());
            moTaArea.setText(chuongTrinh.getMoTa());
            if (chuongTrinh.getNgayBatDau() != null) {
                ngayBDChooser.setDate(chuongTrinh.getNgayBatDau());
            }
            if (chuongTrinh.getNgayKetThuc() != null) {
                ngayKTChooser.setDate(chuongTrinh.getNgayKetThuc());
            }
            kinhPhiField.setText(String.valueOf(chuongTrinh.getTongKinhPhi()));

            if (chuongTrinh.getTrangThai() != null) {
                for (int i = 0; i < trangThaiOptions.length; i++) {
                    if (trangThaiOptions[i].equals(chuongTrinh.getTrangThai())) {
                        trangThaiCombo.setSelectedIndex(i);
                        break;
                    }
                }
            }
        }

        // Add fields to form panel
        formPanel.add(tenLabel);
        formPanel.add(tenField);

        // Mô tả requires special handling due to JTextArea
        JPanel moTaPanel = new JPanel(new BorderLayout());
        moTaPanel.add(moTaLabel, BorderLayout.NORTH);
        formPanel.add(moTaPanel);
        formPanel.add(moTaScroll);

        formPanel.add(ngayBDLabel);
        formPanel.add(ngayBDChooser);
        formPanel.add(ngayKTLabel);
        formPanel.add(ngayKTChooser);
        formPanel.add(kinhPhiLabel);
        formPanel.add(kinhPhiField);
        formPanel.add(trangThaiLabel);
        formPanel.add(trangThaiCombo);

        // Create buttons
        JButton saveButton = new JButton("Lưu");
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);

        JButton cancelButton = new JButton("Hủy");

        // Add action listeners
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Validate inputs
                String ten = tenField.getText().trim();
                if (ten.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên chương trình");
                    return;
                }

                // Validate kinh phi
                double kinhPhi = 0;
                try {
                    if (!kinhPhiField.getText().trim().isEmpty()) {
                        kinhPhi = Double.parseDouble(kinhPhiField.getText().trim());
                        if (kinhPhi < 0) {
                            JOptionPane.showMessageDialog(dialog, "Tổng kinh phí không được âm");
                            return;
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Tổng kinh phí phải là số");
                    return;
                }

                // Validate dates
                Date ngayBD = ngayBDChooser.getDate();
                Date ngayKT = ngayKTChooser.getDate();

                if (ngayBD != null && ngayKT != null && ngayKT.before(ngayBD)) {
                    JOptionPane.showMessageDialog(dialog, "Ngày kết thúc không được trước ngày bắt đầu");
                    return;
                }

                // Create or update ChuongTrinh object
                ChuongTrinh newChuongTrinh;
                if (chuongTrinh == null) {
                    newChuongTrinh = new ChuongTrinh();
                } else {
                    newChuongTrinh = chuongTrinh;
                }

                newChuongTrinh.setTenChuongTrinh(ten);
                newChuongTrinh.setMoTa(moTaArea.getText().trim());
                newChuongTrinh.setNgayBatDau(ngayBD);
                newChuongTrinh.setNgayKetThuc(ngayKT);
                newChuongTrinh.setTongKinhPhi(kinhPhi);
                newChuongTrinh.setTrangThai((String) trangThaiCombo.getSelectedItem());

                // Save to controller
                if (chuongTrinh == null) {
                    controller.addChuongTrinh(newChuongTrinh);
                } else {
                    controller.updateChuongTrinh(newChuongTrinh);
                }

                // Refresh data and close dialog
                loadData();
                dialog.dispose();
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(cancelButton);
        buttonPanel.add(saveButton);

        // Add panels to dialog
        dialog.setLayout(new BorderLayout());
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog
        dialog.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
