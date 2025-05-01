package charity.controller.UserController;

import charity.service.OrganizationService;
import charity.component.IFormatData;
import static charity.component.IFormatData.dateFormat;
import static charity.component.IFormatData.dateTimeFormat;
import static charity.component.IFormatData.moneyFormat;
import charity.model.*;
import charity.service.*;

import charity.service.UserService;

import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ClassTableModel implements IFormatData {

    // service    
    private CharityEventService eventService = new CharityEventService();
    private OrganizationService organizationService = new OrganizationService();
    private UserService userService = new UserService();
//    -----

    private List<CharityEvent> events = null;
    private String[] listEventColumn = {"ID","Tên tổ chức", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc"};
    private String[] listDonationColumn = {"ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung"};
    private String[] listOrganizationColumn = {"ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ", "Số sự kiện"};
    

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
        int columnCount = listDonationColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listOrganizationColumn);
        Object[] obj;
        for (Organization organization : organizations) {
            obj = new Object[columnCount + 1];
            obj[0] = organization.getId();
            obj[1] = organization.getName();
            obj[2] = organization.getEmail();
            obj[3] = organization.getHotline();
            obj[4] = organization.getAddress();
            obj[5] = organizationService.getTotalEvent(organization.getId());
            dtm.addRow(obj);
        }
        return dtm;
    }
    
    
}
