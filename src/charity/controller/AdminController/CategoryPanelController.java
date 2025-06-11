package charity.controller.AdminController;

import charity.model.Category;
import charity.service.CategoryService;
import charity.view.Admin.CategoryPanel;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CategoryPanelController {
    private CategoryPanel view;
    private CategoryService categoryService;
    private DefaultTableModel tableModel;
    
    public CategoryPanelController(CategoryPanel view) {
        this.view = view;
        this.categoryService = new CategoryService();
        this.tableModel = view.getTableModel();
    }
    
    public void loadTable(){
        List<Category> list = categoryService.getAllCategories();
        tableModel.setRowCount(0);
        Object[] obj = new Object[2];
        for (Category c: list){
            obj[0] = c.getCategoryId();
            obj[1] = c.getCategoryName();
            tableModel.addRow(obj);
        }
    }

}
