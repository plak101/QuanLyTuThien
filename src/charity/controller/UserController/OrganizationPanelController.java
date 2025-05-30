package charity.controller.UserController;

import charity.component.GButton;
import charity.component.ClassTableModel;
import charity.model.Organization;
import charity.service.OrganizationService;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
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

public class OrganizationPanelController {

    private JTextField txtSearch;
    private GButton gbtReset;
    private JPanel jpnTable;

    private OrganizationService organizationService = new OrganizationService();
    private ClassTableModel classTableModel = new ClassTableModel();
    private TableRowSorter<TableModel> rowSorter = null;
    private JTable organizationTable = null;

    public OrganizationPanelController(JTextField txtSearch, GButton gbtReset, JPanel jpnTable) {
        this.txtSearch = txtSearch;
        this.gbtReset = gbtReset;
        this.jpnTable = jpnTable;
        setHoverButtonEvent();
    }

    public void setOrganizationTable() {
        List<Organization> organizations = organizationService.getAllOrganization();
        DefaultTableModel model = classTableModel.getOrganizationTable(organizations);
        organizationTable = new JTable(model);

        rowSorter = new TableRowSorter<>(organizationTable.getModel());
        organizationTable.setRowSorter(rowSorter);
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

        designTable(organizationTable);

        //hien thi ra jpnTable
        JScrollPane scroll = new JScrollPane(organizationTable);
        scroll.setViewportView(organizationTable);
        organizationTable.setFillsViewportHeight(true);
        organizationTable.setBackground(Color.white);
        scroll.getViewport().setBackground(Color.white);
        scroll.setPreferredSize(new Dimension(jpnTable.WIDTH, 400));
        jpnTable.setBackground(Color.white);
        
        jpnTable.removeAll();
        jpnTable.setLayout(new CardLayout());
        jpnTable.add(scroll);
        jpnTable.revalidate();
        jpnTable.repaint();
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
        table.getColumnModel().getColumn(1).setPreferredWidth(200);

        table.getColumnModel().getColumn(2).setMaxWidth(500);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);

        table.getColumnModel().getColumn(4).setMaxWidth(500);
        table.getColumnModel().getColumn(4).setPreferredWidth(250);

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
                setOrganizationTable();
            }
        });
    }
    
    public void setHoverButtonEvent(){

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
    
    public void reloadData(){
        setOrganizationTable();
    }
}
