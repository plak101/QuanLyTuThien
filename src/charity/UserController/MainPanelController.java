package charity.UserController;

import charity.component.GButton;
import charity.controller.ClassTableModel;
import charity.model.CharityEvent;
import charity.service.CharityEventService;
import charity.viewUser.DonateDialog;
import charity.viewUser.DonateJDialog;
import java.awt.CardLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class MainPanelController {

    private int userId, accountId;
    private JFrame parent;
    private JTextField txtSearch;
    private JRadioButton jrbtId;
    private JRadioButton jrbtEvent;
    private JRadioButton jrbtCategory;
    private GButton gbtReset;
    private GButton gbtDonate;
    private JButton jbtActive;
    private JButton jbtExpired;
    private JPanel jpnTable;

    private ClassTableModel classTableModel = null;
    private CharityEventService eventService = null;

    private TableRowSorter<TableModel> rowSorter = null;
    private JTable eventTable = null;

    private int selectedEventId = -1;

    public MainPanelController(JFrame parent, int accountId, int userId, JTextField txtSearch, JRadioButton jrbtId, JRadioButton jrbtEvent, JRadioButton jrbtCategory, GButton gbtReset, GButton gbtDonate, JButton jbtActive, JButton jbtExpired, JPanel jpnTable) {
        this.txtSearch = txtSearch;
        this.jrbtId = jrbtId;
        this.jrbtEvent = jrbtEvent;
        this.jrbtCategory = jrbtCategory;
        this.gbtReset = gbtReset;
        this.gbtDonate = gbtDonate;
        this.jbtActive = jbtActive;
        this.jbtExpired = jbtExpired;
        this.jpnTable = jpnTable;
        this.userId = userId;
        this.accountId = accountId;
        this.parent = parent;

        eventService = new CharityEventService();
        classTableModel = new ClassTableModel();
        loadButton();
    }

    public void loadButton() {
        //set enabled
//        gbtDonate.setEnabled(false);
        jbtActive.setEnabled(false);
        jbtExpired.setEnabled(true);

    }

    public void loadJbtEvent() {
        setJbtAcctiveEvent();
        setJbtResetEvent();
        setJbtExpired();
        setJbtDonate();
    }

    public void showEventTable() {
        //setup event table
        List<CharityEvent> events = new ArrayList<>();
        if (!jbtActive.isEnabled()) {
            events = eventService.getActiveEventList();
        } else {
            events = eventService.getExpiredEventList();
        }
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
                    if (jrbtId.isSelected()) {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
                    } else if (jrbtEvent.isSelected()) {
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
                    } else if (jrbtEvent.isSelected()) {
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

    //lam moi bang
    public void reset() {
        loadButton();
        txtSearch.setText("");
        showEventTable();
        selectedEventId = -1;
    }

    //click vao gbtReset
    public void setJbtResetEvent() {
        gbtReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSearch.setText("");
                showEventTable();
                selectedEventId = -1;

            }
        });
    }

    //click vao nut dang hoat dong
    public void setJbtAcctiveEvent() {
        jbtActive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }

        });
    }

    //click vao nut het han
    public void setJbtExpired() {
        jbtExpired.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jbtActive.setEnabled(true);
                jbtExpired.setEnabled(false);

                txtSearch.setText("");
                showEventTable();
                selectedEventId = -1;
            }
        });
    }

    //click vao nut quyen gop
    public void setJbtDonate() {
        gbtDonate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedEventId != -1) {
                    CharityEvent event = eventService.getEventById(selectedEventId);

                    if (event == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sự kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }

//                    DonateJDialog dialog = new DonateJDialog(parent, true, event,accountId, userId);
                    DonateJDialog dialog = new DonateJDialog(parent, true, selectedEventId, accountId, userId);
                    dialog.setVisible(true);
                }
            }

        });
    }

    //click vao bang
    public void setTableClickEvent() {
        eventTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedEventId = -1;
                if (!jbtActive.isEnabled()) {
                    if (!e.getValueIsAdjusting()) {//dam bao dong khong bi chon nhieu lan
                        int selectedRow = eventTable.getSelectedRow();
                        if (selectedRow != -1) {
                            selectedEventId = (int) eventTable.getValueAt(selectedRow, 0);
                            gbtDonate.setEnabled(true);
                        }
                    }
                }
            }

        });
    }
}
