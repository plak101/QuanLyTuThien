package charity.controller.UserController;

import charity.component.MapHelper;
import charity.model.CharityEvent;
import charity.service.CharityEventService;
import charity.view.User.DonateJDialog;
import charity.view.User.MainPanel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JFrame;

public class MainPanelController {

    private MainPanel view;
    private CharityEventService eventService;
    private List<CharityEvent> allEvents, filteredEvents;
    private int currentPage = 0;
    private static final int eventPerPage = 3;
    private String currentCategory = "Tất cả";
    private int endPage;

    public MainPanelController(MainPanel view) {
        this.view = view;
        this.eventService = new CharityEventService();
        loadEvent();
    }

    public void loadEvent() {
        allEvents = eventService.getActiveEventList();
        filteredEvents = new ArrayList<>(allEvents);//khoi tao danh sach loc ban dau
        showCurrentPage();
    }

    public void filterByCategory(String categoryName) {
        currentPage = 0;// reset ve trang dau

        if (categoryName.equals("Tất cả")) {
            filteredEvents = new ArrayList<>(allEvents);
        } else {
            filteredEvents = (List<CharityEvent>) allEvents.stream()
                    .filter(event -> categoryName.equals(MapHelper.getCategoryName(event.getCategoryId())))
                    .collect(Collectors.toList());
        }
        showCurrentPage();
    }

    public void showCurrentPage() {
        int start = currentPage * eventPerPage;
        int end = Math.min(start + eventPerPage, filteredEvents.size());

        if (filteredEvents.isEmpty()) {//danh sach trong
            view.showNoEventMessage();
        } else {
            List<CharityEvent> currentEvents = filteredEvents.subList(start, end);
            view.updateCampaignCards(currentEvents);
        }

        view.updateNavigationButton(
                currentPage > 0, //co the quay lai
                (currentPage + 1) * eventPerPage < filteredEvents.size() //co the tien toi
        );
    }

    public void showNextPage() {
        if ((currentPage + 1) * eventPerPage < filteredEvents.size()) {
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
    
    public void showEventDialog(JFrame frame, CharityEvent event, int accountId, int userId){
        DonateJDialog donateJDialog = new DonateJDialog(frame, true, event.getId(), accountId, userId);
        donateJDialog.setVisible(true);
    }
}
