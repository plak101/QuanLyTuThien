package charity.viewMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import charity.controller.NhaTaiTroController;
import charity.model.NhaTaiTro;
import java.util.List;

public class QuanLyNhaTaiTro extends javax.swing.JPanel {

    private NhaTaiTroController controller;
    private DefaultTableModel tableModel;

    public QuanLyNhaTaiTro() {
        initComponents();
        controller = new NhaTaiTroController();
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
        JLabel titleLabel = new JLabel("Quản Lý Nhà Tài Trợ");
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
        String[] columnNames = {"ID", "Tên Nhà Tài Trợ", "Số Điện Thoại", "Email", "Địa Chỉ"};
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
                    NhaTaiTro nhaTaiTro = controller.findById(id);
                    showAddEditDialog(nhaTaiTro);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhà tài trợ để chỉnh sửa");
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
                            "Bạn có chắc chắn muốn xóa nhà tài trợ này?",
                            "Xác nhận xóa",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        controller.deleteNhaTaiTro(id);
                        loadData();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một nhà tài trợ để xóa");
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
                List<NhaTaiTro> allData = controller.getAllNhaTaiTro();
                tableModel.setRowCount(0); // Clear table

                for (NhaTaiTro ntt : allData) {
                    // Search in multiple fields
                    if (ntt.getTen().toLowerCase().contains(searchText)
                            || (ntt.getEmail() != null && ntt.getEmail().toLowerCase().contains(searchText))
                            || (ntt.getSoDienThoai() != null && ntt.getSoDienThoai().contains(searchText))
                            || (ntt.getDiaChi() != null && ntt.getDiaChi().toLowerCase().contains(searchText))) {

                        Object[] row = {
                            ntt.getId(),
                            ntt.getTen(),
                            ntt.getSoDienThoai(),
                            ntt.getEmail(),
                            ntt.getDiaChi()
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
        List<NhaTaiTro> nhaTaiTroList = controller.getAllNhaTaiTro();

        // Add data to table model
        for (NhaTaiTro ntt : nhaTaiTroList) {
            Object[] row = {
                ntt.getId(),
                ntt.getTen(),
                ntt.getSoDienThoai(),
                ntt.getEmail(),
                ntt.getDiaChi()
            };
            tableModel.addRow(row);
        }
    }

    private void showAddEditDialog(NhaTaiTro nhaTaiTro) {
        // Create dialog
        JDialog dialog = new JDialog();
        dialog.setTitle(nhaTaiTro == null ? "Thêm Nhà Tài Trợ" : "Chỉnh Sửa Nhà Tài Trợ");
        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setModal(true);

        // Create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2, 10, 15));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create form fields
        JLabel tenLabel = new JLabel("Tên Nhà Tài Trợ:");
        JTextField tenField = new JTextField(20);

        JLabel sdtLabel = new JLabel("Số Điện Thoại:");
        JTextField sdtField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);

        JLabel diaChiLabel = new JLabel("Địa Chỉ:");
        JTextField diaChiField = new JTextField(20);

        // If editing, populate fields
        if (nhaTaiTro != null) {
            tenField.setText(nhaTaiTro.getTen());
            sdtField.setText(nhaTaiTro.getSoDienThoai());
            emailField.setText(nhaTaiTro.getEmail());
            diaChiField.setText(nhaTaiTro.getDiaChi());
        }

        // Add fields to form panel
        formPanel.add(tenLabel);
        formPanel.add(tenField);
        formPanel.add(sdtLabel);
        formPanel.add(sdtField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(diaChiLabel);
        formPanel.add(diaChiField);

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
                    JOptionPane.showMessageDialog(dialog, "Vui lòng nhập tên nhà tài trợ");
                    return;
                }

                // Create or update NhaTaiTro object
                NhaTaiTro newNhaTaiTro;
                if (nhaTaiTro == null) {
                    newNhaTaiTro = new NhaTaiTro();
                    // ID will be generated by database
                } else {
                    newNhaTaiTro = nhaTaiTro;
                }

                newNhaTaiTro.setTen(ten);
                newNhaTaiTro.setSoDienThoai(sdtField.getText().trim());
                newNhaTaiTro.setEmail(emailField.getText().trim());
                newNhaTaiTro.setDiaChi(diaChiField.getText().trim());

                // Save to controller
                if (nhaTaiTro == null) {
                    controller.addNhaTaiTro(newNhaTaiTro);
                } else {
                    controller.updateNhaTaiTro(newNhaTaiTro);
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
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
