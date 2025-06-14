package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.CharityEventPanelController;
import charity.controller.AdminController.SelectEventDialogController;
import charity.model.Donation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class SelectEventDialog extends JDialog {

    private Dialog owner;
    private Donation donation;

    private JTextField txtSearch;
    private DefaultTableModel model;
    private JTable table;
    private GButton gbtSelect, gbtCancel;
    private SelectEventDialogController controller;
    private TableRowSorter<TableModel> sorter;

    public SelectEventDialog(Dialog owner, String title, boolean modal, Donation donation) {
        super(owner, title, modal);
        this.owner = owner;
        this.donation = donation;
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(new Dimension(700, 500));
        setResizable(false);
        setLocationRelativeTo(owner);

        initComponent();
        controller = new SelectEventDialogController(this);
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

        String[] columns = {"ID", "Tên sự kiện", "Loại", "Ngày bắt đầu", "Ngày kết thúc"};
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
        //mau cho su kien het han
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Color endDateColor = new Color(255, 204, 204);

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
                setHorizontalAlignment(CENTER);
                int endDateCol = 4;
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
        table.setRowSorter(sorter);
        
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        
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
        SelectEventDialog dialog = new SelectEventDialog(null, "test", true, donation);
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
