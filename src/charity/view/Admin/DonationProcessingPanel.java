package charity.view.Admin;

import charity.component.GButton;
import charity.model.Donation;
import charity.service.DonationService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class DonationProcessingPanel extends JPanel {
    private JTable tblDonations;
    private GButton btnProcess;
    private GButton btnDistribute;
    private JLabel lblTotal;
    private DonationService donationService;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat;
    private DecimalFormat moneyFormat;

    public DonationProcessingPanel() {
        donationService = new DonationService();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        moneyFormat = new DecimalFormat("#,###");
        initComponents();
        loadUnprocessedDonations();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(180, 235, 230));
        JLabel titleLabel = new JLabel("QUẢN LÝ XỬ LÝ TIỀN QUYÊN GÓP");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titlePanel.add(titleLabel);
        
        // Table
        String[] columns = {"ID", "Người quyên góp", "Số tiền", "Ngày quyên góp", "Phương thức", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDonations = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblDonations);
        
        // Action panel
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(Color.WHITE);
        
        btnProcess = new GButton("Xử lý tiền");
        btnProcess.setPreferredSize(new Dimension(120, 40));
        btnProcess.addActionListener(e -> processDonation());
        
        btnDistribute = new GButton("Phân phối");
        btnDistribute.setPreferredSize(new Dimension(120, 40));
        btnDistribute.addActionListener(e -> distributeDonation());
        
        lblTotal = new JLabel("Tổng tiền chưa xử lý: 0đ");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        actionPanel.add(lblTotal);
        actionPanel.add(btnProcess);
        actionPanel.add(btnDistribute);

        // Add to panel
        add(titlePanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }

    private void loadUnprocessedDonations() {
        tableModel.setRowCount(0);
        List<Donation> donations = donationService.getUnprocessedDonations();
        double total = 0;
        
        for (Donation d : donations) {
            tableModel.addRow(new Object[]{
                d.getId(),
                d.getUserId(), // Bạn có thể thay bằng tên người dùng nếu có service lấy thông tin user
                moneyFormat.format(d.getAmount()) + "đ",
                dateFormat.format(d.getDonationDate()),
                "Chuyển khoản", // Thay bằng phương thức thật từ model
                d.getStatus()
            });
            total += d.getAmount();
        }
        
        lblTotal.setText("Tổng tiền chưa xử lý: " + moneyFormat.format(total) + "đ");
    }

    private void processDonation() {
        int row = tblDonations.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng chọn khoản quyên góp cần xử lý",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int donationId = (int) tblDonations.getValueAt(row, 0);
        String note = JOptionPane.showInputDialog(this, "Nhập ghi chú xử lý:");
        
        if (note != null) {
            // Thay 1 bằng ID của admin đang đăng nhập
            if (donationService.processDonation(donationId, 1, note)) {
                JOptionPane.showMessageDialog(this, 
                    "Xử lý thành công!",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
                loadUnprocessedDonations();
            } else {
                JOptionPane.showMessageDialog(this,
                    "Xử lý thất bại!",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void distributeDonation() {
        int row = tblDonations.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this,
                "Vui lòng chọn khoản quyên góp cần phân phối",
                "Thông báo",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        int donationId = (int) tblDonations.getValueAt(row, 0);
        
        // Tạo form phân phối tiền
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this),
                "Phân phối tiền", true);
        dialog.setLayout(new BorderLayout());
        
        // Panel chứa form
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Components
        JLabel lblEvent = new JLabel("Sự kiện:");
        JComboBox<String> cboEvent = new JComboBox<>(); // Cần thêm danh sách sự kiện
        
        JLabel lblAmount = new JLabel("Số tiền:");
        JTextField txtAmount = new JTextField(15);
        
        JLabel lblNote = new JLabel("Ghi chú:");
        JTextArea txtNote = new JTextArea(3, 15);
        JScrollPane noteScroll = new JScrollPane(txtNote);
        
        // Add components
        formPanel.add(lblEvent, gbc);
        gbc.gridx = 1;
        formPanel.add(cboEvent, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblAmount, gbc);
        gbc.gridx = 1;
        formPanel.add(txtAmount, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblNote, gbc);
        gbc.gridx = 1;
        formPanel.add(noteScroll, gbc);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        GButton btnSave = new GButton("Lưu");
        GButton btnCancel = new GButton("Hủy");
        
        btnSave.addActionListener(e -> {
            try {
                double amount = Double.parseDouble(txtAmount.getText().trim());
                String note = txtNote.getText().trim();
                
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(dialog,
                        "Vui lòng nhập số tiền hợp lệ",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Thay các giá trị giả định
                int eventId = 1; // Lấy từ combo box
                int adminId = 1; // Lấy từ user đang đăng nhập
                
                if (donationService.distributeDonation(donationId, eventId, amount, note, adminId)) {
                    JOptionPane.showMessageDialog(dialog,
                        "Phân phối tiền thành công!",
                        "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                    loadUnprocessedDonations();
                } else {
                    JOptionPane.showMessageDialog(dialog,
                        "Phân phối tiền thất bại!",
                        "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog,
                    "Vui lòng nhập số tiền hợp lệ",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        
        btnCancel.addActionListener(e -> dialog.dispose());
        
        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);
        
        // Add to dialog
        dialog.add(formPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
