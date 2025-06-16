package charity.controller.UserController;

import charity.component.ClassTableModel;
import charity.component.GButton;
import charity.component.TableLoader;
import charity.model.CharityEvent;
import charity.model.User;
import charity.service.CharityEventService;
import charity.service.UserService;
import charity.utils.MessageDialog;
import charity.view.User.DonateDialog;
import charity.view.User.DonateJDialog;
import java.awt.CardLayout;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class EventPanelController {

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
    private User user;
    
    private ClassTableModel classTableModel = null;
    private CharityEventService eventService = null;
    private UserService userService;
    private TableRowSorter<TableModel> rowSorter = null;
    private JTable table = null;

    private int selectedEventId = -1;

    public EventPanelController(JFrame parent, int accountId, int userId, JTextField txtSearch, JRadioButton jrbtId, JRadioButton jrbtEvent, JRadioButton jrbtCategory, GButton gbtReset, GButton gbtDonate, JButton jbtActive, JButton jbtExpired, JTable table) {
        this.txtSearch = txtSearch;
        this.jrbtId = jrbtId;
        this.jrbtEvent = jrbtEvent;
        this.jrbtCategory = jrbtCategory;
        this.gbtReset = gbtReset;
        this.gbtDonate = gbtDonate;
        this.jbtActive = jbtActive;
        this.jbtExpired = jbtExpired;
        this.table = table;
        this.userId = userId;
        this.accountId = accountId;
        this.parent = parent;

        eventService = new CharityEventService();
        userService = new UserService();
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
        setHoverButtonEvent();
    }

    public void setHoverButtonEvent(){
        gbtDonate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtDonate.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtDonate.changeColor("#5dc1d3");
            }
        });
        gbtReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                gbtReset.changeColor("#2d99ae");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                gbtReset.changeColor("#5dc1d3");
            }
        });
    }

    public void showEventTable() {
        //setup event table
        List<CharityEvent> events = new ArrayList<>();

        if (!jbtActive.isEnabled()) events = eventService.getActiveEventList();
        else events = eventService.getExpiredEventList();

        DefaultTableModel model = classTableModel.getEventTable(events);
        table.setModel(model);

        //setup rowsorter
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        setupSearchFilter();
        
        designTable(table);
        setTableClickEvent();
    }
    
    private void setupSearchFilter() {
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filterTable(); 
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }
    
    private void filterTable() {
        String text = txtSearch.getText();
        if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            if (jrbtId.isSelected()) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 0));
            } else if (jrbtEvent.isSelected()) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 2));
            } else {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, 3));
            }
        }
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
                if (selectedEventId != -1) {//kiem tra có chọn sự kiện nào không
                    CharityEvent event = eventService.getEventById(selectedEventId);

                    if (event == null) {
                        JOptionPane.showMessageDialog(null, "Không tìm thấy sự kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    DonateJDialog dialog = new DonateJDialog(parent, true, selectedEventId, accountId, userId);
                    dialog.setVisible(true);
                }else{
                    MessageDialog.showPlain("Vui lòng chọn 1 sự kiện để quyên góp");
                }
            }

        });
    }

    //click vao bang
    public void setTableClickEvent() {
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedEventId = -1;
                    if (!e.getValueIsAdjusting()) {//dam bao dong khong bi chon nhieu lan
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            selectedEventId = (int) table.getValueAt(selectedRow, 0);
                            gbtDonate.setEnabled(true);
                        }
                    }
            }

        });
    }
    
    public void reloadData(){
        reset();
        user = userService.getUserByAccountId(accountId);
        if (user == null){
            System.err.print("Không tìm thấy user");
            return;
        }
        userId= user.getId();
    }
}
