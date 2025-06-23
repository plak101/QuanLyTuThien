package charity.controller.AdminController;

import charity.component.*;
import static charity.component.IFormatData.dateFormat;
import charity.model.Category;
import charity.model.CharityEvent;
import charity.model.Organization;
import charity.service.CategoryService;
import charity.service.CharityEventService;
import charity.service.OrganizationService;
import charity.utils.ScannerUtils;
import com.toedter.calendar.JDateChooser;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CharityEventPanelController {

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
    private JComboBox<Object> jcbCategory;
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
    private JComboBox<String> jcbStatus, jcbFilter;
    private DefaultTableModel model;
    private ClassTableModel classTableModel = null;
    private CharityEventService eventService = null;
    private OrganizationService organizationService = null;
    private CategoryService categoryService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable eventTable = null;

    private int selectedEventId = -1;
    private int selectedButton = 0;

    private NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
    private DecimalFormat moneyFormat = new DecimalFormat("#,###");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private List<CharityEvent> events;

    public CharityEventPanelController(JPanel jpnTable) {
        this.jpnTable = jpnTable;

    }

    public CharityEventPanelController(JPanel jpnTable, JTextField txtCurrentAmount, JTextArea txtDescription, JTextField txtEventName, JTextField txtId, JTextField txtProgress, JTextField txtSearch, JTextField txtTargetAmount, JButton jbtChoose, GButton jbtReset, JComboBox<Object> jcbCategory, JComboBox<Object> jcbOrganization, JDateChooser jdcDateBegin, JDateChooser jdcDateEnd, GButton gbtAdd, GButton gbtCancel, GButton gbtDelete, GButton gbtSave, GButton gbtUpdate, JLabel jlbImage, JComboBox<String> jcbStatus, JComboBox<String> jcbFilter) {
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
        this.jcbStatus = jcbStatus;
        this.jcbFilter = jcbFilter;

        eventService = new CharityEventService();
        organizationService = new OrganizationService();
        categoryService = new CategoryService();
        classTableModel = new ClassTableModel();

        init();
        initEvent();

    }

    public void init() {
        loadJcbOrganization();
        loadJcbCategory();
        loadJcbFilter();
        loadJcbStatus();
        showEventTable();
        clearForm();
        resetButtonState();
        jdcDateBegin.setDateFormatString("dd/MM/yyyy");
        jdcDateEnd.setDateFormatString("dd/MM/yyyy");
        jbtChoose.setEnabled(false);
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
        setHoverButton();
        ScannerUtils.setOnlyInputNumber(txtTargetAmount);
        jcbFilter.addActionListener(e -> filterEvent());
    }

    public void setHoverButton() {
        gbtAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtAdd.setColor(ColorCustom.colorBtnAdd());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtAdd.setColor(ColorCustom.colorBtnAddHover());
            }
        });

        gbtDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtDelete.setColor(ColorCustom.colorBtnDelete());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtDelete.setColor(ColorCustom.colorBtnDeleteHover());
            }
        });

        gbtUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtUpdate.setColor(ColorCustom.colorBtnUpdate());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtUpdate.setColor(ColorCustom.colorBtnUpdateHover());
            }
        });

        jbtReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                jbtReset.setColor(ColorCustom.colorBtnReset());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtReset.setColor(ColorCustom.colorBtnResetHover());
            }
        });

        gbtCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtCancel.changeColor("#D32F2F");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtCancel.changeColor("#F44336");
            }
        });

        gbtSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtSave.changeColor("#43A047");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtSave.changeColor("#66BB6A");
            }
        });

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
        List<Category> categorys = categoryService.getAllCategories();
        jcbCategory.removeAllItems();
        jcbCategory.addItem(null);
        for (Category c : categorys) {
            jcbCategory.addItem(c);
        }
    }

    //khoi tao CBB Status
    private void loadJcbStatus() {
        jcbStatus.removeAllItems();
        jcbStatus.addItem("Kêu gọi");
        jcbStatus.addItem("Phân phát");
    }

    //khoi tao CBB Filter
    private void loadJcbFilter() {
        jcbFilter.removeAllItems();
        jcbFilter.addItem("Tất cả");
        jcbFilter.addItem("Kêu gọi");
        jcbFilter.addItem("Phân phát");
    }

    //do in background
    public void showEventTable() {
        new SwingWorker<List<CharityEvent>, Void>() {
            @Override
            protected List<CharityEvent> doInBackground() {
                return eventService.getEventList();
            }

            @Override
            protected void done() {
                try {
                    events=get();
                } catch (InterruptedException ex) {
                    Logger.getLogger(CharityEventPanelController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(CharityEventPanelController.class.getName()).log(Level.SEVERE, null, ex);
                }
                model = classTableModel.getEventTable(events);
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
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                        }
                    }

                    //khi xoa noi dung cua txtSearch
                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        String text = txtSearch.getText();

                        if (text.trim().length() == 0) {
                            rowSorter.setRowFilter(null);
                        } else {
                            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
                scroll.setPreferredSize(new Dimension(jpnTable.getWidth(), 400));
                jpnTable.removeAll();
                jpnTable.setBackground(Color.white);

                jpnTable.setLayout(new CardLayout());
                jpnTable.add(scroll);
                jpnTable.revalidate();
                jpnTable.repaint();
                setTableClickEvent();
            }
        }.execute();


       
    }

//    public void showEventTable() {
//        
//        //setup event table
//
//        events = eventService.getEventList();
//
//        model = classTableModel.getEventTable(events);
//        eventTable = new JTable(model);
//
//        //setup rowsorter
//        rowSorter = new TableRowSorter<>(eventTable.getModel());
//        eventTable.setRowSorter(rowSorter);
//        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
//
//            //"(?i)" khong phan biet chu hoa chu thuong
//            //khi nhap vao txtSearch
//            @Override
//            public void insertUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            //khi xoa noi dung cua txtSearch
//            @Override
//            public void removeUpdate(DocumentEvent e) {
//                String text = txtSearch.getText();
//
//                if (text.trim().length() == 0) {
//                    rowSorter.setRowFilter(null);
//                } else {
//                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//                }
//            }
//
//            //khi co thay doi thuoc tinh van ban
//            @Override
//            public void changedUpdate(DocumentEvent e) {
//            }
//        });
//
//        designTable(eventTable);
//
//        //hien thi ra jpnTable
//        JScrollPane scroll = new JScrollPane(eventTable);
//        eventTable.setFillsViewportHeight(true);
//        eventTable.setBackground(Color.white);
//        scroll.getViewport().setBackground(Color.white);
//        scroll.setPreferredSize(new Dimension(jpnTable.getWidth(), 400));
//        jpnTable.removeAll();
//        jpnTable.setBackground(Color.white);
//
//        jpnTable.setLayout(new CardLayout());
//        jpnTable.add(scroll);
//        jpnTable.revalidate();
//        jpnTable.repaint();
//        setTableClickEvent();
//    }
    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
//        table.getTableHeader().setBackground(Color.decode("#b8e7ea"));
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        //table body
        table.setRowHeight(100);
        table.setShowHorizontalLines(true);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);

        //chu can giua
//        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
//        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
//
//        for (int i = 0; i < table.getColumnCount(); i++) {
//            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//        }
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

        //dat mau cho su kien het han
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Color endDateColor = new Color(255, 204, 204);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                setHorizontalAlignment(CENTER);
                int endDateCol = 7;
                String endDateStr = table.getValueAt(row, endDateCol).toString();
                Date endDate = null;
                try {
                    endDate = sdf.parse(endDateStr);
                } catch (ParseException ex) {
                    Logger.getLogger(CharityEventPanelController.class.getName()).log(Level.SEVERE, null, ex);
                    c.setBackground(Color.white);
                    return c;
                }
                Date now = new Date();

                if (endDate.before(now)) {
                    c.setBackground(endDateColor);
                } else if (!isSelected) {
                    c.setBackground(Color.white);
                }

                if (isSelected) {
                    c.setBackground(table.getSelectionBackground());
                }
                return c;
            }

        });
        table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof ImageIcon) {
                    setIcon((Icon) value);
                } else {
                    super.setText(imageUrl);
                }
            }

            {
                setHorizontalAlignment(CENTER);
            }
        });
        //show
        table.validate();
        table.repaint();
    }

    public void setTableClickEvent() {
        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting() && eventTable.getSelectedRow() != -1) {
                if (eventTable.getSelectedRow() != -1) {
                    int selectedRow = eventTable.convertRowIndexToModel(eventTable.getSelectedRow());
                    TableModel model = eventTable.getModel();

                    // Lấy dữ liệu từ model
                    txtId.setText(model.getValueAt(selectedRow, 0).toString());
                    txtEventName.setText(model.getValueAt(selectedRow, 2).toString());
                    selectComboBoxItemByName(jcbOrganization, model.getValueAt(selectedRow, 1).toString());
                    selectComboBoxItemByName(jcbCategory, model.getValueAt(selectedRow, 3).toString());
                    txtProgress.setText(model.getValueAt(selectedRow, 6).toString());
                    int eventId = Integer.parseInt(txtId.getText());
                    CharityEvent event = eventService.getEventById(eventId);
                    if (event != null) {
                        txtTargetAmount.setText(String.valueOf(event.getTargetAmount()));
                        txtCurrentAmount.setText(String.valueOf(event.getCurrentAmount()));
                        txtDescription.setText(event.getDescription());
                        jdcDateBegin.setDate(event.getDateBegin());
                        jdcDateEnd.setDate(event.getDateEnd());
                        jlbImage.setIcon(ImageIconCustom.getSmoothIcon(event.getImageUrl(), 160, 160));
                        imageUrl = event.getImageUrl();
                        jcbStatus.setSelectedItem(event.getStatus());
                    }
                    // Lưu lại ID sự kiện đã chọn
                    selectedEventId = eventId;

                }
            }
        });
    }

    private void loadEventToForm(int eventId) {
        CharityEvent event = eventService.getEventById(eventId);

        if (event == null) {
            return;
        }

        txtId.setText(String.valueOf(event.getId()));
        txtEventName.setText(event.getName());
        selectComboBoxItemByName(jcbOrganization, organizationService.getNameById(event.getOrganizationId()));
        selectComboBoxItemByName(jcbCategory, categoryService.getCategoryNameById(event.getCategoryId()));
        txtTargetAmount.setText(String.valueOf(event.getTargetAmount()));
        txtCurrentAmount.setText(String.valueOf(event.getCurrentAmount()));
        txtDescription.setText(event.getDescription());
        txtProgress.setText(String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100));

        jdcDateBegin.setDate(event.getDateBegin());
        jdcDateEnd.setDate(event.getDateEnd());

        String img = event.getImageUrl();
        if (img != null && !img.isEmpty()) {
            jlbImage.setIcon(ImageIconCustom.getSmoothIcon(img, 160, 160));
        } else {
            jlbImage.setIcon(null); // hoặc dùng ảnh mặc định
        }

        imageUrl = img; // lưu lại đường dẫn ảnh
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

        setEnabledInput(false);
        jbtChoose.setEnabled(true);
    }

    private void onUpdate() {
        loadEventToForm(selectedEventId);
        selectedButton = 2;

        if (selectedEventId == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sự kiện để cập nhật.");
            return;
        }

        gbtSave.setVisible(true);
        gbtCancel.setVisible(true);
        gbtAdd.setEnabled(false);
        gbtUpdate.setEnabled(false);
        gbtDelete.setEnabled(false);
        setEnabledInput(false);
        jbtChoose.setEnabled(true);

    }

    private void onDelete() {
        if (selectedEventId != -1) {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa sự kiện này?");
            if (confirm == JOptionPane.YES_OPTION) {
                eventService.deleteEvent(selectedEventId);
                showEventTable();
                clearForm();
                MapHelper.refreshEventCache();
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

        //
        if (selectedEventId == -1 && selectedButton == 1) {
            // THÊM MỚI
            if (eventService.addEvent(event)) {
                JOptionPane.showMessageDialog(null, "Thêm sự kiện thành công.");
                MapHelper.refreshEventCache();

            } else {
                JOptionPane.showMessageDialog(null, "Thêm sự kiện thất bại.");
            }
        } else if (selectedEventId != -1 && selectedButton == 2) {
            // CẬP NHẬT
            event.setId(selectedEventId); // Set ID để cập nhật
            if (eventService.updateEvent(event)) {
                JOptionPane.showMessageDialog(null, "Cập nhật sự kiện thành công.");
                MapHelper.refreshEventCache();

            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật sự kiện thất bại.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cập nhật sự kiện thất bại-if");
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
        setEnabledInput(true);
    }

    private void onReset() {
        clearForm();
        showEventTable();
        resetButtonState();
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

        //test
        System.out.println(imageUrl);
    }

    private void resetButtonState() {
        gbtSave.setVisible(false);
        gbtCancel.setVisible(false);
        gbtAdd.setEnabled(true);
        gbtUpdate.setEnabled(true);
        gbtDelete.setEnabled(true);
        jbtChoose.setEnabled(false);

    }

    private CharityEvent getEventFromForm() {
        try {
            String name = txtEventName.getText().trim();
            Category category = (Category) jcbCategory.getSelectedItem();
            Organization org = (Organization) jcbOrganization.getSelectedItem();

            long targetAmount = Long.parseLong(txtTargetAmount.getText().trim());
            String strCurrentAmount = txtCurrentAmount.getText().trim();
            long currentAmount;
            if (strCurrentAmount.equals("")) {
                currentAmount = 0;
            } else {
                currentAmount = Long.parseLong(strCurrentAmount);
            }

            String description = txtDescription.getText().trim();

            //khi lay ngay tu jdc thi kieu du lieu la util
            java.util.Date utilDateBegin = jdcDateBegin.getDate();
            java.util.Date utilDateEnd = jdcDateEnd.getDate();

            //convert sang sql
            java.sql.Date sqlDateBegin = new java.sql.Date(utilDateBegin.getTime());
            java.sql.Date sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());

            CharityEvent event = new CharityEvent();
            event.setId(selectedEventId);
            event.setName(name);
            event.setCategoryId(category.getCategoryId());
            event.setOrganizationId(org.getId());
            event.setTargetAmount(targetAmount);
            event.setCurrentAmount(currentAmount);
            event.setDescription(description);
            event.setDateBegin(sqlDateBegin);
            event.setDateEnd(sqlDateEnd);
            event.setImageUrl(imageUrl);
            event.setStatus((String) jcbStatus.getSelectedItem());
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
        imageUrl = "/charity/image/default.png";
        jlbImage.setIcon(ImageIconCustom.getSmoothIcon(imageUrl, 170, 160));
        jcbFilter.setSelectedIndex(0);
        jcbStatus.setSelectedIndex(0);
    }

    private boolean validateForm() {
        String eventName = txtEventName.getText().trim();
        String targetAmountStr = txtTargetAmount.getText().trim();
        String currentAmountStr = txtCurrentAmount.getText().trim();

        if (eventName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên sự kiện không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (jcbOrganization.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn tổ chức!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (jcbCategory.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn loại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
            long targetAmount = Long.parseLong(targetAmountStr);
            if (targetAmount <= 0) {
                JOptionPane.showMessageDialog(null, "Số tiền kêu gọi phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số tiền kêu gọi không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    //--------------------------------
    //copy anh vao thu muc neu chua co
    //--------------------------------
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

    public void setEnabledInput(boolean b) {
        txtId.setEnabled(b);
        txtCurrentAmount.setEnabled(b);
        txtProgress.setEnabled(b);
    }

    //cap nhat bang
    private void updateTable(List<CharityEvent> list) {
        model.setRowCount(0);
        String[] listEventColumn = {"ID", "Hình ảnh", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Trạng thái"};
        for (CharityEvent event : list) {
            Object[] obj = new Object[listEventColumn.length];
            obj[0] = event.getId();
            obj[1] = ImageIconCustom.getSmoothIcon(event.getImageUrl(), 100, 80);
            obj[2] = event.getName();
            obj[3] = MapHelper.getCategoryName(event.getCategoryId());
            obj[4] = moneyFormat.format(event.getTargetAmount());
            obj[5] = moneyFormat.format(event.getCurrentAmount());
            obj[6] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
            obj[7] = dateFormat.format(event.getDateEnd());
            obj[8] = event.getStatus();
            model.addRow(obj);
        }
    }
    //sự kiện khi chọn filter

    private void filterEvent() {
        String selectedStatus = (String) jcbFilter.getSelectedItem();
        List<CharityEvent> filter;

        if (selectedStatus.equalsIgnoreCase("Tất cả")) {
            filter = events;
        } else {
            filter = events.stream()
                    .filter(e -> e.getStatus().equalsIgnoreCase(selectedStatus))
                    .collect(Collectors.toList());
        }
        updateTable(filter);
    }
}
