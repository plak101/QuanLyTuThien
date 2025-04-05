package charity.UserController;

import charity.controller.ClassTableModel;
import charity.model.Donation;
import charity.service.DonationService;
import charity.service.UserService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MyDonationController {

    private int userId;
    private JTextField txtSearch;
    private JRadioButton jrbtId;
    private JRadioButton jrbtEvent;
    private JRadioButton jrbtUser;
    private JButton jbtReset;
    private JButton jbtPrint;
    private JPanel jpnTable;

    private ClassTableModel classTableModel = null;
    private DonationService donationService = null;
    private UserService userService = null;

    private TableRowSorter<TableModel> rowSorter = null;

    public MyDonationController(int userId, JTextField txtSearch, JRadioButton jrbtId, JRadioButton jrbtEvent, JRadioButton jrbtUser, JButton jbtReset, JButton jbtPrint, JPanel tablePanel) {
        this.userId = userId;
        this.txtSearch = txtSearch;
        this.jrbtId = jrbtId;
        this.jrbtEvent = jrbtEvent;
        this.jrbtUser = jrbtUser;
        this.jbtReset = jbtReset;
        this.jbtPrint = jbtPrint;
        this.jpnTable = tablePanel;

        this.classTableModel = new ClassTableModel();
        this.donationService = new DonationService();
        this.userService = new UserService();
    }

    public void setDonationListTable() {
        List<Donation> donations = donationService.getDonationByUserId(userId);
        DefaultTableModel model = classTableModel.getDonationTable(donations);
        JTable donationTable = new JTable(model);

        rowSorter = new TableRowSorter<>(donationTable.getModel());
        donationTable.setRowSorter(rowSorter);
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

        designTable(donationTable);

//        -------------------------------------------
//        Kiểm tra trạng thái user, thiet dat nut jbtPrint
//      ---------------------------------------------
        if (userService.getUserNameById(userId) == null) {
            JLabel text = new JLabel("Bạn chưa xác minh thông tin", SwingConstants.CENTER);
            text.setFont(new Font("Segoe UI", Font.BOLD, 30));   
            text.setForeground(Color.LIGHT_GRAY);
            jpnTable.removeAll();
            jpnTable.setLayout(new CardLayout());
            jpnTable.add(text);
            jpnTable.revalidate();
            jpnTable.repaint();
            jbtPrint.setEnabled(false);
        } else if (model.getRowCount() == 0) {
            JLabel text = new JLabel("Danh sách trống", SwingConstants.CENTER);
            text.setFont(new Font("Segoe UI", Font.BOLD, 30));
            text.setForeground(Color.LIGHT_GRAY);
            jpnTable.removeAll();
            jpnTable.setLayout(new CardLayout());
            jpnTable.add(text);
            jpnTable.revalidate();
            jpnTable.repaint();
            jbtPrint.setEnabled(false);
        } else {
            //hien thi ra jpnTable
            JScrollPane scroll = new JScrollPane(donationTable);
            scroll.setViewportView(donationTable);
            scroll.setPreferredSize(new Dimension(800, 400));
            jpnTable.removeAll();
            jpnTable.setLayout(new CardLayout());
            jpnTable.add(scroll);
            jpnTable.revalidate();
            jpnTable.repaint();
            jbtPrint.setEnabled(true);
        }
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
        jbtReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDonationListTable();
                txtSearch.setText("");
            }
        });
    }

}
