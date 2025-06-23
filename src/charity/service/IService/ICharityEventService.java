/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package charity.service.IService;

import charity.model.CharityEvent;
import java.util.List;

/**
 *
 * @author phaml
 */
public interface ICharityEventService {
    public List<CharityEvent> getEventListCall();
    public List<CharityEvent> getEventList();
    public List<CharityEvent> getEventListDistribution();
    public List<CharityEvent> getActiveEventList();
    public List<CharityEvent> getExpiredEventList();
    public CharityEvent getEventById(int eventId) ;
    public String getEventNameById(int eventId) ;
    public boolean addEvent(CharityEvent event);
    public boolean updateEvent(CharityEvent event);
    public boolean deleteEvent(int eventId);
    public int getEventCount();
}
