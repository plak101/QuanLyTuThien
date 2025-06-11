package charity.view.Admin;

import charity.component.ColorCustom;
import charity.component.FontCustom;
import charity.component.GButton;
import charity.controller.AdminController.CategoryPanelController;
import charity.model.Category;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CategoryPanel extends JPanel {

    private CategoryPanelController controller;
    private DefaultTableModel tableModel;

    // Components
    private JTable tbCategory;
    private JScrollPane scroll;
    private JTextField txtSearch;
    private JTextField txtCategoryId;
    private JTextField txtCategoryName;
    private JCheckBox chkStatus;
    private GButton btnAdd;
    private GButton btnUpdate;
    private GButton btnDelete;
    private GButton btnClear;

    String[] columns = {"ID", "Tên danh mục"};
    
    private static final Color PRIMARY_COLOR = new Color(41, 128, 185);
    private static final Color BG_COLOR = new Color(245, 245, 245);
    private static final Color WHITE = Color.WHITE;

    public CategoryPanel() {
        initComponents();
        controller = new CategoryPanelController(this);
        initEvent();
    }

    private void initComponents() {
        this.setLayout(new BorderLayout(15, 15));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.setBackground(WHITE);
        
        this.add(createTopPanel(), BorderLayout.NORTH);
        this.add(createTablePanel(), BorderLayout.CENTER);
    }
    
    private void initEvent(){
        controller.loadTable();
    }
    // panel tôp
    private JPanel createTopPanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        // search (left)
        JPanel pnLeft = new JPanel(new FlowLayout(FlowLayout.LEFT,10,5));
        pnLeft.setOpaque(false);
        
        JLabel lbSearch = new JLabel("Tìm kiếm:");
        lbSearch.setFont(FontCustom.Arial13B());
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(200,25));
        pnLeft.add(lbSearch);
        txtSearch = paddingTxt(txtSearch);//padding ben trong
        pnLeft.add(txtSearch);
        
        //button (right)
        JPanel pnRight = new JPanel(new FlowLayout(FlowLayout.RIGHT,10,0));
        pnRight.setOpaque(false);
        btnAdd = createButton("Thêm", ColorCustom.colorBtnAdd());
        btnUpdate = createButton("Sửa", ColorCustom.colorBtnUpdate());
        btnDelete = createButton("Xóa", ColorCustom.colorBtnDelete());
        btnClear = createButton("Làm mới", ColorCustom.colorBtnReset());

        
        pnRight.add(btnAdd);
        pnRight.add(btnUpdate);
        pnRight.add(btnDelete);
        pnRight.add(btnClear);
        
        panel.add(pnLeft, BorderLayout.WEST);
        panel.add(pnRight, BorderLayout.EAST);
        return panel;
    }
    //center panel
    private JPanel createTablePanel(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        tableModel = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        
        tbCategory = new JTable(tableModel);
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        tbCategory.setDefaultRenderer(Object.class, centerRender);
        //can giua
        for (int i=0; i<columns.length; i++ ){
            tbCategory.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }
        scroll = new JScrollPane(tbCategory);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }
    private JPanel padding5(){
        JPanel p = new JPanel();
        p.setPreferredSize(new Dimension(5,0));
        return p;
    }
    
    //padding ben trong textfield
    private JTextField paddingTxt(JTextField txt){
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.gray),
                BorderFactory.createEmptyBorder(2,8,2,8)
        ));
        return txt;
    }
    //dinh dang nut
    private GButton createButton(String text, Color color){
        GButton btn = new GButton(text, color);
        btn.setForeground(WHITE);
        btn.setPreferredSize(new Dimension(100,30));
        btn.setFont(FontCustom.Arial13B());
        return btn;
    }
    
    
    
    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public void setTableModel(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
}
