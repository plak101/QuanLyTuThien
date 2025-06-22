package charity.controller.AdminController;

import charity.component.GButton;
import charity.component.MapHelper;
import charity.model.Category;
import charity.service.CategoryService;
import charity.utils.MessageDialog;
import charity.utils.ScannerUtils;
import charity.view.Admin.CategoryDialog;

public class CategoryDialogController {

    private CategoryDialog dialog;
    private CategoryService service;

    private String action;
    private String name, oldname;
    private Category category;
    private GButton btnSave, btnCancel;

    public CategoryDialogController(CategoryDialog dialog) {
        this.dialog = dialog;
        this.service = new CategoryService();
        this.btnCancel = dialog.getBtnCancel();
        this.btnSave = dialog.getBtnSave();
        this.action = dialog.getAction();

        if (action.equals("UPDATE")) {
            category = dialog.getCategory();
            oldname = category.getCategoryName();
        }

        initEvent();
    }

    public void initEvent() {
        btnSave.addActionListener(e -> onSave());
        btnCancel.addActionListener(e -> onCancel());
    }

    public void onSave() {
        name = dialog.getCategoryName();

        //kiem tra nhan nut luu khi khong thay doi ten
        if (action.equals("UPDATE") && oldname.equals(name)) {
            MessageDialog.showSuccess("Sửa danh mục thành công");
            dialog.dispose();
            return ;
        }
        
        if (valid()) {
            if (action.equals("ADD")) {
                category = new Category(name);
                if (service.addCategory(category)) {
                    dialog.dispose();
                    MessageDialog.showSuccess("Thêm danh mục thành công");
                    //cap nhat maphelper
                    MapHelper.refreshCategoryCache();
                } else {
                    MessageDialog.showError("Thêm danh mục thất bại");
                }
            } else {//action="UPDATE"

                category.setCategoryName(name);
                if (service.updateCategory(category)) {
                    dialog.dispose();
                    MessageDialog.showSuccess("Sửa danh mục thành công");
                    //cap nhat maphelper
                    MapHelper.refreshCategoryCache();
                } else {
                    MessageDialog.showError("Sửa danh mục thất bại");
                }
            }
        }
    }

    public void onCancel() {
        dialog.dispose();
    }

    private boolean valid() {
        if (ScannerUtils.isEmpty(name, "Tên danh mục không được để trống")) {
            return false;
        }

        if (service.isCategoryNameExits(name)) {
            MessageDialog.showError("Tên danh mục đã tồn tại");
            return false;
        }

        return true;
    }
}
