package charity.component;

import charity.service.OrganizationService;
import charity.component.IFormatData;
import static charity.component.IFormatData.dateFormat;
import static charity.component.IFormatData.dateTimeFormat;
import static charity.component.IFormatData.moneyFormat;
import charity.model.*;
import charity.service.*;

import charity.service.UserService;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.table.DefaultTableModel;

public class ClassTableModel implements IFormatData {

    // service    
    private CharityEventService eventService = new CharityEventService();
    private OrganizationService organizationService = new OrganizationService();
    private CategoryService categoryService = new CategoryService();
    private UserService userService = new UserService();

    // Cache
    private final Map<Integer, String> organizationCache = new ConcurrentHashMap<>();
    private final Map<Integer, String> categoryCache = new ConcurrentHashMap<>();
    private final Map<Long, String> moneyFormatCache = new ConcurrentHashMap<>();
//    -----

    private List<CharityEvent> events = null;
    private String[] listEventColumn = {"ID", "Hình ảnh", "Tên sự kiện", "Loại", "Mục tiêu", "Số tiền hiện tại", "Tiến độ", "Ngày kết thúc"};
    private String[] listDonationColumn = {"ID", "Người quyên góp", "Sự kiện", "Số tiền", "Ngày quyên góp", "Nội dung"};
    private String[] listOrganizationColumn = {"ID", "Tên tổ chức", "Email", "Hotline", "Địa chỉ", "Số sự kiện"};
    private String[] listAccountColumn = {"ID", "Tên đăng nhập", "Email", "Mật khẩu", "Vai trò"};

    private String getOrganizationName(int id) {
        return organizationCache.computeIfAbsent(id, organizationService::getNameById);
    }

    private String getCategoryName(int id) {
        return categoryCache.computeIfAbsent(id, categoryService::getCategoryNameById);
    }

    private String formatMoney(long amount) {
        return moneyFormatCache.computeIfAbsent(amount, k -> moneyFormat.format(k));
    }

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
            obj[1] = ImageIconCustom.getSmoothIcon(event.getImageUrl(), 100, 80);
            obj[2] = event.getName();
            obj[3] = MapHelper.getCategoryName(event.getCategoryId());
            obj[4] = formatMoney(event.getTargetAmount());
            obj[5] = formatMoney(event.getCurrentAmount());
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
        //tạo map de luu ten
        Map<Integer, String> userMap = new HashMap<>();
        Map<Integer, String> eventMap = new HashMap<>();

        for (User u: userService.getAllUser()){
            userMap.put(u.getId(), u.getName());
        }
        
        for (CharityEvent e : eventService.getEventList()){
            eventMap.put(e.getId(), e.getName());
        }
        
        dtm.setColumnIdentifiers(listDonationColumn);
        Object[] obj;
        for (Donation donation : listItem) {
            obj = new Object[columnCount + 1];
            obj[0] = donation.getId();
            obj[1] = userMap.getOrDefault(donation.getUserId(), "Không rõ");
            obj[2] = eventMap.getOrDefault(donation.getEventId(), "Không rõ");
            obj[3] = formatMoney(donation.getAmount());
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
            obj = new Object[columnCount + 1];
            obj[0] = a.getId();
            obj[1] = a.getUsername();
            obj[2] = a.getEmail();
            obj[3] = a.getPassword();
            obj[4] = a.getRole();
            dtm.addRow(obj);
        }
        return dtm;
    }
}
