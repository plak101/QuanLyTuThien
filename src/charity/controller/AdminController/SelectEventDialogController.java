package charity.controller.AdminController;

import charity.component.MapHelper;
import charity.model.CharityEvent;
import charity.service.CharityEventService;
import charity.utils.MessageDialog;
import charity.view.Admin.DonationDialog;
import charity.view.Admin.SelectEventDialog;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author phaml
 */
public class SelectEventDialogController {

    private SelectEventDialog view;
    private CharityEventService service = new CharityEventService();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public SelectEventDialogController(SelectEventDialog dialog) {
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
        List<CharityEvent> events = service.getEventList();
        view.getModel().setRowCount(0);
        Object[] obj = new Object[5];
        for (CharityEvent e : events) {
            obj[0] = e.getId();
            obj[1] = e.getName();
            obj[2] = MapHelper.getCategoryName(e.getCategoryId());
            obj[3] = sdf.format(e.getDateBegin());
            obj[4] = sdf.format(e.getDateEnd());
            view.getModel().addRow(obj);
        }
    }
    
    public void onSelect(){
        int row = view.getTable().getSelectedRow();
        if (row<0){
            MessageDialog.showError("Vui lòng chọn một sự kiện");
            return;
        }
        int id = (int) view.getTable().getValueAt(row, 0);
        view.getDonation().setEventId(id);
        ((DonationDialog)view.getOwner()).getTxtEvent().setText(MapHelper.getEventName(id));
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
