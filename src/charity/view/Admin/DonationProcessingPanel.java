package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.GButton;
import charity.model.CharityEvent;
import charity.model.DonationAllocation;
import charity.service.AllocationService;
import charity.service.CharityEventService;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;

public class DonationProcessingPanel extends JPanel {

    private JTable tblEvents;
    private JTable tblDistributions;
    private JPanel infoPanel;
    private DefaultTableModel eventTableModel;
    private DefaultTableModel distributionTableModel;
    private CharityEventService eventService;
    private AllocationService allocationService;
    private JLabel lblTotalAmount, lblAllocated, lblRemaining, lblNumDistributions;
    private DecimalFormat moneyFormat = new DecimalFormat("#,###");
    private GButton btnAddDistribution;
    private GButton btnEditDistribution;
    private GButton btnDeleteDistribution;

    public DonationProcessingPanel() {
        eventService = new CharityEventService();
        allocationService = new AllocationService();
        initComponents();
        loadEventList();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        JLabel title = new JLabel("QUẢN LÝ PHÂN PHÁT TIỀN SỰ KIỆN", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBorder(BorderFactory.createEmptyBorder(16, 0, 16, 0));
        add(title, BorderLayout.NORTH);

        // --- Top: Event Table ---
        String[] eventCols = {"ID", "Tên sự kiện", "Mục tiêu", "Đã quyên góp"};
        eventTableModel = new DefaultTableModel(eventCols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblEvents = new JTable(eventTableModel);
        tblEvents.setRowHeight(28);
        tblEvents.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane eventScroll = new JScrollPane(tblEvents);
        eventScroll.setPreferredSize(new Dimension(600, 120));
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel eventLabel = new JLabel("Danh sách sự kiện", JLabel.CENTER);
        eventLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        eventLabel.setForeground(new Color(0, 102, 204));
        topPanel.add(eventLabel, BorderLayout.NORTH);
        topPanel.add(eventScroll, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // --- Bottom: Center and Right ---
        // Center: Distribution Table
        String[] distCols = {"ID", "Số tiền", "Mục đích", "Ngày phân phát", "Người nhận"};
        distributionTableModel = new DefaultTableModel(distCols, 0) {
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblDistributions = new JTable(distributionTableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (isRowSelected(row)) {
                    c.setBackground(new Color(232, 242, 255));
                } else if (row % 2 == 0) {
                    c.setBackground(new Color(250, 250, 250));
                } else {
                    c.setBackground(Color.WHITE);
                }
                if (c instanceof JLabel) {
                    ((JLabel) c).setHorizontalAlignment(column == 0 ? JLabel.CENTER : JLabel.LEFT);
                    ((JLabel) c).setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }
                return c;
            }
        };
        // Làm đẹp bảng phân phát
        tblDistributions.setRowHeight(36);
        tblDistributions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTableHeader distHeader = tblDistributions.getTableHeader();
        distHeader.setFont(new Font("Segoe UI", Font.BOLD, 15));
        distHeader.setBackground(new Color(178, 235, 242)); // #B2EBF2
        distHeader.setForeground(new Color(33, 33, 33));
        distHeader.setPreferredSize(new Dimension(100, 38));
        ((javax.swing.table.DefaultTableCellRenderer) distHeader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        tblDistributions.setSelectionBackground(new Color(200, 230, 255));
        tblDistributions.setSelectionForeground(Color.BLACK);
        tblDistributions.setGridColor(new Color(220, 220, 220));
        tblDistributions.setShowGrid(true);
        tblDistributions.setRowMargin(4);
        tblDistributions.setIntercellSpacing(new Dimension(0, 4));
        tblDistributions.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblDistributions.getColumnModel().getColumn(0).setPreferredWidth(50); // ID
        tblDistributions.getColumnModel().getColumn(1).setPreferredWidth(100); // Số tiền
        tblDistributions.getColumnModel().getColumn(2).setPreferredWidth(180); // Mục đích
        tblDistributions.getColumnModel().getColumn(3).setPreferredWidth(120); // Ngày phân phát
        tblDistributions.getColumnModel().getColumn(4).setPreferredWidth(220); // Người nhận
        tblDistributions.getColumnModel().getColumn(4).setCellRenderer(new MultiLineTableCellRenderer()); // Người nhận
        JScrollPane distScroll = new JScrollPane(tblDistributions);
        JPanel centerPanel = new JPanel(new BorderLayout());
        JLabel distLabel = new JLabel("Danh sách phân phát", JLabel.CENTER);
        distLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));
        distLabel.setForeground(new Color(0, 102, 204));
        centerPanel.add(distLabel, BorderLayout.NORTH);
        centerPanel.add(distScroll, BorderLayout.CENTER);

        // Panel info bo góc, nền trắng
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(178, 235, 242), 2, true), "Thông tin sự kiện", 0, 0, new Font("Segoe UI", Font.BOLD, 15), new Color(0, 102, 204)));
        lblTotalAmount = new JLabel();
        lblAllocated = new JLabel();
        lblRemaining = new JLabel();
        lblNumDistributions = new JLabel();
        lblTotalAmount.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblAllocated.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblRemaining.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNumDistributions.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        infoPanel.add(lblTotalAmount);
        infoPanel.add(lblAllocated);
        infoPanel.add(lblRemaining);
        infoPanel.add(lblNumDistributions);
        infoPanel.add(Box.createVerticalStrut(8));
        // Nút Thêm màu xanh lá, Sửa xanh dương, Xoá đỏ, Làm mới xám
        btnAddDistribution = new GButton("Thêm phân phát");
        btnEditDistribution = new GButton("Sửa");
        btnDeleteDistribution = new GButton("Xoá");
        btnAddDistribution.setPreferredSize(new Dimension(120, 36));
        btnAddDistribution.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnAddDistribution.setBackground(new Color(76, 175, 80)); // #4CAF50
        btnAddDistribution.setForeground(Color.WHITE);
        btnAddDistribution.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        btnAddDistribution.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditDistribution.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEditDistribution.setBackground(new Color(33, 150, 243)); // #2196F3
        btnEditDistribution.setForeground(Color.WHITE);
        btnEditDistribution.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        btnEditDistribution.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEditDistribution.setEnabled(false);
        btnEditDistribution.setVisible(false);
        btnDeleteDistribution.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnDeleteDistribution.setBackground(new Color(229, 57, 53)); // #E53935
        btnDeleteDistribution.setForeground(Color.WHITE);
        btnDeleteDistribution.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        btnDeleteDistribution.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDeleteDistribution.setEnabled(false);
        btnDeleteDistribution.setVisible(false);
        infoPanel.add(btnAddDistribution);
        // Thêm 2 nút Sửa/Xoá dưới infoPanel
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        actionPanel.setOpaque(false);
        actionPanel.add(btnEditDistribution);
        actionPanel.add(Box.createHorizontalStrut(12));
        actionPanel.add(btnDeleteDistribution);
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(actionPanel);
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(infoPanel, BorderLayout.NORTH);

        // Split bottom: center (distribution) and right (info)
        JSplitPane splitBottom = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, centerPanel, rightPanel);
        splitBottom.setResizeWeight(0.7);
        splitBottom.setPreferredSize(new Dimension(600, 240));
        add(splitBottom, BorderLayout.CENTER);

        // --- Event selection ---
        tblEvents.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int row = tblEvents.getSelectedRow();
                    if (row >= 0) {
                        int eventId = (int) eventTableModel.getValueAt(row, 0);
                        loadDistributions(eventId);
                        updateEventInfo(eventId);
                    }
                }
            }
        });

        // Sửa phần hiển thị chi tiết phân phát (double click) và xử lý nút Sửa/Xoá:
        tblDistributions.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tblDistributions.getSelectedRow();
                // BỎ: click vào nút sửa/xoá trong bảng
                // Chỉ giữ lại double click để xem chi tiết
                if (row >= 0 && evt.getClickCount() == 2) {
                    Object idObj = tblDistributions.getValueAt(row, 0);
                    String id = idObj != null ? idObj.toString() : "";
                    int eventRow = tblEvents.getSelectedRow();
                    String eventName = "";
                    String eventPurpose = "";
                    String eventTime = "";
                    String eventBudget = "";
                    String eventLocation = "";
                    if (eventRow >= 0) {
                        int eventId = (int) eventTableModel.getValueAt(eventRow, 0);
                        CharityEvent ev = eventService.getEventById(eventId);
                        if (ev != null) {
                            eventName = ev.getName();
                            eventPurpose = ev.getPurpose() != null ? ev.getPurpose() : "";
                            eventTime = ev.getStartDate() != null ? ev.getStartDate().toString() : "";
                            eventBudget = moneyFormat.format(ev.getTargetAmount()) + "đ";
                            eventLocation = ev.getDescription() != null ? ev.getDescription() : "";
                        }
                    }
                    DonationAllocation alloc = null;
                    try {
                        alloc = allocationService.getAllocationById(Integer.parseInt(id));
                    } catch (Exception ex) {
                        // fallback: không có dữ liệu
                    }
                    StringBuilder detail = new StringBuilder();
                    detail.append("--- Thông tin chương trình ---\n");
                    detail.append("Tên chương trình: ").append(eventName).append("\n");
                    detail.append("Mục đích: ").append(eventPurpose).append("\n");
                    detail.append("Thời gian tổ chức: ").append(eventTime).append("\n");
                    detail.append("Địa điểm: ").append(eventLocation).append("\n");
                    detail.append("Ngân sách dự kiến: ").append(eventBudget).append("\n\n");
                    detail.append("--- Thông tin phân phát ---\n");
                    if (alloc != null) {
                        detail.append("Mã phân phát: ").append(safe(alloc.getId())).append("\n");
                        if (alloc.getAmount() > 0) {
                            detail.append("Số tiền: ").append(moneyFormat.format(alloc.getAmount())).append("\n");
                        }
                        // Hiển thị Mục đích sát trái, kể cả nhiều dòng
                        if (!safe(alloc.getPurpose()).isEmpty()) {
                            detail.append("Mục đích: ");
                            String[] lines = safe(alloc.getPurpose()).split("\\n");
                            for (int i = 0; i < lines.length; i++) {
                                if (i == 0) {
                                    detail.append(lines[i]).append("\n");
                                } else {
                                    detail.append("           ").append(lines[i]).append("\n");
                                }
                            }
                        }
                        if (alloc.getAllocationDate() != null) {
                            detail.append("Ngày phân phát: ").append(safe(alloc.getAllocationDate())).append("\n");
                        }
                        if (!safe(alloc.getRecipient()).isEmpty()) {
                            detail.append("Người nhận: ").append(safe(alloc.getRecipient())).append("\n");
                        }
                        if (alloc.getNumRecipients() > 0) {
                            detail.append("Số lượng người nhận: ").append(safe(alloc.getNumRecipients())).append("\n");
                        }
                        if (!safe(alloc.getCriteria()).isEmpty()) {
                            detail.append("Tiêu chí lựa chọn: ").append(safe(alloc.getCriteria())).append("\n");
                        }
                        if (!safe(alloc.getRecipientList()).isEmpty()) {
                            detail.append("Danh sách người nhận: ").append(safe(alloc.getRecipientList())).append("\n");
                        }
                        if (!safe(alloc.getGiftType()).isEmpty()) {
                            detail.append("Loại quà/tài sản: ").append(safe(alloc.getGiftType())).append("\n");
                        }
                        if (!safe(alloc.getGiftValue()).isEmpty()) {
                            detail.append("Giá trị từng phần quà: ").append(safe(alloc.getGiftValue())).append("\n");
                        }
                        if (alloc.getTotalGifts() > 0) {
                            detail.append("Tổng số lượng/quà: ").append(safe(alloc.getTotalGifts())).append("\n");
                        }
                        if (alloc.getShippingCost() > 0) {
                            detail.append("Chi phí vận chuyển, nhân công: ").append(safe(alloc.getShippingCost())).append("\n");
                        }
                        if (!safe(alloc.getEvidenceUrl()).isEmpty()) {
                            detail.append("Ảnh/video minh chứng: ").append(safe(alloc.getEvidenceUrl())).append("\n");
                        }
                        if (!safe(alloc.getReceipt()).isEmpty()) {
                            detail.append("Biên nhận, chữ ký người nhận: ").append(safe(alloc.getReceipt())).append("\n");
                        }
                        if (!safe(alloc.getFeedback()).isEmpty()) {
                            detail.append("Phản hồi từ người nhận/địa phương: ").append(safe(alloc.getFeedback())).append("\n");
                        }
                    } else {
                        detail.append("Không tìm thấy dữ liệu chi tiết.\n");
                    }
                    // Hiển thị popup chi tiết đẹp, căn lề, font rõ ràng
                    JTextArea detailArea = new JTextArea(detail.toString());
                    detailArea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                    detailArea.setEditable(false);
                    detailArea.setLineWrap(true);
                    detailArea.setWrapStyleWord(true);
                    detailArea.setBackground(new Color(250, 250, 250));
                    detailArea.setBorder(BorderFactory.createEmptyBorder(16, 24, 16, 24));
                    JScrollPane scroll = new JScrollPane(detailArea);
                    scroll.setPreferredSize(new Dimension(520, 340));
                    JOptionPane.showMessageDialog(DonationProcessingPanel.this, scroll, "Chi tiết phân phát minh bạch", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        // Bật/tắt nút khi chọn dòng bảng phân phát
        tblDistributions.getSelectionModel().addListSelectionListener(e -> {
            boolean enable = tblDistributions.getSelectedRow() >= 0;
            btnEditDistribution.setVisible(enable);
            btnDeleteDistribution.setVisible(enable);
            btnEditDistribution.setEnabled(enable);
            btnDeleteDistribution.setEnabled(enable);
        });

        // Thêm sự kiện cho nút Sửa
        btnEditDistribution.addActionListener(evt -> {
            int row = tblDistributions.getSelectedRow();
            int eventRow = tblEvents.getSelectedRow();
            if (row >= 0 && eventRow >= 0) {
                Object idObj = tblDistributions.getValueAt(row, 0);
                if (idObj != null) {
                    int allocationId = Integer.parseInt(idObj.toString());
                    int eventId = (int) eventTableModel.getValueAt(eventRow, 0);
                    showEditDistributionDialog(allocationId, eventId);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phân phát để sửa.");
            }
        });

        // Thêm sự kiện cho nút Xoá
        btnDeleteDistribution.addActionListener(evt -> {
            int row = tblDistributions.getSelectedRow();
            int eventRow = tblEvents.getSelectedRow();
            if (row >= 0 && eventRow >= 0) {
                Object idObj = tblDistributions.getValueAt(row, 0);
                if (idObj != null) {
                    int allocationId = Integer.parseInt(idObj.toString());
                    int eventId = (int) eventTableModel.getValueAt(eventRow, 0);
                    deleteDistribution(allocationId, eventId);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một phân phát để xoá.");
            }
        });

        // Thêm sự kiện cho nút Thêm phân phát
        btnAddDistribution.addActionListener(e -> showAddDistributionDialog());
    }

    private void loadEventList() {
        eventTableModel.setRowCount(0);
        List<CharityEvent> events = eventService.getExpiredEventList(); // hoặc getEventList nếu muốn tất cả
        for (CharityEvent ev : events) {
            eventTableModel.addRow(new Object[]{
                ev.getId(),
                ev.getName(),
                moneyFormat.format(ev.getTargetAmount()),
                moneyFormat.format(ev.getCurrentAmount())
            });
        }
        if (eventTableModel.getRowCount() > 0) {
            tblEvents.setRowSelectionInterval(0, 0);
        }
    }

    // Sửa loadDistributions: KHÔNG có cột Sửa/Xoá nữa
    private void loadDistributions(int eventId) {
        distributionTableModel.setRowCount(0);
        List<DonationAllocation> allocations = allocationService.getAllocationsByEvent(eventId);
        for (DonationAllocation alloc : allocations) {
            String recipient = "";
            try {
                recipient = alloc.getRecipient();
            } catch (Exception ex) {
                recipient = "";
            }
            distributionTableModel.addRow(new Object[]{
                alloc.getId(),
                moneyFormat.format(alloc.getAmount()),
                alloc.getPurpose(),
                alloc.getAllocationDate(),
                recipient
            });
        }
        // Xoá cột Sửa/Xoá nếu còn tồn tại
        while (tblDistributions.getColumnCount() > 5) {
            tblDistributions.removeColumn(tblDistributions.getColumnModel().getColumn(tblDistributions.getColumnCount() - 1));
        }
    }

    private void updateEventInfo(int eventId) {
        CharityEvent ev = eventService.getEventById(eventId);
        double total = ev.getCurrentAmount();
        double allocated = allocationService.getTotalAllocatedAmount(eventId);
        double remaining = allocationService.getRemainingAmount(eventId);
        int num = allocationService.getAllocationsByEvent(eventId).size();
        lblTotalAmount.setText("Tổng tiền sự kiện: " + moneyFormat.format(total) + "đ");
        lblAllocated.setText("Tổng đã phân phát: " + moneyFormat.format(allocated) + "đ");
        lblRemaining.setText("Còn lại: " + moneyFormat.format(remaining) + "đ");
        lblNumDistributions.setText("Số lượt phân phát: " + num);
    }

    private void showAddDistributionDialog() {
        int row = tblEvents.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sự kiện.");
            return;
        }
        int eventId = (int) eventTableModel.getValueAt(row, 0);
        double remaining = allocationService.getRemainingAmount(eventId);

        // --- Loại phân phát ---
        JRadioButton rbMoney = new JRadioButton("Phân phát tiền");
        JRadioButton rbGoods = new JRadioButton("Phân phát vật tư/quà tặng");
        ButtonGroup group = new ButtonGroup();
        group.add(rbMoney);
        group.add(rbGoods);
        rbMoney.setSelected(true);
        rbMoney.setVisible(false);
        rbGoods.setVisible(false);

        // --- Trường chung ---
        JTextField txtPurpose = new JTextField();
        JTextField txtRecipient = new JTextField();

        // --- Panel nhập liệu động ---
        JPanel cardPanel = new JPanel(new CardLayout());
        // Panel tiền
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new BoxLayout(moneyPanel, BoxLayout.Y_AXIS));

        JTextField txtAmount = new JTextField();
        moneyPanel.add(labelFieldPanel("Số tiền phân phát:", txtAmount));
        moneyPanel.add(Box.createVerticalStrut(8));
        moneyPanel.add(labelFieldPanel("Mục đích:", txtPurpose));
        moneyPanel.add(Box.createVerticalStrut(8));
        moneyPanel.add(labelFieldPanel("Người nhận (hoặc danh sách):", txtRecipient));

        // Panel vật tư
        JPanel goodsPanel = new JPanel();
        goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));
        JTextField txtGiftType = new JTextField();
        JTextField txtTotalGifts = new JTextField();
        JTextField txtGiftValue = new JTextField();
        goodsPanel.add(labelFieldPanel("Loại vật tư/quà tặng:", txtGiftType));
        goodsPanel.add(labelFieldPanel("Số lượng vật tư/quà tặng:", txtTotalGifts));
        goodsPanel.add(labelFieldPanel("Giá trị từng phần quà/vật tư:", txtGiftValue));
        // Thêm vào cardPanel
        cardPanel.add(moneyPanel, "MONEY");
        cardPanel.add(goodsPanel, "GOODS");
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, "MONEY");

        rbMoney.addActionListener(evt -> cl.show(cardPanel, "MONEY"));
        rbGoods.addActionListener(evt -> cl.show(cardPanel, "GOODS"));

        // Panel chính (căn trái, giảm padding) - GIỐNG EDIT
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(248, 252, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Giảm padding

        // Header (tiêu đề) - GIỐNG EDIT
        JLabel lblHeader = new JLabel("TẠO PHÂN PHÁT MỚI", JLabel.CENTER);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setOpaque(true);
//        lblHeader.setBackground(ColorCustom.backroundHeaderTitle()); // Màu xanh lá ví dụ
        lblHeader.setForeground(Color.BLACK);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(300, 40));
        headerPanel.setOpaque(false); // hoặc setBackground nếu muốn
        headerPanel.add(lblHeader, BorderLayout.CENTER);
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Giảm khoảng cách sau tiêu đề

        // Panel chọn loại phân phát (căn trái) - GIỐNG EDIT
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Căn trái, giảm khoảng cách ngang
        typePanel.setOpaque(false);
        typePanel.add(rbMoney);
        typePanel.add(rbGoods);
        mainPanel.add(typePanel);
        mainPanel.add(Box.createVerticalStrut(8)); // Giảm khoảng cách

        // Thêm các trường nhập liệu - GIỐNG EDIT
        mainPanel.add(cardPanel); // Panel động (tiền/vật tư)

        int result = JOptionPane.showConfirmDialog(this, mainPanel, "Tạo phân phát mới", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String purpose = txtPurpose.getText().trim();
                String recipient = txtRecipient.getText().trim();
                if (purpose.isEmpty() || recipient.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mục đích và người nhận.");
                    return;
                }
                DonationAllocation alloc = new DonationAllocation();
                alloc.setEventId(eventId);
                alloc.setPurpose(purpose);
                alloc.setRecipient(recipient);
                alloc.setStatus("Chờ xác nhận");
                alloc.setAllocationDate(new java.sql.Date(System.currentTimeMillis()));
                alloc.setUsedAmount(0);
                alloc.setCreatedBy(1);
                if (rbMoney.isSelected()) {
                    String amountStr = txtAmount.getText().trim();
                    if (amountStr.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền phân phát.");
                        return;
                    }
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0.");
                        return;
                    }
                    if (amount > remaining) {
                        JOptionPane.showMessageDialog(this, "Số tiền phân phát vượt quá số tiền còn lại của sự kiện!");
                        return;
                    }
                    alloc.setAmount(amount);
                    alloc.setGiftType("");
                    alloc.setTotalGifts(0);
                } else {
                    String giftType = txtGiftType.getText().trim();
                    String totalGiftsStr = txtTotalGifts.getText().trim();
                    String giftValue = txtGiftValue.getText().trim();
                    if (giftType.isEmpty() || totalGiftsStr.isEmpty() || giftValue.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin vật tư/quà tặng.");
                        return;
                    }
                    int totalGifts = Integer.parseInt(totalGiftsStr);
                    if (totalGifts <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng vật tư/quà tặng phải lớn hơn 0.");
                        return;
                    }
                    alloc.setAmount(0);
                    alloc.setGiftType(giftType);
                    alloc.setTotalGifts(totalGifts);
                }
                boolean ok = allocationService.createAllocationPlan(alloc);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Tạo phân phát thành công!");
                    loadDistributions(eventId);
                    updateEventInfo(eventId);
                } else {
                    JOptionPane.showMessageDialog(this, "Tạo phân phát thất bại hoặc vượt quá số tiền còn lại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho các trường số lượng/số tiền.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
    }

    // Dialog sửa phân phát
    private void showEditDistributionDialog(int allocationId, int eventId) {
        DonationAllocation alloc = allocationService.getAllocationById(allocationId);
        if (alloc == null) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu phân phát.");
            return;
        }
        double remaining = allocationService.getRemainingAmount(eventId) + alloc.getAmount(); // cho phép sửa không vượt quá tổng ban đầu
        // --- Loại phân phát ---
        boolean isMoney = alloc.getAmount() > 0;
        JRadioButton rbMoney = new JRadioButton("Phân phát tiền");
        JRadioButton rbGoods = new JRadioButton("Phân phát vật tư/quà tặng");
        ButtonGroup group = new ButtonGroup();
        group.add(rbMoney);
        group.add(rbGoods);
        rbMoney.setSelected(isMoney);
        rbGoods.setSelected(!isMoney);
        rbMoney.setVisible(false);
        rbGoods.setVisible(false);

        // --- Trường chung ---
        JTextField txtPurpose = new JTextField(safe(alloc.getPurpose()));
        JTextField txtRecipient = new JTextField(safe(alloc.getRecipient()));

        // --- Panel nhập liệu động ---
        JPanel cardPanel = new JPanel(new CardLayout());
        // Panel tiền
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(new BoxLayout(moneyPanel, BoxLayout.Y_AXIS));
        JTextField txtAmount = new JTextField(alloc.getAmount() > 0 ? String.valueOf(alloc.getAmount()) : "");
        moneyPanel.add(labelFieldPanel("Số tiền phân phát:", txtAmount));
        moneyPanel.add(Box.createVerticalStrut(8));
        moneyPanel.add(labelFieldPanel("Mục đích:", txtPurpose));
        moneyPanel.add(Box.createVerticalStrut(8));
        moneyPanel.add(labelFieldPanel("Người nhận (hoặc danh sách):", txtRecipient));
        moneyPanel.add(Box.createVerticalStrut(8));

        // Panel vật tư
        JPanel goodsPanel = new JPanel();
        goodsPanel.setLayout(new BoxLayout(goodsPanel, BoxLayout.Y_AXIS));
        JTextField txtGiftType = new JTextField(safe(alloc.getGiftType()));
        JTextField txtTotalGifts = new JTextField(alloc.getTotalGifts() > 0 ? String.valueOf(alloc.getTotalGifts()) : "");
        JTextField txtGiftValue = new JTextField(safe(alloc.getGiftValue()));
        goodsPanel.add(labelFieldPanel("Loại vật tư/quà tặng:", txtGiftType));
        goodsPanel.add(labelFieldPanel("Số lượng vật tư/quà tặng:", txtTotalGifts));
        goodsPanel.add(labelFieldPanel("Giá trị từng phần quà/vật tư:", txtGiftValue));
        // Thêm vào cardPanel
        cardPanel.add(moneyPanel, "MONEY");
        cardPanel.add(goodsPanel, "GOODS");
        CardLayout cl = (CardLayout) cardPanel.getLayout();
        cl.show(cardPanel, isMoney ? "MONEY" : "GOODS");

        rbMoney.addActionListener(evt -> cl.show(cardPanel, "MONEY"));
        rbGoods.addActionListener(evt -> cl.show(cardPanel, "GOODS"));

        // Panel chính (căn trái, giảm padding)
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(248, 252, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Giảm padding

        // Header (tiêu đề)
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Căn trái tiêu đề
        headerPanel.setBackground(new Color(33, 120, 200));
        headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // Giảm chiều cao
        headerPanel.setPreferredSize(new Dimension(300, 40)); // Giảm chiều cao
        JLabel lblHeader = new JLabel("SỬA PHÂN PHÁT");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblHeader.setForeground(Color.BLACK);
        headerPanel.add(lblHeader);
        mainPanel.add(headerPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // Giảm khoảng cách sau tiêu đề
        mainPanel.add(cardPanel); // Panel động (tiền/vật tư)

        // Panel chọn loại phân phát (căn trái)
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Căn trái, giảm khoảng cách ngang
        typePanel.setOpaque(false);
        typePanel.add(rbMoney);
        typePanel.add(rbGoods);
        mainPanel.add(typePanel);
        mainPanel.add(Box.createVerticalStrut(8)); // Giảm khoảng cách

        // Thêm các trường nhập liệu (đã căn trái sẵn trong `labelFieldPanel`)
//        mainPanel.add(labelFieldPanel("Mục đích:", txtPurpose));
//        mainPanel.add(Box.createVerticalStrut(8));
//        mainPanel.add(labelFieldPanel("Người nhận (hoặc danh sách):", txtRecipient));
//        mainPanel.add(Box.createVerticalStrut(8));
//        mainPanel.add(cardPanel); // Panel động (tiền/vật tư)
        int result = JOptionPane.showConfirmDialog(this, mainPanel, "Sửa phân phát", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String purpose = txtPurpose.getText().trim();
                String recipient = txtRecipient.getText().trim();
                if (purpose.isEmpty() || recipient.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mục đích và người nhận.");
                    return;
                }
                alloc.setPurpose(purpose);
                alloc.setRecipient(recipient);
                if (rbMoney.isSelected()) {
                    String amountStr = txtAmount.getText().trim();
                    if (amountStr.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập số tiền phân phát.");
                        return;
                    }
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(this, "Số tiền phải lớn hơn 0.");
                        return;
                    }
                    if (amount > remaining) {
                        JOptionPane.showMessageDialog(this, "Số tiền phân phát vượt quá số tiền còn lại của sự kiện!");
                        return;
                    }
                    alloc.setPurpose(purpose);
                    alloc.setRecipient(recipient);
                    alloc.setAmount(amount);
                    alloc.setGiftType("");
                    alloc.setTotalGifts(0);
                } else {
                    String giftType = txtGiftType.getText().trim();
                    String totalGiftsStr = txtTotalGifts.getText().trim();
                    String giftValue = txtGiftValue.getText().trim();
                    if (giftType.isEmpty() || totalGiftsStr.isEmpty() || giftValue.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin vật tư/quà tặng.");
                        return;
                    }
                    int totalGifts = Integer.parseInt(totalGiftsStr);
                    if (totalGifts <= 0) {
                        JOptionPane.showMessageDialog(this, "Số lượng vật tư/quà tặng phải lớn hơn 0.");
                        return;
                    }
                    alloc.setAmount(0);
                    alloc.setGiftType(giftType);
                    alloc.setTotalGifts(totalGifts);
                }
                boolean ok = allocationService.updateAllocation(alloc);
                if (ok) {
                    JOptionPane.showMessageDialog(this, "Cập nhật phân phát thành công!");
                    loadDistributions(eventId);
                    updateEventInfo(eventId);
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật phân phát thất bại!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng số cho các trường số lượng/số tiền.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi: " + ex.getMessage());
            }
        }
    }

    // Xoá phân phát
    private void deleteDistribution(int allocationId, int eventId) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xoá phân phát này?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = allocationService.deleteAllocationById(allocationId);
            if (ok) {
                JOptionPane.showMessageDialog(this, "Đã xoá phân phát thành công!");
                loadDistributions(eventId);
                updateEventInfo(eventId);
            } else {
                JOptionPane.showMessageDialog(this, "Xoá phân phát thất bại!");
            }
        }
    }

    // Renderer cho nút Sửa/Xoá
    class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {

        private Color normalColor;
        private Color hoverColor;

        // private boolean isHover = false; // Xoá biến không dùng
        public ButtonRenderer(String text, Color color) {
            setText(text);
            setForeground(Color.WHITE);
            setBackground(color);
            setFocusPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 13));
            setBorder(BorderFactory.createEmptyBorder(6, 16, 6, 16));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setOpaque(true);
            setBorderPainted(false);
            setContentAreaFilled(true);
            setMargin(new Insets(2, 8, 2, 8));
            setPreferredSize(new Dimension(60, 32));
            setMaximumSize(new Dimension(80, 32));
            setMinimumSize(new Dimension(60, 32));
            setFocusable(false);
            setBorder(BorderFactory.createLineBorder(color.darker(), 1, true));
            normalColor = color;
            hoverColor = color.brighter();
            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    setBackground(hoverColor);
                }

                public void mouseExited(java.awt.event.MouseEvent e) {
                    setBackground(normalColor);
                }
            });
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(normalColor.darker());
            } else {
                setBackground(normalColor);
            }
            return this;
        }
    }

    // Renderer cho hiển thị nhiều dòng (wrap text) cho cột Người nhận
    class MultiLineTableCellRenderer extends JTextArea implements javax.swing.table.TableCellRenderer {

        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value == null ? "-" : value.toString());
            setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
            setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
            // Tính chiều cao phù hợp
            int tableWidth = table.getColumnModel().getColumn(column).getWidth();
            setSize(new Dimension(tableWidth, Short.MAX_VALUE));
            int preferredHeight = getPreferredSize().height;
            if (table.getRowHeight(row) != preferredHeight) {
                table.setRowHeight(row, preferredHeight);
            }
            return this;
        }
    }

    // Tiện ích tạo panel label + field: label trên, field dưới, sát trái, chiều ngang vừa phải
    private JPanel labelFieldPanel(String label, JTextField field) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);
        p.setAlignmentX(Component.LEFT_ALIGNMENT); // Ensure left alignment
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Allow full width
        p.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0)); // Remove left/right padding

        JLabel l = new JLabel(label);
        l.setFont(new Font("Segoe UI", Font.BOLD, 14));
        l.setAlignmentX(Component.LEFT_ALIGNMENT); // Left align label
        p.add(l);

        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 210, 230), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); // Allow full width
        field.setAlignmentX(Component.LEFT_ALIGNMENT); // Left align field
        p.add(field);

        return p;
    }

    // Thêm hàm tiện ích safe để hiển thị giá trị null thành "-"
    private static String safe(Object v) {
        return (v == null || v.toString().trim().isEmpty()) ? "" : v.toString();
    }
}
