package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.UserPanelController;
import charity.model.Category;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class UserPanel extends JPanel {

    private UserPanelController controller;
    private DefaultTableModel tableModel;
    private TableRowSorter<TableModel> rowSorter;
    // Components
    private JTable table;
    private JScrollPane scroll;
    private JTextField txtSearch;
    private GButton btnClear, btnPrint;

    String[] columns = {"Id", "ID tài khoản", "Họ tên", "Địa chỉ", "Số điện thoại", "Ngày sinh", "Giới tính"};

    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color WHITE = Color.WHITE;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public UserPanel() {
        initComponents();
        controller = new UserPanelController(this);
        initEvent();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.setBackground(WHITE);

        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createTablePanel(), BorderLayout.CENTER);
    }

    private void initEvent() {
        controller.loadTable();
        btnClear.addActionListener(e -> controller.onReset());
        btnPrint.addActionListener(e -> controller.onPrint());
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                controller.onSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                controller.onSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        btnHover();
    }

    // panel tôp
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        // search (left)
        JPanel pnLeft = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnLeft.setOpaque(false);

        JLabel lbSearch = new JLabel("Tìm kiếm:");
        lbSearch.setFont(FontCustom.Arial13B());
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(200, 25));
        pnLeft.add(lbSearch);
        txtSearch = paddingTxt(txtSearch);//padding ben trong
        pnLeft.add(txtSearch);

        //button (right)
        JPanel pnRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnRight.setOpaque(false);
        btnClear = createButton("Làm mới", ColorCustom.colorBtnReset());
        btnPrint = createButton("In", ColorCustom.defaultBtn());

        pnRight.add(btnPrint);
        pnRight.add(btnClear);

        panel.add(pnLeft, BorderLayout.WEST);
        panel.add(pnRight, BorderLayout.EAST);
        return panel;
    }

    //center panel
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        //sap xep
        rowSorter = new TableRowSorter<>(tableModel);
        rowSorter.setComparator(0, Comparator.comparingInt(o -> ((Number) o).intValue()));
        rowSorter.setComparator(1, Comparator.comparingInt(o -> ((Number) o).intValue()));
        rowSorter.setComparator(5, (o1, o2) -> {
            try {
                Date d1 = sdf.parse((String) o1);
                Date d2 = sdf.parse((String) o2);
                return d1.compareTo(d2);
            } catch (ParseException ex) {
                Logger.getLogger(UserPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            return 0;
        });
        table.setRowSorter(rowSorter);

        //design
        table.getColumnModel().getColumn(0).setMaxWidth(100);
        table.getColumnModel().getColumn(1).setMaxWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        
        
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        table.setDefaultRenderer(Object.class, centerRender);
        //can giua
        for (int i = 0; i < columns.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

        scroll = new JScrollPane(table);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel padding5() {
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(5, 0));
        return p;
    }

    //padding ben trong textfield
    private JTextField paddingTxt(JTextField txt) {
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray),
                BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        return txt;
    }

    //dinh dang nut
    private GButton createButton(String text, Color color) {
        GButton btn = new GButton(text, color);
        btn.setForeground(WHITE);
        btn.setPreferredSize(new Dimension(100, 30));
        btn.setFont(FontCustom.Arial13B());
        return btn;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(JTextField txtSearch) {
        this.txtSearch = txtSearch;
    }

    public void setRowSorter() {
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(rowSorter);
        rowSorter.setRowFilter(null);
    }

    public JTable getTable() {
        return table;
    }

    public TableRowSorter<TableModel> getRowSorter() {
        return rowSorter;
    }

    private void btnHover() {
        btnPrint.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnPrint.setColor(ColorCustom.defaultBtnHover());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnPrint.setColor(ColorCustom.defaultBtn());
            }
        });

        btnClear.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnClear.setColor(ColorCustom.colorBtnResetHover());
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnClear.setColor(ColorCustom.colorBtnReset());
            }
        });
    }

}
