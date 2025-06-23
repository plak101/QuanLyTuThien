package UI.controller;

import charity.model.CharityEvent;
import charity.service.CharityEventService;
import UI.CampaignPanel;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CampaignController {
    private CampaignPanel view;
    private CharityEventService eventService;
    private List<CharityEvent> allEvents; // Danh sách gốc tất cả sự kiện
    private List<CharityEvent> filteredEvents; // Danh sách sau khi lọc
    private int currentPage = 0;
    private static final int EVENTS_PER_PAGE = 3;
    private String currentCategory = "Tất cả"; // Danh mục hiện tại

    public CampaignController(CampaignPanel view) {
        this.view = view;
        this.eventService = new CharityEventService();
        loadEvents();
    }

    public void loadEvents() {
        allEvents = eventService.getEventListCall();
        filteredEvents = new ArrayList<>(allEvents); // Khởi tạo danh sách lọc ban đầu
        showCurrentPage();
    }

    public void filterByCategory(String category) {
        currentPage = 0; // Reset về trang đầu tiên khi lọc
        
        if (category.equals("Tất cả")) {
            filteredEvents = new ArrayList<>(allEvents);
        } else {
            // Chuyển đổi tên danh mục thành categoryId
            int categoryId = getCategoryId(category);
            
            // Lọc các sự kiện theo categoryId
            filteredEvents = allEvents.stream()
                .filter(event -> event.getCategoryId() == categoryId)
                .collect(Collectors.toList());
        }
        
        showCurrentPage();
    }

    private int getCategoryId(String categoryName) {
        // Ánh xạ tên danh mục sang ID
        switch (categoryName) {
            case "Trẻ em": return 1;
            case "Cộng đồng": return 2;
            case "Giáo dục": return 3;
            case "Hoàn cảnh khó khăn": return 4;
            case "Môi trường": return 5;
            case "Người già neo đơn": return 6;
            case "Thiên tai": return 7;
            case "Y tế": return 8;
            default: return 0;
        }
    }

    public void showNextPage() {
        if ((currentPage + 1) * EVENTS_PER_PAGE < filteredEvents.size()) {
            currentPage++;
            showCurrentPage();
        }
    }

    public void showPreviousPage() {
        if (currentPage > 0) {
            currentPage--;
            showCurrentPage();
        }
    }

    public void showCurrentPage() {
        int start = currentPage * EVENTS_PER_PAGE;
        int end = Math.min(start + EVENTS_PER_PAGE, filteredEvents.size());
        
        if (filteredEvents.isEmpty()) {
            view.showNoEventsMessage(); // Thêm phương thức này vào CampaignPanel
        } else {
            List<CharityEvent> currentEvents = filteredEvents.subList(start, end);
            view.updateCampaignCards(currentEvents);
        }
        
        // Cập nhật trạng thái nút điều hướng
        view.updateNavigationButtons(
            currentPage > 0,  // Có thể quay lại
            (currentPage + 1) * EVENTS_PER_PAGE < filteredEvents.size()  // Có thể tiến tới
        );
    }
}