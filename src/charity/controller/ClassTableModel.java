package charity.controller;

import charity.formatData.IFormatData;
import charity.model.CharityEvent;
import charity.repository.CharityEventRepository;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClassTableModel implements IFormatData {

    private CharityEventRepository eventDAO = new CharityEventRepository();
    private List<CharityEvent> events = null;
    private String[] listColumn = {"ID", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Mô tả",};
    
    public DefaultTableModel getEventTable() {
        events = eventDAO.getEventList();
        int columnCount = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel(listColumn, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CharityEvent event : events) {
            Object[] obj = new Object[columnCount];
            obj[0] = event.getId();
            obj[1] = event.getName();
            obj[2] = event.getCategory();
            obj[3] = moneyFormat.format(event.getTargetAmount());
            obj[4] = moneyFormat.format(event.getCurrentAmount());
            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
            obj[6] = dateFormat.format(event.getDateEnd());
            obj[7] = event.getDescription();

            dtm.addRow(obj);
        }

        return dtm;
    }
    public DefaultTableModel getActiveEventTable() {
        events = eventDAO.getActiveEventList();
        String[] listColumn = {"ID", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Mô tả",};
        int columnCount = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel(listColumn, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CharityEvent event : events) {
            Object[] obj = new Object[columnCount];
            obj[0] = event.getId();
            obj[1] = event.getName();
            obj[2] = event.getCategory();
            obj[3] = moneyFormat.format(event.getTargetAmount());
            obj[4] = moneyFormat.format(event.getCurrentAmount());
            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
            obj[6] = dateFormat.format(event.getDateEnd());
            obj[7] = event.getDescription();

            dtm.addRow(obj);
        }

        return dtm;
    }
    public DefaultTableModel getExpiredEventTable() {
        events = eventDAO.getExpiredEventList();
        String[] listColumn = {"ID", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Mô tả",};
        int columnCount = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel(listColumn, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CharityEvent event : events) {
            Object[] obj = new Object[columnCount];
            obj[0] = event.getId();
            obj[1] = event.getName();
            obj[2] = event.getCategory();
            obj[3] = moneyFormat.format(event.getTargetAmount());
            obj[4] = moneyFormat.format(event.getCurrentAmount());
            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
            obj[6] = dateFormat.format(event.getDateEnd());
            obj[7] = event.getDescription();

            dtm.addRow(obj);
        }

        return dtm;
    }
}
