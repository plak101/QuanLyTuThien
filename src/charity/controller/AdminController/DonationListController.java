package charity.controller.AdminController;

import charity.component.ClassTableModel;
import charity.component.ColorCustom;
import charity.component.GButton;
import charity.component.MapHelper;
import charity.model.Donation;
import charity.service.DonationService;
import charity.utils.MessageDialog;
import charity.utils.ScannerUtils;
import charity.view.Admin.DonationDialog;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class DonationListController {

    private JTextField txtSearch;
    private JRadioButton jrbtId;
    private JRadioButton jrbtEvent;
    private JRadioButton jrbtUser;
    private GButton gbtReset;
    private GButton gbtPrint;
    private GButton gbtAdd, gbtUpdate, gbtDelete;
    private JPanel jpnTable;
    private JDateChooser jdcStartDate, jdcEndDate;
    private ClassTableModel classTableModel = null;
    private DonationService donationService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable table = null;
    private DefaultTableModel model;
    List<Donation> donations;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public DonationListController(JTextField txtSearch, JRadioButton jrbtId, JRadioButton jrbtEvent, JRadioButton jrbtUser, GButton gbtReset, JTable table, GButton gbtPrint, GButton gbtAdd, GButton gbtUpdate, GButton gbtDelete, JDateChooser jdcStartDate, JDateChooser jdcEndDate) {
        this.txtSearch = txtSearch;
        this.jrbtId = jrbtId;
        this.jrbtEvent = jrbtEvent;
        this.jrbtUser = jrbtUser;
        this.gbtReset = gbtReset;
        this.table = table;
        this.gbtPrint = gbtPrint;
        this.gbtAdd = gbtAdd;
        this.gbtUpdate = gbtUpdate;
        this.gbtDelete = gbtDelete;
        this.jdcStartDate = jdcStartDate;
        this.jdcEndDate = jdcEndDate;
        this.classTableModel = new ClassTableModel();
        this.donationService = new DonationService();
        setHoverButtonEvent();
    }

    public void setDonationListTable() {
        donations = donationService.getAllDonation();
        model = classTableModel.getDonationTable(donations);
        table.setModel(model);

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            //"(?i)" khong phan biet chu hoa chu thuong
            //khi nhap vao txtSearch
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    if (jrbtId.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (jrbtUser.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    }

                }
            }

            //khi xoa noi dung cua txtSearch
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    if (jrbtId.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (jrbtUser.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 1));
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
                    }
                }
            }

            //khi co thay doi thuoc tinh van ban
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        designTable(table);
    }

    public void designTable(JTable table) {

        //table header
        table.getTableHeader().setBackground(Color.decode("#B4EBE6"));
        table.getTableHeader().setBorder(null);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getWidth(), 40));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        //table body
        table.setRowHeight(40);
        table.setShowVerticalLines(false);
        table.setShowGrid(false);
        table.setShowHorizontalLines(true);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

//        table.setGridColor(new Color(230, 230, 230));
//        //size column
        table.getColumnModel().getColumn(0).setMaxWidth(400);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);

        table.getColumnModel().getColumn(1).setMaxWidth(500);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
//
//        table.getColumnModel().getColumn(5).setMaxWidth(500);
//        table.getColumnModel().getColumn(5).setPreferredWidth(90);

//        chu can giua
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Căn giữa chữ

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //show
        table.validate();
        table.repaint();
    }

    public void setEvent() {
        gbtReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadData();
            }
        });
        gbtPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    MessageFormat header = new MessageFormat("DANH SÁCH QUYÊN GÓP");
                    MessageFormat footer = new MessageFormat("Trang {0}");

                    try {
                        if (table.print(JTable.PrintMode.FIT_WIDTH, header, footer)) {
                            JOptionPane.showMessageDialog(null, "In thành công!");
                        }
                    } catch (PrinterException ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi khi in: " + ex.getMessage(),
                                "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // If any error occurs, refresh the table
                    setDonationListTable();
                }
            }
        });

        gbtAdd.addActionListener(e -> onAdd());
        gbtUpdate.addActionListener(e -> onUpdate());
        gbtDelete.addActionListener(e -> onDelete());
        jdcStartDate.addPropertyChangeListener("date", e->filterbyDate());
        jdcEndDate.addPropertyChangeListener("date", e->filterbyDate());
    }

    public void setHoverButtonEvent() {

        gbtPrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtPrint.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtPrint.changeColor("#5dc1d3");
            }
        });
        gbtReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtReset.setColor(ColorCustom.colorBtnReset());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtReset.setColor(ColorCustom.colorBtnResetHover());
            }
        });

        gbtAdd.setHover(ColorCustom.colorBtnAdd(), ColorCustom.colorBtnAddHover());
        gbtUpdate.setHover(ColorCustom.colorBtnUpdate(), ColorCustom.colorBtnUpdateHover());
        gbtDelete.setHover(ColorCustom.colorBtnDelete(), ColorCustom.colorBtnDeleteHover());
    }

    private void onAdd() {
        DonationDialog dialog = new DonationDialog(null, "Thêm quyên góp", true, "ADD");
        dialog.setVisible(true);
        setDonationListTable();

    }

    private void onUpdate() {
        int row = table.getSelectedRow();
        if (row < 0) {
            MessageDialog.showError("Vui lòng chọn quyên góp cần sửa");
            return;
        }

        int id = (int) table.getValueAt(row, 0);

        Donation donation = donationService.getDonationById(id);
        if (donation == null) {
            MessageDialog.showError("Không tồn tại quyên góp!");
            return;
        }
        DonationDialog dialog = new DonationDialog(null, "Sửa quyên góp", true, "UPDATE", donation);
        dialog.setVisible(true);
        setDonationListTable();

    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row < 0) {
            MessageDialog.showError("Vui lòng chọn quyên góp cần xóa");
            return;
        }

        int id = (int) table.getValueAt(row, 0);

        boolean option = MessageDialog.showConfirmDialog("Bạn có muốn xóa quyên góp không?", "Xác nhận xóa");
        if (!option) {
            return;
        }
        if (donationService.deleteDonation(id)) {
            MessageDialog.showSuccess("Xóa quyên góp thành công");
        } else {
            MessageDialog.showError("Xóa quyên góp thất bại");
        }
        setDonationListTable();
    }

    public void reloadData() {
        setDonationListTable();
        txtSearch.setText("");
        jdcStartDate.setDate(null);
        jdcEndDate.setDate(null);
    }

    public void updateTable(List<Donation> donations) {
        model.setRowCount(0);
        Object[] obj = new Object[6];
        for (Donation d : donations) {
            obj[0] = d.getId();
            obj[1] = MapHelper.getUserName(d.getUserId());
            obj[2] = MapHelper.getEventName(d.getEventId());
            obj[3] = String.format("%,d", d.getAmount());
            obj[4] = sdf.format(d.getDonationDate());
            obj[5] = d.getDescription();
            model.addRow(obj);
        }
    }

    public void filterbyDate() {
        Date start = jdcStartDate.getDate();
        Date end = jdcEndDate.getDate();

        if (start == null && end == null) {
            return;
        }
        
        //tao bien final
        final Date finalStartDate = (start!=null)? start:new Date(0);
        final Date finalEndDate = (end!=null)?end: new Date();
        
        
        if (finalStartDate.after(finalEndDate)) {
            MessageDialog.showError("Ngày bắt đầu không được lớn hơn ngày kết thúc");
            return;
        }
        List<Donation> filter = donations.stream()
                .filter(e -> !e.getDonationDate().before(finalStartDate) && !e.getDonationDate().after(finalEndDate))
                .collect(Collectors.toList());
        updateTable(filter);
    }
}
