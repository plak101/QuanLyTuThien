package charity.service;

import charity.model.CharityEvent;
import charity.repository.CharityEventRepository;
import charity.repository.ICharityEventRepository;
import java.util.List;

/**
 *
 * @author phaml
 */
public class CharityEventService implements ICharityEvent{
    private ICharityEventRepository charityEventRepository;
    
    
    @Override
    public List<CharityEvent> getEventList() {
        return charityEventRepository.getEventList();
    }

    @Override
    public List<CharityEvent> getActiveEventList() {
        return charityEventRepository.getActiveEventList();
    }

    @Override
    public List<CharityEvent> getExpiredEventList() {
        return charityEventRepository.getExpiredEventList();
    }

    @Override
    public CharityEvent getEventById(int eventId) {
        return charityEventRepository.getEventById(eventId);
    }

    @Override
    public boolean addEvent(CharityEvent event) {
        return charityEventRepository.addEvent(event);
    }

    @Override
    public boolean updateEvent(CharityEvent event) {
        return charityEventRepository.updateEvent(event);
    }

    @Override
    public boolean deleteEvent(int eventId) {
        return charityEventRepository.deleteEvent(eventId);
    }
    
}
