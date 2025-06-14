package charity.view.Admin;

import charity.component.ClassTableModel;
import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.SelectUserDialogController;
import charity.model.Donation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class SelectUserDialog extends JDialog {

    private Dialog owner;
    private Donation donation;

    private JTextField txtSearch;
    private DefaultTableModel model;
    private JTable table;
    private GButton gbtSelect, gbtCancel;
    private SelectUserDialogController controller;
    private TableRowSorter<TableModel> sorter;

    public SelectUserDialog(Dialog owner, String title, boolean modal, Donation donation) {
        super(owner, title, modal);
        this.owner = owner;
        this.donation = donation;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(750, 500));
        setResizable(false);
        setLocationRelativeTo(owner);

        initComponent();
        controller = new SelectUserDialogController(this);
    }

    private void initComponent() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(createTopPanel(), BorderLayout.NORTH);
        mainPanel.add(createTablePanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);

        add(mainPanel);

    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        panel.setOpaque(false);
        JLabel label = new JLabel("Tìm kiếm");
        label.setFont(FontCustom.Arial13());

        txtSearch = new JTextField(20);
        panel.add(label);
        panel.add(txtSearch);
        return panel;
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        String[] columns = {"ID", "Họ tên", "Ngày sinh", "Địa chỉ", "Số điện thoại", "Giới tính"};
        model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sorter = new TableRowSorter<>(model);
        sorter.setComparator(0, (o1,o2) ->{
            Integer i1= Integer.parseInt(o1.toString());
            Integer i2= Integer.parseInt(o2.toString());
            return i1.compareTo(i2);
        });
        
        table = new JTable(model);
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.setRowSorter(sorter);
        
        table.getColumnModel().getColumn(1).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        
        JScrollPane scroll = new JScrollPane(table);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel.setOpaque(false);

        gbtSelect = new GButton("Chọn", ColorCustom.colorBtnAdd());
        gbtSelect.setHover(ColorCustom.colorBtnAdd(), ColorCustom.colorBtnAddHover());
        gbtSelect.setForeground(Color.white);
        gbtCancel = new GButton("Hủy", ColorCustom.colorBtnDelete());
        gbtCancel.setHover(ColorCustom.colorBtnDelete(), ColorCustom.colorBtnDeleteHover());
        gbtCancel.setForeground(Color.white);

        panel.add(gbtSelect);
        panel.add(gbtCancel);
        return panel;
    }

    public static void main(String[] args) {
        Donation donation = null;
        SelectUserDialog dialog = new SelectUserDialog(null, "test", true, donation);
        dialog.setVisible(true);
    }

    public Dialog getOwner() {
        return owner;
    }

    public Donation getDonation() {
        return donation;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public JTable getTable() {
        return table;
    }

    public GButton getGbtSelect() {
        return gbtSelect;
    }

    public GButton getGbtCancel() {
        return gbtCancel;
    }

    public TableRowSorter<TableModel> getSorter() {
        return sorter;
    }



    public JTextField getTxtSearch() {
        return txtSearch;
    }

}
