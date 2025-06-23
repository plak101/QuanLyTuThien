package charity.controller.AdminController;

import charity.component.MapHelper;
import charity.model.User;
import charity.service.UserService;
import charity.utils.MessageDialog;
import charity.view.Admin.DonationDialog;
import charity.view.Admin.SelectUserDialog;
import java.util.List;
import java.util.ArrayList;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class SelectUserDialogController {

    private SelectUserDialog view;
    private UserService service = new UserService();

    public SelectUserDialogController(SelectUserDialog dialog) {
        this.view = dialog;
        loadTable();
        initEvent();
    }

    public void initEvent(){
        view.getGbtCancel().addActionListener(e -> onCancel());
        view.getGbtSelect().addActionListener(e -> onSelect());
        view.getTxtSearch().getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                onSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                onSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

        });
    }
    private void loadTable() {
        List<User> users = service.getUserRoleUser();
        view.getModel().setRowCount(0);
        Object[] obj = new Object[6];
        for (User u : users) {
            obj[0] = u.getId();
            obj[1] = u.getName();
            obj[2] = u.getBirthday();
            obj[3] = u.getAddress();
            obj[4] = u.getPhone();
            obj[5] = u.getGender();
            view.getModel().addRow(obj);
        }
    }
    
    public void onSelect(){
        int row = view.getTable().getSelectedRow();
        if (row<0){
            MessageDialog.showError("Vui lòng chọn một người");
            return;
        }
        int id = (int) view.getTable().getValueAt(row, 0);
        view.getDonation().setUserId(id);
        ((DonationDialog)view.getOwner()).getTxtDonor().setText(MapHelper.getUserName(id));
        view.dispose();
        view.getOwner().setVisible(true);
        
    }
    public void onCancel(){
        view.dispose();
        view.getOwner().setVisible(true);
    }
    public void onSearch(){
        String text = view.getTxtSearch().getText().trim();
        TableRowSorter<TableModel> sorter = view.getSorter();
        if (text.isEmpty()){
            sorter.setRowFilter(null);
        }else{
            sorter.setRowFilter(RowFilter.regexFilter("(?i)"+text));
        }
    }
}
