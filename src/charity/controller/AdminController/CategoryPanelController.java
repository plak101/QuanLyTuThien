package charity.controller.AdminController;

import charity.component.MapHelper;
import charity.model.Category;
import charity.service.CategoryService;
import charity.utils.MessageDialog;
import charity.view.Admin.CategoryDialog;
import charity.view.Admin.CategoryPanel;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CategoryPanelController {

    private CategoryPanel view;
    private CategoryService categoryService;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    List<Category> list;

    public CategoryPanelController(CategoryPanel view) {
        this.view = view;
        this.categoryService = new CategoryService();
        this.tableModel = view.getTableModel();
        this.txtSearch = view.getTxtSearch();
    }

    public void loadTable() {
        list = categoryService.getAllCategories();
        tableModel.setRowCount(0);
        Object[] obj = new Object[2];
        for (Category c : list) {
            obj[0] = c.getCategoryId();
            obj[1] = c.getCategoryName();
            tableModel.addRow(obj);
        }
    }

    public void onReset() {
        loadTable();
        txtSearch.setText("");
        view.setRowSorter();
    }

    public void onAdd() {
        CategoryDialog dialog = new CategoryDialog(null, "Thêm danh mục", "ADD");
        dialog.setVisible(true);
        loadTable();

    }

    public void onUpdate() {
        JTable table = view.getTable();
        int row = table.getSelectedRow();
        if (row < 0) {
            MessageDialog.showWarning("Vui lòng chọn mục cần sửa");
            return;
        }

        int id = (int) table.getValueAt(row, 0);
        Category category = categoryService.getCategoryById(id);

        CategoryDialog dialog = new CategoryDialog(null, "Sửa danh mục", "UPDATE", category);
        dialog.setVisible(true);
        loadTable();
    }

    public void onDelete() {
        JTable table = view.getTable();
        int row = table.getSelectedRow();
        if (row < 0) {
            MessageDialog.showWarning("Vui lòng chọn mục cần xóa");
            return;
        }

        int id = (int) table.getValueAt(row, 0);
        if (categoryService.deleteCategory(id)) {
            MessageDialog.showSuccess("Xóa danh mục thành công");
            //cap nhat maphelper
            MapHelper.refreshCategoryCache();

        }
        loadTable();
    }

    public void onSearch() {
        String text = view.getTxtSearch().getText();
        TableRowSorter<TableModel> sorter = view.getRowSorter();
        if (text.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

}
