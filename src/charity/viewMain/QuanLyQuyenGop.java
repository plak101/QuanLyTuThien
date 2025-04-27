package charity.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import charity.controller.QuyenGopController;
import charity.controller.NhaTaiTroController;
import charity.model.QuyenGop;
import charity.model.NhaTaiTro;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class QuanLyQuyenGop extends javax.swing.JPanel {

    private QuyenGopController controller;
    private NhaTaiTroController nhaTaiTroController;
    private DefaultTableModel tableModel;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

    public QuanLyQuyenGop() {
        initComponents();
        controller = new QuyenGopController();
        nhaTaiTroController = new NhaTaiTroController();
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
        JLabel titleLabel = new JLabel("Quản Lý Quyên Góp");
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
        String[] columnNames = {"ID", "Nhà Tài Trợ", "Chương Trình", "Số Tiền", "Ngày Quyên Góp", "Phương Thức", "Ghi Chú"};
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

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        // Create statistics panel
        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        statsPanel.setBackground(Color.WHITE);
        JLabel totalLabel = new JLabel("Tổng quyên góp: " + currencyFormat.format(controller.getTotalDonationAmount()));
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setForeground(new Color(76, 175, 80));
        statsPanel.add(totalLabel);

        // Put buttons and stats in the same row
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.add(buttonPanel, BorderLayout.WEST);
        topPanel.add(statsPanel, BorderLayout.EAST);

        contentPanel.add(topPanel, BorderLayout.NORTH);

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
                    QuyenGop quyenGop = controller.findById(id);
                    showAddEditDialog(quyenGop);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một quyên góp để chỉnh sửa");
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
                            "Bạn có chắc chắn muốn xóa quyên góp này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.deleteQuyenGop(id);
                        loadData();
                        // Update total after deletion
                        totalLabel.setText("Tổng quyên góp: " + currencyFormat.format(controller.getTotalDonationAmount()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một quyên góp để xóa");
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

                // Get all data and filter by search text
                List<Object[]> allData = controller.getQuyenGopWithNhaTaiTro();
                tableModel.setRowCount(0); // Clear table

                for (Object[] row : allData) {
                    String nhaTaiTroName = (String) row[2];
                    String amount = String.valueOf(row[4]);
                    String date = row[5] != null ? dateFormat.format(row[5]) : "";
                    String paymentMethod = (String) row[6];
                    String note = (String) row[7];

                    if (nhaTaiTroName.toLowerCase().contains(searchText)
                            || amount.contains(searchText)
                            || date.contains(searchText)
                            || (paymentMethod != null && paymentMethod.toLowerCase().contains(searchText))
                            || (note != null && note.toLowerCase().contains(searchText))) {

                        addRowToTable(row);
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

        // Get joined data from controller
        List<Object[]> quyenGopList = controller.getQuyenGopWithNhaTaiTro();

        // Add data to table model
        for (Object[] row : quyenGopList) {
            addRowToTable(row);
        }
    }

    private void addRowToTable(Object[] dataRow) {
        Object[] tableRow = new Object[7];
        tableRow[0] = dataRow[0]; // id
        tableRow[1] = dataRow[2]; // nha tai tro name
        tableRow[2] = dataRow[3]; // chuong trinh id
        tableRow[3] = currencyFormat.format(dataRow[4]); // formatted amount

        // Format date if not null
        if (dataRow[5] != null) {
            tableRow[4] = dateFormat.format(dataRow[5]);
        } else {
            tableRow[4] = "";
        }

        tableRow[5] = dataRow[6]; // payment method
        tableRow[6] = dataRow[7]; // note

        tableModel.addRow(tableRow);
    }

    private void showAddEditDialog(QuyenGop quyenGop) {
        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(quyenGop == null ? "Thêm Quyên Góp" : "Chỉnh Sửa Quyên Góp");
        dialog.setSize(500, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Get list of nhà tài trợ for combo box
        List<NhaTaiTro> nhaTaiTroList = nhaTaiTroController.getAllNhaTaiTro();
        String[] nhaTaiTroNames = new String[nhaTaiTroList.size()];
        int[] nhaTaiTroIds = new int[nhaTaiTroList.size()];

        for (int i = 0; i < nhaTaiTroList.size(); i++) {
            NhaTaiTro ntt = nhaTaiTroList.get(i);
            nhaTaiTroNames[i] = ntt.getTen();
            nhaTaiTroIds[i] = ntt.getId();
        }

        // Create form fields
        JLabel nhaTaiTroLabel = new JLabel("Nhà Tài Trợ:");
        JComboBox<String> nhaTaiTroComboBox = new JComboBox<>(nhaTaiTroNames);

        JLabel chuongTrinhLabel = new JLabel("Chương Trình ID:");
        JTextField chuongTrinhField = new JTextField(20);

        JLabel soTienLabel = new JLabel("Số Tiền:");
        JTextField soTienField = new JTextField(20);

        JLabel ngayLabel = new JLabel("Ngày Quyên Góp (dd/MM/yyyy):");
        JTextField ngayField = new JTextField(20);

        JLabel phuongThucLabel = new JLabel("Phương Thức Thanh Toán:");
        String[] paymentMethods = {"Tiền mặt", "Chuyển khoản", "Ví điện tử", "Khác"};
        JComboBox<String> phuongThucComboBox = new JComboBox<>(paymentMethods);

        JLabel ghiChuLabel = new JLabel("Ghi Chú:");
        JTextArea ghiChuArea = new JTextArea(3, 20);
        JScrollPane ghiChuScrollPane = new JScrollPane(ghiChuArea);

        // If editing, populate fields
        if (quyenGop != null) {
            // Find the index of the nhà tài trợ in the combo box
            for (int i = 0; i < nhaTaiTroIds.length; i++) {
                if (nhaTaiTroIds[i] == quyenGop.getNhaTaiTroId()) {
                    nhaTaiTroComboBox.setSelectedIndex(i);
                    break;
                }
            }

            chuongTrinhField.setText(String.valueOf(quyenGop.getChuongTrinhId()));
            soTienField.setText(String.valueOf(quyenGop.getSoTien()));

            if (quyenGop.getNgayQuyenGop() != null) {
                ngayField.setText(dateFormat.format(quyenGop.getNgayQuyenGop()));
            }

            if (quyenGop.getPhuongThucThanhToan() != null) {
                for (int i = 0; i < paymentMethods.length; i++) {
                    if (paymentMethods[i].equals(quyenGop.getPhuongThucThanhToan())) {
                        phuongThucComboBox.setSelectedIndex(i);
                        break;
                    }
                }
            }

            ghiChuArea.setText(quyenGop.getGhiChu());
        } else {
            // Set default values for new record
            ngayField.setText(dateFormat.format(new Date())); // Today's date
            phuongThucComboBox.setSelectedIndex(0); // Default to first payment method
        }

        // Add fields to form panel
        formPanel.add(nhaTaiTroLabel);
        formPanel.add(nhaTaiTroComboBox);
        formPanel.add(chuongTrinhLabel);
        formPanel.add(chuongTrinhField);
        formPanel.add(soTienLabel);
        formPanel.add(soTienField);
        formPanel.add(ngayLabel);
        formPanel.add(ngayField);
        formPanel.add(phuongThucLabel);
        formPanel.add(phuongThucComboBox);
        formPanel.add(ghiChuLabel);
        formPanel.add(ghiChuScrollPane);

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
                if (nhaTaiTroComboBox.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng chọn nhà tài trợ");
                    return;
                }

                String chuongTrinhStr = chuongTrinhField.getText().trim();
                if (chuongTrinhStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập mã chương trình");
                    return;
                }

                String soTienStr = soTienField.getText().trim();
                if (soTienStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập số tiền");
                    return;
                }

                String ngayStr = ngayField.getText().trim();
                if (ngayStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập ngày quyên góp");
                    return;
                }

                // Parse values
                int chuongTrinhId;
                double soTien;
                Date ngayQuyenGop;

                try {
                    chuongTrinhId = Integer.parseInt(chuongTrinhStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Mã chương trình phải là số");
                    return;
                }

                try {
                    soTien = Double.parseDouble(soTienStr);
                    if (soTien <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Số tiền phải lớn hơn 0");
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Số tiền không hợp lệ");
                    return;
                }

                try {
                    ngayQuyenGop = dateFormat.parse(ngayStr);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(dialog, "Ngày không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy");
                    return;
                }

                // Get selected nhà tài trợ id
                int selectedIndex = nhaTaiTroComboBox.getSelectedIndex();
                int nhaTaiTroId = nhaTaiTroIds[selectedIndex];

                // Create or update QuyenGop object
                QuyenGop newQuyenGop;
                if (quyenGop == null) {
                    newQuyenGop = new QuyenGop();
                } else {
                    newQuyenGop = quyenGop;
                }

                newQuyenGop.setNhaTaiTroId(nhaTaiTroId);
                newQuyenGop.setChuongTrinhId(chuongTrinhId);
                newQuyenGop.setSoTien(soTien);
                newQuyenGop.setNgayQuyenGop(ngayQuyenGop);
                newQuyenGop.setPhuongThucThanhToan((String) phuongThucComboBox.getSelectedItem());
                newQuyenGop.setGhiChu(ghiChuArea.getText().trim());

                // Save to database
                if (quyenGop == null) {
                    controller.addQuyenGop(newQuyenGop);
                } else {
                    controller.updateQuyenGop(newQuyenGop);
                }

                // Refresh table and close dialog
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
