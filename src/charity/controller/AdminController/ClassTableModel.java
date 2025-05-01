package charity.controller.AdminController;

import charity.component.IFormatData;
import static charity.component.IFormatData.dateFormat;
import static charity.component.IFormatData.dateTimeFormat;
import static charity.component.IFormatData.moneyFormat;
import charity.model.Account;
import charity.model.CharityEvent;
import charity.model.Donation;
import charity.model.Organization;
import charity.service.CharityEventService;
import charity.service.OrganizationService;
import charity.service.UserService;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ClassTableModel implements IFormatData {

    // service    
    private CharityEventService eventService = new CharityEventService();
    private final OrganizationService organizationService = new OrganizationService();
    private UserService userService = new UserService();
//    -----

    private List<CharityEvent> events = null;
    private String[] listEventColumn = {"ID", "Tên tổ chức", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc"};
    private String[] listDonationColumn = {"ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung"};
    private String[] listOrganizationColumn = {"ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ", "Số sự kiện"};
    private String[] listAccountColumn = {"ID", "Tên đăng nhập", "Email", "Mật khẩu", "Vai trò"};

//    public DefaultTableModel getEventTable() {
//        events = eventService.getEventList();
//        int columnCount = listEventColumn.length;
//        DefaultTableModel dtm = new DefaultTableModel(listEventColumn, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        for (CharityEvent event : events) {
//            if (event==null)
//            System.out.println(event);
//            else System.out.println("");
//            Object[] obj = new Object[columnCount];
//            obj[0] = event.getId();
//            obj[1] = event.getName();
//            obj[2] = event.getCategory();
//            obj[3] = moneyFormat.format(event.getTargetAmount());
//            obj[4] = moneyFormat.format(event.getCurrentAmount());
//            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
//            obj[6] = dateFormat.format(event.getDateEnd());
//            obj[7] = event.getDescription();
//
//            dtm.addRow(obj);
//        }
//
//        return dtm;
//    }
//    public DefaultTableModel getActiveEventTable(){
//        events = eventService.getActiveEventList();
//        String[] listColumn = {"ID", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Mô tả",};
//        int columnCount = listColumn.length;
//        DefaultTableModel dtm = new DefaultTableModel(listColumn, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        for (CharityEvent event : events) {
//            Object[] obj = new Object[columnCount];
//            obj[0] = event.getId();
//            obj[1] = event.getName();
//            obj[2] = event.getCategory();
//            obj[3] = moneyFormat.format(event.getTargetAmount());
//            obj[4] = moneyFormat.format(event.getCurrentAmount());
//            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
//            obj[6] = dateFormat.format(event.getDateEnd());
//            obj[7] = event.getDescription();
//
//            dtm.addRow(obj);
//        }
//
//        return dtm;
//    }
//
//    public DefaultTableModel getExpiredEventTable() {
//        events = eventService.getExpiredEventList();
//        String[] listColumn = {"ID", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc", "Mô tả",};
//        int columnCount = listColumn.length;
//        DefaultTableModel dtm = new DefaultTableModel(listColumn, 0) {
//            @Override
//            public boolean isCellEditable(int row, int column) {
//                return false;
//            }
//        };
//
//        for (CharityEvent event : events) {
//            Object[] obj = new Object[columnCount];
//            obj[0] = event.getId();
//            obj[1] = event.getName();
//            obj[2] = event.getCategory();
//            obj[3] = moneyFormat.format(event.getTargetAmount());
//            obj[4] = moneyFormat.format(event.getCurrentAmount());
//            obj[5] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
//            obj[6] = dateFormat.format(event.getDateEnd());
//            obj[7] = event.getDescription();
//
//            dtm.addRow(obj);
//        }
//
//        return dtm;
//    }
    public DefaultTableModel getEventTable(List<CharityEvent> listItem) {
        int columnCount = listEventColumn.length;
        DefaultTableModel dtm = new DefaultTableModel(listEventColumn, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (CharityEvent event : listItem) {
            Object[] obj = new Object[columnCount];
            obj[0] = event.getId();
            obj[1] = organizationService.getNameById(event.getOrganizationId());
            obj[2] = event.getName();
            obj[3] = event.getCategory();
            obj[4] = moneyFormat.format(event.getTargetAmount());
            obj[5] = moneyFormat.format(event.getCurrentAmount());
            obj[6] = String.format("%.2f%%", (float) event.getCurrentAmount() / event.getTargetAmount() * 100);
            obj[7] = dateFormat.format(event.getDateEnd());

            dtm.addRow(obj);
        }

        return dtm;
    }

    public DefaultTableModel getDonationTable(List<Donation> listItem) {
        int columnCount = listDonationColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listDonationColumn);
        Object[] obj;
        for (Donation donation : listItem) {
            obj = new Object[columnCount + 1];
            obj[0] = donation.getId();
            obj[1] = userService.getUserNameById(donation.getUserId());
            obj[2] = eventService.getEventNameById(donation.getEventId());
            obj[3] = moneyFormat.format(donation.getAmount());
            obj[4] = dateTimeFormat.format(donation.getDonationDate());
            obj[5] = donation.getDescription();
            dtm.addRow(obj);
        }
        return dtm;
    }


    public DefaultTableModel getOrganizationTable(List<Organization> organizations) {
        // Define column names
        String[] columnNames = {"ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ"};
        
        // Create a default table model with specified columns
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        
        // Add data rows
        for (Organization org : organizations) {
            Object[] row = {
                org.getId(),
                org.getName(),
                org.getEmail(),
                org.getHotline(),
                org.getAddress()
            };
            model.addRow(row);
        }
        
        return model;
    }

    public DefaultTableModel getAccountTable(List<Account> accounts) {
        int columnCount = listAccountColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        dtm.setColumnIdentifiers(listAccountColumn);
        Object[] obj;
        for (Account a : accounts) {
            obj = new Object[columnCount +1];
            obj[0]= a.getId();
            obj[1]= a.getUsername();
            obj[2]= a.getEmail();
            obj[3]= a.getPassword();
            obj[4]= a.getRole();
            dtm.addRow(obj);
        }
        return dtm;
    }
}



