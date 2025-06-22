package charity.controller.AdminController;

import charity.component.MapHelper;
import charity.model.Category;
import charity.model.User;
import charity.service.UserService;
import charity.utils.MessageDialog;
import charity.utils.PDFExporter;
import charity.view.Admin.CategoryDialog;
import charity.view.Admin.UserPanel;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class UserPanelController {

    private UserPanel view;
    private UserService service;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtSearch;
    private List<User> list;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public UserPanelController(UserPanel view) {
        this.view = view;
        this.service = new UserService();
        this.tableModel = view.getTableModel();
        this.txtSearch = view.getTxtSearch();
        this.table = view.getTable();
    }

    public void loadTable() {
        list = service.getAllUser();
        tableModel.setRowCount(0);
        int col = tableModel.getColumnCount();
        Object[] obj = new Object[col];
        for (User u : list) {
            obj[0] = u.getId();
            obj[1] = u.getAccountId();
            obj[2] = u.getName();
            obj[3] = u.getAddress();
            obj[4] = u.getPhone();
            obj[5] = sdf.format(u.getBirthday());
            obj[6] = u.getGender();
            tableModel.addRow(obj);
        }
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

    public void onReset() {
        loadTable();
        txtSearch.setText("");
    }

    public void onPrint() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn thư mục xuất file");
        fileChooser.setSelectedFile(new File("DanhSachNguoiQuyenGop.pdf"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Files PDF", "pdf");
        fileChooser.setFileFilter(filter);

        int select = fileChooser.showSaveDialog(null);
        if (select == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String path = selectedFile.getAbsolutePath();

            if (!path.toLowerCase().endsWith(".pdf")) {
                path += ".pdf";
            }

            PDFExporter exporter = new PDFExporter();
            exporter.exportUser(path, table);
        }
    }
}
