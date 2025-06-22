package charity.controller.UserController;

import charity.component.ClassTableModel;
import charity.component.ColorCustom;
import charity.component.GButton;
import charity.model.Donation;
import charity.service.DonationService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
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
    private JPanel jpnTable;

    private ClassTableModel classTableModel = null;
    private DonationService donationService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable table = null;

    public DonationListController(JTextField txtSearch, JRadioButton jrbtId, JRadioButton jrbtEvent, JRadioButton jrbtUser, GButton gbtReset, JTable table, GButton gbtPrint) {
        this.txtSearch = txtSearch;
        this.jrbtId = jrbtId;
        this.jrbtEvent = jrbtEvent;
        this.jrbtUser = jrbtUser;
        this.gbtReset = gbtReset;
        this.table = table;
        this.gbtPrint = gbtPrint;
        this.classTableModel = new ClassTableModel();
        this.donationService = new DonationService();
        setHoverButtonEvent();
    }

    public void setDonationListTable() {
        List<Donation> donations = donationService.getAllDonation();
        DefaultTableModel model = classTableModel.getDonationTable(donations);
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
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
                setDonationListTable();
            }
        });
        gbtPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    MessageFormat header = new MessageFormat("DANH SÁCH QUYÊN GÓP");
                    MessageFormat footer = new MessageFormat("Trang {0}");

                    try {
                        if (table.print(JTable.PrintMode.FIT_WIDTH, header, footer))
                            JOptionPane.showMessageDialog(null, "In thành công!");
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
    }

    public void reloadData() {
        setDonationListTable();
    }
}
