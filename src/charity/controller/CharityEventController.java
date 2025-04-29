package charity.controller;

import charity.component.*;
import charity.model.CharityEvent;
import charity.model.Organization;
import charity.service.CharityEventService;
import charity.service.OrganizationService;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CharityEventController {

    private JPanel jpnTable;
    private JTextField txtCurrentAmount;
    private JTextArea txtDescription;
    private JTextField txtEventName;
    private JTextField txtId;
    private JTextField txtProgress;
    private JTextField txtSearch;
    private JTextField txtTargetAmount;
    private JButton jbtChoose;
    private GButton jbtReset;
    private JComboBox<String> jcbCategory;
    private JComboBox<Object> jcbOrganization;
    private com.toedter.calendar.JDateChooser jdcDateBegin;
    private com.toedter.calendar.JDateChooser jdcDateEnd;
    private GButton gbtAdd;
    private GButton gbtCancel;
    private GButton gbtDelete;
    private GButton gbtSave;
    private GButton gbtUpdate;
    private JLabel jlbImage;
    private String imageUrl;

    private ClassTableModel classTableModel = null;
    private CharityEventService eventService = null;
    private OrganizationService organizationService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable eventTable = null;

    private int selectedEventId = -1;
    private int selectedButton = 0;

    public CharityEventController(JPanel jpnTable) {
        this.jpnTable = jpnTable;

    }

    public CharityEventController(JPanel jpnTable, JTextField txtCurrentAmount, JTextArea txtDescription, JTextField txtEventName, JTextField txtId, JTextField txtProgress, JTextField txtSearch, JTextField txtTargetAmount, JButton jbtChoose, GButton jbtReset, JComboBox<String> jcbCategory, JComboBox<Object> jcbOrganization, JDateChooser jdcDateBegin, JDateChooser jdcDateEnd, GButton gbtAdd, GButton gbtCancel, GButton gbtDelete, GButton gbtSave, GButton gbtUpdate, JLabel jlbImage) {
        this.jpnTable = jpnTable;
        this.txtCurrentAmount = txtCurrentAmount;
        this.txtDescription = txtDescription;
        this.txtEventName = txtEventName;
        this.txtId = txtId;
        this.txtProgress = txtProgress;
        this.txtSearch = txtSearch;
        this.txtTargetAmount = txtTargetAmount;
        this.jbtChoose = jbtChoose;
        this.jbtReset = jbtReset;
        this.jcbCategory = jcbCategory;
        this.jcbOrganization = jcbOrganization;
        this.jdcDateBegin = jdcDateBegin;
        this.jdcDateEnd = jdcDateEnd;
        this.gbtAdd = gbtAdd;
        this.gbtCancel = gbtCancel;
        this.gbtDelete = gbtDelete;
        this.gbtSave = gbtSave;
        this.gbtUpdate = gbtUpdate;
        this.jlbImage = jlbImage;

        eventService = new CharityEventService();
        organizationService = new OrganizationService();
        classTableModel = new ClassTableModel();

        init();
        initEvent();

    }

    public void init() {
        loadJcbOrganization();
        loadJcbCategory();
        showEventTable();
        clearForm();
        resetButtonState();
    }

    public void initEvent() {
//        setTableClickEvent();
        gbtAdd.addActionListener(e -> onAdd());
        gbtSave.addActionListener(e -> onSave());
        gbtUpdate.addActionListener(e -> onUpdate());
        gbtDelete.addActionListener(e -> onDelete());
        gbtCancel.addActionListener(e -> onCancel());
        jbtReset.addActionListener(e -> onReset());
        jbtChoose.addActionListener(e -> onChooseImage()); // Giả sử chọn ảnhF
    }

    //khoi tao CBB Organization
    private void loadJcbOrganization() {
        List<Organization> organizations = organizationService.getAllOrganization();
        jcbOrganization.removeAllItems();
        jcbOrganization.addItem(null);
        for (Organization o : organizations) {
            jcbOrganization.addItem(o);
        }
    }

    //khoi tao CBB Category
    private void loadJcbCategory() {
        String[] categorys = {"", "Y tế", "Giáo dục", "Cứu trợ", "Môi trường", "Nhà ở", "Trẻ em"};
        jcbCategory.removeAllItems();
        for (String c : categorys) {
            jcbCategory.addItem(c);
        }
    }

    public void showEventTable() {
        //setup event table
        List<CharityEvent> events = new ArrayList<>();

        events = eventService.getActiveEventList();

        DefaultTableModel model = classTableModel.getEventTable(events);
        eventTable = new JTable(model);

        //setup rowsorter
        rowSorter = new TableRowSorter<>(eventTable.getModel());
        eventTable.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            //"(?i)" khong phan biet chu hoa chu thuong
            //khi nhap vao txtSearch
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1, 2));
                }
            }

            //khi xoa noi dung cua txtSearch
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1, 2));
                }
            }

            //khi co thay doi thuoc tinh van ban
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        designTable(eventTable);

        //hien thi ra jpnTable
        JScrollPane scroll = new JScrollPane(eventTable);
        eventTable.setFillsViewportHeight(true);
        eventTable.setBackground(Color.white);
        scroll.getViewport().setBackground(Color.white);
        scroll.setPreferredSize(new Dimension(jpnTable.getWidth(), 250));
        jpnTable.removeAll();
        jpnTable.setBackground(Color.white);

        jpnTable.setLayout(new CardLayout());
        jpnTable.add(scroll);
        jpnTable.revalidate();
        jpnTable.repaint();
        setTableClickEvent();
    }

    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
//        table.getTableHeader().setBackground(Color.decode("#b8e7ea"));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        //table body
        table.setRowHeight(40);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);

        //chu can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //size column
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(3).setMaxWidth(500);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);

        table.getColumnModel().getColumn(5).setMaxWidth(500);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        table.getColumnModel().getColumn(6).setMaxWidth(500);
        table.getColumnModel().getColumn(6).setPreferredWidth(70);

        //show
        table.validate();
        table.repaint();
    }

    public void setTableClickEvent() {
        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && eventTable.getSelectedRow() != -1) {
                    int selectedRow = eventTable.convertRowIndexToModel(eventTable.getSelectedRow());
                    TableModel model = eventTable.getModel();

                    // Lấy dữ liệu từ model
                    txtId.setText(model.getValueAt(selectedRow, 0).toString());
                    txtEventName.setText(model.getValueAt(selectedRow, 2).toString());
                    selectComboBoxItemByName(jcbOrganization, model.getValueAt(selectedRow, 1).toString());
//                    jcbOrganization.setSelectedItem(model.getValueAt(selectedRow, 1).toString());
                    jcbCategory.setSelectedItem(model.getValueAt(selectedRow, 3).toString());
                    txtTargetAmount.setText(model.getValueAt(selectedRow, 4).toString());
                    txtCurrentAmount.setText(model.getValueAt(selectedRow, 5).toString());
                    txtProgress.setText(model.getValueAt(selectedRow, 6).toString());

                    // Giả sử bạn có hàm getEventById để lấy dữ liệu đầy đủ (ví dụ: Description, Dates,...)
                    int eventId = Integer.parseInt(txtId.getText());
                    CharityEvent event = eventService.getEventById(eventId); // Phải thêm phương thức này vào service nếu chưa có

                    if (event != null) {
                        txtDescription.setText(event.getDescription());
                        jdcDateBegin.setDate(event.getDateBegin());
                        jdcDateEnd.setDate(event.getDateEnd());
                        jlbImage.setIcon(ImageIconCustom.getSmoothIcon(event.getImageUrl(), 160, 160));
                    }

                    // Lưu lại ID sự kiện đã chọn
                    selectedEventId = eventId;

//                    // Kích hoạt các nút
//                    gbtUpdate.setEnabled(true);
//                    gbtDelete.setEnabled(true);
//                    gbtSave.setEnabled(false);
//                    gbtAdd.setEnabled(false);
                }
            }
        });
    }
private void selectComboBoxItemByName(JComboBox<?> comboBox, String name) {
    for (int i = 0; i < comboBox.getItemCount(); i++) {
        Object item = comboBox.getItemAt(i);
        if (item != null && item.toString().equals(name)) {
            comboBox.setSelectedIndex(i);
            return;
        }
    }
}
    private void onAdd() {

        clearForm();
        selectedButton = 1;

        selectedEventId = -1;

        // Hiển thị nút Lưu và Hủy
        gbtSave.setVisible(true);
        gbtCancel.setVisible(true);

        // Vô hiệu hóa các nút khác
        gbtAdd.setEnabled(false);
        gbtUpdate.setEnabled(false);
        gbtDelete.setEnabled(false);
        
        imageUrl = "/charity/image/default.png";
        jlbImage.setIcon(ImageIconCustom.getSmoothIcon(imageUrl, 170, 160));
    }

    private void onUpdate() {

        if (selectedEventId == -1) {
            selectedButton = 2;
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sự kiện để cập nhật.");
            return;
        }

        gbtSave.setVisible(true);
        gbtCancel.setVisible(true);
        gbtAdd.setEnabled(false);
        gbtUpdate.setEnabled(false);
        gbtDelete.setEnabled(false);
    }

    private void onDelete() {
        if (selectedEventId != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sự kiện này?");
            if (confirm == JOptionPane.YES_OPTION) {
                eventService.deleteEvent(selectedEventId);
                showEventTable();
                clearForm();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sự kiện để xóa.");
        }
    }

    private void onSave() {
        if (!validateForm()) {
            return;
        }

        CharityEvent event = getEventFromForm();

        if (selectedEventId == -1 && selectedButton == 1) {
            // THÊM MỚI
            if (eventService.addEvent(event)) {
                JOptionPane.showMessageDialog(null, "Thêm sự kiện thành công.");
            } else {
                JOptionPane.showMessageDialog(null, "Thêm sự kiện thất bại.");
            }
        } else if (selectedEventId != -1 && selectedButton == 2) {
            // CẬP NHẬT
            event.setId(selectedEventId); // Set ID để cập nhật
            if (eventService.updateEvent(event)) {
                JOptionPane.showMessageDialog(null, "Cập nhật sự kiện thành công.");
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật sự kiện thất bại.");
            }
        }

        showEventTable();
        clearForm();
        resetButtonState();
        selectedEventId = -1;
    }

    private void onCancel() {
        clearForm();         // Làm sạch lại form
        showEventTable();    // Tải lại bảng dữ liệu
        resetButtonState();
        selectedEventId = -1;
    }

    private void onReset() {
        clearForm();
    }

    private void onChooseImage() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(imageFilter);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            String fileName = selectedFile.getName();

            //Đường dẫn tương đối đưa vào dâtbase
            String relativePath = "/charity/image/" + fileName;
            
            //copy file vao thu muc image
            copyFileToImageFolder(selectedFile, fileName);
            
            imageUrl = relativePath;
            jlbImage.setIcon(ImageIconCustom.getSmoothIcon(imageUrl, 170, 160));
        }
    }

    private void resetButtonState() {
        gbtSave.setVisible(false);
        gbtCancel.setVisible(false);
        gbtAdd.setEnabled(true);
        gbtUpdate.setEnabled(true);
        gbtDelete.setEnabled(true);
    }

    private CharityEvent getEventFromForm() {
        try {
            String name = txtEventName.getText().trim();
            String category = (String) jcbCategory.getSelectedItem();
            Organization org = (Organization) jcbOrganization.getSelectedItem();
            double targetAmount = Double.parseDouble(txtTargetAmount.getText().trim());
            double currentAmount = Double.parseDouble(txtCurrentAmount.getText().trim());
            String description = txtDescription.getText().trim();
            java.sql.Date beginDate = (java.sql.Date) jdcDateBegin.getDate();
            java.sql.Date endDate = (java.sql.Date) jdcDateEnd.getDate();

            CharityEvent event = new CharityEvent();
            event.setName(name);
            event.setCategory(category);
            event.setOrganizationId(org.getId());
            event.setTargetAmount((long) targetAmount);
            event.setCurrentAmount((long) currentAmount);
            event.setDescription(description);
            event.setDateBegin(beginDate);
            event.setDateEnd(endDate);
            return event;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Lỗi khi nhập thông tin: " + ex.getMessage());
            return null;
        }
    }

    private void clearForm() {
        txtId.setText("");
        txtEventName.setText("");
        txtCurrentAmount.setText("");
        txtTargetAmount.setText("");
        txtProgress.setText("");
        txtDescription.setText("");
        jcbCategory.setSelectedIndex(0);
        txtSearch.setText("");
        if (jcbOrganization.getItemCount() > 0) {
            jcbOrganization.setSelectedIndex(0);
        }
        jdcDateBegin.setDate(null);
        jdcDateEnd.setDate(null);
//        gbtAdd.setEnabled(true);
//        gbtSave.setEnabled(false);
//        gbtUpdate.setEnabled(false);
//        gbtDelete.setEnabled(false);
        selectedEventId = -1;
        selectedButton = 0;
    }

    private boolean validateForm() {
        String eventName = txtEventName.getText().trim();
        String targetAmountStr = txtTargetAmount.getText().trim();
        String currentAmountStr = txtCurrentAmount.getText().trim();

        if (eventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên sự kiện không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (jdcDateBegin.getDate() == null || jdcDateEnd.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn đầy đủ ngày bắt đầu và kết thúc!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (jdcDateEnd.getDate().before(jdcDateBegin.getDate())) {
            JOptionPane.showMessageDialog(null, "Ngày kết thúc không được trước ngày bắt đầu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            double targetAmount = Double.parseDouble(targetAmountStr);
            if (targetAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Số tiền kêu gọi phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số tiền kêu gọi không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            double currentAmount = Double.parseDouble(currentAmountStr);
            if (currentAmount < 0) {
                JOptionPane.showMessageDialog(null, "Số tiền hiện tại không được âm!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số tiền hiện tại không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private void copyFileToImageFolder(File sourceFile, String fileName) {
        //đường dẫn thực tế đến thư mục ảnh trong project
        File destDir = new File("src/charity/image");

        if (!destDir.exists()) {
            destDir.mkdirs();//tạo thư mục nếu chưa có
        }

        File destFile = new File(destDir, fileName);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
//            Logger.getLogger(CharityEventController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Lỗi khi sao chép ảnh.");
        }
    }
}
