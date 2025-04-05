package charity.service;

import charity.model.CharityEvent;
import charity.repository.CharityEventRepository;
import charity.repository.IRepository.ICharityEventRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class CharityEventService{
    private ICharityEventRepository charityEventRepository;
    
    public CharityEventService(){
        this.charityEventRepository= new CharityEventRepository(); 
    }
    public List<CharityEvent> getEventList() {
        return charityEventRepository.getEventList();
    }

    public List<CharityEvent> getActiveEventList() {
        return charityEventRepository.getActiveEventList();
    }

    public List<CharityEvent> getExpiredEventList() {
        return charityEventRepository.getExpiredEventList();
    }

    public CharityEvent getEventById(int eventId) {
        return charityEventRepository.getEventById(eventId);
    }

    public boolean addEvent(CharityEvent event) {
        return charityEventRepository.addEvent(event);
    }

    public boolean updateEvent(CharityEvent event) {
        return charityEventRepository.updateEvent(event);
    }

    public boolean deleteEvent(int eventId) {
        return charityEventRepository.deleteEvent(eventId);
    }

    public String getEventNameById(int eventId) {
        return charityEventRepository.getEventNameById(eventId);
    }
    
}
